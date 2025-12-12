# 功能菜单组件

功能菜单组件管理系统中的功能点和菜单结构，支持权限控制和个性化定制。

## 使用方法

在项目的 pom.xml 中引入依赖：

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>func-boot-starter</artifactId>
    <version>${particle.version}</version>
</dependency>
```

## 核心组件

- [Func](func-domain/src/main/java/com/particle/func/domain/Func.java)：功能菜单实体模型
- [FuncGroup](func-domain/src/main/java/com/particle/func/domain/FuncGroup.java)：功能组实体模型
- [FuncApplication](func-domain/src/main/java/com/particle/func/domain/application/FuncApplication.java)：功能应用实体模型
- [FuncApplicationFuncRel](func-domain/src/main/java/com/particle/func/domain/funcapplicationfuncrel/FuncApplicationFuncRel.java)：功能应用功能关系实体模型
- [FuncAutoConfiguration](func-boot-starter/src/main/java/com/particle/func/FuncAutoConfiguration.java)：功能菜单自动配置类

## 自动配置

功能菜单组件由 [FuncAutoConfiguration](func-boot-starter/src/main/java/com/particle/func/FuncAutoConfiguration.java) 类完成自动配置，主要提供了以下功能：

- 注册功能菜单相关Mapper扫描路径
- 提供基于Swagger的功能菜单接口文档

## 使用说明

功能菜单组件为系统提供统一的功能权限管理，支持灵活的菜单结构和权限控制。
