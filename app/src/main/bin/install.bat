@echo off 

if not "%JAVA_HOME%" == "" goto gotJdkHome
SET JAVA_HOME=C:\Program Files\Java\jdk1.7.0_07
if exist "%JAVA_HOME%\jre\bin\java.exe" goto gotJdkHome
@echo The JAVA_HOME environment variable is not defined.
goto end

:gotJdkHome
set CLASSPATH=
SET CURRENT_DIR=%~dp0
SET PROJECT_DIR=%CURRENT_DIR%..
set JVM_HOME=%JAVA_HOME%\jre\bin\server\jvm.dll

@echo %PROJECT_DIR%

set CLASSPATH=%CLASSPATH%;%PROJECT_DIR%

SET APPNAME=com.iydsj.sw.erp.jobs.App

FOR /R %%F IN ("..\lib\*.jar") DO call :ADDCP %%F
goto RUN

:ADDCP
set CLASSPATH=%CLASSPATH%;%1
goto :EOF

:RUN
tlsysjob //IS//%1 --DisplayName="%1" --Install=%CURRENT_DIR%tlsysjob.exe --Jvm="%JVM_HOME%" --JvmMs=256 --JvmMx=256 --StartMode=jvm --StopMode=jvm --Classpath=%CLASSPATH% --StartClass=%APPNAME% --StartParams="start" --StopClass=%APPNAME% --StopParams="stop"
@echo %1 Installed!

:end
cd "%CURRENT_DIR%"
