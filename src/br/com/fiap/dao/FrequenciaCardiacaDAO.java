package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.entities.FrequenciaCardiaca;
import br.com.fiap.exceptions.FalhaConexaoException;
import br.com.fiap.utils.FormatadorData;

public class FrequenciaCardiacaDAO {
	private final String GET_ALL_BY_USER_ID = "SELECT f.frequencia frequencia, f.data data_frequencia FROM frequencia_cardiaca f WHERE f.usuario_id = ?";
    private final String INSERT = "INSERT INTO frequencia_cardiaca (frequencia, data, usuario_id) values (?,?,?)";
    private final String GET_LAST_FREQUENCIA = "SELECT frequencia, max(data) FROM frequencia_cardiaca WHERE usuario_id = ? GROUP BY data, frequencia";
    
    public List<FrequenciaCardiaca> getAllByUserId(int usuarioId) throws FalhaConexaoException {
    	
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
    	
        Connection conn;
        PreparedStatement ps;
        ResultSet rs;

        List<FrequenciaCardiaca> listaFrequencia = new ArrayList<>();

        try {
        	Conexao conexao = new Conexao();
            conn = conexao.getConnection();
            ps = conn.prepareStatement(GET_ALL_BY_USER_ID);
            
            ps.setInt(1, usuarioId);
            
            rs = ps.executeQuery();

            FrequenciaCardiaca frequenciaCardiaca;

            while (rs.next()) {

            	frequenciaCardiaca = new FrequenciaCardiaca();
            	frequenciaCardiaca.setFrequencia(rs.getString("frequencia"));
            	frequenciaCardiaca.setData(rs.getDate("data_frequencia"));
            	frequenciaCardiaca.setUsuario(usuarioDAO.getById(usuarioId));

            	listaFrequencia.add(frequenciaCardiaca);
            }

        } catch (SQLException e) {
        	e.printStackTrace();
            throw new FalhaConexaoException("Não foi possível conectar no database");
        } finally {
            rs = null;
            ps = null;
            conn = null;
        }

        return listaFrequencia;
    }

    
	public void insert(FrequenciaCardiaca frequenciaCardiaca) throws FalhaConexaoException {
	    Connection conn;
	    PreparedStatement ps;
	    ResultSet rs;
	
	    try {
	    	Conexao conexao = new Conexao();
	        conn = conexao.getConnection();
	        ps = conn.prepareStatement(INSERT);
	
	        ps.setString(1, frequenciaCardiaca.getFrequencia());
	        ps.setDate(2,  FormatadorData.toDateSql(frequenciaCardiaca.getData()));
	        ps.setInt(3, frequenciaCardiaca.getUsuario().getId());
	
	        ps.executeUpdate();
	
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        rs = null;
	        ps = null;
	        conn = null;
	    }
	}
	
	public String getLastFrequencia(int usuarioId) throws FalhaConexaoException {
    	
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
    	
        Connection conn;
        PreparedStatement ps;
        ResultSet rs;
        
        List<String> listaFrequencia = new ArrayList<>();
        String frequencia = "Sem registros";
        try {
        	Conexao conexao = new Conexao();
            conn = conexao.getConnection();
            ps = conn.prepareStatement(GET_LAST_FREQUENCIA);
            
            ps.setInt(1, usuarioId);
            
            rs = ps.executeQuery();

            while (rs.next()) {
            	frequencia = rs.getString("frequencia");
                
                listaFrequencia.add(frequencia);
            }

        } catch (SQLException e) {
        	e.printStackTrace();
            throw new FalhaConexaoException("Não foi possível conectar no database");
        } finally {
            rs = null;
            ps = null;
            conn = null;
        }

        if (listaFrequencia.isEmpty()) {
        	return frequencia;
        } else {
        	return listaFrequencia.get(listaFrequencia.size()-1);
        }
    }
}
