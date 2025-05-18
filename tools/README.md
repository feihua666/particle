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
NAME=particle-project-start
NAMEPREFIX=$NAME-
JAR_NAME=`ls |grep ${NAMEPREFIX}|grep -v original`
MAVEN_VERSION=${JAR_NAME##*$NAMEPREFIX}
VERSION=${MAVEN_VERSION%.*}
echo "Project version: $VERSION"
```
## 数据库创建
```sql
CREATE DATABASE `particle` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */ /*!80016 DEFAULT ENCRYPTION='N' */
```

### 清空开放接口调用记录
```sql
START TRANSACTION;
ALTER TABLE `component_openplatform_openapi_record` RENAME TO `component_openplatform_openapi_record_bak`;
CREATE TABLE component_openplatform_openapi_record LIKE component_openplatform_openapi_record_bak;
ALTER TABLE `component_openplatform_openapi_record_param` RENAME TO `component_openplatform_openapi_record_param_bak`;
CREATE TABLE component_openplatform_openapi_record_param LIKE component_openplatform_openapi_record_param_bak;

ALTER TABLE `component_openplatform_provider_record` RENAME TO `component_openplatform_provider_record_bak`;
CREATE TABLE component_openplatform_provider_record LIKE component_openplatform_provider_record_bak;
ALTER TABLE `component_openplatform_provider_record_param` RENAME TO `component_openplatform_provider_record_param_bak`;
CREATE TABLE component_openplatform_provider_record_param LIKE component_openplatform_provider_record_param_bak;
COMMIT;

DROP TABLE component_openplatform_openapi_record_bak;
DROP TABLE component_openplatform_openapi_record_param_bak;

DROP TABLE component_openplatform_provider_record_bak;
DROP TABLE component_openplatform_provider_record_param_bak;
```
