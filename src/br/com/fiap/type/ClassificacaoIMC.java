package br.com.fiap.type;

public enum ClassificacaoIMC {
	DESCONHECIDO("Cadastre peso e/ou altura"),
	ABAIXO_PESO("Abaixo do peso"),
	PESO_NORMAL("Peso normal"),
	SOBREPESO("Sobrepeso"),
	OBESIDADE_1("Obesidade Grau 1"),
	OBESIDADE_2("Obesidade Grau 2"),
	OBESIDADE_3("Obesidade Grau 3");
	
	private String nome;

	ClassificacaoIMC(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
