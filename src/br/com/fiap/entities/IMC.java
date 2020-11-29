package br.com.fiap.entities;

import java.io.Serializable;

import br.com.fiap.type.ClassificacaoIMC;

public class IMC implements Serializable{
	
	private Double imcUsuario;
	private ClassificacaoIMC classificacao;
	private Usuario usuario;
	
	public IMC(Double imcUsuario, ClassificacaoIMC classificacao, Usuario usuario) {
		super();
		this.usuario = usuario;
		this.imcUsuario = imcUsuario;
		this.classificacao = classificacao;
	}
	public IMC() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Double getImcUsuario() {
		return imcUsuario;
	}
	public void setImcUsuario(Double imcUsuario) {
		this.imcUsuario = imcUsuario;
	}
	public ClassificacaoIMC getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(ClassificacaoIMC classificacao) {
		this.classificacao = classificacao;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public IMC createIMC(Double peso, Usuario usuario) {
		if (peso != null && peso != 0.0 && usuario.getAltura() != null && usuario.getAltura() != 0.0) {
			Double imc = peso / (usuario.getAltura()*usuario.getAltura());
			ClassificacaoIMC classificacao = null;
			
			if (imc<18.5) {
				classificacao = ClassificacaoIMC.ABAIXO_PESO;
			}
			else if (imc<24.9 && imc>=18.5) {
				classificacao = ClassificacaoIMC.PESO_NORMAL;
			}
			else if (imc<29.9 && imc>=25) {
				classificacao = ClassificacaoIMC.SOBREPESO;
			}
			else if (imc<34.9 && imc>=30) {
				classificacao = ClassificacaoIMC.OBESIDADE_1;
			}
			else if (imc<39.9 && imc>=35) {
				classificacao = ClassificacaoIMC.OBESIDADE_2;
			}
			else {
				classificacao = ClassificacaoIMC.OBESIDADE_3;
			}
	
			return new IMC(imc, classificacao, usuario);
		}

		return null;
	}

}
