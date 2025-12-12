# 全局异常捕获并打印日志模块

该模块基于Spring AOP功能，结合@CatchAndLog注解，实现了在Service层全局捕获并打印日志的功能。该模块参考了COLA架构设计。

## 功能特性

1. **异常捕获**：通过AOP自动捕获Service层方法异常
2. **日志记录**：自动记录方法执行过程和异常信息
3. **非侵入式**：通过注解方式使用，无需修改业务代码
4. **可配置性**：支持通过配置控制是否启用日志记录功能

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-catchlog-boot-starter</artifactId>
</dependency>
```

### 配置选项

默认日志记录不开启，可通过以下配置开启：

```yaml
particle:
  catchlog:
    enabled: true
```

### 核心组件

- CatchAndLog：异常捕获和日志记录注解
- CatchAndLogAspect：AOP切面实现
- CatchLogAutoConfiguration：自动配置类

## 依赖组件

- Spring Boot Starter AOP
- Spring Context

## 示例代码

```java
// 在Service类或方法上添加注解
@Service
@CatchAndLog
public class UserService {
    
    public User findById(Long id) {
        // 业务逻辑，异常会被自动捕获和记录
        return userRepository.findById(id);
    }
}
```