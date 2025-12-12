# 全局任务计划模块

该模块旨在统一任务计划入口，规范信息和封装共用的处理逻辑，仅对Spring的@Scheduled注解方式生效。

## 功能特性

1. **任务调度**：基于Spring Scheduled实现任务调度
2. **统一入口**：提供统一的任务计划配置入口
3. **开关控制**：支持通过配置控制任务计划的启用和禁用
4. **异常处理**：提供任务执行异常处理机制

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-scheduler-boot-starter</artifactId>
</dependency>
```

### 配置选项

可以通过配置particle.scheduler.enabled=false关闭任务计划，但这并不代表@Scheduled注解的方式一定不会执行，这有可能其它依赖的组件开启了@EnableScheduling：
比如：在spring session的jdbc实现或redis实现时会默认启用 @EnableScheduling，参见：org.springframework.session.jdbc.config.annotation.web.http.JdbcHttpSessionConfiguration.SessionCleanupConfiguration

```yaml
particle:
  scheduler:
    enabled: true
```

### 核心组件

- 任务配置类：配置任务调度相关参数
- 任务执行器：负责任务的实际执行
- 异常处理器：处理任务执行过程中出现的异常

## 依赖组件

- Spring Boot Starter
- Spring Context Support

## 示例代码

```java
// 定义调度任务
@Component
public class ScheduledTasks {
    
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println("当前时间: " + new Date());
    }
    
    @Scheduled(cron = "0 0 12 * * ?")
    public void dailyTask() {
        // 每天中午12点执行的任务
    }
}

// 启用任务调度
@EnableScheduling
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```