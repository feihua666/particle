# tools

提供常用工具脚本等  
## linux 脚本
### 获取maven项目版本
1. maven命令提取项目版本，需要 particle 项目根目录下执行可获取版本 前提是 pom.xml中的 Properties 中有这个变量
```shell
VERSION=$(mvn help:evaluate -Dexpression=particle.version -q -DforceStdout)
echo $VERSION
```
2. 提取jar版本，需要在target目录下执行
```shell
NAME=particle-demo-start
NAMEPREFIX=$NAME-
JAR_NAME=`ls |grep ${NAMEPREFIX}|grep -v original`
MAVEN_VERSION=${JAR_NAME##*$NAMEPREFIX}
VERSION=${MAVEN_VERSION%.*}
echo "Project version: $VERSION"
```