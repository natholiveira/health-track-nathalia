package br.com.fiap.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.entities.Usuario;

//Consultar Readme
//Acesse https://health-track-nathalia.herokuapp.com/login para testar

@WebServlet({ "/logout" })
public class LogoutController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	HttpSession session = request.getSession();

		session.setAttribute("usuario", null);
		session.setAttribute("historicoAtividade", null);
		session.setAttribute("historicoPeso", null);
		session.setAttribute("meta", null);
		session.setAttribute("historicoAlimentacao", null);
		session.setAttribute("historicoFrequencia", null);
	
		response.sendRedirect("http://localhost:9091/health-track/login"); 
	}
}