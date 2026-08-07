# 全局审计监控组件

该模块基于 Spring Boot Actuator 进行扩展，提供统一的监控方法调用。通过集成 Micrometer 和 Prometheus，实现了应用程序性能监控和健康检查等功能。

注意：该模块依赖 Micrometer Tracing Bridge Brave (日志链路追踪)

## 功能特性

1. **服务可用性控制**：提供 RESTful 接口用于动态控制服务状态（上线、下线、存活、宕机）
2. **监控指标收集**：基于 Micrometer 实现通用监控接口，支持时间记录和计数统计
3. **Spring Boot Admin 集成**：内建支持 Spring Boot Admin Client 和 Server
4. **Prometheus 集成**：开箱即用地支持 Prometheus 监控指标导出
5. **健康探针支持**：支持 Kubernetes 风格的就绪和存活探针

### 服务可用性控制
- [ApplicationAvailabilityController](src/main/java/com/particle/global/actuator/endpoint/ApplicationAvailabilityController.java)：REST API 控制器，提供四种状态变更接口：
  - `/actuator/available/refusing_traffic`：服务下线
  - `/actuator/available/accepting_traffic`：服务上线
  - `/actuator/available/broken`：标记为宕机
  - `/actuator/available/correct`：标记为正常运行
- [AvailableTool](src/main/java/com/particle/global/actuator/endpoint/AvailableTool.java)：可用性状态管理工具类
- 自定义 Actuator Endpoints：
  - [ApplicationAvailableAcceptingTrafficEndpoint](src/main/java/com/particle/global/actuator/endpoint/ApplicationAvailableAcceptingTrafficEndpoint.java)
  - [ApplicationAvailableCorrectEndpoint](src/main/java/com/particle/global/actuator/endpoint/ApplicationAvailableCorrectEndpoint.java)
  - [ApplicationAvailableRefusingTrafficEndpoint](src/main/java/com/particle/global/actuator/endpoint/ApplicationAvailableRefusingTrafficEndpoint.java)
  - [ApplicationAvailablebrokenEndpoint](src/main/java/com/particle/global/actuator/endpoint/ApplicationAvailablebrokenEndpoint.java)

### Spring Boot Admin
- [GlobalSpringBootAdminServerAutoConfiguration](src/main/java/com/particle/global/actuator/bootadmin/server/GlobalSpringBootAdminServerAutoConfiguration.java)：Spring Boot Admin Server 自动配置

## 配置选项

### 启用/禁用配置

1. 禁用 Spring Boot Admin 服务端：
   ```yaml
   particle:
     global:
       actuator:
         bootadmin:
           server:
             enabled: false
   ```

2. 禁用 Spring Boot Admin 客户端：
   ```yaml
   spring:
     boot:
       admin:
         client:
           enabled: false
   ```

3. 禁用 Actuator Endpoints：
   ```yaml
   management:
     endpoints:
       web:
         # 配置哪些端点被暴露
         exposure:
           include: ""
       # 控制已暴露的端点允许何种操作
       access:
         default: none
     endpoint:
       prometheus:
         access: none
   ```

### 默认配置

模块提供了以下默认配置文件：
- [application-actuator.yml](src/main/resources/application-actuator.yml)：Actuator 相关配置
- [application-boot-admin-client-local.yml](src/main/resources/application-boot-admin-client-local.yml)：本地开发环境的 Boot Admin Client 配置
- [application-boot-admin-server.yml](src/main/resources/application-boot-admin-server.yml)：Boot Admin Server 配置

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-actuator-boot-starter</artifactId>
</dependency>
```

### 监控使用示例

```java
// 记录方法执行时间
MonitorTool.timer("my.method.timer", durationMs, "方法执行时间");

// 记录事件发生次数
MonitorTool.count("my.event.counter", "事件计数");
```

### 权限控制

服务可用性控制接口默认需要特定角色权限才能访问：
- `refusing_traffic`：需要 `availabel_refusing_traffic` 或超级管理员角色
- `accepting_traffic`：需要 `availabel_accepting_traffic` 或超级管理员角色
- `broken`：需要 `availabel_broken` 或超级管理员角色
- `correct`：需要 `availabel_correct` 或超级管理员角色
