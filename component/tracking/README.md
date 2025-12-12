# 埋点记录组件

埋点记录组件主要记录前端自行调用埋点接口，关于数据统计与展示暂未实现。

## 使用方法

在项目的 pom.xml 中引入依赖：

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>tracking-boot-starter</artifactId>
    <version>${particle.version}</version>
</dependency>
```

## 核心组件

- 埋点页面管理：管理需要埋点的页面定义
- 页面埋点记录：记录页面埋点的具体数据

## 菜单功能

### 埋点管理
- 埋点页面管理
  - 查询、创建、删除、修改埋点页面

- 页面埋点记录
  - 查询页面埋点记录


## 自动配置

组件通过 [TrackingAutoConfiguration](tracking-boot-starter/src/main/java/com/particle/tracking/TrackingAutoConfiguration.java) 实现自动配置，
并扫描 `com.particle.tracking` 包下的组件，包括：
- Mapper接口：埋点相关的数据访问层接口
- 应用服务：tracking-app模块的应用服务类

该组件还集成了Swagger文档功能，提供了后端管理接口文档分组。
