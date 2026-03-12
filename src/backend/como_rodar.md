# Guia: Como rodar o projeto

Este projeto usa scripts que automatizam o build, então você não precisa ficar digitando `javac` e `java` o tempo todo no terminal.

---

## Passo a passo (Mac ou Linux)

1. **Acesse a pasta do backend**:

   ```bash
   cd src/backend
   ```

2. **Dê permissão ao script** (só precisa fazer uma vez):

   ```bash
   chmod +x mac_linux.sh
   ```

3. **Rode o projeto**:

   ```bash
   ./mac_linux.sh
   ```

---

## Passo a passo (Windows)

1. **Abra o CMD ou PowerShell** na pasta `src/backend`.
2. **Rode o arquivo**:

   ```cmd
   windows.bat
   ```

---

## O que os scripts fazem?

- Limpam os arquivos `.class` antigos.
- Compilam o código Java incluindo o driver do banco.
- Executam o arquivo `select.java` (na pasta `test`) para mostrar o resultado da consulta.
