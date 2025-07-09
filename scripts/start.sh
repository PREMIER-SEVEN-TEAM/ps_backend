#!/usr/bin/env bash

APP_DIR="/home/ubuntu/app"
JAR_SOURCE=$(ls $APP_DIR/*.jar | head -n 1)
JAR_TARGET="$APP_DIR/premier7-backend.jar"

APP_LOG="$APP_DIR/application.log"
ERROR_LOG="$APP_DIR/error.log"
DEPLOY_LOG="$APP_DIR/deploy.log"
TIME_NOW=$(date +%c)

# JAR 복사 및 이름 통일
echo "$TIME_NOW > JAR 파일 복사 및 통일" >> $DEPLOY_LOG
cp $JAR_SOURCE $JAR_TARGET

# 앱 실행
echo "$TIME_NOW > 앱 실행 시작" >> $DEPLOY_LOG
nohup java -jar $JAR_TARGET > $APP_LOG 2> $ERROR_LOG &

# PID 기록
CURRENT_PID=$(pgrep -f $JAR_TARGET)
echo "$TIME_NOW > 실행된 PID: $CURRENT_PID" >> $DEPLOY_LOG
