# 全局数据权限模块

该模块提供数据权限控制功能，用于实现基于用户角色和权限的数据访问控制。
说明：
1. 该模块定义数据权限控制的核心规范
2. 通过动态注入条件sql来实现数据权限控制
## 功能特性

1. **数据权限控制**：可用于基于用户角色和权限控制数据访问范围
2. **以数据对象为粒度**：针对不同数据对象实现数据权限控制
3. **灵活配置**：借助业务模块（[data-constraint](../../component/data-constraint)）可动态配置数据权限控制逻辑
4. **集成友好**：与 Mybatis Plus 良好集成,依赖可扩展

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-data-permission-boot-starter</artifactId>
</dependency>
```

### 核心组件

- DataPermissionService：数据权限服务接口

## 依赖组件

- Spring Boot Starter
