CREATE TABLE produto
(
  id int PRIMARY KEY AUTO_INCREMENT,
  descricao varchar(75) NOT NULL,
  quantidade decimal(12,2) DEFAULT 0.0,
  preco decimal(16,2) NOT NULL,
  categoria_id int NOT NULL,
  unidade_medida_id int NOT NULL,
  CONSTRAINT fk_categoria_produto FOREIGN KEY (categoria_id) REFERENCES categoria (id),
  CONSTRAINT fk_unidade_medida_produto FOREIGN KEY (unidade_medida_id) REFERENCES unidade_medida (id)
);