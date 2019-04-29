CREATE TABLE pessoa
(
  id int PRIMARY KEY AUTO_INCREMENT,
  tipo_pessoa varchar(30) NOT NULL,
  cpf varchar(14) NOT NULL,
  rg varchar(14) NOT NULL,
  nome varchar(75) NOT NULL,
  data_nascimento DATE NOT NULL,
  sexo varchar(10) NOT NULL,
  logradouro varchar(255),
  numeral varchar(15),
  cep varchar(11),
  cidade_id int NOT NULL,
  pontuacao_total decimal(16,2),
  username varchar(16),
  password varchar(512),
  CONSTRAINT fk_cidade_endereco_pessoa FOREIGN KEY (cidade_id) REFERENCES cidade (id)

);

CREATE TABLE telefone
(
  id int PRIMARY KEY AUTO_INCREMENT,
  telefone varchar(20) NOT NULL,
  tipo_contato varchar(20),
  pessoa_id int NOT NULL,
  CONSTRAINT fk_pessoa_telefone FOREIGN KEY (pessoa_id) REFERENCES pessoa (id)

);

CREATE TABLE email
(
  id int PRIMARY KEY AUTO_INCREMENT,
  email varchar(75) NOT NULL,
  tipo_contato varchar(20),
  pessoa_id int NOT NULL,
  CONSTRAINT fk_pessoa_email FOREIGN KEY (pessoa_id) REFERENCES pessoa (id)

);