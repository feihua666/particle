# 验证码提供支持

提供在系统核心操作时的验证码功能，防止恶意操作和自动化攻击。
说明：
1. 该模块的动态动态验证码功能实现依赖 message 业务模块的实现以将验证码发送给用户。
2. 支持以url校验的配置方式，用户可以根据自身需求进行配置，无需修改代码。
## 功能特性

1. **图形验证码**：支持多种类型的图形验证码，包括数字、字母、算术等
2. **动态验证码**：支持通过消息服务发送的动态验证码
3. **多存储支持**：支持内存、数据库等多种存储方式
4. **安全防护**：防止暴力破解，支持验证失败次数限制
5. **易于集成**：提供标准接口，便于与现有安全框架集成

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-captcha-boot-starter</artifactId>
</dependency>
```

### 配置选项

默认配置已经可以满足大部分需求，但你可以通过以下配置进行定制：

```yaml
particle:
  captcha:
    # 验证码相关配置
```

### 核心组件

- CaptchaService：验证码生成和验证服务接口
- CaptchaController：提供验证码相关 REST API
- CaptchaStorage：验证码存储接口及其实现

## 依赖组件

- EasyCaptcha：图形验证码生成库
- Spring Boot Starter Web
- Spring Boot Security（用于权限保护）
- JDBC（可选，用于数据库存储）

## 示例代码

```java
// 生成验证码
@Autowired
private CaptchaService captchaService;

public void generateCaptcha() {
    String captchaId = captchaService.generateCaptcha();
    // 返回给前端
}

// 验证验证码
public boolean validateCaptcha(String captchaId, String userInput) {
    return captchaService.validateCaptcha(captchaId, userInput);
}
```
