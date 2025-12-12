# 使用次数组件

使用次数组件主要是记录一些功能的使用次数，包括但不限于前端功能、后端使用次数，用以限制某些功能的使用次数。

该组件有点类似于埋点，但区别于埋点，埋点主要用于统计，该组件主要用于计数并根据计数限制一些功能的使用。

## 使用方法

在项目的 pom.xml 中引入依赖：

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>usage-count-boot-starter</artifactId>
    <version>${particle.version}</version>
</dependency>
```

## 核心组件

- 使用次数定义管理：定义需要统计和限制使用次数的功能
- 使用次数配置管理：配置具体功能的使用次数限制规则
- 使用次数记录管理：记录和查看功能使用情况
- 使用次数记录明细：详细记录每次使用情况

## 菜单功能

- 使用次数管理（根菜单）
  - 使用次数定义管理页面
    - 查询、创建、删除、修改功能
  - 使用次数配置管理页面
    - 查询、创建、删除、修改功能
  - 使用次数记录管理页面
    - 查询、删除功能
    - 使用次数记录明细查询功能

## 自动配置

组件基于 Spring Boot AutoConfiguration 机制，在应用启动时会自动配置以下内容：
- 注册使用次数统计过滤器，用于拦截和统计功能使用情况
- 配置 Swagger 接口文档信息
- 扫描并注册相关 Mapper

## 字典数据

组件提供以下字典数据：
- 限制规则类型：按用户计数(user_count)、按租户计数(tenant_count)
- 限制周期：按天限制(day_period)、按周限制(week_period)、按月限制(month_period)、按季度限制(quarter_period)、按年限制(year_period)
