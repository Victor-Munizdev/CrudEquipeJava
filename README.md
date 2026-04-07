# Projeto AXIO - Gestão de Equipes

Sistema de CRUD desenvolvido em Java para a gestão de equipes e usuários, aplicando os conceitos de Orientação a Objetos e a arquitetura MVC (Model-View-Controller) integrada ao MySQL.

## Estrutura do Projeto (MVC)

O código está organizado em camadas para facilitar a manutenção:

- **Model (`src/model/`)**: Classes que representam as entidades do sistema.
- **View**: Menu interativo via terminal (`src/main/Main.java`).
- **Controller (`src/controller/`)**: Faz a ponte entre a interface e os dados.
- **Service (`src/service/`)**: Camada de regras de negócio e validações.
- **Repository (`src/repository/`)**: Lógica de persistência e acesso ao banco de dados.
- **Lib (`src/lib/`)**: Contém o driver JDBC do MySQL.

## Requisitos para rodar

1. **Java JDK 17** ou superior instalado.
2. **XAMPP** ou servidor MySQL rodando na porta 3306.
3. Banco de dados criado com o nome `axio_db`.

## Documentação Técnica

Para facilitar o entendimento de cada parte do sistema, separamos os guias abaixo:

1. [**Configuração do Banco de Dados**](src/como_conectar_o_db.md)
2. [**Como Rodar o Projeto**](src/como_rodar.md)
3. [**Entendendo as operações (Listar/GET)**](src/get.md)
4. [**Entendendo as operações (Criar/POST)**](src/post.md)
5. [**Entendendo as operações (Editar/PUT)**](src/put.md)
6. [**Entendendo as operações (Deletar/DELETE)**](src/delete.md)

## Desenvolvedores

- [Victor Rego Muniz](https://github.com/Victor-Munizdev)
- [Gabriel Lourenço Datilo](https://github.com/JAPAxx)
- O outro integrante do projeto teria sido a Talísia Vitória Santos Matos Maia, mas a faculdade nivelou o curso dela e ela já tinha feito esta matéria, então ela não pode participar do projeto.
