# 全局Spring自动注入相关模块

该模块提供Spring自动配置功能，用于那些不需要强制依赖Spring但仍需要依赖注入的Bean。主要解决全局模块相互依赖的问题。

## 功能特性

1. **自动配置支持**：为不强制依赖全局模块提供自动配置能力
2**条件化配置**：基于条件注解实现灵活的配置激活机制

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-autoconfigure-boot-starter</artifactId>
</dependency>
```

### 配置选项

该模块主要通过Spring Boot的自动配置机制工作，通常不需要额外配置。

### 核心组件

- GlobalAdapterAutoConfiguration：全局适配器自动配置类
- DatasourceToolConfig：数据源工具配置类
- DynamicDatasourceServiceImpl：动态数据源服务实现

## 依赖组件

- Spring Boot Autoconfigure
- Spring Context
