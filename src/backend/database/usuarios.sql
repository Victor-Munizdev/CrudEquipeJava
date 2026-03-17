-- Script para PostgreSQL (Supabase)

-- Criar tipos ENUM se necessário (PostgreSQL requer criação prévia)
DO $$ BEGIN
    CREATE TYPE cargo_tipo AS ENUM ('administrador', 'gerente', 'colaborador');
EXCEPTION
    WHEN duplicate_object THEN null;
END $$;

CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    nome_completo VARCHAR(100),
    cpf CHAR(11) UNIQUE,
    email VARCHAR(100) UNIQUE,
    cargo cargo_tipo DEFAULT 'colaborador',
    login VARCHAR(30) UNIQUE,
    senha VARCHAR(255)
);