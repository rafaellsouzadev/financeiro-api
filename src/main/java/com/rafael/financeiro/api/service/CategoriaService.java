package com.rafael.financeiro.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafael.financeiro.api.model.Categoria;
import com.rafael.financeiro.api.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Transactional(readOnly = true)
	@Cacheable
	public List<Categoria> findAll() {
		List<Categoria> categorias = categoriaRepository.findAll();
		return categorias;
	}
	
	public Optional<Categoria> find(Long id) {
		return categoriaRepository.findById(id);
		
	}
	
	public Categoria insert(Categoria categoria) {
		Categoria save = categoriaRepository.save(categoria);
		return save;
	}
	
	public void delete(Long id) {
		categoriaRepository.deleteById(id);
	}
	
	public Categoria update(Long id, Categoria categoria) {
		Categoria categoriaSalva = find(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
		BeanUtils.copyProperties(categoria, categoriaSalva, "id");
		return categoriaRepository.save(categoriaSalva);		
	}

}
