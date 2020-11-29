package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.entities.Peso;
import br.com.fiap.entities.Usuario;
import br.com.fiap.exceptions.FalhaConexaoException;
import br.com.fiap.utils.FormatadorData;

public class UsuarioDAO {
	 	
	private final String GET_ALL = "SELECT u.id id, u.nome nome, u.email email, u.senha senha FROM usuario u";
    private final String INSERT = "INSERT INTO usuario (nome, email, senha) values (?,?,?)";
    private final String GET_BY_ID = "SELECT u.id id, u.nome nome, u.email email, u.senha senha, u.altura altura FROM usuario u where u.id = ?";
    private final String GET_BY_EMAIL_AND_PASSWORD = "SELECT id, nome, email, senha, altura FROM usuario where email = ? AND senha = ?";
    private final String GET_BY_EMAIL = "SELECT u.id id FROM usuario u WHERE u.email = ?";
    private final String UPDATE = "UPDATE usuario SET nome = ?, email = ?, senha = ?, altura = ? WHERE id = ?";
    
	public Usuario getById(int id) throws FalhaConexaoException {
	    Connection conn;
	    PreparedStatement ps;
	    ResultSet rs;
	    
	    Usuario usuario = null;
	
	    try {
	    	Conexao conexao = new Conexao();
	        conn = conexao.getConnection();
	        ps = conn.prepareStatement(GET_BY_ID);
	        
	        ps.setInt(1, id);
	        
	        rs = ps.executeQuery();
	        
	        while (rs.next()) {
		    	usuario = new Usuario();
		        usuario.setAltura(rs.getDouble("altura"));
		        usuario.setEmail(rs.getString("email"));
		        usuario.setNome(rs.getString("nome"));
		        usuario.setSenha(rs.getString("senha"));
		        usuario.setId(rs.getInt("id"));
	        }
	
	    } catch (SQLException e) {
	        throw new FalhaConexaoException("Não foi possível conectar no database");
	    } finally {
	        rs = null;
	        ps = null;
	        conn = null;
	    }
	
	    return usuario;
	}
	
	public Usuario getByEmailAndPassword(String email, String senha) throws FalhaConexaoException {
	    Connection conn;
	    PreparedStatement ps;
	    ResultSet rs;
	    
	    Usuario usuario = null;
	
	    try {
	    	Conexao conexao = new Conexao();
	        conn = conexao.getConnection();
	        ps = conn.prepareStatement(GET_BY_EMAIL_AND_PASSWORD);
	        
	        ps.setString(1, email);
	        ps.setString(2, senha);
	        
	        rs = ps.executeQuery();
	
	        while (rs.next()) {
		    	usuario = new Usuario();
		        usuario.setAltura(rs.getDouble("altura"));
		        usuario.setEmail(rs.getString("email"));
		        usuario.setNome(rs.getString("nome"));
		        usuario.setSenha(rs.getString("senha"));
		        usuario.setId(rs.getInt("id"));
	        }
	
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        rs = null;
	        ps = null;
	        conn = null;
	    }
	
	    return usuario;
	}
	
	public Usuario getByEmail(String email) throws FalhaConexaoException {
	    Connection conn;
	    PreparedStatement ps;
	    ResultSet rs;
	    
	    Usuario usuario = null;
	
	    try {
	    	Conexao conexao = new Conexao();
	        conn = conexao.getConnection();
	        ps = conn.prepareStatement(GET_BY_EMAIL);
	        
	        ps.setString(1, email);
	        
	        rs = ps.executeQuery();
	
	        while (rs.next()) {
		    	usuario = new Usuario();
		        usuario.setId(rs.getInt("id"));
	        }
	
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        rs = null;
	        ps = null;
	        conn = null;
	    }
	
	    return usuario;
	}
    
	public void insert(Usuario usuario) throws FalhaConexaoException {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			Conexao conexao = new Conexao();
		    conn = conexao.getConnection();
		    ps = conn.prepareStatement(INSERT);
		
		    ps.setString(1, usuario.getNome());
		    ps.setString(2, usuario.getEmail());
		    ps.setString(3, usuario.getSenha());
		
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
	
	public void update(Usuario usuario) throws FalhaConexaoException {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			Conexao conexao = new Conexao();
		    conn = conexao.getConnection();
		    ps = conn.prepareStatement(UPDATE);
		
		    ps.setString(1, usuario.getNome());
		    ps.setString(2, usuario.getEmail());
		    ps.setString(3, usuario.getSenha());
		    ps.setDouble(4, usuario.getAltura());
		    ps.setInt(5, usuario.getId());
		
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
