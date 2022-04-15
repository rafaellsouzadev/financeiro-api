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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.financeiro.api.event.RecursoCriadoEvent;
import com.rafael.financeiro.api.model.Lancamento;
import com.rafael.financeiro.api.model.dto.LancamentoNewDTO;
import com.rafael.financeiro.api.model.dto.LancamentoUpdateDTO;
import com.rafael.financeiro.api.repository.filter.LancamentoFilter;
import com.rafael.financeiro.api.repository.projection.ResumoLancamento;
import com.rafael.financeiro.api.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and hasAuthority('SCOPE_read')" )
	@GetMapping
	public ResponseEntity<Page<Lancamento>> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable) {
		Page<Lancamento> lancamentos = lancamentoService.pesquisar(lancamentoFilter, pageable);
		return ResponseEntity.ok(lancamentos);
	}
	
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and hasAuthority('SCOPE_read')" )
	@GetMapping(params = "resumo")
	public ResponseEntity<Page<ResumoLancamento>> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
		Page<ResumoLancamento> resumir = lancamentoService.resumir(lancamentoFilter, pageable);
		return ResponseEntity.ok(resumir);
	}
	
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and hasAuthority('SCOPE_read')" )
	@GetMapping("/{id}")
	public ResponseEntity<Lancamento> find(@PathVariable Long id) {
		Optional<Lancamento> lancamento = lancamentoService.find(id);
		
		return lancamento.isPresent() ? ResponseEntity.ok(lancamento.get())
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
	}
	
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and hasAuthority('SCOPE_write')")
	@PostMapping
	public ResponseEntity<Lancamento> insert(@Valid @RequestBody LancamentoNewDTO lancamento, HttpServletResponse response) {
		Lancamento lancamentoSalva = lancamentoService.fromNewDto(lancamento);
		lancamentoSalva = lancamentoService.insert(lancamentoSalva);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalva.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalva);
	}
	
	@PreAuthorize("hasAuthority('ROLE_DELETAR_LANCAMENTO') and hasAuthority('SCOPE_write')")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		lancamentoService.delete(id);
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and hasAuthority('SCOPE_write')")
	@PutMapping("/{id}/pagamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> update(@Valid @RequestBody Lancamento dataPagamento, @PathVariable Long id) {		
		dataPagamento.setId(id);
		dataPagamento = lancamentoService.updatePagamento(dataPagamento);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ATUALIZAR_LANCAMENTO') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Lancamento> atualizar(@PathVariable Long id, @Valid @RequestBody LancamentoUpdateDTO lancamento) {	
			Lancamento lancamentoSalva = lancamentoService.fromUpdateDTO(lancamento);
			lancamentoSalva = lancamentoService.atualizar(id, lancamentoSalva);
			return ResponseEntity.ok(lancamentoSalva);		
	}
	
	

}
