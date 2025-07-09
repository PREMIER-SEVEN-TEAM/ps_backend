#!/usr/bin/env bash

APP_DIR="/home/ubuntu/app"
JAR_FILE="$APP_DIR/premier7-backend.jar"
DEPLOY_LOG="$APP_DIR/deploy.log"
TIME_NOW=$(date +%c)

# 현재 실행 중인 프로세스 ID 확인
CURRENT_PID=$(pgrep -f $JAR_FILE)

if [ -z "$CURRENT_PID" ]; then
  echo "$TIME_NOW > 현재 실행 중인 애플리케이션이 없습니다." >> $DEPLOY_LOG
else
  echo "$TIME_NOW > 실행 중인 애플리케이션(PID: $CURRENT_PID)을 종료합니다." >> $DEPLOY_LOG
  kill -15 $CURRENT_PID
fi
