package com.rafael.financeiro.api.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pessoa")
@SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa", initialValue = 1, allocationSize = 1)
public class Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa")
	private Long id;
	
	@NotNull
	private String nome;
	
	@Embedded
	private Endereco endereco;
	
	@NotNull
	private Boolean ativo;
	
		
	public Pessoa(Long id, String nome, Endereco endereco, Boolean ativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.ativo = ativo;
	}

	public Pessoa() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	@JsonIgnore
	@Transient
	public boolean isInativo() {
		return !this.ativo;
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
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
