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

import br.com.fiap.dao.FrequenciaCardiacaDAO;
import br.com.fiap.entities.FrequenciaCardiaca;
import br.com.fiap.entities.Peso;
import br.com.fiap.entities.Usuario;
import br.com.fiap.exceptions.FalhaConexaoException;
import br.com.fiap.utils.FormatadorData;

//Consultar Readme
//Acesse https://health-track-nathalia.herokuapp.com/login para testar

@WebServlet({ "/frequencia-cardiaca" })
public class FrequenciaCardiacaController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		FrequenciaCardiacaDAO frequenciaDAO = new FrequenciaCardiacaDAO();
		
		if (usuario == null) {
			response.sendRedirect("http://localhost:9091/health-track/login");
		} else {
		
			List<FrequenciaCardiaca> historicoFrequenciaCardiaca;
			try {
				historicoFrequenciaCardiaca = frequenciaDAO.getAllByUserId(usuario.getId());
				request.setAttribute("historicoFrequencia", historicoFrequenciaCardiaca);
			} catch (FalhaConexaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			RequestDispatcher rd = request.getRequestDispatcher("frequenciaCardiaca.jsp");  
			rd.forward(request, response); 	
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		String frequencia = "";
		if(request.getParameter("frequencia") != null) {
			frequencia = request.getParameter("frequencia");
		}

		String data = "";
		if(request.getParameter("data") != null) {
			data = request.getParameter("data");
		}

		if ((frequencia !="") && (data != "")) {
			
			FrequenciaCardiacaDAO frequenciaDAO = new FrequenciaCardiacaDAO();
			
			FrequenciaCardiaca frequenciaCardiaca = new FrequenciaCardiaca(frequencia, FormatadorData.parseDate(data), usuario);
			
			try {
				frequenciaDAO.insert(frequenciaCardiaca);
			} catch (FalhaConexaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		response.sendRedirect("http://localhost:9091/health-track/frequencia-cardiaca");
	}
}