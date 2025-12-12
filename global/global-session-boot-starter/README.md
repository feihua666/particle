# HttpSession全局组件

基于Spring Session实现的HTTP会话管理组件，支持多种存储方式，包括内存存储、Redis存储和JDBC存储。支持通过HTTP Header或Cookie传递会话信息。

## 功能特性

1. **多种存储支持**：支持内存、Redis和JDBC三种会话存储方式
2. **灵活的会话传递**：支持通过HTTP Header或Cookie传递会话信息
3. **自动配置**：基于Spring Boot自动配置机制，简化配置过程
4. **数据库兼容**：针对不同数据库提供专门的SQL适配器
5. **并发安全**：解决会话属性设置时的并发问题

## 核心组件

### 配置类
- [SessionRepositoryConfiguration](src/main/java/com/particle/global/session/SessionRepositoryConfiguration.java)：会话存储配置类
- [GlobalSessionProperties](src/main/java/com/particle/global/session/GlobalSessionProperties.java)：全局会话属性配置

### 存储配置
- [SessionRepositoryMapConfiguration](src/main/java/com/particle/global/session/SessionRepositoryConfiguration.java)：内存存储配置
- [SessionRepositoryRedisConfiguration](src/main/java/com/particle/global/session/SessionRepositoryConfiguration.java)：Redis存储配置
- [SessionRepositoryJdbcConfiguration](src/main/java/com/particle/global/session/SessionRepositoryConfiguration.java)：JDBC存储配置

### 自定义适配器
- [JdbcSessionRepositoryCustomizer](src/main/java/com/particle/global/session/SessionRepositoryConfiguration.java)：JDBC会话存储自定义器
- 数据库方言支持：MySQL、Oracle、PostgreSQL、DB2

## 配置选项

### 会话传递方式

支持两种会话传递方式：

1. **HTTP Header传参**：默认Header名称为SESSION，与Cookie名称保持一致
2. **HTTP Cookie传参**：默认Cookie名称为SESSION，使用Spring Session原生实现

### 存储方式配置

使用以下注解启用相应存储方式：

1. `@EnableSpringHttpSession`：启用HTTP存储，需要自定义存储实现
2. `@EnableRedisHttpSession`：启用Redis存储，需要依赖spring-session-data-redis包
3. `@EnableJdbcHttpSession`：启用JDBC存储，需要依赖spring-session-jdbc包

### 数据库脚本

各数据库的SQL脚本位于：`classpath:org/springframework/session/jdbc`目录下

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-session-boot-starter</artifactId>
</dependency>
```

### 配置存储方式

在配置文件中指定会话存储类型：

```yaml
# 使用内存存储（默认）
particle.session.store-type=none

# 使用Redis存储
particle.session.store-type=redis

# 使用JDBC存储
particle.session.store-type=jdbc
```

### 数据库配置

当使用JDBC存储时，可以配置数据库类型以避免并发问题：

```yaml
particle.session.jdbc.type=mysql
```

支持的数据库类型包括：mysql、oracle、postgresql、db2

## 自动配置

模块通过以下自动配置类实现功能：
- [SessionRepositoryConfiguration](src/main/java/com/particle/global/session/SessionRepositoryConfiguration.java)：会话存储自动配置

## 依赖组件

- Spring Session Core
- Spring Session Data Redis (可选)
- Spring Session JDBC (可选)
- Spring Boot Starter Web (provided)
- global-swagger-boot-starter: Swagger接口文档支持

## 注意事项

Spring Session Repository允许多个实例存在，在Spring自动配置中如果检测到存在配置生效就会生成对应的仓库存储对象。

## 问题处理

### 并发问题

在使用Spring Session JDBC实现时，由于项目中添加了一个apiCount会话属性，如果没有设置该属性，当并发两个请求同时获取到没有该属性的情况时，都会添加insert语句，导致主键冲突。

目前的解决方案是在登录时添加该属性。根本解决办法是使用自定义SQL，参考：https://github.com/spring-projects/spring-session/issues/1213

Spring Session JDBC提供了自定义SQL适配，如MySQL：`org.springframework.session.jdbc.MySqlJdbcIndexedSessionRepositoryCustomizer`，但并未提供通过配置文件配置的方式。本组件兼容了该配置，参见：[SessionRepositoryConfiguration](src/main/java/com/particle/global/session/SessionRepositoryConfiguration.java)配置类。

### 响应头问题

在登录时返回数据响应头中可能没有c-token-id的情况，这是因为使用了Cookies里面的数据。