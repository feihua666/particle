
# 测试代码规范

## 命名规范

### 测试类命名
- 测试类应以 `Test` 结尾,如 `UserServiceTest`
- 或以 `Test` 开头,如 `TestUserService`
- 保持与被测试类的对应关系

### 测试基类
所有测试类必须继承对应的测试基类,根据被测试代码所在的层级选择合适的基类:

**Application Client 测试 → 继承 `AppClientTest`**
- 适用场景: 测试应用层客户端、外部服务调用
- 示例: `UserAppClientTest`, `PaymentAppClientTest`

**Application 测试 → 继承 `AppTest`**
- 适用场景: 测试应用层服务、用例编排
- 示例: `UserApplicationServiceTest`, `OrderApplicationServiceTest`

**Controller 测试 → 继承 `ControllerTest`**
- 适用场景: 测试 REST API、Controller 层
- 示例: `UserControllerTest`, `OrderControllerTest`

**Domain 测试 → 继承 `DomainTest`**
- 适用场景: 测试领域模型、领域服务、业务逻辑
- 示例: `UserDomainServiceTest`, `OrderDomainServiceTest`

**Feign Client 测试 → 继承 `FeignClientTest`**
- 适用场景: 测试 Feign 远程调用客户端
- 示例: `OrderFeignClientTest`, `PaymentFeignClientTest`

**Infrastructure 测试 → 继承 `InfrastructureTest`**
- 适用场景: 测试基础设施层、Repository、数据访问
- 示例: `UserRepositoryTest`, `OrderRepositoryTest`

**其他/通用测试 → 继承 `SuperTest`**
- 适用场景: 不属于上述分类的测试
- 示例: `DateUtilTest`, `StringHelperTest`

### 测试方法命名
- 使用描述性命名,清晰表达测试意图
- 推荐格式: `should_ExpectedBehavior_When_Condition`
- 或使用: `given_When_Then` 格式
- 示例: `shouldReturnUser_WhenValidIdProvided()`
- 

## 测试结构

### 使用 AAA 模式
```java
@Test
void shouldCalculateTotal_WhenItemsAdded() {
    // Arrange - 准备测试数据
    Cart cart = new Cart();
    
    // Act - 执行被测试的操作
    cart.addItem(new Item("Book", 10.0));
    
    // Assert - 验证结果
    assertEquals(10.0, cart.getTotal());
}
```

## 断言规范

### 使用有意义的断言消息
```java
// ❌ 不好
assertEquals(5, result);

// ✅ 好
assertEquals(5, result, "购物车应包含5个商品");
```
### 使用测试框架的断言方法
```java
// ❌ 错误:使用 Java assert 关键字
assert result != null : "Execution result should not be null";
assert result.containsKey("status") : "Result should contain status";
```
```java
// ✅ 正确:使用测试框架断言
assertNotNull(result, "Execution result should not be null");
assertTrue(result.containsKey("status"), "Result should contain status");
```

## 测试粒度原则

### 优先使用大粒度功能单元

**以最大的功能入口作为测试单元**,除非技术上无法实现。
```java
// ✅ 好:测试完整的业务功能
@Test
void testCreateOrder() {
    // 一次测试覆盖:验证库存、计算价格、扣减库存、生成订单等完整流程
    Order order = orderService.createOrder(userId, items);
    assertNotNull(order);
    
    // 在同一个测试方法中,用不同参数测试各种场景
    assertThrows(InsufficientStockException.class, 
        () -> orderService.createOrder(userId, outOfStockItems));
}
```
```java
// ❌ 避免:过度拆分
// 不要为同一个功能创建多个测试类或测试方法
class OrderValidationTest { ... }
class OrderPriceTest { ... }
class OrderStockTest { ... }
```

### 一个测试方法,多个参数场景

如果一个测试方法加多个参数用例就能满足,就不要写多个测试方法或测试类。
```java
@Test
void testUserLogin() {
    // 正常场景
    assertTrue(userService.login("user", "password"));
    
    // 错误密码
    assertFalse(userService.login("user", "wrongpass"));
    
    // 空值
    assertThrows(IllegalArgumentException.class, 
        () -> userService.login(null, "password"));
}
```

## 注解规范

- 遵循 javadoc 规范,添加必要的注释
- 测试方法上也要添加注释,说明测试目的和场景

## 代码覆盖率要求

- 所有 Service 类必须有对应的单元测试，覆盖率 ≥ 80%
- Controller 类使用 MockMvc 进行测试，覆盖率 ≥ 70%
- 工具类必须有完整的单元测试，覆盖率 ≥ 85%
