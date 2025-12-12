# 消息组件

消息组件提供系统内部消息的收发和管理功能，支持站内信、系统通知等多种消息类型。

## 使用方法

在项目的 pom.xml 中引入依赖：

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>message-boot-starter</artifactId>
    <version>${particle.version}</version>
</dependency>
```

## 功能特性

- 消息发送与接收
- 消息类型管理
- 消息状态跟踪
- 消息模板管理
- 消息推送服务

## 核心组件

- [Message](message-domain/src/main/java/com/particle/message/domain/Message.java)：消息实体模型
- [MessageTemplate](message-domain/src/main/java/com/particle/message/domain/MessageTemplate.java)：消息模板实体模型
- [MessageAutoConfiguration](message-boot-starter/src/main/java/com/particle/message/MessageAutoConfiguration.java)：消息自动配置类

## 自动配置

消息组件由 [MessageAutoConfiguration](message-boot-starter/src/main/java/com/particle/message/MessageAutoConfiguration.java) 类完成自动配置，主要提供了以下功能：

- 注册消息相关Mapper扫描路径
- 提供基于Swagger的消息接口文档

## 使用说明

消息组件为系统提供统一的消息处理能力，支持各种场景下的消息通信需求。
