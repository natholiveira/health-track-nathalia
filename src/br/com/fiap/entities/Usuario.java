package br.com.fiap.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
	
	private int id;
	private String nome;
	private String email;
	private String senha;
	private Double imc;
	private Double altura = 0.0;
	private List<Peso> historicoPeso = new ArrayList<>();
	private List<FrequenciaCardiaca> historicoFrequencia = new ArrayList<>();
	private List<AlimentoConsumido> historicoAlimento = new ArrayList<>();
	private List<AtividadeFisica> historicoAtividade = new ArrayList<>();
	private Meta meta;
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Usuario(String nome, String email, String senha) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	
	

	public Usuario(String nome, String email, String senha, Double altura) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.altura = altura;
	}

	public Usuario(String nome, String email, String senha, Double imc, List<Peso> historicoPeso,
			List<FrequenciaCardiaca> historicoFrequencia, List<AlimentoConsumido> historicoAlimento,
			List<AtividadeFisica> historicoAtividade, Meta meta, Double altura) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.imc = imc;
		this.historicoPeso = historicoPeso;
		this.historicoFrequencia = historicoFrequencia;
		this.historicoAlimento = historicoAlimento;
		this.historicoAtividade = historicoAtividade;
		this.meta = meta;
		this.altura = altura;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Double getImc() {
		return imc;
	}

	public void setImc(Double imc) {
		this.imc = imc;
	}

	public List<Peso> getHistoricoPeso() {
		return historicoPeso;
	}

	public void setHistoricoPeso(List<Peso> historicoPeso) {
		this.historicoPeso = historicoPeso;
	}

	public List<FrequenciaCardiaca> getHistoricoFrequencia() {
		return historicoFrequencia;
	}

	public void setHistoricoFrequencia(List<FrequenciaCardiaca> historicoFrequencia) {
		this.historicoFrequencia = historicoFrequencia;
	}

	public List<AlimentoConsumido> getHistoricoAlimento() {
		return historicoAlimento;
	}

	public void setHistoricoAlimento(List<AlimentoConsumido> historicoAlimento) {
		this.historicoAlimento = historicoAlimento;
	}

	public List<AtividadeFisica> getHistoricoAtividade() {
		return historicoAtividade;
	}

	public void setHistoricoAtividade(List<AtividadeFisica> historicoAtividade) {
		this.historicoAtividade = historicoAtividade;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}
	
}
