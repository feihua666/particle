# 全局Web安全模块

全局Web安全模块集成Spring Security，封装了常用功能，方便扩展和统一配置。提供认证授权、OAuth2服务、租户管理等安全相关功能。

## 功能特性

1. **Spring Security集成**：基于Spring Security实现认证和授权功能
2. **OAuth2授权服务器**：支持OAuth2协议的授权服务器实现
3. **资源服务器支持**：支持作为OAuth2资源服务器使用
4. **跨域配置支持**：提供灵活的跨域配置选项
5. **租户安全管理**：支持多租户环境下的安全控制
6. **线程变量管理**：提供登录用户和租户信息的线程上下文管理

## 核心组件

### 安全配置类
- [AuthorizationServerSecurityAutoConfiguration](src/main/java/com/particle/global/security/authorizationserver/AuthorizationServerSecurityAutoConfiguration.java)：OAuth2授权服务器自动配置
- [ResourceServerSecurityAutoConfiguration](src/main/java/com/particle/global/security/resourceserver/ResourceServerSecurityAutoConfiguration.java)：资源服务器安全配置
- [ResourceServerAuthorizationServerCombineSecurityAutoConfiguration](src/main/java/com/particle/global/security/resourceserver/ResourceServerAuthorizationServerCombineSecurityAutoConfiguration.java)：授权服务器和资源服务器合并部署配置

### 安全服务与存储
- [CustomJdbcRegisteredClientRepository](src/main/java/com/particle/global/security/authorizationserver/CustomJdbcRegisteredClientRepository.java)：自定义JDBC客户端存储实现
- [AuthorizationServerSecurityServiceAndRepositoryAutoConfiguration](src/main/java/com/particle/global/security/authorizationserver/AuthorizationServerSecurityServiceAndRepositoryAutoConfiguration.java)：授权服务器服务和存储自动配置

### 租户安全支持
- [LoginUserToolPersistentSecurityFilter](src/main/java/com/particle/global/security/tenant/LoginUserToolPersistentSecurityFilter.java)：登录用户信息持久化过滤器
- [TenantToolPersistentSecurityFilter](src/main/java/com/particle/global/security/tenant/TenantToolPersistentSecurityFilter.java)：租户信息持久化过滤器

## 配置选项

### 跨域配置说明

在Spring生态中，跨域配置一般有三种方式：

1. 直接配置corsFilter
2. 如果使用Spring Security，可以在security中配置，一般在WebSecurityConfigurerAdapter继承该类并添加cors配置
3. MVC配置，MVC也支持跨域配置，一般继承WebMvcConfigurer进行配置

跨域配置建议：
- 一般想全局跨域，不细分，直接配置corsFilter就好了，简单
- 如果项目使用了Spring Security，最好在security配置，security就是安全管理的
- 如果想在MVC个别接口是单独跨域，那么需要在MVC中配置比较好

本项目默认已在全局组件[global-web-filter-boot-starter](../global-web-filter-boot-starter)中全局过滤器配置中([GlobalWebFilterAutoConfiguration](../global-web-filter-boot-starter/src/main/java/com/particle/global/web/filter/GlobalWebFilterAutoConfiguration.java))配置了corsFilter。

### Spring Security自带登录和授权页面CSS访问问题解决

可以通过 https://ping.chinaz.com 多点ping，配置一下host，一般也就开发时使用：

```shell
#spring security
104.18.10.207 stackpath.bootstrapcdn.com maxcdn.bootstrapcdn.com
```

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-security-boot-starter</artifactId>
</dependency>
```

### 关于常用的租户和当前登录用户线程变量

1. 目前在security中添加了两个过滤器[LoginUserToolPersistentSecurityFilter](src/main/java/com/particle/global/security/tenant/LoginUserToolPersistentSecurityFilter.java)、[TenantToolPersistentSecurityFilter](src/main/java/com/particle/global/security/tenant/TenantToolPersistentSecurityFilter.java)
2. 必须保证[LoginUserToolPersistentSecurityFilter](src/main/java/com/particle/global/security/tenant/LoginUserToolPersistentSecurityFilter.java)在[TenantToolPersistentSecurityFilter](src/main/java/com/particle/global/security/tenant/TenantToolPersistentSecurityFilter.java)之前，有依赖关系
3. 以上保证解析租户（登录前根据域名配置解析租户，登录后以登录用户正在使用租户为准）和当前登录用户正在使用的租户，分别放到TenantTool和LoginUserTool中以供后续逻辑使用
4. 在用户登录中如果也同样设置了当前租户，这是因为有可能在登录的时候前面两个filter都没有解析到用户数据（在登录时如果前面已经解析到租户会延用已经解析到的租户）时设置当前租户以影响后续逻辑处理

## Authorization Server相关

参考文档：
- https://www.yii666.com/blog/520502.html
- https://blog.csdn.net/weixin_41866717/article/details/129027551

默认是jdbc存储配置，所以需要依赖，本模块没有传递依赖，scope为provided，但其它模块有使用如：global-messaging-boot-starter，如果使用了message模块会间接引入：

```xml
<!-- jdbc支持时可以依赖使用，这里不传递依赖 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
    <scope>provided</scope>
    <exclusions>
        <!-- 排除默认的logback，使用log4j2 -->
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

## Resource Server相关

Spring Security默认情况下资源服务器和授权服务器分开部署是没有问题的，如果想合并在一起部署需要自定义配置。

这可以参考[ResourceServerSecurityAutoConfiguration](src/main/java/com/particle/global/security/resourceserver/ResourceServerSecurityAutoConfiguration.java)配置类。

## 自动配置

模块通过以下自动配置类实现功能：
- [AuthorizationServerSecurityAutoConfiguration](src/main/java/com/particle/global/security/authorizationserver/AuthorizationServerSecurityAutoConfiguration.java)：OAuth2授权服务器自动配置
- [ResourceServerSecurityAutoConfiguration](src/main/java/com/particle/global/security/resourceserver/ResourceServerSecurityAutoConfiguration.java)：资源服务器安全配置
- [AuthorizationServerSecurityServiceAndRepositoryAutoConfiguration](src/main/java/com/particle/global/security/authorizationserver/AuthorizationServerSecurityServiceAndRepositoryAutoConfiguration.java)：授权服务器服务和存储自动配置

## 依赖组件

- Spring Boot Starter Security
- Spring Boot Starter OAuth2 Authorization Server
- Spring Boot Starter Web (provided)
- Spring Boot Starter JDBC (provided)
- global-dto: 全局数据传输对象
- global-swagger-boot-starter: Swagger接口文档支持
- global-concurrency-boot-starter: 并发处理支持