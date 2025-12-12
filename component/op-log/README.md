# 操作日志组件

操作日志组件记录用户的操作行为，支持操作审计数据。

## 使用方法

在项目的 pom.xml 中引入依赖：

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>op-log-boot-starter</artifactId>
    <version>${particle.version}</version>
</dependency>
```

## 功能特性

- 操作日志记录
- 日志查询
- 异常操作告警

## 核心组件

- [OpLogAutoConfiguration](op-log-boot-starter/src/main/java/com/particle/oplog/OpLogAutoConfiguration.java)：操作日志自动配置类
- [OpLog](op-log-domain/src/main/java/com/particle/oplog/domain/OpLog.java)：操作日志实体模型
- [OpLogAuditData](op-log-domain/src/main/java/com/particle/oplog/domain/OpLogAuditData.java)：操作日志审计数据实体模型
- [OpLogError](op-log-domain/src/main/java/com/particle/oplog/domain/error/OpLogError.java)：操作异常日志实体模型

## 自动配置

操作日志组件由 [OpLogAutoConfiguration](op-log-boot-starter/src/main/java/com/particle/oplog/OpLogAutoConfiguration.java) 类完成自动配置，主要提供了以下功能：

- 注册操作日志相关Mapper扫描路径
- 提供基于Swagger的操作日志接口文档

## 使用说明

操作日志组件为系统提供完整的操作审计功能，满足合规性要求和安全监控需求。
