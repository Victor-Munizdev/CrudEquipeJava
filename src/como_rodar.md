# Como rodar o projeto

O projeto já tem scripts que fazem o build automático, para você não precisar digitar comandos no terminal toda vez.

## Rodando no Mac ou Linux

1. Abre o terminal na pasta `src`.
2. Se for a primeira vez, dá permissão para o script rodar:
   ```bash
   chmod +x mac_linux.sh
   ```
3. Agora é só rodar o projeto:
   ```bash
   ./mac_linux.sh
   ```

## Rodando no Windows

1. Abre o CMD ou PowerShell na pasta `src`.
2. Digita o nome do arquivo e aperta enter:
   ```cmd
   windows.bat
   ```

## O que esses scripts fazem?

- Limpam arquivos de compilações antigas.
- Compilam o projeto inteiro com os drivers do banco.
- Abrem o menu interativo para você gerenciar as equipes direto pelo terminal.
