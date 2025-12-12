# 全局测试模块

该模块提供全局的单元测试相关依赖及封装。

## 功能特性

1. **测试框架集成**：集成常用的测试框架和工具
2. **测试基类**：提供通用的测试基类和工具方法
3. **模拟支持**：支持Mockito等模拟框架
4. **断言工具**：提供丰富的断言工具方法

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-test</artifactId>
    <scope>test</scope>
</dependency>
```

### 配置选项

该模块主要用于测试环境，通常不需要特殊配置。

### 核心组件

- SuperTest：基础测试类
- AppTest：应用层测试类
- DomainTest：领域层测试类
- ControllerTest：控制器层测试类
- 各类测试工具类：提供测试辅助方法

## 依赖组件

- JUnit：单元测试框架
- Mockito：模拟框架
- Spring Boot Test：Spring Boot测试支持

## 示例代码

```java
// 继承测试基类编写单元测试
@SpringBootTest
public class UserServiceTest extends SuperTest {
    
    @Autowired
    private UserService userService;
    
    @MockBean
    private UserRepository userRepository;
    
    @Test
    public void testFindById() {
        // 准备测试数据
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("Test User");
        
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        
        // 执行测试
        User user = userService.findById(1L);
        
        // 验证结果
        assertNotNull(user);
        assertEquals("Test User", user.getName());
    }
}
```