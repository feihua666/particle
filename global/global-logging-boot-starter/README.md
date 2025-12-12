# 全局日志模块

该模块封装了Log4j2日志模块，并配置了常用的日志格式，适用于所有Spring Boot项目。

## 功能特性

1. **Log4j2集成**：基于Log4j2实现高性能日志记录
2. **标准日志格式**：配置了统一的日志输出格式
3. **多环境支持**：支持不同环境下的日志级别配置
4. **性能优化**：利用Log4j2的异步日志提升性能

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-logging-boot-starter</artifactId>
</dependency>
```

### 配置选项

```yaml
logging:
  level:
    com.particle: INFO
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
```

### 核心组件

- Log4j2配置文件：定义日志输出格式和级别
- 日志工具类：提供便捷的日志记录方法
- 自动配置类：负责日志系统的自动配置

## 依赖组件

- Spring Boot Starter Logging（Log4j2）
- Log4j2 Core

## 示例代码

```java
// 使用日志记录
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    
    public User findById(Long id) {
        logger.info("开始查询用户，ID: {}", id);
        try {
            // 业务逻辑
            User user = userRepository.findById(id);
            logger.info("查询用户成功，ID: {}", id);
            return user;
        } catch (Exception e) {
            logger.error("查询用户失败，ID: {}", id, e);
            throw e;
        }
    }
}
```