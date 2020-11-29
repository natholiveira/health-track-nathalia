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

import br.com.fiap.dao.ImcDAO;
import br.com.fiap.dao.PesoDAO;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.entities.AtividadeFisica;
import br.com.fiap.entities.IMC;
import br.com.fiap.entities.Usuario;
import br.com.fiap.exceptions.FalhaConexaoException;
import br.com.fiap.type.TipoAtividade;
import br.com.fiap.type.TipoDistancia;
import br.com.fiap.utils.FormatadorData;
import br.com.fiap.entities.Peso;

//Consultar Readme
//Acesse https://health-track-nathalia.herokuapp.com/login para testar

@WebServlet({ "/peso" })
public class PesoController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		PesoDAO pesoDAO = new PesoDAO();
		
		if (usuario == null) {
			response.sendRedirect("http://localhost:9091/health-track/login");
		} else {
		
			List<Peso> historicoPeso;
			try {
				historicoPeso = pesoDAO.getAllByUserId(usuario.getId());
				request.setAttribute("historicoPeso", historicoPeso);
			} catch (FalhaConexaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			RequestDispatcher rd = request.getRequestDispatcher("peso.jsp");  
			rd.forward(request, response); 	
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		Double inputPeso = 0.0;
		if(request.getParameter("inputPeso") != null) {
			inputPeso = Double.valueOf(request.getParameter("inputPeso"));
		}

		String data = "";
		if(request.getParameter("data") != null) {
			data = request.getParameter("data");
		}

		if ((inputPeso != 0.0) && (data != "")) {
			Peso peso = new Peso(inputPeso, FormatadorData.parseDate(data), usuario);
			
			PesoDAO pesoDAO = new PesoDAO();
			ImcDAO imcDAO = new ImcDAO();
			
			IMC imc = new IMC();
			try {
				pesoDAO.insert(peso);
				imcDAO.insert(imc.createIMC(peso.getPeso(), usuario));
				
				request.getSession().setAttribute("peso", inputPeso.toString());
			} catch (FalhaConexaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		response.sendRedirect("http://localhost:9091/health-track/peso");
	}
}
