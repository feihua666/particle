# 全局审计监控组件
该模块依赖 srping actutor，对其进行扩展形成统一的监控方法调用  
该组件默认是可选的，需要自动引入

集成了 spring-boot-admin-starter-client 以注册到服务端并显露端点

# 禁用相关配置
1. 禁用bootadmin服务端
particle.actuator.bootadmin.server.enabled=false
2. 禁用bootadmin客户端
spring.boot.admin.client.enabled=false
3. 禁用actuator
management.endpoints.access.default=none
management.endpoints.web.exposure.include=
management.endpoint.prometheus.access=none
