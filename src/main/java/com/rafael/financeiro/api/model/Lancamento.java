package com.rafael.financeiro.api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rafael.financeiro.api.model.enums.TipoLancamento;

@Entity
@Table(name = "lancamento")
@SequenceGenerator(name = "seq_lancamento", sequenceName = "seq_lancamento", initialValue = 1, allocationSize = 1)
public class Lancamento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lancamento")
	private Long id;
	
	private String descricao;
	
	@JsonFormat(pattern = "dd/MM/yyyy", timezone="GMT-3")
	@Temporal(TemporalType.DATE)	
	@Column(name = "data_vencimento", nullable = false)
	private Date dataVencimento;
	
	@JsonFormat(pattern = "dd/MM/yyyy", timezone="GMT-3")
	@Temporal(TemporalType.DATE)	
	@Column(name = "data_pagamento")
	private Date dataPagamento;
	
	@Column(nullable = false)
	private BigDecimal valor;
	
	private String observacao;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoLancamento tipo;
	
	
	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "categoria_fk"))
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name = "pessoa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
	private Pessoa pessoa;
	
	public Lancamento() {
	}
	

	public Lancamento(Long id, String descricao, Date dataVencimento, Date dataPagamento, BigDecimal valor,
			String observacao, TipoLancamento tipo, Categoria categoria, Pessoa pessoa) {
		this.id = id;
		this.descricao = descricao;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
		this.valor = valor;
		this.observacao = observacao;
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		return Objects.equals(id, other.id);
	}
	
	


}
