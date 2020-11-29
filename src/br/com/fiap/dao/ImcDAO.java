package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.entities.IMC;
import br.com.fiap.entities.Peso;
import br.com.fiap.exceptions.FalhaConexaoException;
import br.com.fiap.type.ClassificacaoIMC;
import br.com.fiap.utils.FormatadorData;

public class ImcDAO {
	
	private final String GET_BY_USER_ID = "SELECT imc_usuario, classificacao FROM imc WHERE usuario_id = ?";
    private final String INSERT = "INSERT INTO imc (imc_usuario, classificacao, usuario_id) values (?,?,?)";
    
  public IMC getByUserId(int usuarioId) throws FalhaConexaoException {
    	
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
    	
        Connection conn;
        PreparedStatement ps;
        ResultSet rs;
        
        IMC imc = null;

        try {
        	Conexao conexao = new Conexao();
            conn = conexao.getConnection();
            ps = conn.prepareStatement(GET_BY_USER_ID);
            
            ps.setInt(1, usuarioId);
            
            rs = ps.executeQuery();

            while (rs.next()) {

            	imc = new IMC();
            	imc.setImcUsuario(rs.getDouble("imc_usuario"));
            	imc.setClassificacao(ClassificacaoIMC.valueOf(rs.getString("classificacao")));
            	imc.setUsuario(usuarioDAO.getById(usuarioId));
            	
            }

        } catch (SQLException e) {
        	e.printStackTrace();
            throw new FalhaConexaoException("Não foi possível conectar no database");
        } finally {
            rs = null;
            ps = null;
            conn = null;
        }

        return imc;
    }
	
	public void insert(IMC imc) throws FalhaConexaoException {
		if (imc != null) {
		    Connection conn;
		    PreparedStatement ps;
		    ResultSet rs;
		
		    try {
		    	Conexao conexao = new Conexao();
		        conn = conexao.getConnection();
		        ps = conn.prepareStatement(INSERT);
		        
		  
		
		        ps.setDouble(1, imc.getImcUsuario());
		        ps.setString(2, imc.getClassificacao().toString());
		        ps.setInt(3, imc.getUsuario().getId());
		
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
}
