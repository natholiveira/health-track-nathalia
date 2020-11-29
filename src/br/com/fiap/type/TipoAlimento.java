package br.com.fiap.type;

public enum TipoAlimento {
    FRUTA("Fruta"),
    MASSA("Massa"),
    DOCE("Doce"),
    LANCHE("Lanche"),
    LANCHE_NATURAL("Lanche Natural");
	
	private String nome;

	TipoAlimento(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
