# 全局数据传输对象模块

该模块提供全局的DTO（Data Transfer Object）数据对象依赖，参考了cola-component-dto设计理念。

## 功能特性

1. **DTO基类**：提供通用的DTO基类和接口
2. **命令对象**：定义标准的命令对象结构
3. **查询对象**：提供统一的查询对象规范
4. **响应对象**：定义标准的响应对象格式

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-dto</artifactId>
</dependency>
```

### 配置选项

该模块主要提供数据结构定义，通常不需要额外配置。

### 核心组件

- Command：命令对象基类
- Query：查询对象基类
- Response：响应对象基类
- DTO基类：通用数据传输对象基类

## 依赖组件

- Spring Boot Starter
- Swagger Annotations（用于API文档）

## 示例代码

```java
// 定义命令对象
public class CreateUserCommand extends Command {
    private String name;
    private int age;
    
    // getter和setter方法
}

// 定义查询对象
public class UserQuery extends Query {
    private String name;
    
    // getter和setter方法
}

// 定义响应对象
public class UserResponse extends Response {
    private Long id;
    private String name;
    private int age;
    
    // getter和setter方法
}
```