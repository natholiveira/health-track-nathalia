package br.com.fiap.entities;

import java.io.Serializable;
import java.util.Date;

import br.com.fiap.type.TipoAlimento;
import br.com.fiap.utils.FormatadorData;

public class AlimentoConsumido implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private TipoAlimento tipoAlimento;
    private String alimento;
    private Date data;
    private Double calorias;
    private Usuario usuario;
    
	public AlimentoConsumido() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AlimentoConsumido(TipoAlimento tipoAlimento, String alimento, Date data, Double calorias, Usuario usuario) {
		super();
		this.tipoAlimento = tipoAlimento;
		this.alimento = alimento;
		this.data = data;
		this.calorias = calorias;
		this.usuario = usuario;
	}

	public TipoAlimento getTipoAlimento() {
		return tipoAlimento;
	}
	public void setTipoAlimento(TipoAlimento tipoAlimento) {
		this.tipoAlimento = tipoAlimento;
	}
	public String getAlimento() {
		return alimento;
	}
	public void setAlimento(String alimento) {
		this.alimento = alimento;
	}
	public String getData() {
		return FormatadorData.toDate(data);
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Double getCalorias() {
		return calorias;
	}
	public void setCalorias(Double calorias) {
		this.calorias = calorias;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
    
    

}
