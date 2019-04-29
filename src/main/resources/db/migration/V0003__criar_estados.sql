CREATE TABLE estado (
	uf CHAR(2)  NOT NULL PRIMARY KEY,
    nome VARCHAR(32) NOT NULL,    
    pais_id INT NOT NULL,
    CONSTRAINT fk_pais_estado FOREIGN KEY (pais_id) REFERENCES pais (id)
);