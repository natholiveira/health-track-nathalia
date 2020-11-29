package br.com.fiap.entities;

import java.io.Serializable;
import java.util.Date;

import br.com.fiap.utils.FormatadorData;

public class FrequenciaCardiaca implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private String frequencia;
    private Date data;
    private Usuario usuario;
    
	public FrequenciaCardiaca(String frequencia, Date data, Usuario usuario) {
		super();
		this.frequencia = frequencia;
		this.data = data;
		this.usuario = usuario;
	}
	
	public FrequenciaCardiaca() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getFrequencia() {
		return frequencia;
	}
	public void setFrequencia(String frequencia) {
		this.frequencia = frequencia;
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
    
    
}
