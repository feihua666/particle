# 对象存储服务

该模块提供统一的对象存储服务接口，兼容 Amazon S3 协议，支持多种云存储服务和本地存储。

Amazon Simple Storage Service（Amazon S3，Amazon 简便存储服务）是 AWS 最早推出的云服务之一，经过多年的发展，S3 协议在对象存储行业事实上已经成为标准。

## 功能特性

1. **统一接口**：提供统一的 REST接口
2. **协议兼容**：完全兼容 Amazon S3 协议，支持主流云存储服务
3. **多存储支持**：支持云存储（阿里云、腾讯云、七牛云等）和本地存储、FTP存储

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-oss-boot-starter</artifactId>
</dependency>
```

### 配置选项

在 application.yml 中配置对象存储服务：

```yaml
particle:
  oss:
    # OSS 配置
```

### 核心组件

- OssTemplate：对象存储操作模板类，提供统一的操作接口
- OssProperties：配置属性类，用于配置各种 OSS 参数
- 云存储客户端：基于 AWS SDK for Java 实现的 S3 客户端
- 本地存储适配器：本地文件系统存储实现

## 依赖组件

- AWS SDK for Java：Amazon S3 协议的官方 Java SDK
- Spring Boot Starter Web
- Global DTO：提供数据传输对象支持
