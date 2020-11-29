package br.com.fiap.type;

public enum  TipoDistancia {
    KM("KM"),
    M("M");
	
	private String nome;

	TipoDistancia(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
