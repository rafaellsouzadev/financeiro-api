package com.rafael.financeiro.api.repository.projection;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rafael.financeiro.api.model.enums.TipoLancamento;

public class ResumoLancamento {
	
	private Long id;
	private String descricao;
	
	@JsonFormat(pattern = "dd/MM/yyyy", timezone="GMT-3")
	private Date dataVencimento;
	
	@JsonFormat(pattern = "dd/MM/yyyy", timezone="GMT-3")
	private Date dataPagamento;
	
	private BigDecimal valor;
	
	private TipoLancamento tipo;
	
	private String categoria;
	
	private String pessoa;
		
	public ResumoLancamento(Long id, String descricao, Date dataVencimento, Date dataPagamento, BigDecimal valor,
			TipoLancamento tipo, String categoria, String pessoa) {
		this.id = id;
		this.descricao = descricao;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
		this.valor = valor;
		this.tipo = tipo;
		this.categoria = categoria;
		this.pessoa = pessoa;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public TipoLancamento getTipo() {
		return tipo;
	}
	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getPessoa() {
		return pessoa;
	}
	public void setPessoa(String pessoa) {
		this.pessoa = pessoa;
	}
	
	

}
