package br.com.fiap.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.dao.Conexao;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.entities.Usuario;
import br.com.fiap.exceptions.FalhaConexaoException;

//Consultar Readme
// Acesse https://health-track-nathalia.herokuapp.com/login para testar

@WebServlet({ "/login" })
public class LoginController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Conexao conexao = new Conexao();
		try {
			conexao.getConnection();
		} catch (FalhaConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");  
		rd.forward(request, response); 	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		String email = "";
		if(request.getParameter("email") != null) {
			email = request.getParameter("email");
		}

		String senha = "";
		if(request.getParameter("senha") != null) {
			senha = request.getParameter("senha");
		}

		if ((email != "") && (senha != "")) {
			Usuario usuario = null;
			try {
				usuario = usuarioDAO.getByEmailAndPassword(email, senha);
			} catch (FalhaConexaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (usuario == null) {
				response.sendRedirect("http://localhost:9091/health-track/login");
			} else {
				session.setAttribute("usuario", usuario);
				response.sendRedirect("http://localhost:9091/health-track/home");
			}
		} 
	}
}
