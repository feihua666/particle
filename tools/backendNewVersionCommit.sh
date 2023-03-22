#!/bin/bash
cd ../
pwd
mvn versions:commit

# 请在tools目录下执行该脚本
# 如果执行完 backendNewVersion.sh 没有问题，可以使用该脚本提交,提交完成后会清除 pom.xml.versionsBackup 文件