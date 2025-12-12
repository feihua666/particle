# 全局接口文档模块

该模块封装了Swagger常用配置，方便统一管理和适配，支持从项目信息模块展示首页信息。

## 功能特性

1. **接口文档**：基于Swagger/Knife4j提供接口文档功能
2. **统一配置**：封装常用Swagger配置，便于统一管理
3. **项目信息集成**：支持从项目信息模块展示首页信息
4. **注解支持**：支持丰富的Swagger注解用于文档描述

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-swagger-boot-starter</artifactId>
</dependency>
```

### 配置选项

```yaml
springdoc:
  swagger-ui:
    path: /swagger-ui.html
```

注意：@Schema value值不能全局相同，若相同，可能出现参数显示混乱。

### 核心组件

- Swagger配置类：配置Swagger相关参数
- Knife4j集成：提供增强的UI界面
- 自动配置类：负责Swagger的自动配置

## 依赖组件

- SpringDoc OpenAPI
- Knife4j Spring Boot Starter
- Global Project Info模块

## 示例代码

```java
// 使用Swagger注解描述接口
@RestController
@RequestMapping("/api/users")
@Tag(name = "用户管理", description = "用户相关的接口")
public class UserController {
    
    @PostMapping
    @Operation(summary = "创建用户", description = "创建一个新的用户")
    public User createUser(
            @RequestBody @Parameter(description = "用户信息") CreateUserRequest request) {
        // 业务逻辑
        return user;
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "获取用户", description = "根据ID获取用户信息")
    public User getUser(
            @PathVariable @Parameter(description = "用户ID") Long id) {
        // 业务逻辑
        return user;
    }
}
```