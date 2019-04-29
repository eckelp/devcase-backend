CREATE TABLE cidade (
	id int primary key auto_increment,
  codigo_ibge int,
  nome VARCHAR(128) NOT NULL,
  uf CHAR(2) NOT NULL,
  CONSTRAINT fk_estado_cidade FOREIGN KEY (uf) references estado (uf)
);