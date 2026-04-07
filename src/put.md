# Edição de Dados (PUT)

A gente atualiza o que já existe no banco informando o registro pelo seu ID.

## Estrutura da Edição

1. **Model**: Carrega as informações novas para salvar.
2. **Repository**: Faz o UPDATE no banco filtrando pelo ID que você mandou. Isso garante que a gente só mude o registro certo.
3. **Service**: Só checa se o ID é válido antes de deixar seguir para o banco.
4. **Controller**: Recebe os dados novos do terminal e coordena a atualização.

## Fluxo da Edição

- Você escolhe editar no menu.
- Digita o ID da equipe que quer mexer.
- Informa o novo nome e nova descrição.
- O Java atualiza o registro no MySQL por trás das cenas.

## Como testar no terminal
- Rode o script `./mac_linux.sh` na pasta `src`.
- Escolha a opção 3.
- Coloque o ID da equipe que você quer mudar e as novas informações.
- Verifique na listagem (opção 1) que os dados mudaram no banco.
