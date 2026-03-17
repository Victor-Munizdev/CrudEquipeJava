#!/bin/bash

# Configurações de Caminho
LIB_DIR="lib"
BIN_DIR="bin"
PSTGRS_DRIVER=$(ls $LIB_DIR/postgresql-*.jar 2>/dev/null | head -n 1)

# Caso não ache o postgres, tenta o mysql anterior
if [ -z "$PSTGRS_DRIVER" ]; then
    DRIVER="lib/mysql-connector-j.jar"
else
    DRIVER="$PSTGRS_DRIVER"
fi

MAIN_CLASS="test.select"

# Função para Limpeza
clean_project() {
    echo "🧹 Limpando diretório de build ($BIN_DIR)..."
    rm -rf "$BIN_DIR"
    find . -name "*.class" -type f -delete
    echo "✨ Projeto limpo!"
}

# Verifica se o comando é 'clean'
if [ "$1" == "clean" ]; then
    clean_project
    exit 0
fi

echo "------------------------------------------"
echo "🚀 Iniciando Build e Execução (AXIS)"
echo "------------------------------------------"

# 1. Preparando diretório de saída
if [ ! -d "$BIN_DIR" ]; then
    mkdir "$BIN_DIR"
fi

# 2. Compilando o projeto
echo "⚙️  Compilando classes em $BIN_DIR..."
# -d bin envia os .class para a pasta bin
javac -d "$BIN_DIR" -cp ".:$DRIVER" $(find . -name "*.java")

if [ $? -eq 0 ]; then
    echo "✅ Compilação concluída com sucesso!"
    echo "------------------------------------------"

    # 3. Executando
    # O classpath agora prioriza a pasta 'bin'
    echo "🏃 Rodando o projeto..."
    echo ""
    java -cp "$BIN_DIR:.:$DRIVER" $MAIN_CLASS
else
    echo ""
    echo "❌ ERRO: Falha na compilação. Verifique o código."
fi

echo ""
echo "------------------------------------------"
