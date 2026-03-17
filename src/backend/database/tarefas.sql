-- Script para PostgreSQL (Supabase)

DO $$ BEGIN
    CREATE TYPE status_tarefa AS ENUM ('pendente', 'em_andamento', 'concluida');
EXCEPTION
    WHEN duplicate_object THEN null;
END $$;

CREATE TABLE IF NOT EXISTS tarefas (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descricao TEXT,
    data_vencimento DATE,
    status status_tarefa DEFAULT 'pendente',
    id_projeto INT REFERENCES projetos(id) ON DELETE CASCADE,
    id_usuario_responsavel INT REFERENCES usuarios(id) ON DELETE SET NULL
);
