package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.entities.AlimentoConsumido;
import br.com.fiap.exceptions.FalhaConexaoException;
import br.com.fiap.type.TipoAlimento;
import br.com.fiap.type.TipoAtividade;
import br.com.fiap.type.TipoDistancia;
import br.com.fiap.utils.FormatadorData;

public class AlimentoConsumidoDAO {
	private final String GET_ALL_BY_USER_ID = "SELECT "
		+ "a.tipo_alimento tipo_alimento, a.alimento alimento, a.data data_alimento, a.calorias calorias "
		+ "FROM alimento_consumido a WHERE a.usuario_id = ?";
	private final String INSERT = "INSERT INTO alimento_consumido (tipo_alimento, alimento, data, calorias, usuario_id) values (?,?,?,?,?)";
	private final String GET_LAST_CALORIAS = "SELECT calorias, max(data) FROM alimento_consumido WHERE usuario_id = ? GROUP BY data, calorias";

	public List<AlimentoConsumido> getAllByUserId(int usuarioId) throws FalhaConexaoException {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
	    Connection conn;
	    PreparedStatement ps;
	    ResultSet rs;
	
	    List<AlimentoConsumido> listaAlimento = new ArrayList<>();
	
	    try {
	    	Conexao conexao = new Conexao();
	        conn = conexao.getConnection();
	        ps = conn.prepareStatement(GET_ALL_BY_USER_ID);
	        
	        ps.setInt(1, usuarioId);
	        
	        rs = ps.executeQuery();
	
	        AlimentoConsumido alimentoConsumido;
	
	        while (rs.next()) {
	
	        	alimentoConsumido = new AlimentoConsumido();
	        	alimentoConsumido.setAlimento(rs.getString("alimento"));
	            alimentoConsumido.setCalorias(rs.getDouble("calorias"));
	            alimentoConsumido.setData(rs.getDate("data_alimento"));
	            alimentoConsumido.setTipoAlimento(TipoAlimento.valueOf(rs.getString("tipo_alimento")));
	            alimentoConsumido.setUsuario(usuarioDAO.getById(usuarioId));
	
	            listaAlimento.add(alimentoConsumido);
	        }
	
	    } catch (SQLException e) {
	    	e.printStackTrace();
	        throw new FalhaConexaoException("Não foi possível conectar no database");
	    } finally {
	        rs = null;
	        ps = null;
	        conn = null;
	    }
	
	    return listaAlimento;
	}


	public void insert(AlimentoConsumido alimentoConsumido) throws FalhaConexaoException {
	        Connection conn;
	        PreparedStatement ps;
	        ResultSet rs;
	
	        try {
	        	Conexao conexao = new Conexao();
	            conn = conexao.getConnection();
	            ps = conn.prepareStatement(INSERT);
	
	            ps.setString(1, alimentoConsumido.getTipoAlimento().toString());
	            ps.setString(2, alimentoConsumido.getAlimento());
	            ps.setDate(3, FormatadorData.toDateSql(alimentoConsumido.getData()));
	            ps.setDouble(4, alimentoConsumido.getCalorias());
	            ps.setInt(5, alimentoConsumido.getUsuario().getId());
	        
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
	
	public Double getLastCaloria(int usuarioId) throws FalhaConexaoException {
    	
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
    	
        Connection conn;
        PreparedStatement ps;
        ResultSet rs;
        
        List<Double> listaCalorias = new ArrayList<>();
        Double calorias = 0.0;
        try {
        	Conexao conexao = new Conexao();
            conn = conexao.getConnection();
            ps = conn.prepareStatement(GET_LAST_CALORIAS);
            
            ps.setInt(1, usuarioId);
            
            rs = ps.executeQuery();

            while (rs.next()) {
            	calorias = rs.getDouble("calorias");
                
                listaCalorias.add(calorias);
            }

        } catch (SQLException e) {
        	e.printStackTrace();
            throw new FalhaConexaoException("Não foi possível conectar no database");
        } finally {
            rs = null;
            ps = null;
            conn = null;
        }
        
        if (listaCalorias.isEmpty()) {
        	return calorias;
        } else {
        	return listaCalorias.get(listaCalorias.size()-1);
        }
    }
}
