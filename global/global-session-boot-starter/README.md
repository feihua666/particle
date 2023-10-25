# httpsession全局组件
依赖spring session，支持两种方式：
1. 请求httpHeader 传参，默认 HeaderName=SESSION 和 cookieName保持一致  
2. 请求http Cookie 传参，默认 cookieName=SESSION, 这个是原生springsession的实现没有做改动

使用时需要添加注解启用，可用的常用注解有：
1. EnableSpringHttpSession 启用http，存储需要自定义
2. EnableRedisHttpSession 启动redis存储，需要依赖 spring-session-data-redis包  
3. 各数据库的sql脚本在classpath:org/springframework/session/jdbc目录下

## 注意
spring session repository 允许有多个实例，在spring auto configure中如果检测到存在配置生效就生成对应的仓库存储对象


## 问题处理
在使用spring session jdbc实现时，由于本项目中添加了 一个 apiCount session属性，如果在没有设置该属性，就有并发两个请求，同时获取到没有该属性，如果都设置了该属性
而会都添加insert语句，导致主键冲突，问题解决目前是在登录是添加了该属性，勉强解决，查看了github有关该问题的讨论，最终解决办法是使用自定义sql，参见：https://github.com/spring-projects/spring-session/issues/1213
对此spring session jdbc给出了自定义sql适配，如mysql：org.springframework.session.jdbc.MySqlJdbcIndexedSessionRepositoryCustomizer，但并未提供通过配置文件配置的方式,本组件兼容了该配置 参见：com.particle.global.session.SessionRepositoryConfiguration 配置类  
在登录时返回数据响应头中可能没有c-token-id的情况，这是因为使用了cookies里面的数据，
