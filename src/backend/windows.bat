@echo off
set DRIVER=lib\mysql-connector-j.jar
set MAIN_CLASS=select

echo ------------------------------------------
echo ^🚀 Iniciando Build e Execucao (AXIS)
echo ------------------------------------------

echo ^🧹 Limpando arquivos temporarios...
del /s /q *.class >nul 2>&1

echo ^⚙️  Compilando classes...
javac database\ConexaoBanco.java test\select.java

if %ERRORLEVEL% EQU 0 (
    echo ^✅ Compilacao concluida com sucesso!
    echo ------------------------------------------
    echo ^🏃 Rodando o projeto...
    echo.
    java -cp .;test;%DRIVER% %MAIN_CLASS%
) else (
    echo ^❌ ERRO: Falha na compilacao. Verifique o codigo.
)

echo.
echo ------------------------------------------
pause
