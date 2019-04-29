CREATE TABLE produto_venda 
(
  id int primary key auto_increment,
  venda_id int,
  produto_id int NOT NULL,
  quantidade decimal(12,2) NOT NULL,
  CONSTRAINT fk_venda_produto_venda FOREIGN KEY (venda_id) REFERENCES venda (id),
  CONSTRAINT fk_produto_produto_venda FOREIGN KEY (produto_id) REFERENCES produto (id)  
);