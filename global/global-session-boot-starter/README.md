# httpsession全局组件
依赖spring session，支持两种方式：
1. 请求httpHeader 传参，默认 HeaderName=SESSION 和 cookieName保持一致  
2. 请求http Cookie 传参，默认 cookieName=SESSION, 这个是原生springsession的实现没有做改动

使用时需要添加注解启用，可用的常用注解有：
1. EnableSpringHttpSession 启用http，存储需要自定义
2. EnableRedisHttpSession 启动redis存储，需要依赖 spring-session-data-redis包  