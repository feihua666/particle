# 全局 Freemarker 模块

该模块提供基于 Freemarker 的模板引擎支持，用于渲染页面或生成文本内容。

## 功能特性

1. **模板渲染**：支持基于 Freemarker 的模板渲染功能
2. **Spring Boot 集成**：无缝集成 Spring Boot Web 环境
3. **自动配置**：通过 starter 方式自动配置 Freemarker 相关组件

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-freemarker-boot-starter</artifactId>
</dependency>
```

### 配置选项

默认情况下，Freemarker 会在以下位置查找模板文件：
- src/main/resources/templates/

可通过 application.yml 配置更多选项：

```yaml
spring:
  freemarker:
    template-loader-path: classpath:/templates/
    suffix: .ftl
    cache: false
    charset: UTF-8
```

## 核心组件

该模块基于 Spring Boot 的 Freemarker Starter 实现，主要包括：
- FreemarkerAutoConfiguration：自动配置类
- FreeMarkerConfigurer：模板配置器
- FreeMarkerViewResolver：视图解析器

## 依赖组件

- Spring Boot Starter Web
- Spring Boot Starter Freemarker