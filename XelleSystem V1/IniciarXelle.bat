@echo off
TITLE Xelle System - Launcher
COLOR 0A

:: --- CONFIGURACIÓN ---
:: 1. Ruta del proyecto
SET "PROJECT_DIR=C:\Users\WINDOWS\Desktop\XelleSystem"

:: 2. URL corregida: Probamos ir a la raíz (/) o a /login
:: Intenta primero con esta (Raíz):
SET "URL_LOGIN=http://localhost:8081/"
:: Si la de arriba no funciona, cambia a: http://localhost:8081/login

SET "PORT=8081"
:: ---------------------

ECHO ---------------------------------------
ECHO      INICIANDO XELLE SYSTEM
ECHO ---------------------------------------

:: 1. Ir a la carpeta del proyecto
CD /D "%PROJECT_DIR%"

:: 2. Arrancar el servidor Backend en segundo plano (Minimizado)
ECHO [1/3] Iniciando servidor Spring Boot (Minimizado)...
start /min "XelleBackend - NO CERRAR" cmd /k "mvnw spring-boot:run"

:: 3. Bucle de espera inteligente (Ping al puerto)
ECHO [2/3] Esperando a que el sistema cargue...
:WAIT_LOOP
timeout /t 2 /nobreak >nul
netstat -an | find ":%PORT%" | find "LISTENING" >nul
if %errorlevel% neq 0 (
    goto WAIT_LOOP
)

:: 4. Abrir el navegador
ECHO [3/3] Servidor listo. Abriendo navegador...
start %URL_LOGIN%

:: 5. Cerrar ESTA ventana (el lanzador)
EXIT