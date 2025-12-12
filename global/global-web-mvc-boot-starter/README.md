# 全局MVC模块

该模块对MVC常用功能进行封装，目前实现了当前登录用户注入参数功能。

## 功能特性

1. **MVC封装**：封装Spring MVC常用功能
2. **用户注入**：自动注入当前登录用户信息
3. **参数解析**：支持自定义参数解析器
4. **响应处理**：统一响应格式处理

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-web-mvc-boot-starter</artifactId>
</dependency>
```

### 配置选项

```yaml
particle:
  web:
    mvc:
      # MVC相关配置
```

### 核心组件

- GlobalWebMvcAutoConfiguration：全局MVC自动配置
- LoginUserArgumentResolver：登录用户参数解析器
- 全局异常处理器：统一处理MVC层异常
- 响应包装器：统一响应格式包装

## 依赖组件

- Spring Boot Starter Web
- Global Security模块

## 示例代码

```java
// 在Controller中使用登录用户参数
@RestController
public class UserController {
    
    // 自动注入当前登录用户
    @GetMapping("/users/profile")
    public User getProfile(@CurrentUser LoginUser loginUser) {
        return userService.findById(loginUser.getId());
    }
    
    // 使用标准参数
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }
}

// 自定义参数解析器
@Component
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {
    
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(CurrentUser.class) != null
                && parameter.getParameterType().equals(LoginUser.class);
    }
    
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        // 从安全上下文中获取当前用户
        return LoginUserTool.getCurrentLoginUser();
    }
}
```