package br.com.primemacedo.comercial.model;

public enum FormaPagamento {
	DINHEIRO("Dinheiro"),
	CARTAO_CREDITO("Cartão de credito"), 
	CARTAO_DEBITO("Cartão de débito"),
	CHEQUE("Cheque"),
	BOLETO_BANCARIO("Boleto bancário"),
	DEPOSITO_BANCARIO("Depósito bancário");
	
	FormaPagamento(String descricao) {
		this.descricao = descricao;
	}
	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}
}
