# 全局应用启停模块

该模块封装了Spring Boot应用启动和关闭的事件监听接口，方便扩展，适用于Spring Boot项目依赖。

## 功能特性

1. **启动监听**：提供应用启动事件监听功能
2. **关闭监听**：提供应用关闭事件监听功能
3. **事件扩展**：支持自定义启动和关闭事件处理逻辑
4. **生命周期管理**：完整的应用生命周期管理支持

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-bootstrap-boot-starter</artifactId>
</dependency>
```

### 配置选项

该模块主要通过Spring Boot的事件机制工作，通常不需要额外配置。

### 核心组件

- BootstrapAutoConfiguration：启动自动配置类
- 应用启动监听器：监听应用启动事件
- 应用关闭监听器：监听应用关闭事件

## 依赖组件

- Spring Boot Starter
- Spring Context
