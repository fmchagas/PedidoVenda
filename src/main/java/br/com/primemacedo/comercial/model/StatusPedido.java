package br.com.primemacedo.comercial.model;

public enum StatusPedido {
	ORCAMENTO("Orçamento")
	, EMITIDO("Emitido")
	, CANCELADO("Cancelado");
	
	StatusPedido(String descricao) {
		this.descricao = descricao;
	}
	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}
}
