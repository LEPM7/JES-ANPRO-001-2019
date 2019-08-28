#!/bin/bash
echo 'Conectado con la base de datos'
/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P Qwerty1234 -i /opt/bin/create_database.sql -o /opt/bin/output_sql.txt

echo 'Terminado, revisar el archivo output_sql.txt'