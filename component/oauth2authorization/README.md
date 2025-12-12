# OAuth2组件

OAuth2组件结合全局模块 global-security-boot-starter 中的 authorization server 功能配置实现客户端管理。

## 使用方法

在项目的 pom.xml 中引入依赖：

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>oauth2authorization-boot-starter</artifactId>
    <version>${particle.version}</version>
</dependency>
```

## 功能特性

- OAuth2客户端管理
- 授权码模式支持
- 客户端凭证模式支持
- 密码模式支持
- 刷新令牌机制

## 核心组件

- [Oauth2authorizationAutoConfiguration](oauth2authorization-boot-starter/src/main/java/com/particle/oauth2authorization/Oauth2authorizationAutoConfiguration.java)：OAuth2自动配置类
- [Oauth2RegisteredClient](oauth2authorization-domain/src/main/java/com/particle/oauth2authorization/domain/client/Oauth2RegisteredClient.java)：OAuth2客户端实体模型

## 菜单功能

oauth2授权管理：
- oauth2客户端管理
  - oauth2客户端管理查询
  - oauth2客户端管理添加
  - oauth2客户端管理删除
  - oauth2客户端管理修改

## 自动配置

OAuth2组件由 [Oauth2authorizationAutoConfiguration](oauth2authorization-boot-starter/src/main/java/com/particle/oauth2authorization/Oauth2authorizationAutoConfiguration.java) 类完成自动配置，主要提供了以下功能：

- 注册OAuth2相关Mapper扫描路径
- 提供基于Swagger的OAuth2接口文档

## 使用说明

OAuth2组件为第三方应用提供标准的OAuth2授权服务，确保系统资源的安全访问。
