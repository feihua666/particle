# 全局领域模型模块

该模块主要提供领域模型相关的基础支持，包括@Entity注解和DomainFactory辅助类。

## 功能特性

1. **实体注解**：提供@Entity注解，将Spring Bean的scope定义为prototype
2. **领域工厂**：提供DomainFactory辅助类，帮助应用创建Domain Entity
3. **状态管理**：确保Domain Entity的有状态特性，避免多线程共享问题
4. **生命周期管理**：支持领域对象的完整生命周期管理

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-domain-boot-starter</artifactId>
</dependency>
```

### 配置选项

该模块主要通过注解方式使用，通常不需要额外配置。

### 核心组件

- @Entity：领域实体注解，定义Bean为prototype作用域
- DomainFactory：领域工厂类，用于创建Domain Entity实例
- ApplicationContextHelper：应用上下文助手类

## 依赖组件

- Spring Context
- Spring Beans

## 示例代码

```java
// 使用@Entity注解定义领域实体
@Entity
public class User {
    private String name;
    private int age;
    
    // 构造函数、getter和setter方法
}

// 使用DomainFactory创建领域实体
@Service
public class UserService {
    @Autowired
    private DomainFactory domainFactory;
    
    public User createUser(String name, int age) {
        User user = domainFactory.create(User.class);
        user.setName(name);
        user.setAge(age);
        return user;
    }
}
```