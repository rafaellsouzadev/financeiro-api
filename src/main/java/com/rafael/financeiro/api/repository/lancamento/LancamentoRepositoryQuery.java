package com.rafael.financeiro.api.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rafael.financeiro.api.model.Lancamento;
import com.rafael.financeiro.api.repository.filter.LancamentoFilter;
import com.rafael.financeiro.api.repository.projection.ResumoLancamento;

public interface LancamentoRepositoryQuery {
	
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
	
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);

}
