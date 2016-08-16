/*-------------------------------- BORDA ----------------------------------------*/
CREATE TABLE borda
(
  id BIGINT NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(100) NOT NULL,
  codigo VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*-------------------------------------------------------------------------------*/
/*-------------------------------- TIPO COLECAO ---------------------------------*/
CREATE TABLE tipo_colecao
(
  id BIGINT NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(100) NOT NULL,
  codigo VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*-------------------------------------------------------------------------------*/
/*-------------------------------- ARTISTA --------------------------------------*/
CREATE TABLE artista
(
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*-------------------------------------------------------------------------------*/
/*-------------------------------- BLOCO ----------------------------------------*/
CREATE TABLE bloco
(
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
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
  id_bloco BIGINT NULL,
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
  PRIMARY KEY (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*-------------------------------------------------------------------------------*/
/*-------------------------------- TIPO CARTA -----------------------------------*/
CREATE TABLE tipo_carta
(
  id BIGINT NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(100) NOT NULL,
  codigo VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*-------------------------------------------------------------------------------*/
/*-------------------------------- COR ------------------------------------------*/
CREATE TABLE cor
(
  id BIGINT NOT NULL AUTO_INCREMENT,
  sigla VARCHAR(1) NOT NULL,
  descricao VARCHAR(100) NOT NULL,
  codigo VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*-------------------------------------------------------------------------------*/
/*-------------------------------- LAYOUT ---------------------------------------*/
CREATE TABLE layout
(
  id BIGINT NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(100) NOT NULL,
  codigo VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
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
  numero VARCHAR(100) NOT NULL,
  tipo VARCHAR(100) NOT NULL,
  poder VARCHAR(100) NULL,
  resistencia VARCHAR(100) NULL,
  lealdade VARCHAR(100) NULL,
  id_raridade BIGINT NOT NULL,
  id_colecao BIGINT NOT NULL,
  id_layout BIGINT NOT NULL,
  texto VARCHAR(4000) NULL,
  texto_original VARCHAR(4000) NULL,
  citacao VARCHAR(4000) NULL,
  id_artista BIGINT NOT NULL,
  json_id VARCHAR(100) NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_colecao) REFERENCES colecao(id) ON DELETE CASCADE,
  FOREIGN KEY (id_raridade) REFERENCES raridade(id) ON DELETE CASCADE,
  FOREIGN KEY (id_layout) REFERENCES layout(id) ON DELETE CASCADE,
  FOREIGN KEY (id_artista) REFERENCES artista(id) ON DELETE CASCADE
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*-------------------------------------------------------------------------------*/
/*-------------------------------- TIPOS CARTAS ---------------------------------*/
CREATE TABLE tipos_cartas
(
  id_carta BIGINT NOT NULL,
  id_tipo_carta BIGINT NOT NULL,
  PRIMARY KEY (id_carta, id_tipo_carta),
  FOREIGN KEY (id_carta) REFERENCES carta(id) ON DELETE CASCADE,
  FOREIGN KEY (id_tipo_carta) REFERENCES tipo_carta(id) ON DELETE CASCADE
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*-------------------------------------------------------------------------------*/
/*-------------------------------- CORES CARTAS ---------------------------------*/
CREATE TABLE cores_cartas
(
  id_carta BIGINT NOT NULL,
  id_cor BIGINT NOT NULL,
  PRIMARY KEY (id_carta, id_cor),
  FOREIGN KEY (id_carta) REFERENCES carta(id) ON DELETE CASCADE,
  FOREIGN KEY (id_cor) REFERENCES cor(id) ON DELETE CASCADE
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*-------------------------------------------------------------------------------*/
/*-------------------------------- IDENTIFICADORES CORES CARTAS -----------------*/
CREATE TABLE identificadores_cores_cartas
(
  id_carta BIGINT NOT NULL,
  id_cor BIGINT NOT NULL,
  PRIMARY KEY (id_carta, id_cor),
  FOREIGN KEY (id_carta) REFERENCES carta(id) ON DELETE CASCADE,
  FOREIGN KEY (id_cor) REFERENCES cor(id) ON DELETE CASCADE
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*-------------------------------------------------------------------------------*/