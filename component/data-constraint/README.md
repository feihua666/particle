# 数据约束/数据权限组件

数据约束/数据权限组件提供细粒度的数据访问控制和数据权限管理功能。

## 功能特性

- 数据行级权限控制
- 数据对象分类管理
- 数据范围定义与管理
- 动态数据过滤
- 自定义数据范围分配

## 核心组件

### 领域模型
- [DataObject](data-constraint-domain/src/main/java/com/particle/dataconstraint/domain/DataObject.java)：数据对象模型，用于定义可进行数据权限控制的业务对象
- [DataScope](data-constraint-domain/src/main/java/com/particle/dataconstraint/domain/DataScope.java)：数据范围模型，定义数据访问的范围规则
- [DataScopeCustomDataRel](data-constraint-domain/src/main/java/com/particle/dataconstraint/domain/DataScopeCustomDataRel.java)：数据范围与自定义数据关系模型

### 应用服务
- [IDataScopeApplicationService](data-constraint-client/src/main/java/com/particle/dataconstraint/client/api/IDataScopeApplicationService.java)：数据范围应用服务接口

### 自动配置
- [DataConstraintAutoConfiguration](data-constraint-boot-starter/src/main/java/com/particle/dataconstraint/DataConstraintAutoConfiguration.java)：数据约束组件自动配置类，负责扫描mapper和配置Swagger文档

### 数据访问
- 数据约束相关数据仓储接口和实现
- MyBatis Mapper接口

## 配置选项

数据约束组件暂无特殊配置选项，使用默认配置即可。

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>data-constraint-boot-starter</artifactId>
</dependency>
```

### 功能使用

数据约束组件提供数据对象管理、数据范围管理等核心功能，可通过应用服务接口进行调用。

## 自动配置

当classpath下存在相关依赖时，[DataConstraintAutoConfiguration](data-constraint-boot-starter/src/main/java/com/particle/dataconstraint/DataConstraintAutoConfiguration.java)会自动配置以下内容：
- 扫描com.particle.dataconstraint.infrastructure.mapper包下的Mapper接口
- 注册数据约束管理相关的REST API文档
