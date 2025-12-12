# 报告组件

报告组件提供数据报表生成和管理功能，支持多种格式的报表输出。

## 使用方法

在项目的 pom.xml 中引入依赖：

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>report-boot-starter</artifactId>
    <version>${particle.version}</version>
</dependency>
```

## 核心组件

- 报告模板管理：管理报告的模板定义和配置
- 报告接口管理：提供报告生成的API接口

## 菜单功能

### 报告管理
- 报告模板管理
  - 查询、创建、删除、修改报告模板
  - 复制节点、刷新缓存功能
  
- 报告接口管理
  - 查询、创建、删除、修改报告接口
  - 刷新缓存功能

## 自动配置

组件通过 [ReportAutoConfiguration](report-boot-starter/src/main/java/com/particle/report/ReportAutoConfiguration.java) 实现自动配置，
并扫描 `com.particle.report` 包下的组件，包括：
- Mapper接口：报告模板和报告接口相关的数据访问层接口
- 应用服务：report-app模块的应用服务类

该组件还集成了以下功能：
1. 提供默认的基于登录用户的权限校验器
2. 提供将报告生成结果上传到OSS的服务（当GlobalOssClientService存在时）

同时该组件也集成了Swagger文档功能，提供了后端管理接口文档分组。