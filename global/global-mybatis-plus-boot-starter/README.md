# 全局MyBatis Plus封装模块

该模块对MyBatis Plus进行了封装，提供了常用的CRUD操作和扩展功能。

## 功能特性

1. **CRUD封装**：提供基础的增删改查操作封装
2. **租户支持**：内置多租户支持
3. **数据审计**：支持数据操作审计功能
4. **安全填充**：支持自动填充创建人、更新人等安全字段

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-mybatis-plus-boot-starter</artifactId>
</dependency>
```

### 配置选项

需要自定义com.particle.global.mybatis.plus.fill.LoginUserIdResolver注入到Spring环境容器中，用来填充当前登录用户：

```yaml
mybatis-plus:
  configuration:
    map-underscore-to-camel-case: true
```

### 核心组件

- IBaseService：基础服务接口
- IBaseServiceImpl：基础服务实现
- IBaseMapper：基础Mapper接口
- 数据填充器：自动填充安全字段

## 依赖组件

- MyBatis Plus
- Spring Boot Starter JDBC

## 示例代码

```java
// 定义实体类
@TableName("user")
public class User extends BaseDO {
    private String name;
    private Integer age;
    
    // getter和setter方法
}

// 定义Mapper接口
@Mapper
public interface UserMapper extends IBaseMapper<User> {
}

// 定义Service接口
public interface UserService extends IBaseService<User> {
}

// 实现Service接口
@Service
public class UserServiceImpl extends IBaseServiceImpl<UserMapper, User> implements UserService {
}

// 使用
@Autowired
private UserService userService;

public User createUser(CreateUserCommand command) {
    User user = new User();
    user.setName(command.getName());
    user.setAge(command.getAge());
    return userService.add(user);
}
```