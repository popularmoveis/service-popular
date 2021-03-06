package br.com.solo.popular.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.solo.popular.domain.model.Categoria;
import br.com.solo.popular.domain.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping
	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}

	@GetMapping("/{categoriaId}")
	public ResponseEntity<Categoria> buscar(@PathVariable("categoriaId") final Long id) {
		
		return ResponseEntity.ok(categoriaRepository.findById(id) .orElseThrow(() 
				-> new ResourceNotFoundException("Categoria não encontrada com categoriaId: " +id )));
	}
}
