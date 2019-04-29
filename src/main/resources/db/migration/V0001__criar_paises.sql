CREATE TABLE pais
(
  id int primary key auto_increment,
  nome varchar(32) not null unique,
  abv varchar(3)
);