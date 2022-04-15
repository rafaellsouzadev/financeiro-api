package com.rafael.financeiro.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafael.financeiro.api.model.Categoria;
import com.rafael.financeiro.api.model.Lancamento;
import com.rafael.financeiro.api.model.Pessoa;
import com.rafael.financeiro.api.model.dto.LancamentoNewDTO;
import com.rafael.financeiro.api.model.dto.LancamentoUpdateDTO;
import com.rafael.financeiro.api.repository.CategoriaRepository;
import com.rafael.financeiro.api.repository.LancamentoRepository;
import com.rafael.financeiro.api.repository.PessoaRepository;
import com.rafael.financeiro.api.repository.filter.LancamentoFilter;
import com.rafael.financeiro.api.repository.projection.ResumoLancamento;
import com.rafael.financeiro.api.service.exception.CategoriaInexistenteException;
import com.rafael.financeiro.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	@Transactional(readOnly = true)
	@Cacheable
	public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoRepository.filtrar(lancamentoFilter, pageable);
	}
	
	@Transactional(readOnly = true)
	@Cacheable
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoRepository.resumir(lancamentoFilter, pageable);
	}

	public Optional<Lancamento> find(Long id) {
		return lancamentoRepository.findById(id);
	}
	
	public Lancamento insert(Lancamento lancamento) {
		if (!lancamento.getPessoa().equals(lancamento.getPessoa())) {
			validarPessoa(lancamento);
		}
		lancamento.setId(null);		
		return lancamentoRepository.save(lancamento);		
	}
	
	
	public Lancamento fromNewDto(LancamentoNewDTO lancamentoDto) {
		Categoria categoria = new Categoria(lancamentoDto.getCategoria().getId(), lancamentoDto.getCategoria().getNome());
		Categoria categoriaId = categoriaRepository.findById(categoria.getId()).orElseThrow(() -> new CategoriaInexistenteException());
		Pessoa pessoa = new Pessoa(lancamentoDto.getPessoa().getId(), lancamentoDto.getPessoa().getNome(), lancamentoDto.getPessoa().getEndereco(), lancamentoDto.getPessoa().getAtivo());
		Pessoa pessoaId = pessoaRepository.findById(pessoa.getId()).orElseThrow(() -> new PessoaInexistenteOuInativaException());
		Lancamento lancamento = new Lancamento(null, lancamentoDto.getDescricao(), 
				lancamentoDto.getDataVencimento(), lancamentoDto.getDataPagamento(), lancamentoDto.getValor()
				, lancamentoDto.getObservacao(), lancamentoDto.getTipo(), categoriaId, pessoaId);
		
		return lancamento;
	}
	// atualiza o lancamento pelo dto
	public Lancamento fromUpdateDTO(LancamentoUpdateDTO objDto) {
		Categoria categoria = new Categoria(objDto.getCategoria().getId(), objDto.getCategoria().getNome());
		Categoria categoriaId = categoriaRepository.findById(categoria.getId()).orElseThrow(() -> new CategoriaInexistenteException());
		Pessoa pessoa = new Pessoa(objDto.getPessoa().getId(), objDto.getPessoa().getNome(), objDto.getPessoa().getEndereco(), objDto.getPessoa().getAtivo());
		Pessoa pessoaId = pessoaRepository.findById(pessoa.getId()).orElseThrow(() -> new PessoaInexistenteOuInativaException());
		Lancamento lancamento = new Lancamento(objDto.getId(), objDto.getDescricao(), objDto.getDataVencimento(), 
				objDto.getDataPagamento(), objDto.getValor(), objDto.getObservacao(), objDto.getTipo(), categoriaId, pessoaId);
		return lancamento;
	}
	
	public void delete(Long id) {
		lancamentoRepository.deleteById(id);
	}
	
	public Lancamento updatePagamento(Lancamento obj) {			
		Lancamento newobj = find(obj.getId()).orElseThrow(() -> new EmptyResultDataAccessException(1));		
		updateData(newobj, obj);
		return lancamentoRepository.save(newobj);
	}
	
	public void updateData(Lancamento newobj, Lancamento obj) {
		newobj.setDataPagamento(obj.getDataPagamento());		
	}
	
	
	
	public Lancamento atualizar(Long id, Lancamento lancamento) {
		Lancamento lancamentoSalvo = find(id).orElseThrow(() ->  new EmptyResultDataAccessException(1));
		BeanUtils.copyProperties(lancamento, lancamentoSalvo, "id");
		return lancamentoRepository.save(lancamentoSalvo);
	}

	private void validarPessoa(Lancamento lancamento) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(lancamento.getPessoa().getId());
		if (pessoa.isEmpty() || pessoa.get().isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
	}

}
