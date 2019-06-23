ECHO OFF
chcp 65001
SET WORK_DIR=%~dp0..\

@: -- раскоментировать и указать путь к JDK
SET JAVA_HOME=C:\JDK
IF "%JAVA_HOME%"=="" (
^ECHO -------------------------------------------
^ECHO НE ЗАДАН ПУТЬ К JAVA!!!
^ECHO -------------------------------------------) ELSE (
ECHO ВЫБЕРИТЕ РЕЖИМ ОБНОВЛЕНИЯ :
ECHO 1--СЕРВИСНЫЙ ЭМУЛЯТОР
SET /P arg1 = %1
"%JAVA_HOME%\bin\java" -cp %WORK_DIR%lib\* src.Main
)