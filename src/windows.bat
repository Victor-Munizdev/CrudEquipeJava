@echo off
setlocal enabledelayedexpansion

:: Configurações
set BIN_DIR=bin
set MAIN_CLASS=test.select

:: Lógica de Limpeza
if "%1"=="clean" (
    echo Limpando diretorio de build (!BIN_DIR!)...
    if exist !BIN_DIR! rd /s /q !BIN_DIR!
    del /s /q *.class >nul 2>&1
    echo Projeto limpo!
    goto :eof
)

:: Busca o driver do PostgreSQL
set "DRIVER="
for %%f in (lib\postgresql-*.jar) do (
    set "DRIVER=%%f"
    goto :found
)
:found
if "%DRIVER%"=="" (
    set DRIVER=lib\mysql-connector-j.jar
)

echo ------------------------------------------
echo Iniciando Build e Execucao (AXIS)
echo ------------------------------------------

:: 1. Criar diretório de build
if not exist !BIN_DIR! mkdir !BIN_DIR!

echo Compilando classes em !BIN_DIR!...
dir /s /b *.java > sources.txt
javac -d !BIN_DIR! -cp ".;lib\*" @sources.txt
del sources.txt

if %ERRORLEVEL% EQU 0 (
    echo Compilacao concluida com sucesso!
    echo ------------------------------------------
    echo Rodando o projeto...
    echo.
    java -cp "!BIN_DIR!;.;lib\*" %MAIN_CLASS%
) else (
    echo ERRO: Falha na compilacao. Verifique o codigo.
)

echo.
echo ------------------------------------------
pause
