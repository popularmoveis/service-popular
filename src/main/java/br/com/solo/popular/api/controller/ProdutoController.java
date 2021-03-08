package br.com.solo.popular.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.solo.popular.domain.exception.EntidadeNaoEncontradaException;
import br.com.solo.popular.domain.model.Produto;
import br.com.solo.popular.domain.repository.ProdutoRepository;
import br.com.solo.popular.domain.service.CadastroProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	CadastroProdutoService cadastroProduto;

	@GetMapping
	public List<Produto> listar() {
		return produtoRepository.findAll();
	}

	@GetMapping("/{produtoId}")
	public ResponseEntity<Produto> buscar(@PathVariable("produtoId") Long id) {

		return ResponseEntity.ok(produtoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com produtoId: " + id)));
	}

	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Produto produto) {

		try {

			produto = cadastroProduto.salvar(produto);

			return ResponseEntity.status(HttpStatus.CREATED).body(produto);

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{produtoId}")
	public ResponseEntity<Optional<Produto>> alterar(Long produtoId, @RequestBody Produto produto) {

		Optional<Produto> produtoAtual = produtoRepository.findById(produtoId);

		if (produtoAtual.isPresent()) {

			BeanUtils.copyProperties(produto, produtoAtual);
			produto = cadastroProduto.salvar(produtoAtual.get());

			return ResponseEntity.ok(produtoAtual);
		} else {
			return ResponseEntity.notFound().build();
		}

	}
}
