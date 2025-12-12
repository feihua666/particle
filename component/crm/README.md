# CRM组件

CRM（客户关系管理）组件提供客户信息管理等功能。

## 功能特性

- 客户公司管理
- 客户部门管理
- 客户信息管理
- 客户联系方式管理
- 客户关系管理
- 客户标签管理

## 核心组件

### 领域模型
- 客户公司相关实体模型
- 客户相关实体模型
- 客户标签相关实体模型
- 客户关系相关实体模型

### 应用服务
- [ICrmCustomerApplicationService](crm-client/src/main/java/com/particle/crm/client/customer/api/ICrmCustomerApplicationService.java)：客户提供应用服务接口

### 自动配置
- [CrmAutoConfiguration](crm-boot-starter/src/main/java/com/particle/crm/CrmAutoConfiguration.java)：CRM组件自动配置类，负责扫描mapper和配置Swagger文档

### 数据访问
- CRM各业务模块数据仓储接口和实现
- MyBatis Mapper接口

## 配置选项

CRM组件暂无特殊配置选项，使用默认配置即可。

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>crm-boot-starter</artifactId>
</dependency>
```

### 功能使用

CRM组件提供标准的增删改查接口，可通过各业务模块的应用服务接口进行调用。

## 自动配置

当classpath下存在相关依赖时，[CrmAutoConfiguration](crm-boot-starter/src/main/java/com/particle/crm/CrmAutoConfiguration.java)会自动配置以下内容：
- 扫描CRM各业务模块mapper包下的Mapper接口
- 注册CRM管理相关的REST API文档
