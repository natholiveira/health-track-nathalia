package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.exceptions.FalhaConexaoException;

public class Conexao {
	
     static final String URL = "jdbc:postgresql://postgres-instance.c6mnbfnsazox.us-east-2.rds.amazonaws.com:5432/health-database";
     static final String USER = "aws_postgres";
     static final String PASSWORD = "password";
     static final String DRIVER = "org.postgresql.Driver";
	 
	 public Connection getConnection() throws FalhaConexaoException {
		 	Boolean conexaoAtiva = false;
	        Connection conn;
	        PreparedStatement ps;
	        ResultSet rs;
	        
	        
	        try {

	            Class.forName(DRIVER);
	            conn = DriverManager.getConnection(URL,USER, PASSWORD);
	       

	        } catch (ClassNotFoundException e) {
	            throw new FalhaConexaoException("Não foi possível encontrar o driver da database");
	        } catch (SQLException e) {
	            throw new FalhaConexaoException("Não foi possível conectar no database");
	        }
	        
	        return conn;
	    }

}
