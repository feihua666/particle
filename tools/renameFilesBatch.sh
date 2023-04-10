#!/bin/bash

#############################################################
# description: batch rename files name to another script
# example: sudo ./renameFilesBatch.sh

#############################################################
# 配置相关

# workdir
WORK_DIR=./
# 递归子文件夹
RECURSION_SUB_DIR=true
# 文本替换
FROM_STR='\[www.oiabc.com\]'
TO_STR=''

# 检查请求参数
check_param() {
  echo ''
}

# 真正启动
dorename(){
	mv  "$1" "$2"
	echo "rename $1 to $2 success!"
}
REPLACE_RESULT=
# 名称替换
replace() {
  # 接收第一个参数
  filename=$1
  # 接收第二个参数
  from=$2
  # 接收第三个参数
  to=$3

  # 输出正在处理的文件名
  echo 'Processing filename='$filename' from='$from' to='$to
  # 真正替换的命令sed
  REPLACE_RESULT=$(echo $filename | sed "s/$from/$to/g")

  # 统计替换后的次数
  c=`echo $REPLACE_RESULT | grep "$from" | wc -l`

  # 如果统计后的次数不为0就表示替换失败
  if [[ $c -gt 0 ]]; then
    # 输出替换失败的提示语
    echo "replace $filename failed! exit directly!"
    # 退出
    exit 1
  fi
  # 输出替换成功的提示语
  echo "Replace $filename to $REPLACE_RESULT success!"
}
# 启动服务
start() {
  echo "start rename files"
  cd $WORK_DIR
  echo "current dir is $WORK_DIR"
  # 直接遍历当前文件夹下的文件
  for file in *
  do
    echo "current file is $file"
    replace "$file" "$FROM_STR" "$TO_STR"
    dorename "$file" "$REPLACE_RESULT"
    done
}

#begin 首先检查参数
check_param

start

exit 0