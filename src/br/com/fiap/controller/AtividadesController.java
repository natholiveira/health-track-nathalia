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

import br.com.fiap.dao.AtividadeFisicaDAO;
import br.com.fiap.entities.AtividadeFisica;
import br.com.fiap.entities.Usuario;
import br.com.fiap.exceptions.FalhaConexaoException;
import br.com.fiap.type.TipoAtividade;
import br.com.fiap.type.TipoDistancia;
import br.com.fiap.utils.FormatadorData;

//Consultar Readme
//Acesse https://health-track-nathalia.herokuapp.com/login para testar

@WebServlet({ "/atividades" })
public class AtividadesController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		AtividadeFisicaDAO atividadeDAO = new AtividadeFisicaDAO();
		
		if (usuario == null) {
			response.sendRedirect("http://localhost:9091/health-track/login");
		} else {
			
			List<AtividadeFisica> historicoAtividade;
			try {
				historicoAtividade = atividadeDAO.getAllByUserId(usuario.getId());
				request.setAttribute("historicoAtividade", historicoAtividade);
			} catch (FalhaConexaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("atividades.jsp");  
			rd.forward(request, response); 	
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		String atividade = "";
		if(request.getParameter("tipoAtividade") != null) {
			atividade = request.getParameter("tipoAtividade");
		}

		Double distancia = 0.0;
		if(request.getParameter("distancia") != null && request.getParameter("distancia") != "") {
			distancia = Double.valueOf(request.getParameter("distancia"));
		}

		String dataHoraInicio = "";
		if(request.getParameter("dataHoraInicio") != null) {
			dataHoraInicio = request.getParameter("dataHoraInicio");
		}

		String dataHoraTermino = "";
		if(request.getParameter("dataHoraTermino") != null) {
			dataHoraTermino = request.getParameter("dataHoraTermino");
		}

		String tipoDistancia = "";
		if(request.getParameter("tipoDistancia") != null) {
			tipoDistancia = request.getParameter("tipoDistancia");
		}

		String tipoAtividade = "";
		if(request.getParameter("tipoAtividade") != null) {
			tipoAtividade = request.getParameter("tipoAtividade");
		}

		if ((atividade != "") && (dataHoraInicio != "") && (dataHoraTermino != "")) {
			
			AtividadeFisicaDAO atividadeDAO = new AtividadeFisicaDAO();
			
			AtividadeFisica atividadeFisica = new AtividadeFisica(TipoAtividade.valueOf(tipoAtividade), FormatadorData.parseDateTime(dataHoraInicio), FormatadorData.parseDateTime(dataHoraTermino), distancia, TipoDistancia.valueOf(tipoDistancia), usuario);

			try {
				atividadeDAO.insert(atividadeFisica);
			} catch (FalhaConexaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		response.sendRedirect("http://localhost:9091/health-track/atividades");

	}
}
