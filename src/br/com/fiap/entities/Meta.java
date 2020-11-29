package br.com.fiap.entities;

import java.io.Serializable;
import java.util.Date;

public class Meta implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Double totalDiferencaPeso;
	private Double peso;
	private Date data;
	private Usuario usuario;
	
	public Meta() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Meta(Double totalDiferencaPeso, Double peso, Date data, Usuario usuario) {
		super();
		this.totalDiferencaPeso = totalDiferencaPeso;
		this.peso = peso;
		this.data = data;
		this.usuario = usuario;
	}

	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Double getTotalDiferencaPeso() {
		return totalDiferencaPeso;
	}

	public void setTotalDiferencaPeso(Double totalDiferencaPeso) {
		this.totalDiferencaPeso = totalDiferencaPeso;
	}
	
}
