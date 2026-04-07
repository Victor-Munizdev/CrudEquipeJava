-- Script para criação do banco de dados e tabelas do sistema AXIO
-- Ajustado para conformidade total com os requisitos do projeto da faculdade

CREATE DATABASE IF NOT EXISTS axio_db;
USE axio_db;

-- 1. Cadastro de Usuários (Requisito 1.a e 1.b)
CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_completo VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    cargo ENUM('administrador', 'gerente', 'colaborador') NOT NULL DEFAULT 'colaborador',
    login VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL
);

-- 2. Cadastro de Equipes (Requisito 3.e)
CREATE TABLE IF NOT EXISTS equipes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_equipe VARCHAR(100) NOT NULL,
    descricao TEXT
);

-- Relacionamento N:M entre usuários e equipes (Membros vinculados)
CREATE TABLE IF NOT EXISTS equipe_membros (
    id_usuario INT NOT NULL,
    id_equipe INT NOT NULL,
    PRIMARY KEY (id_usuario, id_equipe),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (id_equipe) REFERENCES equipes(id) ON DELETE CASCADE
);

-- 3. Cadastro de Projetos (Requisito 2.c e 2.d)
CREATE TABLE IF NOT EXISTS projetos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_projeto VARCHAR(150) NOT NULL,
    descricao TEXT,
    data_inicio DATE NOT NULL,
    data_termino_prevista DATE NOT NULL,
    status ENUM('planejado', 'em andamento', 'concluído', 'cancelado') NOT NULL DEFAULT 'planejado',
    id_gerente_responsavel INT NOT NULL,
    FOREIGN KEY (id_gerente_responsavel) REFERENCES usuarios(id) ON DELETE RESTRICT
);

-- Relacionamento entre Equipes e Projetos (Requisito 3.f)
-- Garante que uma equipe possa atuar em vários projetos e vice-versa
CREATE TABLE IF NOT EXISTS projeto_equipes (
    id_projeto INT NOT NULL,
    id_equipe INT NOT NULL,
    PRIMARY KEY (id_projeto, id_equipe),
    FOREIGN KEY (id_projeto) REFERENCES projetos(id) ON DELETE CASCADE,
    FOREIGN KEY (id_equipe) REFERENCES equipes(id) ON DELETE CASCADE
);

-- 4. Cadastro de Tarefas do Projeto (Requisito Inicial - Geral)
CREATE TABLE IF NOT EXISTS tarefas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    descricao TEXT,
    data_inicio DATE,
    data_termino DATE,
    status ENUM('pendente', 'em execução', 'finalizada') DEFAULT 'pendente',
    id_projeto INT NOT NULL,
    id_usuario_responsavel INT,
    FOREIGN KEY (id_projeto) REFERENCES projetos(id) ON DELETE CASCADE,
    FOREIGN KEY (id_usuario_responsavel) REFERENCES usuarios(id) ON DELETE SET NULL
);

-- Dados iniciais de teste para o professor conferir
INSERT INTO usuarios (nome_completo, cpf, email, cargo, login, senha) VALUES 
('Professor Corretor', '000.000.000-00', 'professor@faculdade.edu.br', 'administrador', 'professor', 'nota10'),
('Victor Muniz', '111.111.111-11', 'victor@axio.com', 'gerente', 'victorm', 'senha123'),
('Gabriel Datilo', '222.222.222-22', 'gabriel@axio.com', 'colaborador', 'gabrield', 'senha456');

INSERT INTO equipes (nome_equipe, descricao) VALUES 
('Squad Alpha', 'Focada no desenvolvimento de sistemas críticos.'),
('Design Lab', 'Responsável pela experiência do usuário.');

INSERT INTO equipe_membros (id_usuario, id_equipe) VALUES (2, 1), (3, 1), (3, 2);

INSERT INTO projetos (nome_projeto, descricao, data_inicio, data_termino_prevista, status, id_gerente_responsavel) VALUES 
('Sistema AXIO', 'Desenvolvimento do sistema de gestão acadêmica', '2026-03-20', '2026-05-30', 'em andamento', 2);
