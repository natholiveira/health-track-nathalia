package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.entities.AtividadeFisica;
import br.com.fiap.exceptions.FalhaConexaoException;
import br.com.fiap.type.TipoAtividade;
import br.com.fiap.type.TipoDistancia;
import br.com.fiap.utils.FormatadorData;

public class AtividadeFisicaDAO {
	
	private final String GET_ALL_BY_USER_ID = "SELECT "
		+ "a.tipo_atividade tipo_atividade, a.tipo_distancia tipo_distancia, a.data_hora_inicio data_inicio, a.data_hora_termino data_termino, a.distancia_percorrida distancia "
		+ "FROM atividade_fisica a WHERE a.usuario_id = ?";
	private final String INSERT = "INSERT INTO atividade_fisica(tipo_atividade, tipo_distancia, data_hora_inicio, data_hora_termino, distancia_percorrida, usuario_id) values (?,?,?,?,?,?)";
	
	private Conexao conexao = new Conexao();

	public List<AtividadeFisica> getAllByUserId(int usuarioId) throws FalhaConexaoException {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
	    Connection conn;
	    PreparedStatement ps;
	    ResultSet rs;
	
	    List<AtividadeFisica> listaAtividade = new ArrayList<>();
	
	    try {
	    	Conexao conexao = new Conexao();
	        conn = conexao.getConnection();
	        ps = conn.prepareStatement(GET_ALL_BY_USER_ID);
	        
	        ps.setInt(1, usuarioId);
	        
	        rs = ps.executeQuery();
	
	        AtividadeFisica atividadeFisica;
	
	        while (rs.next()) {
	
	        	atividadeFisica = new AtividadeFisica();
	            atividadeFisica.setDataHoraInicio(rs.getTimestamp("data_inicio"));
	            atividadeFisica.setDataHoraTermino(rs.getTimestamp("data_termino"));
	            atividadeFisica.setDistanciaPercorrida(rs.getDouble("distancia"));
	            atividadeFisica.setTipoAtividade(TipoAtividade.valueOf(rs.getString("tipo_atividade")));
	            atividadeFisica.setTipoDistancia(TipoDistancia.valueOf(rs.getString("tipo_distancia")));
	            atividadeFisica.setUsuario(usuarioDAO.getById(usuarioId));
	
	            listaAtividade.add(atividadeFisica);
	        }
	
	    } catch (SQLException e) {
	    	e.printStackTrace();
	        throw new FalhaConexaoException("Não foi possível conectar no database");
	    } finally {
	        rs = null;
	        ps = null;
	        conn = null;
	    }
	
	    return listaAtividade;
	}


	public void insert(AtividadeFisica atividadeFisica) throws FalhaConexaoException {
        Connection conn;
        PreparedStatement ps;
        ResultSet rs;

        try {
        	Conexao conexao = new Conexao();
            conn = conexao.getConnection();
            ps = conn.prepareStatement(INSERT);
            
            java.util.Date dataInicio = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(atividadeFisica.getDataHoraInicio());
            java.util.Date dataTermino = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(atividadeFisica.getDataHoraTermino());

            ps.setString(1, atividadeFisica.getTipoAtividade().toString());
            ps.setString(2, atividadeFisica.getTipoDistancia().toString());
            ps.setTimestamp(3, new Timestamp(dataInicio.getTime()));
            ps.setTimestamp(4, new Timestamp(dataTermino.getTime()));
            ps.setDouble(5, atividadeFisica.getDistanciaPercorrida());
            ps.setInt(6, atividadeFisica.getUsuario().getId());

            ps.executeUpdate();

        } catch (SQLException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		} catch (ParseException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        rs = null;
	        ps = null;
	        conn = null;
	    }
	}
}
