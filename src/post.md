# Criação de Dados (POST)

O foco aqui é o padrão MVC para manter o código organizado e fácil de mexer.

## Divisão do código

1. **Model**: Classe que representa o que estamos criando (ex: Equipe).
2. **Repository**: Onde o SQL acontece. Uso o INSERT INTO para mandar os dados para o MySQL de um jeito seguro.
3. **Service**: Onde ficam as validações. O sistema checa se você preencheu o nome antes de tentar salvar.
4. **Controller**: É o meio de campo. Recebe o que você digitou no terminal e manda para o serviço.

## Caminho da informação

Quando você cria uma equipe no menu:
- Você digita o nome e a descrição.
- O Java cria um objeto e manda para o Controller.
- O Controller passa para o Service (para validar).
- O Service manda para o Repository salvar no banco.
- O banco gera um ID sozinho (autoincrement) e pronto.

## Como testar
- Rode o `./mac_linux.sh` na pasta `src`.
- Escolha a opção 2 e preencha os dados.
- O banco vai salvar e você já consegue ver na lista logo em seguida.
