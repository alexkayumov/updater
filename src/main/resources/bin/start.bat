ECHO OFF
chcp 65001
SET WORK_DIR=%~dp0..\

SET JAVA_HOME=C:\JDK
IF "%JAVA_HOME%"=="" goto error
ECHO ВЫБЕРИТЕ РЕЖИМ ОБНОВЛЕНИЯ :
ECHO 1--СЕРВИСНЫЙ ЭМУЛЯТОР
ECHO 2--СЕРВЕР ИНТЕГРАЦИИ
SET /P value=
"%JAVA_HOME%\bin\java" -cp %WORK_DIR%lib\* Main %value%
exit
:error
ECHO НE ЗАДАН ПУТЬ К JRE!!!
:exit