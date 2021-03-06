package br.com.solo.popular.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import br.com.solo.popular.domain.model.Categoria;

@Component
public class CadastroCategoria {

	@PersistenceContext
	private EntityManager manager;

	public List<Categoria> listaCategorias() {
		return manager.createQuery("from Categoria", Categoria.class).getResultList();
	}

	public Categoria findById(final Long id) {
		return manager.find(Categoria.class, id);
	}

	@Transactional
	public Categoria addCategoria(Categoria categoria) {
		return manager.merge(categoria);
	}
	
	@Transactional
	public void remove(Categoria categoria) {
		categoria = findById(categoria.getId());
		manager.remove(categoria);
	}
}
