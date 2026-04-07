# Como conectar ao banco

Configuração do MySQL local via XAMPP.

## 1. Configurar o MySQL (XAMPP)

1. Ligue o XAMPP e veja se o Apache e o MySQL estão rodando.
2. Abre o seu phpMyAdmin pelo navegador (geralmente localhost/phpmyadmin).
3. Cria um banco novo chamado `axio_db`.
4. Importa o arquivo `src/database/schema.sql` que está aqui no projeto para criar as tabelas.

## 2. Configurando o .env na raiz (pasta /src)

No seu arquivo `.env`, deixe os dados de conexão assim (estilo XAMPP):

```env
DB_HOST=localhost
DB_PORT=3306
DB_NOME=axio_db
DB_USUARIO=root
DB_SENHA=
```

## 3. O Driver do Banco

A gente usa o driver `mysql-connector-j.jar` que já deixei na pasta `src/lib/`. Se precisar trocar, é só baixar o do MySQL Connector/J e jogar lá.

## 4. Resolvendo Erros de Permissão (Necessário em alguns computadores)

Se o Java der um erro de `Access denied for user 'root'`, rode o conteúdo do arquivo abaixo na aba SQL do seu phpMyAdmin:

- Arquivo: `src/database/configura_permissao.sql`

Isso vai garantir que o usuário `root` tenha permissão total para rodar os comandos a partir do seu Java independentemente do sistema operacional.
