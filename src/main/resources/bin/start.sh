#!/bin/bash
# Устанавливаем рабочую директорию
WORK_DIR=$(dirname $PWD)
# Путь к JAVA
JAVA_HOME="/home/kayumov/JDK/jdk1.8.0_211/"

if [ -z $JAVA_HOME ]; then
    echo "JAVA_HOME is not set"
    exit 1
fi

echo "ВЫБЕРИТЕ РЕЖИМ ОБНОВЛЕНИЯ :"
echo "1--СЕРВИСНЫЙ ЭМУЛЯТОР"
echo "2--СЕРВЕР ИНТЕГРАЦИИ"
read n
"$JAVA_HOME/bin/java" -cp $WORK_DIR/lib/*: Main $n
