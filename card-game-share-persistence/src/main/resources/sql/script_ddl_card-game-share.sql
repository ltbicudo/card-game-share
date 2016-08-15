-- COLECAO
CREATE TABLE colecao
(
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  codigo VARCHAR(3) NOT NULL,
  data_lancamento DATE NOT NULL,
  PRIMARY KEY (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- TIPO CONTATO
CREATE TABLE tipo_contato
(
  id BIGINT NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(256) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- CONTATO
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