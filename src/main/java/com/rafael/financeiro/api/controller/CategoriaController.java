package com.rafael.financeiro.api.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
import com.rafael.financeiro.api.model.Categoria;
import com.rafael.financeiro.api.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<List<Categoria>> findAll() {
		List<Categoria> categorias = categoriaService.findAll();
		return ResponseEntity.ok(categorias) ;				
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<Categoria> find(@PathVariable Long id) {
		Optional<Categoria> categoria = categoriaService.find(id);
		return categoria.isPresent() ? ResponseEntity.ok(categoria.get())
				: ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Categoria> insert(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria save = categoriaService.insert(categoria);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, save.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(save);
	}
	
	@PreAuthorize("hasAuthority('ROLE_DELETAR_CATEGORIA') and hasAuthority('SCOPE_write')" )
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		categoriaService.delete(id);
	}
	
	@PreAuthorize("hasAuthority('ROLE_ATUALIZAR_CATEGORIA') and hasAuthority('SCOPE_write')" )
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> update(@PathVariable Long id, @Valid @RequestBody Categoria categoria) {
		Categoria categoriaSalva = categoriaService.update(id, categoria);
		return ResponseEntity.ok(categoriaSalva);
	}

}
