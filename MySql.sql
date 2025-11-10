CREATE DATABASE IF NOT EXISTS inovalocal_db
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_0900_ai_ci;
USE inovalocal_db;

DROP TABLE IF EXISTS deslocamento;
DROP TABLE IF EXISTS recompensa;
DROP TABLE IF EXISTS usuario;

CREATE TABLE usuario (
  id       BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  nome     VARCHAR(255)    NOT NULL,
  email    VARCHAR(255)    NOT NULL,
  senha    VARCHAR(255)    NOT NULL,
  pontos   INT             NOT NULL DEFAULT 0,
  PRIMARY KEY (id),
  UNIQUE (email)
) ENGINE=InnoDB;

CREATE TABLE recompensa (
  id            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  titulo        VARCHAR(255)    NOT NULL,
  custo_pontos  INT             NOT NULL,
  descricao     TEXT            NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE deslocamento (
  id             BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  usuario_id     BIGINT UNSIGNED NOT NULL,
  tipo           VARCHAR(20)     NOT NULL,
  distancia      DECIMAL(8,2)    NOT NULL,
  pontos_ganhos  INT             NOT NULL,
  data_registro  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (usuario_id) REFERENCES usuario(id)
) ENGINE=InnoDB;

INSERT INTO usuario (nome, email, senha, pontos)
VALUES ('Leandro', 'leandro@example.com', '123456', 0);

INSERT INTO recompensa (titulo, custo_pontos, descricao) VALUES
('Desconto no Passe', 100, 'R$ 10 de desconto no transporte'),
('Café da Semana',     60, 'Um café grátis na rede parceira'),
('Voucher Bike',      150, 'Voucher para manutenção básica de bike');

INSERT INTO deslocamento (usuario_id, tipo, distancia, pontos_ganhos)
VALUES (1, 'BICICLETA', 2.50, 10);
