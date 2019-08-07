#!/bin/bash
# Устанавливаем рабочую директорию
WORK_DIR=$(dirname $PWD)
# Путь к JAVA
JAVA_HOME=""

if [ -z $JAVA_HOME ]; then
    echo "JAVA_HOME is not set"
    exit 1
fi

"$JAVA_HOME/bin/java" -cp $WORK_DIR/lib/*: Main $1
