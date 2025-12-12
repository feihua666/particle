# 全局大数据源模块

该模块提供自定义大数据源支持，区别于传统的JDBC数据源，专注于统一各种数据来源的调用方式，如JDBC数据源、HTTP远程调用、Neo4j图数据库、Elasticsearch等。

## 功能特性

1. **统一大数据源**：提供统一的数据访问接口，屏蔽底层数据源差异
2. **多数据源支持**：支持多种数据源类型，包括关系型数据库、NoSQL、搜索引擎等
3. **分页支持**：内置分页查询功能，支持大数据量的分页处理
4. **执行器模式**：通过大数据源执行器和接口执行器实现灵活的数据处理
5. **配置管理**：支持灵活的配置信息管理

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-big-datasource-boot-starter</artifactId>
</dependency>
```
具体使用方法可参考测试相关类

### 核心组件

- BigDatasource：大数据源接口
- BigDatasourceExecutor：大数据源执行器
- BigDatasourceInterfaceExecutor：大数据源接口执行器

## 依赖组件

- Spring Boot Starter Web
- MyBatis Plus
- 全局数据源模块
- 全局缓存模块（可选）
