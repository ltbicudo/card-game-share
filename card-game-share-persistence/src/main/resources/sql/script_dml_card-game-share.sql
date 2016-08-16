/*-------------------------------- Tipo Contato ---------------------------------*/
INSERT INTO tipo_contato (id, descricao) VALUES (1, 'Dúvida');
INSERT INTO tipo_contato (id, descricao) VALUES (2, 'Sugestão');
INSERT INTO tipo_contato (id, descricao) VALUES (3, 'Reclamação');
INSERT INTO tipo_contato (id, descricao) VALUES (4, 'Outros');
/*-------------------------------------------------------------------------------*/
/*-------------------------------- BORDA ----------------------------------------*/
INSERT INTO borda (descricao, codigo) VALUES ('Preta', 'black');
/*-------------------------------------------------------------------------------*/
/*-------------------------------- RARIDADE -------------------------------------*/
INSERT INTO raridade (descricao, codigo) VALUES ('Comum', 'common');
INSERT INTO raridade (descricao, codigo) VALUES ('Incomum', 'uncommon');
INSERT INTO raridade (descricao, codigo) VALUES ('Rara', 'rare');
INSERT INTO raridade (descricao, codigo) VALUES ('Lendária', 'mythic rare');
INSERT INTO raridade (descricao, codigo) VALUES ('Terreno Básico', 'basic land');
/*-------------------------------------------------------------------------------*/
/*-------------------------------- TIPO COLECAO ---------------------------------*/
INSERT INTO tipo_colecao (descricao, codigo) VALUES ('Expansão', 'expansion');
INSERT INTO tipo_colecao (descricao, codigo) VALUES ('Reimpressão', 'reprint');
/*-------------------------------------------------------------------------------*/
/*-------------------------------- TIPO CARTA -----------------------------------*/
INSERT INTO tipo_carta (descricao, codigo) VALUES ('Encantamento', 'enchantment');
INSERT INTO tipo_carta (descricao, codigo) VALUES ('Criatura', 'creature');
INSERT INTO tipo_carta (descricao, codigo) VALUES ('Feitiço', 'sorcery');
INSERT INTO tipo_carta (descricao, codigo) VALUES ('Mágica Instantânea', 'instant');
INSERT INTO tipo_carta (descricao, codigo) VALUES ('Planeswalker', 'planeswalker');
INSERT INTO tipo_carta (descricao, codigo) VALUES ('Artefato', 'artifact');
INSERT INTO tipo_carta (descricao, codigo) VALUES ('Terreno', 'land');
INSERT INTO tipo_carta (descricao, codigo) VALUES ('Tribal', 'tribal');
/*-------------------------------------------------------------------------------*/
/*-------------------------------- COR ------------------------------------------*/
INSERT INTO cor (sigla, descricao, codigo) VALUES ('W', 'Branco', 'white');
INSERT INTO cor (sigla, descricao, codigo) VALUES ('R', 'Vermelho', 'red');
INSERT INTO cor (sigla, descricao, codigo) VALUES ('B', 'Preto', 'black');
INSERT INTO cor (sigla, descricao, codigo) VALUES ('U', 'Azul', 'blue');
INSERT INTO cor (sigla, descricao, codigo) VALUES ('G', 'Verde', 'green');
/*-------------------------------------------------------------------------------*/
/*-------------------------------- LAYOUT ---------------------------------------*/
INSERT INTO layout (descricao, codigo) VALUES ('Normal', 'normal');
INSERT INTO layout (descricao, codigo) VALUES ('Face Dupla', 'double-faced');
/*-------------------------------------------------------------------------------*/