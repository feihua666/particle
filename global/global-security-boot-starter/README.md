# 全局web安全模块
集成springSecurity，封装了常用功能，方便扩展，统一配置  
跨域配置说明：  
在spring生态中，跨域配置一般有三种方式  
1 直接配置 corsFilter  
2 如果使用spring security，可以在security中配置，一般在 WebSecurityConfigurerAdapter继承该类并添加cors配置
由于security中配置也是添加corsFilter，如果和1重复同时配置，这得看两个的顺序谁在前，security中本质也是添加corsFilter，
所以security做的比较灵活，会复用 corsFilter自定义的配置，见org.springframework.security.config.annotation.web.builders.HttpSecurity.cors()注释。  
3 mvc配置，mvc也支持跨域配置，一般继承 WebMvcConfigurer 进行配置。  mvc是复用拦截器实现，所以其生效顺序是在corsFilter之后。

跨域配置建议：  
一般想全局跨域，不细分，直接配置corsFilter就好了，简单；  
如果项目使用了spring security，最好在security配置，security就是安全管理的；  
如果想在mvc个别接口是单独跨域，那么需要在mvc中配置比较好。  
本项目默认已在全局组件 global-web-filter-boot-starter中全局过滤器配置中(GlobalWebFilterAutoConfiguration)配置了corsFilter

## spring security 自带登录和授权页面css访问不了的问题解决
可以通过 https://ping.chinaz.com 多点ping， 配置一下host，一般也就开发时使用
```shell
#sping security
104.18.10.207 stackpath.bootstrapcdn.com maxcdn.bootstrapcdn.com
```

## 关于常用的租户和当前登录用户线程变量
1. 目前在security中添加了两个过滤器LoginUserToolPersistentSecurityFilter、TenantToolPersistentSecurityFilter
2. 必须保证 LoginUserToolPersistentSecurityFilter 在 TenantToolPersistentSecurityFilter 之前，有依赖关系
3. 以上保证解析租户（登录前根据域名配置解析租户，登录后以登录用户正在使用租户为准）和当前登录用户正在使用的租户，分别放到TenantTool和LoginUserTool中以供后续逻辑使用
4. 在用户登录中如果也同样设置了当前租户，这是因为有可能在登录的时候前面两个filter都没有解析到用户数据（在登录时如果前面已经解析到租户会延用已经解析到的租户）时设置当前租户以影响后续逻辑处理

## authorization server相关
参考文档：https://www.yii666.com/blog/520502.html
参考文档源码解读：https://blog.csdn.net/weixin_41866717/article/details/129027551
1. 默认是jdbc存储配置，所以需要依赖,本模块没有传递依赖，scope 为 provided,但其它模板有使用如：global-messaging-boot-starter，如果使用了 message 模块会间接引入
```xml
        <!-- jdbc shedlockjdbc支持时可以依赖使用，这里不传递依赖 -->
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
## resource server相关
spring sesurity 默认情况下资源服务器和授权服务器分开部署是没有问题的，如果想合并在一起部署需要自定义配置  
这可以从参考 com.particle.global.security.resourceserver.ResourceServerSecurityAutoConfiguration 配置类