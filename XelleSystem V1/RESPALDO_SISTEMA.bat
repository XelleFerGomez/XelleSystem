@echo off
TITLE Sistema de Respaldo XELLE SYSTEM
color 0A
echo ==========================================
echo      INICIANDO RESPALDO DE SEGURIDAD
echo      De: xelle_db  -->  A: xelle_bank_backup
echo ==========================================
echo.
echo 1. Cerrando conexiones activas...
REM Esto evita errores si el sistema esta en uso

echo 2. Exportando datos de Produccion...
set PGPASSWORD=root
"C:\Program Files\PostgreSQL\18\bin\pg_dump.exe" -U postgres -h localhost -c xelle_db > copia_temporal.sql

echo 3. Escribiendo en Base de Respaldo...
"C:\Program Files\PostgreSQL\18\bin\psql.exe" -U postgres -h localhost -d xelle_bank_backup < copia_temporal.sql

echo 4. Limpiando archivos temporales...
del copia_temporal.sql

echo.
echo ==========================================
echo      RESPALDO COMPLETADO CON EXITO
echo ==========================================
pause