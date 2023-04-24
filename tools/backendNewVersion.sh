#!/bin/bash
cd ../
pwd
mvn versions:set -DnewVersion=0.1.0-beta

# 请在tools目录下执行该脚本
# 执行完成后默认会生成 pom.xml.versionsBackup 文件，该文件可用于回退 参见 backendNewVersionRevert.sh
# 尽量保持前后端版本一致 同步修改 web/project/xxx/package.json 和 doc/vuepress/package.json 中 version 版本