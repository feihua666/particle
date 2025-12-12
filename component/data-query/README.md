# 数据查询组件

数据查询组件提供了全局大数据源的配置支持，旨在解决接口临时替换、变更快速迭代的功能时以前需要开发速度慢不够灵活的问题。

## 功能特性

- 多数据源配置管理
- 动态查询接口
- 查询结果缓存
- 数据供应商管理
- 数据源接口管理
- 查询接口管理

## 核心组件

### 领域模型
- 数据供应商相关实体模型
- 数据源相关实体模型
- 数据接口相关实体模型

### 应用服务
- [IDataQueryDatasourceApplicationService](data-query-client/src/main/java/com/particle/dataquery/client/datasource/api/IDataQueryDatasourceApplicationService.java)：数据查询数据源应用服务接口

### 自动配置
- [DataqueryAutoConfiguration](data-query-boot-starter/src/main/java/com/particle/dataquery/DataqueryAutoConfiguration.java)：数据查询组件自动配置类，负责扫描mapper、配置Swagger文档和提供预热功能

### 数据访问
- 数据查询相关数据仓储接口和实现
- MyBatis Mapper接口

## 配置选项

### 接口预热配置
```yaml
# 启用数据查询接口预热
com.particle.dataquery.api.warm-up-light: true
```

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>data-query-boot-starter</artifactId>
</dependency>
```

### 功能使用

数据查询组件提供数据供应商管理、数据源管理、数据接口管理等核心功能，可通过各业务模块的应用服务接口进行调用。

## 自动配置

当classpath下存在相关依赖时，[DataqueryAutoConfiguration](data-query-boot-starter/src/main/java/com/particle/dataquery/DataqueryAutoConfiguration.java)会自动配置以下内容：
- 扫描数据查询相关mapper包下的Mapper接口
- 注册数据查询管理相关的REST API文档
- 提供数据接口预热功能
- 支持开放接口远程调用
