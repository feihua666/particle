# 全局异常模块

该模块提供全局异常处理机制，参考了cola-component-exception设计理念。

## 功能特性

1. **异常层次结构**：定义清晰的异常类层次结构
2. **业务异常**：提供业务相关的异常类型
3. **系统异常**：处理系统级异常情况
4. **异常工厂**：提供异常创建的工厂方法

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-exception</artifactId>
</dependency>
```

### 配置选项

该模块主要提供异常类定义，通常不需要额外配置。

### 核心组件

- BaseException：异常基类
- BizException：业务异常类
- SysException：系统异常类
- ExceptionFactory：异常工厂类
- ErrorCode：错误码枚举

## 依赖组件

- 无强制依赖

## 示例代码

```java
// 抛出业务异常
public class UserService {
    public User findById(Long id) {
        if (id == null || id <= 0) {
            throw ExceptionFactory.bizException("用户ID不能为空");
        }
        // 业务逻辑
        return user;
    }
}

// 自定义业务异常
public class UserNotFoundException extends BizException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
```