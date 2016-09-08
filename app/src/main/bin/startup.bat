@echo off  

SET CLASSPATH=
SET CURRENT_DIR=%~dp0
SET PROJECT_DIR=%CURRENT_DIR%..

@echo %PROJECT_DIR%

set CLASSPATH=%CLASSPATH%;%PROJECT_DIR%

SET APPNAME=com.iydsj.sw.erp.jobs.App

FOR /R %%F IN ("..\lib\*.jar") DO call :ADDCP %%F 
goto RUN
:ADDCP
set CLASSPATH=%CLASSPATH%;%1
goto :EOF
:RUN
java -Xms256m -Xmx256m -classpath %CLASSPATH% %APPNAME% start
pause
