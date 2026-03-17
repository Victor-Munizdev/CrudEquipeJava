-- Script para PostgreSQL (Supabase)

DO $$ BEGIN
    CREATE TYPE status_projeto AS ENUM ('planejado', 'em_andamento', 'concluido', 'cancelado');
EXCEPTION
    WHEN duplicate_object THEN null;
END $$;

CREATE TABLE IF NOT EXISTS projetos (
    id SERIAL PRIMARY KEY,
    nome_projeto VARCHAR(100),
    descricao TEXT,
    data_inicio DATE,
    data_termino_prevista DATE,
    status status_projeto DEFAULT 'planejado',
    id_gerente_responsavel INT REFERENCES usuarios(id)
);