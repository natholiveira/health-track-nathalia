package br.com.fiap.controller;


import java.io.IOException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.dao.AlimentoConsumidoDAO;
import br.com.fiap.dao.AtividadeFisicaDAO;
import br.com.fiap.dao.FrequenciaCardiacaDAO;
import br.com.fiap.dao.ImcDAO;
import br.com.fiap.dao.PesoDAO;
import br.com.fiap.entities.AtividadeFisica;
import br.com.fiap.entities.IMC;
import br.com.fiap.entities.Meta;
import br.com.fiap.entities.Peso;
import br.com.fiap.entities.Usuario;
import br.com.fiap.exceptions.FalhaConexaoException;
import br.com.fiap.type.ClassificacaoIMC;
import br.com.fiap.type.TipoAtividade;
import br.com.fiap.utils.FormatadorData;

//Consultar Readme
//Acesse https://health-track-nathalia.herokuapp.com/login para testar

@WebServlet({ "/home" })
public class HomeController extends HttpServlet {
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		PesoDAO pesoDAO = new PesoDAO();
		FrequenciaCardiacaDAO frequenciaDAO = new FrequenciaCardiacaDAO();
		AlimentoConsumidoDAO alimentoDAO = new AlimentoConsumidoDAO();
		AtividadeFisicaDAO atividadeDAO = new AtividadeFisicaDAO();
		ImcDAO imcDAO = new ImcDAO();
		
		if (usuario == null) {
			response.sendRedirect("http://localhost:9091/health-track/login");
		} else {
		
			Double pesoAtual = 0.0;
			String frequenciaCardiaca = "Sem registros";
			Double calorias = 0.0;
			IMC imc = null;
			try {
				pesoAtual = pesoDAO.getLastPeso(usuario.getId());
				frequenciaCardiaca = frequenciaDAO.getLastFrequencia(usuario.getId());
				calorias = alimentoDAO.getLastCaloria(usuario.getId());
				imc = imcDAO.getByUserId(usuario.getId());
				request.setAttribute("historicoAtividade", atividadeDAO.getAllByUserId(usuario.getId()));
				
			} catch (FalhaConexaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			session.setAttribute("peso", pesoAtual.toString());
			request.setAttribute("peso", String.format("%.2f", pesoAtual));
			request.setAttribute("frequenciaCardiaca", frequenciaCardiaca);
			request.setAttribute("calorias", String.format("%.2f", calorias));
			
			if (usuario.getMeta() == null) {
				request.setAttribute("pesoMeta","0.0");
				request.setAttribute("faltaMeta", "0.0");
				request.setAttribute("meta", "0.0");
				request.setAttribute("semMeta", "1.0");
			} else {
				Meta meta = (Meta) session.getAttribute("meta");
				Double faltaMeta = meta.getTotalDiferencaPeso() - (pesoAtual - meta.getPeso());
				Double metaConcluida = meta.getTotalDiferencaPeso() - faltaMeta;
				request.setAttribute("pesoMeta", meta.getPeso().toString());
				request.setAttribute("faltaMeta", faltaMeta.toString());
				request.setAttribute("meta", metaConcluida.toString());
				request.setAttribute("semMeta", null);
			}
			
			if (imc == null) {
				request.setAttribute("imcTipo", ClassificacaoIMC.DESCONHECIDO.getNome());
				request.setAttribute("imc", String.format("%.2f", 0.0));
			} else {
				request.setAttribute("imcTipo", imc.getClassificacao().getNome());
				request.setAttribute("imc", String.format("%.2f", imc.getImcUsuario()));
			}
			
			String nomeSplit[] = usuario.getNome().split(" ");
			request.setAttribute("nome", nomeSplit[0]);
			
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");  
			rd.forward(request, response); 	
		}
	}
}
