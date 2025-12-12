# 全局限流模块

该模块提供全局限流功能，支持 HTTP 请求的本地限流，防止系统因突发流量而过载。
说明：
1. 该模块依赖 Google Guava 的 RateLimiter 实现，目前是单机版本，不支持集群，在集群模式下可以根据实例数量配置限流量
## 功能特性

1. **本地限流**：基于 Guava 的 RateLimiter 实现高效的本地限流
2. **注解支持**：通过 @RateLimit 注解快速为接口添加限流功能
3. **监控集成**：与全局监控模块集成，提供限流统计和监控功能
4. **拦截器机制**：基于 Spring MVC 拦截器实现，无侵入性

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-ratelimit-boot-starter</artifactId>
</dependency>
```

### 配置选项

可以通过以下配置项调整限流行为：

```yaml
particle:
  rate-limit:
    # 限流相关配置
```

### 核心组件

- RateLimitInterceptor：限流拦截器，负责具体的限流逻辑
- RateLimitAnnotation：限流注解，用于标记需要限流的接口
- RateLimiterRegistry：限流器注册中心，管理所有的限流器实例
- MonitorIntegration：与监控模块集成，记录限流事件

## 依赖组件

- Google Guava：提供 RateLimiter 限流实现
- Spring Boot Starter Web
- Global Actuator：提供监控支持
