# 工具组件

工具组件提供系统常用工具和服务，支持各种业务场景下的工具需求。

## 使用方法

在项目的 pom.xml 中引入依赖：

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>tools-boot-starter</artifactId>
    <version>${particle.version}</version>
</dependency>
```

## 核心组件

- 文本工具：提供文本处理相关功能
- 任务计划工具：提供cron表达式等相关功能
- 表单工具：提供表单设计器等功能
- JSON工具：提供JSON处理相关功能
- 项目工具：提供项目开发辅助工具

## 菜单功能

### 开发者工具
- 文本工具
  - 文本替换
  - 多行文本比较处理

- 任务计划工具
  - cron表达式生成和解析

- 表单工具
  - 表单设计器

- JSON工具
  - JSON对象转字符串
  - 批量提取JSON中的字段
  - 批量JSON转插入语句
  - 批量JSON解析

- 项目工具
  - 生成字典枚举Java类
  - 添加字段
  - 删除模型服务
  - 批量生成ID
  - 批量替换SQL插入ID

## 自动配置

组件通过 [ToolsAutoConfiguration](tools-boot-starter/src/main/java/com/particle/tools/ToolsAutoConfiguration.java) 实现自动配置，
并扫描 `com.particle.tools` 包下的组件，包括：
- Mapper接口：工具相关的数据访问层接口
- 应用服务：tools-app模块的应用服务类

该组件还集成了Swagger文档功能，提供了后端管理接口文档分组。
