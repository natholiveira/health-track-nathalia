package br.com.fiap.entities;

import java.io.Serializable;
import java.util.Date;

import br.com.fiap.type.TipoAtividade;
import br.com.fiap.type.TipoDistancia;
import br.com.fiap.utils.FormatadorData;

public class AtividadeFisica implements Serializable  {

	private static final long serialVersionUID = 1L;
	
    private TipoAtividade tipoAtividade;
    private Date dataHoraInicio;
    private Date dataHoraTermino;
    private Double distanciaPercorrida;
    private TipoDistancia tipoDistancia;
    private Usuario usuario;
    
	public AtividadeFisica() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AtividadeFisica(TipoAtividade tipoAtividade, Date dataHoraInicio, Date dataHoraTermino,
			Double distanciaPercorrida, TipoDistancia tipoDistancia, Usuario usuario) {
		super();
		this.tipoAtividade = tipoAtividade;
		this.dataHoraInicio = dataHoraInicio;
		this.dataHoraTermino = dataHoraTermino;
		this.distanciaPercorrida = distanciaPercorrida;
		this.tipoDistancia = tipoDistancia;
		this.usuario = usuario;
	}

	public TipoAtividade getTipoAtividade() {
		return tipoAtividade;
	}
	public void setTipoAtividade(TipoAtividade tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}
	public String getDataHoraInicio() {
		return FormatadorData.toDateTime(dataHoraInicio);
	}
	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}
	public String getDataHoraTermino() {
		return FormatadorData.toDateTime(dataHoraTermino);
	}
	public void setDataHoraTermino(Date dataHoraTermino) {
		this.dataHoraTermino = dataHoraTermino;
	}
	public Double getDistanciaPercorrida() {
		return distanciaPercorrida;
	}
	public void setDistanciaPercorrida(Double distanciaPercorrida) {
		this.distanciaPercorrida = distanciaPercorrida;
	}
	public TipoDistancia getTipoDistancia() {
		return tipoDistancia;
	}
	public void setTipoDistancia(TipoDistancia tipoDistancia) {
		this.tipoDistancia = tipoDistancia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	    
}
