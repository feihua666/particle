# 全局异常处理模块

该模块是Controller层全局异常处理模块，里面转换了常用的全局异常编码。后期有可能会合并到global-web-mvc-boot-starter模块中。

## 功能特性

1. **全局异常捕获**：统一捕获Controller层抛出的异常
2. **异常编码转换**：将异常转换为标准的错误响应格式
3. **日志记录**：自动记录异常信息到日志
4. **友好响应**：提供用户友好的错误信息响应

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-exception-handle-boot-starter</artifactId>
</dependency>
```

### 配置选项

该模块通过自动配置工作，通常不需要额外配置。

### 核心组件

- GlobalExceptionAdvice：全局异常处理切面
- GlobalExceptionHandleAutoConfiguration：自动配置类
- GlobalMvcExceptionDTO：全局MVC异常数据传输对象
- GlobalMvcExceptionListener：全局MVC异常监听器

## 依赖组件

- Spring Boot Starter Web
- Global Exception模块

## 示例代码

```java
// 控制器中抛出异常
@RestController
public class UserController {
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        if (id == null || id <= 0) {
            throw ExceptionFactory.bizException("用户ID无效");
        }
        // 业务逻辑
        return user;
    }
}

// 异常会自动被处理并返回标准格式响应
// {
//   "code": 40000000001,
//   "message": "用户ID无效",
//   "data": null
// }
```