# 参数配置组件

参数配置组件提供系统配置参数的管理功能，支持动态配置。

## 功能特性

- 系统参数配置管理
- 配置参数动态更新
- 内置参数保护机制
- 参数禁用控制
- 参数标签分类管理

## 核心组件

### 领域模型
- [SystemConfig](config-domain/src/main/java/com/particle/config/domain/system/SystemConfig.java)：系统参数配置实体模型，包含编码、名称、值、内置标识、禁用状态等属性
- [SystemConfigId](config-domain/src/main/java/com/particle/config/domain/system/SystemConfigId.java)：系统参数配置标识符模型

### 应用服务
- [ISystemConfigApplicationService](config-client/src/main/java/com/particle/config/client/system/api/ISystemConfigApplicationService.java)：系统参数配置应用服务接口，提供创建、更新、删除、查询等基本操作

### 自动配置
- [ConfigAutoConfiguration](config-boot-starter/src/main/java/com/particle/config/ConfigAutoConfiguration.java)：参数配置组件自动配置类，负责扫描mapper和配置Swagger文档

### 数据访问
- 系统参数配置数据仓储接口和实现
- MyBatis Mapper接口

## 配置选项

参数配置组件暂无特殊配置选项，使用默认配置即可。

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>config-boot-starter</artifactId>
</dependency>
```

### 功能使用

参数配置组件提供标准的增删改查接口，可通过[ISystemConfigApplicationService](config-client/src/main/java/com/particle/config/client/system/api/ISystemConfigApplicationService.java)接口进行调用。

## 自动配置

当classpath下存在相关依赖时，[ConfigAutoConfiguration](config-boot-starter/src/main/java/com/particle/config/ConfigAutoConfiguration.java)会自动配置以下内容：
- 扫描com.particle.config.infrastructure.system.mapper包下的Mapper接口
- 注册参数配置管理相关的REST API文档
