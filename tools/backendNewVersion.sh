#!/bin/bash
cd ../
pwd
mvn versions:set -DnewVersion=0.0.1-beta

# 请在tools目录下执行该脚本
# 执行完成后默认会生成 pom.xml.versionsBackup 文件，该文件可用于回退 参见 backendNewVersionRevert.sh