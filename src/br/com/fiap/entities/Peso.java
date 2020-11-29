package br.com.fiap.entities;

import java.io.Serializable;
import java.util.Date;

import br.com.fiap.utils.FormatadorData;

public class Peso implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private Double peso;
    private Date data;
    private Usuario usuario;
    
	public Peso() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Peso(Double peso, Date data, Usuario usuario) {
		super();
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
	public String getData() {
		return FormatadorData.toDate(data);
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}
