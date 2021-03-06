package br.com.solo.popular.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.solo.popular.PopularServiceApplication;
import br.com.solo.popular.domain.model.Categoria;

public class BuscaCategoria {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(PopularServiceApplication.class)
				.web(WebApplicationType.NONE).run(args);

		CadastroCategoria cadastroCategoria = applicationContext.getBean(CadastroCategoria.class);

		Categoria categoria = cadastroCategoria.findById(1L);

		System.out.println(categoria.getNome());

	}
}
