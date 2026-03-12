# Guia: Como conectar ao banco (MySQL)

Para fazer o backend Java falar com o banco de dados do XAMPP, siga estes passos:

---

## 1. Scripts SQL
Os arquivos de criação das tabelas estão na pasta `src/backend/database/`.
- Importe o `database.sql` para criar o banco de dados `axis`.
- Em seguida, rode o `usuarios.sql` e o `equipe.sql`.

## 2. Configurações (.env)
O arquivo de configuração está em `src/backend/.env`. Crie ele e verifique se as credenciais batem com o seu XAMPP:

```env
DB_HOST=localhost
DB_PORT=3306
DB_NOME=axis
DB_USUARIO=root
DB_SENHA=
```

## 3. O Driver JDBC
Para a conexão funcionar, você precisa do driver do MySQL. Ele já está incluído no projeto dentro da pasta:
`src/backend/lib/mysql-connector-j.jar`

Se o professor perguntar: este arquivo é o "tradutor" que o Java usa para enviar comandos SQL para o servidor MySQL.

## 4. Usando no Código
Sempre que precisar buscar algo no banco dentro de um Service ou Repository, chame a nossa classe de conexão assim:

```java
import database.ConexaoBanco;
import java.sql.Connection;

Connection conexao = ConexaoBanco.getConexao();
// Daqui pra frente é só usar o 'conexao'
```
