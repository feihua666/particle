# 开放平台组件

开放平台组件提供对外API接口管理和开放平台能力，支持第三方应用接入。

## 使用方法

在项目的 pom.xml 中引入依赖：

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>open-platform-boot-starter</artifactId>
    <version>${particle.version}</version>
</dependency>
```

## 核心组件

- App管理：管理接入的第三方应用
- 开放接口管理：管理系统提供的API接口
- 计费规则管理：配置API调用的计费规则
- 调用记录管理：记录API调用详情
- 供应商管理：管理API数据供应商
- 供应商调用记录：记录对供应商的调用详情
- 接口文档管理：维护API接口文档
- 账单管理：管理客户账单和统计信息

## 菜单功能

### 平台管理
- App管理
  - 查询、创建、删除、修改App
  - 刷新App缓存
  
- App接口配置管理
  - 查询、创建、删除、修改App接口配置
  - 刷新App接口配置缓存

- 开放接口管理
  - 查询、创建、删除、修改开放接口

- 计费规则管理
  - 查询、创建、删除、修改计费规则

- 开放接口调用记录
  - 查询调用记录
  - 查看调用参数
  - 查看供应商调用记录

- 供应商管理
  - 查询、创建、删除、修改供应商

- 供应商调用记录
  - 查询供应商调用记录
  - 查看调用参数

### 接口文档管理
- 目录名称管理
- 目录管理
- 文档接口管理
- 接口文档内容管理
- 接口文档内容参数字段管理
- 接口文档内容响应码管理
- 接口文档内容示例代码管理
- 文档内容模板管理
- 文档内容模板参数字段管理
- 文档内容模板响应码管理
- 文档内容模板示例代码管理
- 接口与目录绑定管理

### 账单管理
- 应用接口日汇总管理
- 应用接口月汇总管理
- 客户月账单管理

### 接口查询
- 接口单次查询
- 接口批量查询

## 自动配置

组件通过 [OpenPlatformAutoConfiguration](open-platform-boot-starter/src/main/java/com/particle/openplatform/OpenPlatformAutoConfiguration.java) 实现自动配置，
并扫描 `com.particle.openplatform` 包下的组件，包括：
- Mapper接口：各模块数据访问层接口
- 应用服务：openplatform-app模块的应用服务类

该组件还集成了Swagger文档功能，提供了后端管理接口文档分组。