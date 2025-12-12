# 全局翻译模块

该模块主要在前端展示时，将ID翻译为名称。

## 功能特性

1. **数据翻译**：将数据ID翻译为可读的名称
2. **前端集成**：专为前端展示场景设计
3. **缓存支持**：支持翻译结果缓存，提高性能
4. **灵活配置**：支持多种翻译规则配置

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-trans-boot-starter</artifactId>
</dependency>
```

### 配置选项

```yaml
particle:
  trans:
    # 翻译相关配置
```

### 核心组件

- TransService：翻译服务接口
- TransHandler：翻译处理器
- 翻译注解：用于标记需要翻译的字段
- 缓存管理器：管理翻译结果缓存

## 依赖组件

- Spring Boot Starter
- Global Cache模块（可选）

## 示例代码

```java
// 在实体类中使用翻译注解
public class OrderVO {
    private Long userId;
    
    @Trans(type = "user", field = "userName")
    private String userName;
    
    // getter和setter方法
}

// 在服务类中使用翻译功能
@Service
public class OrderService {
    @Autowired
    private TransService transService;
    
    public List<OrderVO> getOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderVO> vos = convertToVO(orders);
        
        // 执行翻译
        transService.trans(vos);
        
        return vos;
    }
}
```