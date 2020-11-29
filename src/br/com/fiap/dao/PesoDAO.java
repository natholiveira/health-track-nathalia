package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.entities.Peso;
import br.com.fiap.exceptions.FalhaConexaoException;
import br.com.fiap.utils.FormatadorData;

public class PesoDAO {

    private final String GET_ALL_BY_USER_ID = "SELECT p.peso_usuario peso, p.data data_peso FROM peso p WHERE p.usuario_id = ?";
    private final String INSERT = "INSERT INTO peso (peso_usuario, data, usuario_id) values (?,?,?)";
    private final String GET_LAST_PESO = "SELECT peso_usuario, max(data) FROM peso WHERE usuario_id = ? GROUP BY data, peso_usuario";
    
    public List<Peso> getAllByUserId(int usuarioId) throws FalhaConexaoException {
    	
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
    	
        Connection conn;
        PreparedStatement ps;
        ResultSet rs;

        List<Peso> listaPeso = new ArrayList<>();

        try {
        	Conexao conexao = new Conexao();
            conn = conexao.getConnection();
            ps = conn.prepareStatement(GET_ALL_BY_USER_ID);
            
            ps.setInt(1, usuarioId);
            
            rs = ps.executeQuery();

            Peso peso;

            while (rs.next()) {

                peso = new Peso();
                peso.setPeso(rs.getDouble("peso"));
                peso.setData(rs.getDate("data_peso"));
                peso.setUsuario(usuarioDAO.getById(usuarioId));

                listaPeso.add(peso);
            }

        } catch (SQLException e) {
        	e.printStackTrace();
            throw new FalhaConexaoException("Não foi possível conectar no database");
        } finally {
            rs = null;
            ps = null;
            conn = null;
        }

        return listaPeso;
    }
    
    public Double getLastPeso(int usuarioId) throws FalhaConexaoException {
    	
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
    	
        Connection conn;
        PreparedStatement ps;
        ResultSet rs;
        
        List<Double> listaPeso = new ArrayList<>();
        Double peso = 0.0;
        try {
        	Conexao conexao = new Conexao();
            conn = conexao.getConnection();
            ps = conn.prepareStatement(GET_LAST_PESO);
            
            ps.setInt(1, usuarioId);
            
            rs = ps.executeQuery();

            while (rs.next()) {
                peso = rs.getDouble("peso_usuario");
                
                listaPeso.add(peso);
            }

        } catch (SQLException e) {
        	e.printStackTrace();
            throw new FalhaConexaoException("Não foi possível conectar no database");
        } finally {
            rs = null;
            ps = null;
            conn = null;
        }
        
        if (listaPeso.isEmpty()) {
        	return peso;
        } else {
        	return listaPeso.get(listaPeso.size()-1);
        }
    }

    
	public void insert(Peso peso) throws FalhaConexaoException {
	    Connection conn;
	    PreparedStatement ps;
	    ResultSet rs;
	
	    try {
	    	Conexao conexao = new Conexao();
	        conn = conexao.getConnection();
	        ps = conn.prepareStatement(INSERT);
	        
	        String data = peso.getData();
	
	        ps.setDouble(1, peso.getPeso());
	        ps.setDate(2, FormatadorData.toDateSql(peso.getData()));
	        ps.setInt(3, peso.getUsuario().getId());
	
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
}
