#!/bin/bash
# Устанавливаем рабочую директорию
WORK_DIR=$PWD
# Путь к JAVA
JAVA_HOME=""

if [ "$JAVA_HOME" == "" ]; then
    echo "НE ЗАДАН ПУТЬ К JRE"
else
	echo "ВЫБЕРИТЕ РЕЖИМ ОБНОВЛЕНИЯ :"
	echo "1--СЕРВИСНЫЙ ЭМУЛЯТОР"
	read -n 1
	echo $WORK_DIR/lib/
	"$JAVA_HOME/bin/java" -cp $WORK_DIR/lib/* Main $1
fi