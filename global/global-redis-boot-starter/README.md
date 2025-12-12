# 全局Redis模块

Redis在项目中非常常用，该模块提供了一个全局组件以方便集成和使用常用的Redis功能。
其中 spring-boot-starter-data-redis 只是组了一个局，默认依赖了 lettuce-core （redis的一个客户端，这里spring官方推荐的，原来的jedis不再默认使用）
同时也依赖了 spring-data-redis
说明：
1. 该模块暂只是提供了相关依赖，方便使用，但未封装成工具

## 功能特性

1. **Redis集成**：基于Spring Data Redis实现Redis集成
2. **连接池管理**：默认使用Lettuce客户端，提供高性能连接池
3. **序列化支持**：支持多种数据序列化方式
4. **缓存操作**：提供便捷的缓存操作接口

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-redis-boot-starter</artifactId>
</dependency>
```

### 配置选项

```yaml
spring:
  redis:
    host: localhost
    port: 6379
    database: 0
```

### 核心组件

- RedisTemplate：Redis操作模板
- StringRedisTemplate：字符串操作模板
- RedisRepository：Redis数据访问仓库
- Lettuce连接池：高性能Redis客户端连接池

## 依赖组件

- Spring Boot Starter Data Redis
- Lettuce Core：Redis客户端
- Spring Data Redis

## 示例代码

```java
// 使用RedisTemplate操作Redis
@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }
    
    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}

// 使用注解方式操作缓存
@Service
public class UserService {
    @Cacheable(value = "users", key = "#id")
    public User findById(Long id) {
        // 从数据库查询用户
        return userRepository.findById(id);
    }
}
```
