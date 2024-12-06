#!/bin/bash

#############################################################
# description: springboot jar start|stop|restart|status|info script
# example: sudo ./tools.sh [start|stop|restart|status|info] xxx-xxxx.jar

#############################################################
# 配置相关

# java home
JAVA_HOME=$JAVA_HOME
# 自定义 java home
CUSTOM_JAVA_HOME=

# 应用名称
APP_NAME=particle-demo-start-4.0.2-beta-SNAPSHOT.jar
# 应用所在目录
APP_HOME=$(pwd)
# 应用全路径
APP_PATH=$APP_HOME/$APP_NAME

# 日志
#日志文件名称
APP_LOG_NAME=particle-demo-start/particle-demo-start.log
# log所在目录
APP_LOG_HOME=$APP_HOME/logs
# 日志全路径
APP_LOG_PATH=$APP_LOG_HOME/$APP_LOG_NAME


#应用参数定义
APP_PARAMS=""

# 参数错误提示信息
ERROR_MSG="参数错误，当前只支持一个参数，请参照以下参数传递： [start|stop|restart|status|info]"

# java 启动参数
JAVA_OPTS="-server -Dparticle.actuator.bootadmin.server.enabled=false -Dspring.boot.admin.client.enabled=false -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true -Dfile.encoding=UTF-8  -Xms5g -Xmx5g -Xmn3g -Xss256k -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=500m -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+CMSScavengeBeforeRemark -XX:+ExplicitGCInvokesConcurrent -XX:+CMSClassUnloadingEnabled -XX:+UseCMSCompactAtFullCollection -XX:CMSFullGCsBeforeCompaction=5 -XX:CMSInitiatingOccupancyFraction=75 -XX:+UseCMSInitiatingOccupancyOnly -XX:+UseCompressedOops -XX:StringTableSize=100013"

# 10 秒后判断应用是否停止, 否则 kill -9
SHUTDOWN_WAIT=10

# 打印当前时间
CURR_DATE=`date "+%Y-%m-%d %H:%M:%S"`
echo "当前时间：$CURR_DATE"

#获取当前脚本名称
SELF_SCRIPT_NAME=`basename "$0"`

# 检查pid
checkpid(){
  echo $(ps -ef | grep "$APP_PATH" | grep java | grep -v grep | grep -v "$SELF_SCRIPT_NAME" | awk '{print $2}')
}


# 检查是否配置了java_home
checkJavaHome(){
	if [ -n "$JAVA_HOME" ]; then
		echo "使用默认 JAVA_HOME=$JAVA_HOME"
	elif [ -n "$CUSTOM_JAVA_HOME" ]; then
		echo "使用自定义 JAVA_HOME=$CUSTOM_JAVA_HOME"
		JAVA_HOME=$CUSTOM_JAVA_HOME
	else
		echo "JAVA_HOME 未定义，请检查配置"
		exit 1
	fi
}
# 检查请求参数
check_param() {
  if [ ! -n "$1" ]; then
    echo -e "$ERROR_MSG"
    exit 0
  fi
}
# 用户是否存在 1=存在，0=不存在
user_exists(){
  if id -u $1 >/dev/null 2>&1; then
    echo "1"
  else
    echo "0"
  fi
}
# tail -f 日志
taillog(){
  if [ -n "$APP_LOG_PATH" ];then
    echo "5s后自动打开tail日志"
    sleep 5
    if test -f "$APP_LOG_PATH"; then
        echo "$APP_LOG_PATH"
        tail -f $APP_LOG_PATH
    else
      echo -e "日志文件不存在：$APP_LOG_PATH"
    fi
  fi
}
# 应用是否存在
status(){
  pid=$(checkpid)
  if [ -n "$pid" ]; then
    echo -e "应用状态：正在运行 (pid: $pid)"
  else
    echo -e "应用状态：未运行"
  fi
}

# 真正启动
dorun(){
  echo "$JAVA_HOME/bin/java $JAVA_OPTS -jar $APP_PATH $APP_PARAMS >/dev/null 2>&1 &"
	nohup  $JAVA_HOME/bin/java $JAVA_OPTS -jar $APP_PATH $APP_PARAMS >/dev/null 2>&1 &
	taillog
}
# 真正停止 参数为pid
dostop(){
  if [ -n "$1" ]; then
    echo "优雅结束应用 (pid: $1)"
    kill -15 "$1"
  else
    echo "未找到需要结束的 pid"
  fi
}
# 强制结束应用 参数为pid
dostopForce(){
  if [ -n "$1" ]; then
    echo "强制结束应用 (pid: $1)"
    kill -9 "$1"
  else
    echo "未找到需要结束的 pid"
  fi
}
# 系统信息统计
information() {
   echo "系统信息:"
   echo "****************************"
   echo `head -n 1 /etc/issue`
   echo `uname -a`
   echo
   echo "JAVA_HOME=$JAVA_HOME"
   echo `$JAVA_HOME/bin/java -version`
   echo
   echo "APP_PATH=$APP_PATH"
   echo "pid=$(checkpid)"
   echo "****************************"
}
# 启动服务
start() {
  pid=$(checkpid)
  if [  -n "$pid" ];then
    echo -e "应用已经是启动状态 (pid: $pid)，如果需要重启请进行重启操作"
  else
    echo -e "开始启动应用 $APP_PATH"
    echo "使用当前用户 $(whoami) 启动"
    dorun
    status
  fi
  return 0
}



stop() {
  pid=$(checkpid)
  if [ -n "$pid" ];then
    echo "开始停止应用..."
    echo "使用当前用户 $(whoami) 停止应用"
    dostop $pid

    let kwait=$SHUTDOWN_WAIT
    count=0;
    until [ `ps -p $pid | grep -c $pid` = '0' ] || [ $count -gt $kwait ]
    do
      echo "请耐心等待，应用正在退出,已等待 $count 秒";
      sleep 1
      count=$((count + 1))
    done

    if [ $count -gt $kwait ];then
        echo "$SHUTDOWN_WAIT 秒内程序未正常结束，将强制结束程序"
        status
        pid=$(checkpid)
        if [ -n "$pid" ];then
          echo "在强制结束程序前检查，应用依然没有结束，进行强制结束！！"
          dostopForce $pid
          else
            echo "在强制结束程序前检查，应用已经优雅结束，恭喜！"
        fi

    fi
  else
      echo "应用已经是停止状态，如果需要启动，请执行启动操作"
  fi

  return 0
}


#begin 首先检查参数
check_param $1
# 检查javahome
checkJavaHome

case $1 in
   'start')
      start
      ;;
   'stop')
     stop
     ;;
   'restart')
     stop
     echo "应用已结束，请稍候，将在2s后启动"
     sleep 2
     start
     ;;
   'status')
     status
     ;;
   'info')
     information
     ;;
  *)
  echo $ERROR_MSG
    ;;
esac
exit 0
