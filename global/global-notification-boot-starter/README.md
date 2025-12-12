# 全局通知模块

该模块定义了适用全局的通知规范，并提供默认的通知实现。通知的范围比较广泛，该模块主要处理系统内通知情况。

## 功能特性

1. **通知规范**：定义统一的通知参数和接口规范
2. **多种通知方式**：支持站内信、邮件、短信等多种通知方式
3. **可扩展设计**：支持自定义通知渠道和实现
4. **异步处理**：支持异步通知发送，提高系统性能

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-notification-boot-starter</artifactId>
</dependency>
```

### 配置选项

该组件默认是可选的，需要自动引入：

```yaml
particle:
  notification:
    # 通知相关配置
```

### 核心组件

- NotifyParam：通知参数类
- NotifyTool：通知工具类
- 通知发送器：具体实现各类通知的发送逻辑

## 依赖组件

- Spring Boot Starter
- 全局异常模块

## 示例代码

```java
// 发送通知
@Service
public class NotificationService {
    public void sendNotification(String title, String content) {
        NotifyParam notifyParam = NotifyParam.system();
        notifyParam.setTitle(title);
        notifyParam.setContent(content);
        NotifyTool.notify(notifyParam);
    }
}
```