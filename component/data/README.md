# 数据组件

数据组件主要为标准化数据结构，提供数据字典管理，数据映射管理，数据结构统一。目前已整理企业征信数据相关数据结构、动态数据导入导出功能。

数据业务中可能接入三方数据，对三方数据接入原则：
1. 统一对接本地数据结构，统一数据结构
2. 对接的三方数据转为本地数据结构时不匹配主要数据id，但匹配字典id
3. 三方数据接入入库尽量在在同一个维度保持完整

## 功能特性

- 企业征信数据结构标准化
- 动态数据表管理
- 动态数据导入导出
- 数据字典管理
- 数据映射管理

## 核心组件

### 领域模型
- 动态数据表相关实体模型
- 动态数据相关实体模型
- 企业征信数据相关实体模型

### 应用服务
- [IDynamicTableApplicationService](data-client/src/main/java/com/particle/data/client/dynamictable/api/IDynamicTableApplicationService.java)：动态数据表格应用服务接口

### 自动配置
- [DataAutoConfiguration](data-boot-starter/src/main/java/com/particle/data/DataAutoConfiguration.java)：数据组件自动配置类，负责扫描mapper和配置Swagger文档

### 数据访问
- 数据各业务模块数据仓储接口和实现
- MyBatis Mapper接口

## 配置选项

数据组件暂无特殊配置选项，使用默认配置即可。

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>data-boot-starter</artifactId>
</dependency>
```

### 功能使用

数据组件提供动态数据表管理、数据导入导出等核心功能，可通过各业务模块的应用服务接口进行调用。

## 自动配置

当classpath下存在相关依赖时，[DataAutoConfiguration](data-boot-starter/src/main/java/com/particle/data/DataAutoConfiguration.java)会自动配置以下内容：
- 扫描数据各业务模块mapper包下的Mapper接口
- 注册数据管理相关的REST API文档
