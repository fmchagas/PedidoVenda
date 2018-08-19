package br.com.primemacedo.comercial.model;

public enum TipoPessoa {
	FISICA("Fisica"), JURIDICA("Juridica");
	
	private TipoPessoa(String descricao) {
		this.descricao = descricao;
	}
	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}
}
