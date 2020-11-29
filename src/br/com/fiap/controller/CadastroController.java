package br.com.fiap.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.entities.Usuario;
import br.com.fiap.exceptions.FalhaConexaoException;

//Consultar Readme
//Acesse https://health-track-nathalia.herokuapp.com/login para testar

@WebServlet({ "/cadastro" })
public class CadastroController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("cadastro.jsp");  
		rd.forward(request, response); 	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		String nome = "";
		if(request.getParameter("nome") != null) {
			nome = request.getParameter("nome");
		}

		String email = "";
		if(request.getParameter("email") != null) {
			email = request.getParameter("email");
		}

		String senha = "";
		if(request.getParameter("senha") != null) {
			senha = request.getParameter("senha");
		}
		
		String confirmarSenha = "";
		if(request.getParameter("confirmarSenha") != null) {
			confirmarSenha = request.getParameter("confirmarSenha");
		}

		if ((nome != "") && (senha != "") && (email != "") && (senha.equals(confirmarSenha))) {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			
			Usuario u = new Usuario(nome, email, senha);
			
			Usuario usuario = null;
			try {
				usuario = usuarioDAO.getByEmail(email);
			} catch (FalhaConexaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
			if (usuario == null) {
				try {
					usuarioDAO.insert(u);
					
					session.setAttribute("usuario",u);
					response.sendRedirect("http://localhost:9091/health-track/login");
				} catch (FalhaConexaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				response.sendRedirect("http://localhost:9091/health-track/cadastro");
			}		
		} else {
			response.sendRedirect("http://localhost:9091/health-track/cadastro");
		}
	
	}
}
