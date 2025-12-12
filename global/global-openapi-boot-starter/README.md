# 全局开放接口服务模块

该模块旨在提供开放接口服务，提供统一的技术支持，包括验证签名、定义统一开放接口错误编码等。

## 功能特性

1. **接口签名验证**：提供统一的接口签名验证机制
2. **错误编码规范**：定义统一的开放接口错误编码体系
3. **安全控制**：提供接口访问安全控制
4. **文档支持**：支持开放接口文档自动生成

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-openapi-boot-starter</artifactId>
</dependency>
```

### 配置选项

```yaml
particle:
  openapi:
    # 开放接口相关配置
```

### 核心组件

- 签名验证器：验证接口请求签名
- 错误编码管理器：管理开放接口错误编码
- 安全过滤器：处理接口访问安全控制

## 依赖组件

- Spring Boot Starter Web
- 全局安全模块

## 示例代码

```java
// 开放接口控制器
@RestController
@RequestMapping("/openapi")
public class OpenApiController {
    
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest request, 
                                         @RequestHeader("signature") String signature) {
        // 验证签名
        if (!signatureValidator.validate(request, signature)) {
            throw new OpenApiException("签名验证失败");
        }
        
        // 业务逻辑
        User user = userService.create(request);
        return ResponseEntity.ok(user);
    }
}
```