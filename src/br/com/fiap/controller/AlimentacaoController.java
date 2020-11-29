package br.com.fiap.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.dao.AlimentoConsumidoDAO;
import br.com.fiap.entities.AlimentoConsumido;
import br.com.fiap.entities.AtividadeFisica;
import br.com.fiap.entities.Usuario;
import br.com.fiap.exceptions.FalhaConexaoException;
import br.com.fiap.type.TipoAlimento;
import br.com.fiap.type.TipoAtividade;
import br.com.fiap.type.TipoDistancia;
import br.com.fiap.utils.FormatadorData;

//Consultar Readme
//Acesse https://health-track-nathalia.herokuapp.com/login para testar

@WebServlet({ "/alimentacao" })
public class AlimentacaoController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		AlimentoConsumidoDAO alimentoDAO = new AlimentoConsumidoDAO();

		HttpSession session = request.getSession();

		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			response.sendRedirect("http://localhost:9091/health-track/login");
		} else {

			List<AlimentoConsumido> historicoAlimentacao;
			try {
				historicoAlimentacao = alimentoDAO.getAllByUserId(usuario.getId());
				request.setAttribute("historicoAlimentacao", historicoAlimentacao);
			} catch (FalhaConexaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			RequestDispatcher rd = request.getRequestDispatcher("alimentacao.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		Usuario usuario = (Usuario) session.getAttribute("usuario");

		String tipoAlimento = "";
		if (request.getParameter("tipoAlimento") != null) {
			tipoAlimento = request.getParameter("tipoAlimento");
		}

		String alimento = "";
		if (request.getParameter("alimento") != null) {
			alimento = request.getParameter("alimento");
		}

		String data = "";
		if (request.getParameter("data") != null) {
			data = request.getParameter("data");
		}

		Double calorias = 0.0;
		if (request.getParameter("calorias") != null) {
			calorias = Double.valueOf(request.getParameter("calorias"));
		}

		if ((tipoAlimento != "") && (data != "") && (alimento != "") && (calorias != 0.0)) {

			AlimentoConsumido alimentoConsumido = new AlimentoConsumido(TipoAlimento.valueOf(tipoAlimento), alimento,
					FormatadorData.parseDate(data), calorias, usuario);
			
			AlimentoConsumidoDAO alimentoDAO = new AlimentoConsumidoDAO();
			
			try {
				alimentoDAO.insert(alimentoConsumido);
			} catch (FalhaConexaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
		}

		response.sendRedirect("http://localhost:9091/health-track/alimentacao");

	}
}