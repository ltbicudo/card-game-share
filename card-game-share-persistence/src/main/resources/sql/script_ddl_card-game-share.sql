/*-------------------------------- BORDA ----------------------------------------*/
CREATE TABLE borda
(
  id BIGINT NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(100) NOT NULL,
  codigo VARCHAR(100) NOT NULL,
  PRIMARY KEY(id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*-------------------------------------------------------------------------------*/
/*-------------------------------- TIPO COLECAO ---------------------------------*/
CREATE TABLE tipo_colecao
(
  id BIGINT NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(100) NOT NULL,
  codigo VARCHAR(100) NOT NULL,
  PRIMARY KEY(id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*-------------------------------------------------------------------------------*/
/*-------------------------------- BLOCO ----------------------------------------*/
CREATE TABLE bloco
(
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  PRIMARY KEY(id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*-------------------------------------------------------------------------------*/
/*-------------------------------- COLECAO --------------------------------------*/
CREATE TABLE colecao
(
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  codigo VARCHAR(3) NOT NULL,
  data_lancamento DATE NOT NULL,
  id_borda BIGINT NOT NULL,
  id_tipo_colecao BIGINT NOT NULL,
  id_bloco BIGINT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_borda) REFERENCES borda(id) ON DELETE CASCADE,
  FOREIGN KEY (id_tipo_colecao) REFERENCES tipo_colecao(id) ON DELETE CASCADE,
  FOREIGN KEY (id_bloco) REFERENCES bloco(id) ON DELETE CASCADE
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*-------------------------------------------------------------------------------*/
/*-------------------------------- TIPO CONTATO ---------------------------------*/
CREATE TABLE tipo_contato
(
  id BIGINT NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(256) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*-------------------------------------------------------------------------------*/
/*-------------------------------- CONTATO --------------------------------------*/
CREATE TABLE contato
(
  id BIGINT NOT NULL AUTO_INCREMENT,
  id_tipo_contato BIGINT NOT NULL,
  mensagem VARCHAR(4000) NOT NULL,
  nome VARCHAR(100) NULL,
  email VARCHAR(100) NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_tipo_contato) REFERENCES tipo_contato(id) ON DELETE CASCADE
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*-------------------------------------------------------------------------------*/
/*-------------------------------- RARIDADE -------------------------------------*/
CREATE TABLE raridade
(
  id BIGINT NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(100) NOT NULL,
  codigo VARCHAR(100) NOT NULL,
  PRIMARY KEY(id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*-------------------------------------------------------------------------------*/
/*-------------------------------- CARTA ----------------------------------------*/
CREATE TABLE carta
(
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  custo_mana_convertido BIGINT NULL,
  custo_mana VARCHAR(100) NULL,
  numero varchar(100) NOT NULL,
  id_raridade BIGINT NOT NULL,
  id_colecao BIGINT NOT NULL,
  citacao VARCHAR(4000) NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_colecao) REFERENCES colecao(id) ON DELETE CASCADE,
  FOREIGN KEY (id_raridade) REFERENCES raridade(id) ON DELETE CASCADE
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*-------------------------------------------------------------------------------*/