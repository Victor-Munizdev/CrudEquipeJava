# Listar Dados (GET)

A listagem usa o padrão MVC junto com o JDBC para ler do banco local.

## Como o código está organizado

O código está separado em pastas específicas por responsabilidade:

1. **Model**: As classes que representam o mundo real (ex: Usuário, Equipe). Atributos privados e métodos get/set.
2. **Repository**: Onde o SQL acontece. Faz o SELECT e transforma o resultado em Objetos Java.
3. **Service**: Tem as regras de negócio. Atualmente ela só intermedia a chamada, mas está lá para receber validações.
4. **Controller**: O ponto de entrada. Tudo que a tela ou terminal pedir, passa por aqui.

## O Fluxo da Operação

Ao rodar o projeto:
- O Controller pede ao Service para listar as equipes.
- O Service repassa para o Repository.
- O Repository executa o SELECT no MySQL.
- O banco devolve os dados brutos e o Java transforma em uma lista de objetos.
- O terminal (View) recebe essa lista e imprime os dados na tela.

## Como testar
1. Certifique-se de que o MySQL no XAMPP está ligado.
2. Rode o arquivo `./mac_linux.sh` dentro da pasta `src`.
3. Escolha a opção 1 e o terminal vai mostrar todos os registros do seu banco.

Se precisar mudar o banco de MySQL para qualquer outro, a gente só altera o Repository e o resto do sistema continua funcionando igual. Isso é o que chamamos de baixo acoplamento.
