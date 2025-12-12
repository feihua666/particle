# 全局缓存模块

该模块旨在为系统中的多查询少写入场景提供高性能的查询速度，封装了基于Spring的缓存功能。
说明：
1. 需要在启动类上添加@EnableCaching注解
2. @EnableCaching只是配置拦截器，以实现缓存功能
3. CacheManager是缓存管理器，用于管理缓存实例，是通过 CacheAutoConfiguration 自动配置的
4. 默认使用内存缓存，如果不配置缓存类型，则默认使用内存缓存，其机制是通过 CacheAutoConfiguration 中的配置选择器根据缓存类型动态导入 SimpleCacheConfiguration，SimpleCacheConfiguration 通过 CacheCondition 自动配置
## 功能特性

1. **缓存抽象**：基于Spring Cache提供统一的缓存操作接口
2. **多种实现**：支持内存缓存、Redis缓存等多种缓存实现
3. **自动配置**：通过starter方式自动配置缓存相关组件
4. **灵活切换**：可通过添加不同依赖轻松切换缓存实现
5. **多级缓存**：基于Jetcache实现多级缓存

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-cache-boot-starter</artifactId>
</dependency>
```

### 配置选项

默认使用内存Map缓存，可通过添加其他依赖切换缓存实现：

```yaml
spring:
  cache:
    type: redis  # 使用Redis缓存
```

### 核心组件

- CacheManager：缓存管理器
- Cacheable注解：方法级缓存注解
- CacheEvict注解：缓存清除注解

## 依赖组件

- Spring Boot Starter Cache
- Spring Context Support

## 示例代码

```java
// 启用缓存
@EnableCaching
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

// 使用缓存
@Service
public class UserService {
    @Cacheable("users")
    public User findById(Long id) {
        // 查询用户逻辑
        return user;
    }
}
```
