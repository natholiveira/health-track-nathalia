package br.com.fiap.controller;

import java.io.IOException;

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
import br.com.fiap.entities.IMC;
import br.com.fiap.entities.Usuario;
import br.com.fiap.exceptions.FalhaConexaoException;

//Consultar Readme
//Acesse https://health-track-nathalia.herokuapp.com/login para testar

@WebServlet({ "/perfil" })
public class PerfilController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		if (usuario == null) {
			response.sendRedirect("http://localhost:9091/health-track/login");
		} else { 
			request.setAttribute("usuario", usuario);
			
			RequestDispatcher rd = request.getRequestDispatcher("perfil.jsp");  
			rd.forward(request, response); 	
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		String nome = "";
		if(request.getParameter("nome") != null) {
			nome = request.getParameter("nome");
		}
		
		Double altura = 0.0;
		if(request.getParameter("altura") != null) {
			altura = Double.valueOf(request.getParameter("altura"));
		}

		String email = "";
		if(request.getParameter("email") != null) {
			email = request.getParameter("email");
		}

		String senha = "";
		if(request.getParameter("senha") != null) {
			senha = request.getParameter("senha");
		}

		if ((nome != "") && (senha != "") && (email != "") && (altura != 0.0)) {
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			
			PesoDAO pesoDAO = new PesoDAO();
			ImcDAO imcDAO = new ImcDAO();
			
			IMC imc = new IMC();
			
			usuario.setNome(nome);
			usuario.setSenha(senha);
			usuario.setEmail(email);
			usuario.setAltura(altura);
			
			try {
				usuarioDAO.update(usuario);
				Double pesoAtual = pesoDAO.getLastPeso(usuario.getId());
				imcDAO.insert(imc.createIMC(pesoAtual, usuario));
				session.setAttribute("usuario", usuario);
			} catch (FalhaConexaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} 
		
		response.sendRedirect("http://localhost:9091/health-track/perfil");
	}
}