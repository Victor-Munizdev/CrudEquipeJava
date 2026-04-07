-- Comandos para configurar as permissões do XAMPP/MySQL
-- Rode estes comandos na aba "SQL" do seu phpMyAdmin (não precisa selecionar banco de dados)

-- 1. Cria o usuário root para conexões externas (necessário em alguns ambientes como Mac XAMPP-VM)
CREATE USER IF NOT EXISTS 'root'@'%' IDENTIFIED BY '';

-- 2. Dá permissão total para o root de qualquer lugar acessar qualquer banco
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;

-- 3. Atualiza as permissões no servidor
FLUSH PRIVILEGES;

-- 4. Garante que o banco de dados principal exista
CREATE DATABASE IF NOT EXISTS axio_db;
