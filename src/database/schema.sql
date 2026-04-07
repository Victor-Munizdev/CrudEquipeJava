-- Script para criação do banco de dados e tabelas do sistema AXIO
-- Para uso no MySQL (XAMPP/phpMyAdmin)

CREATE DATABASE IF NOT EXISTS axio_db;
USE axio_db;

-- Tabela: usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_completo VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    cargo ENUM('administrador', 'gerente', 'colaborador') NOT NULL DEFAULT 'colaborador',
    login VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL
);

-- Tabela: equipes
CREATE TABLE IF NOT EXISTS equipes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_equipe VARCHAR(100) NOT NULL,
    descricao TEXT
);

-- Tabela: equipe_membros (Relacionamento N:M entre usuarios e equipes)
CREATE TABLE IF NOT EXISTS equipe_membros (
    id_usuario INT NOT NULL,
    id_equipe INT NOT NULL,
    PRIMARY KEY (id_usuario, id_equipe),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (id_equipe) REFERENCES equipes(id) ON DELETE CASCADE
);

-- Tabela: projetos
CREATE TABLE IF NOT EXISTS projetos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_projeto VARCHAR(150) NOT NULL,
    descricao TEXT,
    data_inicio DATE NOT NULL,
    data_termino_prevista DATE,
    status VARCHAR(50) DEFAULT 'Em Planejamento',
    id_gerente_responsavel INT,
    FOREIGN KEY (id_gerente_responsavel) REFERENCES usuarios(id) ON DELETE SET NULL
);

-- Tabela: tarefas
CREATE TABLE IF NOT EXISTS tarefas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    descricao TEXT,
    data_vencimento DATE,
    status VARCHAR(50) DEFAULT 'Pendente',
    id_projeto INT NOT NULL,
    id_usuario_responsavel INT,
    FOREIGN KEY (id_projeto) REFERENCES projetos(id) ON DELETE CASCADE,
    FOREIGN KEY (id_usuario_responsavel) REFERENCES usuarios(id) ON DELETE SET NULL
);

-- Dados iniciais de teste
INSERT INTO usuarios (nome_completo, cpf, email, cargo, login, senha) VALUES 
('Administrador Geral', '111.111.111-11', 'admin@axio.com', 'administrador', 'admin', 'admin123'),
('Gerente de Projetos', '222.222.222-22', 'gerente@axio.com', 'gerente', 'gerente', 'gerente123');

INSERT INTO equipes (nome_equipe, descricao) VALUES 
('Desenvolvimento Backend', 'Equipe responsável pela lógica e banco de dados.'),
('Frontend & UX/UI', 'Equipe focada na interface e experiência do usuário.');

INSERT INTO equipe_membros (id_usuario, id_equipe) VALUES (1, 1), (2, 2);
