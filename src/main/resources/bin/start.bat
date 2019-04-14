echo off
chcp 1251
set WORK_DIR=%~dp0..\

@: -- раскоментировать и указать путь к JDK
set JAVA_HOME=
if "%JAVA_HOME%"=="" (echo Не задан путь к java) else (
"%JAVA_HOME%\bin\java" -cp %WORK_DIR%lib\* src.Main
)
