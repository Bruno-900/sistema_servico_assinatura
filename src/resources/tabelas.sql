CREATE DATABASE servico_assinatura;
USE servico_assinatura;

-- -----------------------------------------------------
-- Tabela Assinante
-- -----------------------------------------------------
CREATE TABLE assinante (
  id_assinante INT PRIMARY KEY AUTO_INCREMENT,
  nome         VARCHAR(45) NOT NULL,
  cpf          VARCHAR(45) NOT NULL UNIQUE,
  email        VARCHAR(45) NOT NULL,
  senha        VARCHAR(45) NOT NULL
);

-- -----------------------------------------------------
-- Tabela Plano
-- -----------------------------------------------------
CREATE TABLE plano (
  id_plano   INT PRIMARY KEY AUTO_INCREMENT,
  nome       VARCHAR(45) NOT NULL,
  descricao  TEXT NOT NULL,
  preco      DECIMAL(10,2) NOT NULL
);

-- -----------------------------------------------------
-- Tabela Avaliacao
-- -----------------------------------------------------
CREATE TABLE avaliacao (
  id_avaliacao    INT PRIMARY KEY AUTO_INCREMENT,
  nota            INT NULL,
  comentario      TEXT NULL,
  data            VARCHAR(45) NOT NULL,
  fk_id_assinante INT,
  CONSTRAINT fk_avaliacao_assinante FOREIGN KEY (fk_id_assinante) REFERENCES assinante(id_assinante)
);

-- -----------------------------------------------------
-- Tabela Assinatura
-- -----------------------------------------------------
CREATE TABLE assinatura (
  id_assinatura       INT PRIMARY KEY AUTO_INCREMENT,
  status              ENUM('ativa', 'cancelada', 'expirada') DEFAULT 'ativa',
  data_inicio         DATE NOT NULL,
  data_fim            DATE NULL,
  fk_id_assinante     INT NOT NULL,
  fk_id_plano         INT NOT NULL,
  CONSTRAINT fk_assinatura_assinante FOREIGN KEY (fk_id_assinante) REFERENCES assinante(id_assinante),
  CONSTRAINT fk_assinatura_plano FOREIGN KEY (fk_id_plano) REFERENCES plano(id_plano)
);

-- -----------------------------------------------------
-- Tabela Metodo_Pagamento
-- -----------------------------------------------------
CREATE TABLE metodo_pagamento (
  id_metodo_pagamento INT PRIMARY KEY AUTO_INCREMENT,
  detalhes VARCHAR(100) NOT NULL,
  taxa DECIMAL(10,2) DEFAULT 0.00,
  ativo BOOLEAN DEFAULT TRUE,
  prazo_compensacao INT DEFAULT 0,
  fk_id_assinante     INT NOT NULL,
  CONSTRAINT fk_metodo_pagamento_assinante FOREIGN KEY (fk_id_assinante) REFERENCES assinante(id_assinante)
);

-- -----------------------------------------------------
-- Tabela Pagamento
-- -----------------------------------------------------
CREATE TABLE pagamento (
  id_pagamento              INT PRIMARY KEY AUTO_INCREMENT,
  valor DECIMAL(10,2) NOT NULL,
  status ENUM('PENDENTE', 'PROCESSANDO', 'CONCLUIDO', 'FALHOU', 'ESTORNADO') NOT NULL,
  data_pagamento DATETIME NOT NULL,
  id_metodo_agamento INT,
  fk_id_metodo_pagamento    INT NOT NULL,
  fk_id_assinatura          INT NOT NULL,
  CONSTRAINT fk_pagamento_metodo FOREIGN KEY (fk_id_metodo_pagamento) REFERENCES metodo_pagamento(id_metodo_pagamento),
  CONSTRAINT fk_pagamento_assinatura FOREIGN KEY (fk_id_assinatura) REFERENCES assinatura(id_assinatura)
);

-- -----------------------------------------------------
-- Tabela Administrador
-- -----------------------------------------------------
CREATE TABLE administrador (
  id_administrador INT PRIMARY KEY AUTO_INCREMENT,
  nome             VARCHAR(200) NOT NULL,
  senha            VARCHAR(45) NOT NULL,
  email            VARCHAR(45) NOT NULL
);
