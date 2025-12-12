# 全局微信开发支持模块

该模块基于 [WxJava](https://github.com/Wechat-Group/WxJava)（微信Java开发工具包）提供微信相关的开发支持。

## 功能特性

1. **微信公众平台支持**：支持微信公众平台相关的API调用
2. **微信支付支持**：支持微信支付相关的功能
3. **企业微信支持**：支持企业微信相关的功能
4. **小程序支持**：支持微信小程序开发相关功能
5. **Spring Boot 集成**：无缝集成 Spring Boot 环境，提供自动配置功能

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-wxjava-boot-starter</artifactId>
</dependency>
```

### 配置选项

在 application.yml 中添加相关配置：

```yaml
wx:
  mp:
    # 公众号配置
    app-id: your_app_id
    secret: your_app_secret
    token: your_token
  pay:
    # 微信支付配置
    app-id: your_pay_app_id
    mch-id: your_mch_id
    mch-key: your_mch_key
```

## 核心组件

该模块基于 WxJava 实现，主要包括：
- WxMpService：微信公众平台服务
- WxPayService：微信支付服务
- 相关自动配置类

## 依赖组件

- WxJava 相关依赖
- Spring Boot Starter Web