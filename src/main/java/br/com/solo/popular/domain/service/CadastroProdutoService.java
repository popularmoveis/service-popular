package br.com.solo.popular.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.solo.popular.domain.exception.EntidadeEmUsoException;
import br.com.solo.popular.domain.exception.EntidadeNaoEncontradaException;
import br.com.solo.popular.domain.model.Produto;
import br.com.solo.popular.domain.repository.ProdutoRepository;

@Service
public class CadastroProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}

	public void remover(final Long idProduto) {

		try {

			Optional<Produto> produto = produtoRepository.findById(idProduto);

			if (produto.isPresent()) {
				produtoRepository.delete(produto.get());
			} else {
				throw new EmptyResultDataAccessException(1);
			}
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de Produto com código %d", idProduto));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Produto de código %d não pode ser removida, pois está em uso", idProduto));
		}
	}
}
