package com.rafael.financeiro.api.model.enums;

public enum TipoLancamento {

	RECEITA("Receita"),
	DESPESA("Despesa");
	
	private String descricao;
	
	private TipoLancamento(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	@Override
	public String toString() {
		return this.descricao;
	}
}
