echo off
chcp 65001
set WORK_DIR=%~dp0..\

@: -- раскоментировать и указать путь к JDK
set JAVA_HOME=
if "%JAVA_HOME%"=="" (
^echo -------------------------------------------
^echo НE ЗАДАН ПУТЬ К JAVA!!!
^echo -------------------------------------------) else (
"%JAVA_HOME%\bin\java" -cp %WORK_DIR%lib\* src.Main
)