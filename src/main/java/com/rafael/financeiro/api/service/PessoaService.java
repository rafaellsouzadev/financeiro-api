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

import com.rafael.financeiro.api.model.Endereco;
import com.rafael.financeiro.api.model.Pessoa;
import com.rafael.financeiro.api.model.dto.PessoaNewDTO;
import com.rafael.financeiro.api.model.dto.PessoaUpdateDTO;
import com.rafael.financeiro.api.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Transactional(readOnly = true)
	@Cacheable
	public Optional<Pessoa> find(Long id) {
		return pessoaRepository.findById(id);
	}

	@Transactional(readOnly = true)
	@Cacheable
	public Page<Pessoa> pesquisarNome(String nome, Pageable pageable) {
		return pessoaRepository.findByNomeContainingIgnoreCase(nome, pageable);
	}

	public Pessoa insert(Pessoa pessoa) {
		pessoa.setId(null);
		pessoa = pessoaRepository.save(pessoa);
		return pessoa;
	}

	public Pessoa fromDTO(PessoaNewDTO objDto) {
		Endereco endereco = new Endereco(objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), objDto.getCidade(), objDto.getEstado());
		Pessoa pessoa = new Pessoa(null, objDto.getNome(), endereco, objDto.getAtivo());

		return pessoa;
	}

	public Pessoa fromUpdateDTO(PessoaUpdateDTO objDto) {
		Endereco endereco = new Endereco(objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), objDto.getCidade(), objDto.getEstado());

		Pessoa pessoa = new Pessoa(null, objDto.getNome(), endereco, objDto.getAtivo());

		return pessoa;
	}

	public void delete(Long id) {
		pessoaRepository.deleteById(id);
	}

	public Pessoa update(Long id, Pessoa pessoa) {
		Pessoa pessoaSalva = find(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
		BeanUtils.copyProperties(pessoa, pessoaSalva, "id");
		return pessoaRepository.save(pessoaSalva);
	}

	public void updateAtivo(Long id, Boolean ativo) {
		Pessoa pessoaSalva = find(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
	}

}
