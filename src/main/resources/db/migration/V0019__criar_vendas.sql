CREATE TABLE venda 
(
  id int PRIMARY KEY AUTO_INCREMENT,
  data DATETIME NOT NULL,
  cliente_id int NOT NULL,
  func_vendedor_id int,
  func_caixa_id int NOT NULL,
  pontuacao_id int NOT NULL,
  forma_pgto_id int NOT NULL,
  CONSTRAINT fk_cliente_venda FOREIGN KEY (cliente_id) REFERENCES cliente (id),
  CONSTRAINT fk_f_vendedor_venda FOREIGN KEY (func_vendedor_id) REFERENCES funcionario (id),
  CONSTRAINT fk_f_caixa_venda FOREIGN KEY (func_caixa_id) REFERENCES funcionario (id),
  CONSTRAINT fk_pontuacao_venda FOREIGN KEY (pontuacao_id) REFERENCES pontuacao (id),
  CONSTRAINT fk_forma_pgto_venda FOREIGN KEY (forma_pgto_id) REFERENCES forma_pagamento (id)
);