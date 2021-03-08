/**
 * 
 */
package br.com.solo.popular.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Joaquim de Castro
 *
 */

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_produto")
public class Produto {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pro_id;

	@Column(nullable = false)
	private String pro_nome;

	@Column(nullable = false)
	private String pro_descricao;

	@Column(nullable = false)
	private BigDecimal pro_altura;

	@Column(nullable = false)
	private BigDecimal pro_largura;

	@Column(nullable = false)
	private BigDecimal pro_comprimento;

	@Column(nullable = false)
	private BigDecimal pro_preco;

	@Column(nullable = false)
	private Boolean pro_ativo;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "TB_PRODUTO_CATEGORIA", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private List<Categoria> categorias = new ArrayList<>();

}
