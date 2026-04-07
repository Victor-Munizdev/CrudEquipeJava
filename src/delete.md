# Deletar Dados (DELETE)

Aqui mostro como a remoção definitiva acontece no banco MySQL. 

## Como o Delete é feito

1. **Repository**: Faz o DELETE no banco filtrando o registro pelo seu ID.
2. **Service**: Só deixa deletar se o ID existir e for válido.
3. **Controller**: Coordena o processo de exclusão vindo do menu.

## Etapas ao deletar no menu interativo

- Você escolhe deletar (opção 4).
- Digita o ID da equipe que quer remover.
- O sistema pede para você confirmar (S ou N) para não deletar coisa por engano.
- Confirmado, o Java manda deletar o registro no banco.

## Como testar
- Roda o `./mac_linux.sh` no seu terminal na pasta `src`.
- Escolha a opção 4.
- Digite o ID desejado e confirma o pedido.
- Na lista (opção 1), você já vê que o registro foi removido.
