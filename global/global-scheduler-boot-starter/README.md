# 全局任务计划模块
旨在统一任务计划入口，规范一些信息和封装一些共用的处理逻辑
可以通过配置 particle.scheduler.enabled=false 关闭任务计划，但这并不代理 @Scheduled注解的方式一定不会执行，这有可能其它依赖的组件开启了 @EnableScheduling  
比如：在spring session的jdbc实现或redis实现时会默认启用 @EnableScheduling，参见：org.springframework.session.jdbc.config.annotation.web.http.JdbcHttpSessionConfiguration.SessionCleanupConfiguration