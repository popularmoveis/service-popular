package br.com.solo.popular.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.solo.popular.domain.exception.EntidadeEmUsoException;
import br.com.solo.popular.domain.exception.EntidadeNaoEncontradaException;
import br.com.solo.popular.domain.model.Categoria;
import br.com.solo.popular.domain.repository.CategoriaRepository;
import br.com.solo.popular.domain.service.CadastroCategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private CadastroCategoriaService cadastroCategoria;

	@GetMapping
	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}

	@GetMapping("/{categoriaId}")
	public ResponseEntity<Categoria> buscar(@PathVariable("categoriaId") final Long id) {

		return ResponseEntity.ok(categoriaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com categoriaId: " + id)));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void adicionar(@RequestBody Categoria categoria) {
		this.cadastroCategoria.salvar(categoria);
	}

	@PutMapping("/{categoriaId}")
	public ResponseEntity<Optional<Categoria>> alterar(Long categoriaId, @RequestBody Categoria categoria) {

		Optional<Categoria> categoriaAtual = categoriaRepository.findById(categoriaId);

		if (categoriaAtual.isPresent()) {

			BeanUtils.copyProperties(categoria, categoriaAtual);
			categoria = categoriaRepository.save(categoriaAtual.get());

			return ResponseEntity.ok(categoriaAtual);
		} else {
			ResponseEntity.notFound().build();
		}
		return null;

	}

	@DeleteMapping("/{categoriaId}")
	public ResponseEntity<Categoria> remover(@PathVariable Long categoriaId) {
		try {
			cadastroCategoria.remover(categoriaId);
			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
