CREATE TABLE pontuacao
(
  id int PRIMARY KEY AUTO_INCREMENT,
  valor_minimo decimal(16,2) NOT NULL,
  valor_maximo decimal(16,2) NOT NULL,
  pontos decimal(10,2)
);