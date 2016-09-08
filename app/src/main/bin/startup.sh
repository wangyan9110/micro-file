#!/bin/sh

if [ -z "$1" ]; then
	echo "请在参数中指定进程Id文件的名称！"
	exit 1
fi

CURRENT_DIR=$(pwd)
PROJECT_DIR=$CURRENT_DIR"/.."

# echo $PROJECT_DIR

CLASSPATH=
CLASSPATH=$CLASSPATH:$PROJECT_DIR

for i in "$PROJECT_DIR"/lib/*.jar;do
	CLASSPATH="$CLASSPATH":"$i"
done

# echo $CLASSPATH

APPNAME=info.yywang.file.app.Application

java -Xms256m -Xmx256m  -Ddubbo.registry.file=/home/newad/.dubbo/dubbo-registry-creatorgod-0.cache -classpath $CLASSPATH $APPNAME start >/dev/null 2>&1 &

echo $! > "/var/run/file-$1.pid"

echo "started"
