package br.com.solo.popular.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.solo.popular.PopularServiceApplication;
import br.com.solo.popular.domain.model.Categoria;

public class ConsultaCategoria {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(PopularServiceApplication.class)
				.web(WebApplicationType.NONE).run(args);

		CadastroCategoria cadastroCategoria = applicationContext.getBean(CadastroCategoria.class);
		
		List<Categoria> listaCategoria = cadastroCategoria.listaCategorias();
		
		for (Categoria categoria : listaCategoria) {
			
			System.out.println("Lista Categorias: " + categoria.getNome());
		}
		
	}
}
