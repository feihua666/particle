# 全局数据源模块

该模块提供自定义SQL初始化增强功能，并引入多数据源支持。

## 功能特性

1. **多数据源支持**：支持配置和管理多个数据源
2. **SQL初始化增强**：提供自定义SQL初始化功能
3. **数据源切换**：支持运行时动态切换数据源

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-datasource-boot-starter</artifactId>
</dependency>
```

### 配置选项

```yaml
spring:
  datasource:
    # 数据源配置
```

### 核心组件

- 多数据源配置类：管理多个数据源实例
- 数据源切换工具：实现运行时数据源切换
- SQL初始化增强器：提供自定义SQL初始化功能

## 依赖组件

- Spring Boot Starter JDBC
- 数据库连接池（如HikariCP）
