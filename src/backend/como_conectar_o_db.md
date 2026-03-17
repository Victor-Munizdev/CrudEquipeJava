# Guia: Como conectar ao Supabase (PostgreSQL)

## 1. Configurar o Supabase

1. Crie um projeto no [Supabase](https://supabase.com/).
2. Vá em **SQL Editor** e cole o conteúdo dos arquivos na pasta `src/backend/database/` (na ordem: `usuarios.sql`, `projetos.sql`, `equipe.sql`, `tarefas.sql`).
3. Vá em **Project Settings > Database** e pegue os dados de conexão (Host, Port, User, Password).

## 2. Configurações (.env)

## 3. O Driver JDBC (PostgreSQL)

A conexão usa o driver do PostgreSQL na pasta `lib`:

## 4. Mudanças no Código

A classe `ConexaoBanco.java` já foi atualizada para usar:

- Driver: `org.postgresql.Driver`
- Prefixo: `jdbc:postgresql://`
