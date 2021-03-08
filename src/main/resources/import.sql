insert into bd_popular.tb_categoria(cat_nome) values ('Armario');
insert into bd_popular.tb_categoria(cat_nome) values ('Mesa');
insert into bd_popular.tb_produto (pro_nome, pro_descricao, pro_altura, pro_largura, pro_comprimento, pro_preco, pro_ativo ) values ('Armario Sasazaqui', 'armario de parede teste', 1.90, 0.75, 3.99, 190.00, true);
insert into bd_popular.tb_produto_categoria(produto_id, categoria_id) values (1,1);