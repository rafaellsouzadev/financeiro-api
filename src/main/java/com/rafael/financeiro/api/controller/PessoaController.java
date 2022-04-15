package com.rafael.financeiro.api.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.financeiro.api.event.RecursoCriadoEvent;
import com.rafael.financeiro.api.model.Pessoa;
import com.rafael.financeiro.api.model.dto.PessoaNewDTO;
import com.rafael.financeiro.api.model.dto.PessoaUpdateDTO;
import com.rafael.financeiro.api.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
		
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and hasAuthority('SCOPE_read')" )
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> find(@PathVariable Long id) {
		Optional<Pessoa> pessoa = pessoaService.find(id);
		return pessoa.isPresent() ? ResponseEntity.ok(pessoa.get())
				: ResponseEntity.notFound().build();
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA')")
	public Page<Pessoa> pesquisar(@RequestParam(required = false, defaultValue = "") String nome, Pageable pageable) {
	    return pessoaService.pesquisarNome(nome, pageable);
	}
	
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and hasAuthority('SCOPE_write')")
	@PostMapping
	public ResponseEntity<Pessoa> insert(@Valid @RequestBody PessoaNewDTO pessoa, HttpServletResponse response) {
		Pessoa pessoaSalva = pessoaService.fromDTO(pessoa);
		pessoaSalva = pessoaService.insert(pessoaSalva);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}
	
	@PreAuthorize("hasAuthority('ROLE_REMOVER_PESSOA') and hasAuthority('SCOPE_write')")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {
		pessoaService.delete(id);
	}
	
	@PreAuthorize("hasAuthority('ROLE_ATUALIZAR_PESSOA') and hasAuthority('SCOPE_write')")
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> update(@PathVariable Long id, @Valid @RequestBody PessoaUpdateDTO pessoa) {
		Pessoa pessoaSalva = pessoaService.fromUpdateDTO(pessoa);			
		pessoaSalva = pessoaService.update(id, pessoaSalva);
		return ResponseEntity.ok(pessoaSalva);
	}
	
	
	@PutMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateAtivo(@PathVariable Long id, @RequestBody Boolean ativo) {
		pessoaService.updateAtivo(id, ativo);
	}
	


}
