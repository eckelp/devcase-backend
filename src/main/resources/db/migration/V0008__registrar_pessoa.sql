INSERT INTO pessoa (tipo_pessoa, cpf, rg, nome, data_nascimento, sexo, logradouro, numeral, cep, cidade_id, pontuacao_total) 
            VALUES ('CLIENTE', '045.321.789-99', '32.765.987-22', 'José da Silva', STR_TO_DATE("10/05/1974", "%d/%m/%Y"), 'MASCULINO'
            	   ,'Rua das Cordilheiras', '1050', NULL, 45, 0);


INSERT INTO pessoa (tipo_pessoa, cpf, rg, nome, data_nascimento, sexo, logradouro, numeral, cep, cidade_id, username, password) 
            VALUES ('FUNCIONARIO', '380.717.598-99', '48.765.426-2', 'Gabriel', STR_TO_DATE("03/11/1993", "%d/%m/%Y"), 'MASCULINO'
            	   ,'Rua das Andes', '1040', NULL, 45, 'devcase', '$2a$10$tZKICJm0BoVNk5TO.r8etOaua8gRjlyWUUldcpN3Rv1fxRslDXeoS');           


INSERT INTO telefone (telefone, tipo_contato, pessoa_id) VALUES ('(18) 3022-2222', 'COMERCIAL', 1);
INSERT INTO telefone (telefone, tipo_contato, pessoa_id) VALUES ('(18) 3223-3333', 'RESIDENCIAL', 1);
INSERT INTO telefone (telefone, tipo_contato, pessoa_id) VALUES ('(18)99799-9090', 'PARTICULAR', 1);

INSERT INTO telefone (telefone, tipo_contato, pessoa_id) VALUES ('(18) 3306-4608', 'COMERCIAL', 2);
INSERT INTO telefone (telefone, tipo_contato, pessoa_id) VALUES ('(18) 3223-5244', 'RESIDENCIAL', 2);
INSERT INTO telefone (telefone, tipo_contato, pessoa_id) VALUES ('(18)99788-8080', 'PARTICULAR', 2);

INSERT INTO email (email, tipo_contato, pessoa_id) VALUES ('jose@comercial.com.br', 'COMERCIAL', 1);
INSERT INTO email (email, tipo_contato, pessoa_id) VALUES ('jose@particular.com.br', 'PARTICULAR', 1);

INSERT INTO email (email, tipo_contato, pessoa_id) VALUES ('gabriel@comercial.com.br', 'COMERCIAL', 2);
INSERT INTO email (email, tipo_contato, pessoa_id) VALUES ('gabriel@particular.com.br', 'PARTICULAR', 2);