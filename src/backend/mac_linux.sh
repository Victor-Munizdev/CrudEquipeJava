#!/bin/bash

# Configurações
# O arquivo está na pasta test/ mas sem pacote
DRIVER="lib/mysql-connector-j.jar"
MAIN_CLASS="select"

echo "------------------------------------------"
echo "🚀 Iniciando Build e Execução (AXIS)"
echo "------------------------------------------"

# 1. Limpando classes antigas
echo "🧹 Limpando arquivos temporários..."
find . -name "*.class" -type f -delete

# 2. Compilando o projeto
echo "⚙️  Compilando classes..."
javac database/ConexaoBanco.java test/select.java

if [ $? -eq 0 ]; then
    echo "✅ Compilação concluída com sucesso!"
    echo "------------------------------------------"

    # 3. Executando
    # Incluímos a pasta 'test' no classpath para ele achar a classe select
    echo "🏃 Rodando o projeto..."
    echo ""
    java -cp .:test:./$DRIVER $MAIN_CLASS
else
    echo "❌ ERRO: Falha na compilação. Verifique o código."
fi

echo ""
echo "------------------------------------------"
