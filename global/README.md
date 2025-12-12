# 全局模块

全局模块包含一系列根据技术要点建立的全局子模块，这些模块提供系统级的规范和功能，部分模块包含默认配置。

## 模块概览

全局模块是 Particle 项目的基础技术支撑层，提供各种通用技术和业务能力的支持。这些模块可以在整个系统中被复用，确保技术栈的一致性和系统的稳定性。

## 核心组件

- **global-actuator-boot-starter**: 系统监控和管理端点
- **global-ai-boot-starter**: AI能力集成支持
- **global-autoconfigure-boot-starter**: 自动配置模块
- **global-big-datasource-boot-starter**: 大数据源支持
- **global-bootstrap-boot-starter**: 系统启动配置
- **global-cache-boot-starter**: 缓存支持
- **global-captcha-boot-starter**: 验证码功能
- **global-catchlog-boot-starter**: 异常日志捕获
- **global-common**: 全局通用功能
- **global-concurrency-boot-starter**: 并发处理支持
- **global-crawler-boot-starter**: 爬虫功能支持
- **global-data-audit-boot-starter**: 数据审计功能
- **global-data-permission-boot-starter**: 数据权限控制
- **global-datasource-boot-starter**: 数据源配置
- **global-document-boot-starter**: 文档处理支持
- **global-domain-boot-starter**: 领域模型支持
- **global-dto**: 数据传输对象定义
- **global-elasticsearch-boot-starter**: Elasticsearch集成
- **global-exception**: 全局异常定义
- **global-exception-handle-boot-starter**: 异常处理机制
- **global-freemarker-boot-starter**: Freemarker模板引擎
- **global-logging-boot-starter**: 日志处理支持
- **global-messaging-boot-starter**: 消息处理机制
- **global-mybatis-plus-boot-starter**: MyBatis Plus集成
- **global-neo4j-boot-starter**: Neo4j图数据库支持
- **global-notification-boot-starter**: 通知服务支持
- **global-openapi-boot-starter**: OpenAPI规范支持
- **global-oss-boot-starter**: 对象存储服务
- **global-project-info-boot-starter**: 项目信息支持
- **global-ratelimit-boot-starter**: 限流功能
- **global-redis-boot-starter**: Redis集成
- **global-scheduler-boot-starter**: 任务调度支持
- **global-security-boot-starter**: 安全框架支持
- **global-session-boot-starter**: session分页式会话支持
- **global-swagger-boot-starter**: Swagger文档支持、springdoc文档支持
- **global-test**: 测试工具集
- **global-tool**: 全局工具集
- **global-trans-boot-stater**: 翻译支持
- **global-validation-boot-starter**: 参数校验支持
- **global-web-filter-boot-starter**: Web过滤器支持
- **global-web-mvc-boot-starter**: Web MVC配置
- **global-wxjava-boot-starter**: 微信Java开发支持

## 使用说明

全局模块中的各个starter模块可以通过Maven依赖引入到项目中，大多数模块都提供了自动配置功能，可以根据项目配置自动启用相应的功能。

注意：模块中有的已经提供了默认配置，也可以根据需要进行自定义配置。如要使用这些配置请在配置文件中指定。

例如，要在项目中使用Session功能，只需在pom.xml中添加以下依赖：

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-session-boot-starter</artifactId>
</dependency>
```
使用Session模块提供的默认配置，可以在配置文件中添加以下内容

```yaml
spring:
  profiles:
    include:
      - session
```
## 设计理念

全局模块的设计遵循以下原则：

1. **高内聚低耦合**: 每个模块专注于特定的技术领域，减少模块间的依赖
2. **开箱即用**: 大多数模块提供合理的默认配置，降低使用门槛
3. **可扩展性**: 模块设计考虑了可扩展性，允许根据具体需求进行定制
4. **统一规范**: 提供系统级的技术规范和标准，确保整个系统的一致性
