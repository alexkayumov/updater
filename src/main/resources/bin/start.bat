ECHO OFF
chcp 65001
SET WORK_DIR=%~dp0..\

SET JAVA_HOME=
IF "%JAVA_HOME%"=="" goto error
ECHO ВЫБЕРИТЕ РЕЖИМ ОБНОВЛЕНИЯ :
ECHO 1--СЕРВИСНЫЙ ЭМУЛЯТОР
ECHO 2--СЕРВЕР ИНТЕГРАЦИИ

SET /P value=
"%JAVA_HOME%\bin\java" -cp %WORK_DIR%lib\*; -Dapplication.root=%WORK_DIR% Main %WORK_DIR% %value%
exit
:error
ECHO НE ЗАДАН ПУТЬ К JRE!!!
:exit