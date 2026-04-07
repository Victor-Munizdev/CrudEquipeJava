-- Script para PostgreSQL (Supabase)

CREATE TABLE IF NOT EXISTS equipes (
    id SERIAL PRIMARY KEY,
    nome_equipe VARCHAR(100),
    descricao TEXT
);

CREATE TABLE IF NOT EXISTS equipe_membros (
    id_equipe INT REFERENCES equipes(id) ON DELETE CASCADE,
    id_usuario INT REFERENCES usuarios(id) ON DELETE CASCADE,
    PRIMARY KEY (id_equipe, id_usuario)
);

CREATE TABLE IF NOT EXISTS projeto_equipes (
    id_projeto INT REFERENCES projetos(id) ON DELETE CASCADE,
    id_equipe INT REFERENCES equipes(id) ON DELETE CASCADE,
    PRIMARY KEY (id_projeto, id_equipe)
);