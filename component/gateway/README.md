# Gateway 网关模块

## 模块概述

Gateway 模块是基于 Spring Cloud Gateway 构建的微服务网关，提供统一的入口、路由转发、安全认证、限流熔断等功能。

本模块包含两个子模块：
- **gateway-webflux-start**: 基于 WebFlux 的响应式网关（推荐）
- **gateway-webmvc-start**: 基于 WebMvc 的传统网关

## 技术栈

- Spring Cloud Gateway
- Spring Cloud Alibaba Nacos (服务发现)
- Spring Cloud LoadBalancer (负载均衡)
- Spring Boot WebFlux/WebMvc
- Knife4j/Swagger (API 文档)

## 端口分配

- gateway-webflux-start: **8051**
- gateway-webmvc-start: **8050**

## 快速开始

### 1. 环境要求

- JDK 17+
- Maven 3.6+
- Nacos Server

### 2. 配置 Nacos

在 `application.yml` 中配置 Nacos 地址：

```yaml
spring:
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
    openfeign:
      client:
        config:
          default:
            # 开启 feign client 调用日志日志，需要设置日志级别为 debug，参见：feign.slf4j.Slf4jLogger
            loggerLevel: FULL
```

### 3. 启动服务

```bash
# 启动 WebFlux 版本
cd gateway-webflux-start
mvn spring-boot:run

# 或启动 WebMvc 版本
cd gateway-webmvc-start
mvn spring-boot:run
```

## 核心功能

### 1. 路由配置

#### 方式一：配置文件配置

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: func-service
          uri: lb://func-start
          predicates:
            - Path=/api/func/**
          filters:
            - StripPrefix=1
```

#### 方式二：代码配置

参见 `GatewayRouteConfig.java`：

```java
@Bean
public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    return builder.routes()
            .route("func-route", r -> r
                    .path("/api/func/**")
                    .filters(f -> f.stripPrefix(1))
                    .uri("lb://func-start"))
            .build();
}
```

### 2. 全局过滤器

已实现的全局过滤器：

- **GlobalLoggingFilter**: 请求日志记录，包含请求方法、路径、IP、耗时等信息
- 可扩展实现：权限验证、限流、熔断等

### 3. 跨域配置

已配置全局 CORS 支持，允许跨域请求。

### 4. 服务发现与负载均衡

集成 Nacos 服务发现，自动注册和发现服务，支持客户端负载均衡。

## 扩展开发

### 添加新的路由规则

1. 在 `GatewayRouteConfig.java` 中添加新的路由配置
2. 或在 `application.yml` 中添加路由配置

### 添加自定义过滤器

创建实现 `GlobalFilter` 接口的类：

```java
@Component
public class CustomFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 自定义逻辑
        return chain.filter(exchange);
    }
    
    @Override
    public int getOrder() {
        return 0; // 优先级
    }
}
```

### 添加限流功能

可以使用 Redis + Lua 脚本实现限流，或使用 Sentinel 集成。

## API 文档

启动后访问：
- Swagger UI: `http://localhost:8050/webjars/swagger-ui/index.html`
- Knife4j UI: `http://localhost:8050/doc.html`

## 监控与管理

Spring Boot Actuator 已启用，可访问：
- 健康检查：`http://localhost:8050/actuator/health`
- 指标监控：`http://localhost:8050/actuator/metrics`
- 路由信息：`http://localhost:8050/actuator/gateway/routes`

## 生产环境建议

1. **安全配置**
   - 配置认证授权机制
   - 启用 HTTPS
   - 限制 CORS 来源

2. **性能优化**
   - 调整连接池大小
   - 配置合适的超时时间
   - 启用响应式压缩

3. **高可用**
   - 多实例部署
   - 配置健康检查
   - 集成熔断降级

4. **日志管理**
   - 配置日志级别
   - 集成 ELK 日志收集
   - 添加链路追踪（Sleuth/Zipkin）

## 常见问题

### Q: 如何选择 WebFlux 还是 WebMvc 版本？
A: 推荐使用 WebFlux 版本，具有更好的性能和响应式支持。如果需要兼容传统的 Servlet 容器，可以选择 WebMvc 版本。

### Q: 如何配置动态路由？
A: 可以通过 Nacos 配置中心动态下发路由配置，或通过管理端点实时更新路由。

### Q: 如何实现统一认证？
A: 可以在全局过滤器中校验 Token，或集成 OAuth2、JWT 等认证机制。

## 开发者

- Author: yangwei
- Since: 2026/3/3

## 参考文档

- [Spring Cloud Gateway 官方文档](https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/)
- [Spring Cloud Alibaba 文档](https://sca.aliyun.com/docs/)
