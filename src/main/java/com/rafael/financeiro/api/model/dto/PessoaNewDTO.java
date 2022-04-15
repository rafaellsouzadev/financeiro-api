package com.rafael.financeiro.api.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PessoaNewDTO {
	
	@Size(min=5, max=30)
	@NotNull	
	private String nome;
	
	@Size(min=5, max=30)
	@NotNull	
	private String logradouro;
	
	
	@NotNull	
    private String numero;
    private String complemento;
    
    @NotNull
    private String bairro;
    
    
    @NotNull
    private String cep;
    
    @Size(min=5, max=30)
	@NotNull	
    private String cidade;
    
    @Size(min=5, max=30)
	@NotNull	
    private String estado;   
    
    @NotNull
    private Boolean ativo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getComplemento() {
		return complemento;
	}
	
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
    
    

}
