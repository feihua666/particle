# 全局表单验证模块

该模块对表单验证提供支持，包含自定义扩展验证注解。

## 功能特性

1. **表单验证**：基于Hibernate Validator提供表单验证功能
2. **自定义注解**：支持自定义验证注解扩展
3. **国际化支持**：支持多语言验证消息
4. **分组验证**：支持按组进行验证

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-validation-boot-starter</artifactId>
</dependency>
```

### 配置选项

```yaml
spring:
  messages:
    basename: i18n/messages
```

### 核心组件

- 自定义验证注解：扩展验证规则
- 验证器实现：实现具体的验证逻辑
- 验证工具类：提供便捷的验证方法
- 配置类：配置验证相关参数

## 依赖组件

- Spring Boot Starter Validation
- Hibernate Validator

## 示例代码

```java
// 定义带有验证注解的DTO
public class CreateUserRequest {
    @NotBlank(message = "用户名不能为空")
    @Size(min = 2, max = 20, message = "用户名长度必须在2-20个字符之间")
    private String username;
    
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d@$!%*?&]{8,}$",
             message = "密码必须包含大小写字母和数字，长度至少8位")
    private String password;
    
    @Email(message = "邮箱格式不正确")
    private String email;
    
    // getter和setter方法
}

// 在Controller中使用验证
@RestController
public class UserController {
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserRequest request) {
        // 参数会自动进行验证，验证失败会抛出异常
        User user = userService.create(request);
        return ResponseEntity.ok(user);
    }
}

// 自定义验证注解
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface PhoneNumber {
    String message() default "手机号格式不正确";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

// 自定义验证器实现
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    @Override
    public boolean isValid(String value, ConstraintValidationContext context) {
        if (value == null) return true;
        return value.matches("^1[3-9]\\d{9}$");
    }
}
```