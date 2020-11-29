package br.com.fiap.type;

public enum TipoAtividade {
    CORRIDA("Corrida"),
    CICLISMO("Ciclismo"),
    YOGA("Yoga"),
    CAMINHADA("Caminhada"),
    AEROBICO("Aeróbico");

	private String nome;

	TipoAtividade(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
