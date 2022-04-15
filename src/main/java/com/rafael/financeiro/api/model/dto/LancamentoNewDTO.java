package com.rafael.financeiro.api.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rafael.financeiro.api.model.Categoria;
import com.rafael.financeiro.api.model.Lancamento;
import com.rafael.financeiro.api.model.Pessoa;
import com.rafael.financeiro.api.model.enums.TipoLancamento;

public class LancamentoNewDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotNull
	private String descricao;
	
	@JsonFormat(pattern = "dd/MM/yyyy", timezone="GMT-3")
	@Temporal(TemporalType.DATE)	
	private Date dataVencimento;
	
	@JsonFormat(pattern = "dd/MM/yyyy", timezone="GMT-3")
	@Temporal(TemporalType.DATE)	
	private Date dataPagamento;
	
	@NotNull
	private BigDecimal valor;
	
	private String observacao;	
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoLancamento tipo;
	
	@NotNull
	private Categoria categoria;
	
	@NotNull
	private Pessoa pessoa;
	
	public LancamentoNewDTO() {
	}
	
	public LancamentoNewDTO(Lancamento lancamento) {
		this.descricao = lancamento.getDescricao();
		this.dataVencimento = lancamento.getDataVencimento();
		this.dataPagamento = lancamento.getDataPagamento();
		this.valor = lancamento.getValor();
		this.observacao = lancamento.getObservacao();
		this.categoria = lancamento.getCategoria();
		this.pessoa = lancamento.getPessoa();
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

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public TipoLancamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


	

}
