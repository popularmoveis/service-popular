package br.com.solo.popular.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.solo.popular.domain.exception.EntidadeEmUsoException;
import br.com.solo.popular.domain.exception.EntidadeNaoEncontradaException;
import br.com.solo.popular.domain.model.Categoria;
import br.com.solo.popular.domain.repository.CategoriaRepository;

@Service
public class CadastroCategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria salvar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public void remover(final Long idCategoria) {

		try {

			Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);

			if (categoria.isPresent()) {
				categoriaRepository.delete(categoria.get());
			} else {
				throw new EmptyResultDataAccessException(1);
			}
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de categoria com código %d", idCategoria));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Categoria de código %d não pode ser removida, pois está em uso", idCategoria));
		}
	}
}
