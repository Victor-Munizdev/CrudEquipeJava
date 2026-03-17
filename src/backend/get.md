# Documentação da Operação GET (Leitura de Dados)

Este documento explica como a operação **GET** foi implementada no sistema AXIS utilizando os princípios de **Programação Orientada a Objetos (POO)** e a arquitetura **MVC (Model-View-Controller)**.

---

## 🏗️ Arquitetura do Sistema

Dividimos a responsabilidade de leitura de dados em 4 camadas principais para garantir organização e facilidade de manutenção:

### 1. Camada de Modelo (Model)
Contém as classes que representam os objetos do mundo real.
- **Entidades**: `Usuario`, `Equipe`, `Projeto`, `Tarefa`.
- **Destaque**: Uso de `Enum Cargo` para garantir que o tipo de usuário seja sempre válido.
- **POO**: Atributos privados com métodos `get` e `set` (Encapsulamento).

### 2. Camada de Repositório (Repository)
É a única camada que "fala" com o Banco de Dados através do JDBC.
- **Lógica**: Contém o SQL (`SELECT * FROM ...`) e transforma o `ResultSet` do banco em **Objetos Java**.
- **Segurança**: Uso de `PreparedStatement` para evitar ataques de SQL Injection.

### 3. Camada de Serviço (Service)
Contém as regras de negócio.
- **Função**: Atualmente, ela intermedia a chamada do Controller para o Repository, mas está pronta para receber validações (ex: verificar se um usuário tem permissão para ver certos dados).

### 4. Camada de Controller (Controller)
O ponto de entrada para as requisições.
- **Função**: Orquestra as ações. Se amanhã tivéssemos uma API Web ou uma tela Swing, elas chamariam este Controller para buscar os dados.

---

## 🔍 Operações Realizadas

Para cada entidade (`Usuario`, `Equipe`, `Projeto`, `Tarefa`), implementamos:

1.  **Listagem Geral (`listarTodos`)**:
    *   Busca todos os registros da tabela correspondente.
    *   Retorna uma `List<Objeto>` (ex: `List<Usuario>`).
2.  **Busca por ID (`buscarPorId`)**:
    *   Busca um único registro utilizando a Chave Primária.
    *   Retorna o objeto populado ou `null` caso não encontre.

---

## 🚦 Fluxo da Informação

Ao rodar o teste em `select.java`, o caminho percorrido pela informação é:

1.  **View (select.java)** chama `UsuarioController.listar()`.
2.  **Controller** solicita ao `UsuarioService`.
3.  **Service** aciona o `UsuarioRepository`.
4.  **Repository** executa o SQL no **MySQL** e recebe dados brutos.
5.  **Repository** "instancia" objetos `Usuario` e os coloca em uma lista.
6.  A lista volta subindo até chegar no **select.java**, que imprime os dados na tela.

---

## 🧪 Como Testar

O console de teste foi atualizado para demonstrar todo esse ecossistema:

1. Certifique-se de que o **XAMPP (MySQL)** está rodando.
2. Verifique se as tabelas foram criadas usando os scripts em `src/backend/database/`.
3. Execute o arquivo `src/backend/test/select.java`.

Você verá no terminal a listagem organizada de todas as categorias de dados do sistema Axis.

---

> [!TIP]
> **Por que fazer assim?**
> Se precisarmos mudar o banco de dados de MySQL para PostgreSQL, alteramos **apenas** a camada de Repository. O resto do sistema nem perceberia a mudança. Isso é **Baixo Acoplamento**.
