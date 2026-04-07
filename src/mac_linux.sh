#!/bin/bash

# Configurações de Caminho
LIB_DIR="lib"
BIN_DIR="bin"

# Pega todos os arquivos .jar da pasta lib e formata para o classpath (separados por :)
CLASSPATH_LIBS=$(ls "$LIB_DIR"/*.jar 2>/dev/null | tr '\n' ':')
CLASSPATH_LIBS=${CLASSPATH_LIBS%:} # Remove o último ':'

MAIN_CLASS="main.Main"

# Função para Limpeza
clean_project() {
    echo "Limpando diretório de build ($BIN_DIR)..."
    rm -rf "$BIN_DIR"
    find . -name "*.class" -type f -delete
    echo "Projeto limpo!"
}

# Verifica se o comando é 'clean'
if [ "$1" == "clean" ]; then
    clean_project
    exit 0
fi

echo "------------------------------------------"
echo "Iniciando Build e Execução (AXIS)"
echo "------------------------------------------"

# 1. Preparando diretório de saída
if [ ! -d "$BIN_DIR" ]; then
    mkdir "$BIN_DIR"
fi

# 2. Compilando o projeto
echo "Compilando classes em $BIN_DIR..."
# -d bin envia os .class para a pasta bin
javac -d "$BIN_DIR" -cp ".:$CLASSPATH_LIBS" $(find . -name "*.java")

if [ $? -eq 0 ]; then
    echo "Compilação concluída com sucesso!"
    echo "------------------------------------------"

    # 3. Executando
    # O classpath agora prioriza a pasta 'bin'
    echo "Rodando o projeto..."
    echo ""
    java -cp "$BIN_DIR:.:$CLASSPATH_LIBS" $MAIN_CLASS
else
    echo ""
    echo "ERRO: Falha na compilação. Verifique o código."
fi

echo ""
echo "------------------------------------------"
