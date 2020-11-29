package br.com.fiap.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.entities.AtividadeFisica;
import br.com.fiap.entities.Meta;
import br.com.fiap.entities.Usuario;
import br.com.fiap.type.TipoAtividade;
import br.com.fiap.type.TipoDistancia;
import br.com.fiap.utils.FormatadorData;
import br.com.fiap.entities.Peso;

//Consultar Readme

@WebServlet({ "/meta" })
public class MetaController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		if (usuario == null) {
			response.sendRedirect("http://localhost:9091/health-track/login");
		} else {
		
			Meta meta = (Meta) session.getAttribute("meta");
			
			Double pesoAtual = Double.valueOf((String) session.getAttribute("peso")); ; 
			
			if (usuario.getMeta() == null) {
				request.setAttribute("pesoMeta","0.0");
				request.setAttribute("faltaMeta", "0.0");
				request.setAttribute("meta", "0.0");
				request.setAttribute("semMeta", "1.0");
			} else {
				Double faltaMeta = meta.getTotalDiferencaPeso() - (pesoAtual - meta.getPeso());
				Double metaConcluida = meta.getTotalDiferencaPeso() - faltaMeta;
				request.setAttribute("pesoMeta", meta.getPeso().toString());
				request.setAttribute("faltaMeta", faltaMeta.toString());
				request.setAttribute("meta", metaConcluida.toString());
				request.setAttribute("semMeta", null);
			}
			
			request.setAttribute("peso", pesoAtual.toString());
			RequestDispatcher rd = request.getRequestDispatcher("meta.jsp");  
			rd.forward(request, response); 	
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		Double pesoAtual = Double.valueOf((String) session.getAttribute("peso")); 
		
		Double peso = 0.0;
		if(request.getParameter("peso") != null) {
			peso = Double.valueOf(request.getParameter("peso"));
		}

		String data = "";
		if(request.getParameter("data") != null) {
			data = request.getParameter("data");
		}

		if ((peso != 0.0) && (data != "") && usuario.getMeta()==null) {
			
			Double totalKgASerPerdido = pesoAtual - peso;
			Meta metaUsuario = new Meta(totalKgASerPerdido, peso, FormatadorData.parseDate(data), usuario);

			usuario.setMeta(metaUsuario);
			
			
			request.getSession().setAttribute("usuario", usuario);
			request.getSession().setAttribute("meta", usuario.getMeta());
		}
		

		response.sendRedirect("http://localhost:9091/health-track/meta");
	}
}
