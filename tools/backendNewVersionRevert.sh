#!/bin/bash
cd ../
pwd
mvn versions:revert

# 请在tools目录下执行该脚本
# 如果执行完 backendNewVersion.sh 发现设计的版本号错误，可以使用该脚本回退，回退完成后会清除 pom.xml.versionsBackup 文件