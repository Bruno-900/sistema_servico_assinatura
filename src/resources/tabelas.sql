CREATE DATABASE servico_assinatura;
USE servico_assinatura;

-- Tabela Administrador
CREATE TABLE administrador (
    id_administrador INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(200) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL
);

-- Tabela Plano
CREATE TABLE plano (
    id_plano INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10,2) NOT NULL
);

-- Tabela Assinante (agora com a coluna id_plano)
CREATE TABLE assinante (
    id_assinante INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    id_plano INT NULL, -- permite assinante sem plano
    FOREIGN KEY (id_plano) REFERENCES plano(id_plano)
);

USE servico_assinatura;

-- 1 administrador (login padrão)
INSERT INTO administrador (nome, email, senha) VALUES
('Administrador Master', 'admin@admin.com', '123');

-- 4 planos bem reais
INSERT INTO plano (nome, descricao, preco) VALUES
('Básico', 'Antivírus essencial + firewall', 29.90),
('Premium', 'Proteção total + VPN ilimitada + gerenciador de senhas', 59.90),
('Família', 'Até 5 dispositivos + controle parental', 89.90),
('Empresarial', 'Até 20 dispositivos + suporte prioritário', 199.90);

-- 7 assinantes variados (uns com plano, outros sem)
INSERT INTO assinante (nome, cpf, email, senha, id_plano) VALUES
('Maria Silva', '123.456.789-00', 'maria@gmail.com', '123456', 2),      -- Premium
('João Pedro', '987.654.321-00', 'joao@hotmail.com', 'senha123', 1),   -- Básico
('Ana Costa', '111.222.333-44', 'ana.costa@yahoo.com', 'ana2025', 3),   -- Família
('Carlos Souza', '555.666.777-88', 'carlos@souza.com', 'carlos123', 4), -- Empresarial
('Fernanda Lima', '999.888.777-66', 'fernanda.lima@gmail.com', 'fe123', NULL), -- Sem plano
('Lucas Oliveira', '444.333.222-11', 'lucas.oli@outlook.com', 'lucas99', 2),   -- Premium
('Patrícia Alves', '222.111.000-99', 'paty.alves@gmail.com', 'paty2024', 1);   -- Básico
