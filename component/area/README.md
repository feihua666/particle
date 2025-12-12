# 区域组件

区域组件提供地理区域数据的管理功能，支持国家、省份、城市、区县等多级行政区域划分。

## 功能特性

- 多级行政区划管理
- 区域数据维护
- 区域编码体系
- 区域关联关系管理
- 区域数据查询接口
- 区域名称拼音自动生成

## 核心组件

### 领域模型
- [Area](area-domain/src/main/java/com/particle/area/domain/Area.java)：区域实体模型，包含区域编码、名称、类型、经纬度等属性
- [AreaId](area-domain/src/main/java/com/particle/area/domain/AreaId.java)：区域标识符模型

### 应用服务
- [IAreaApplicationService](area-client/src/main/java/com/particle/area/client/api/IAreaApplicationService.java)：区域应用服务接口，提供创建、更新、删除、查询等基本操作

### 自动配置
- [AreaAutoConfiguration](area-boot-starter/src/main/java/com/particle/area/AreaAutoConfiguration.java)：区域组件自动配置类，负责扫描mapper和配置Swagger文档

### 数据访问
- 区域数据仓储接口和实现
- MyBatis Mapper接口

## 配置选项

区域组件暂无特殊配置选项，使用默认配置即可。

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>area-boot-starter</artifactId>
</dependency>
```

### 功能使用

区域组件提供标准的增删改查接口，可通过[IAreaApplicationService](area-client/src/main/java/com/particle/area/client/api/IAreaApplicationService.java)接口进行调用。

## 自动配置

当classpath下存在相关依赖时，[AreaAutoConfiguration](area-boot-starter/src/main/java/com/particle/area/AreaAutoConfiguration.java)会自动配置以下内容：
- 扫描com.particle.area.infrastructure.mapper包下的Mapper接口
- 注册区域管理相关的REST API文档
