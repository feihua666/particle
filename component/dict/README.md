# 字典组件

字典组件提供系统字典数据的管理功能，支持字典项分组、排序特性。

## 功能特性

- 字典项分组管理
- 字典项排序
- 字典缓存机制

## 使用方法

在项目的 pom.xml 中添加以下依赖：

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>dict-boot-starter</artifactId>
    <version>${particle.version}</version>
</dependency>
```

## 核心组件

### 领域模型
- Dict：字典实体模型
- DictId：字典标识符模型

### 应用服务
- IDictApplicationService：字典应用服务接口，提供字典管理的标准增删改查操作

### 自动配置
- [DictAutoConfiguration](dict-boot-starter/src/main/java/com/particle/dict/DictAutoConfiguration.java)：字典组件自动配置类，负责扫描mapper和配置Swagger文档

### 数据访问
- 字典相关数据仓储接口和实现
- MyBatis Mapper接口

## 自动配置

当classpath下存在相关依赖时，[DictAutoConfiguration](dict-boot-starter/src/main/java/com/particle/dict/DictAutoConfiguration.java)会自动配置以下内容：
- 扫描com.particle.dict.infrastructure.mapper包下的Mapper接口
- 注册字典管理相关的REST API文档

## 使用说明

字典组件为系统提供统一的字典数据管理服务，支持各种业务场景下的字典数据需求。
