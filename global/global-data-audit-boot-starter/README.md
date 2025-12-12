# 操作日志与数据审计功能支持

该模块基于 Spring AOP 功能，结合 @OpLog 注解，实现了在 Service 或 Controller 层（建议将注解注释到 Controller 层方法上）全局拦截并记录操作日志和数据审计日志的功能。

默认日志记录为开启，可以通过配置禁用，禁用后将不再自动装配日志记录模块：
```yaml
particle.dataaudit.enable=false
```

需要实现 OpLogRepository 接口以自定义持久化逻辑。

## 功能特性

1. **操作日志记录**：基于注解自动记录用户操作行为
2. **数据审计功能**：自动比对数据变化，记录数据变更历史
3. **父子级关系**：支持操作日志的层级关系管理
4. **微服务兼容**：兼容分布式微服务环境下的日志追踪
5. **自定义持久化**：支持自定义日志存储方式

## 核心组件

### 操作日志组件
- [@OpLog](src/main/java/com/particle/global/dataaudit/op/OpLog.java)：操作日志注解，用于标记需要记录的操作
- [OpLogAspect](src/main/java/com/particle/global/dataaudit/op/OpLogAspect.java)：操作日志切面，拦截注解并处理日志记录
- [OpLogTool](src/main/java/com/particle/global/dataaudit/op/OpLogTool.java)：操作日志工具类，提供线程上下文管理
- [OpLogRepository](src/main/java/com/particle/global/dataaudit/op/OpLogRepository.java)：操作日志持久化接口，需要自定义实现

### 数据审计组件
- [DataAuditCollectTool](src/main/java/com/particle/global/dataaudit/audit/DataAuditCollectTool.java)：数据审计收集工具
- [IDataAuditHandler](src/main/java/com/particle/global/dataaudit/audit/IDataAuditHandler.java)：数据审计处理器接口
- [DataAuditTransactionalEventListener](src/main/java/com/particle/global/dataaudit/audit/DataAuditTransactionalEventListener.java)：事务监听器，确保数据审计在事务提交后执行

## 配置选项

### 启用/禁用配置

禁用操作日志与数据审计功能：
```yaml
particle.dataaudit.enable=false
```

默认情况下功能是启用的。

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-data-audit-boot-starter</artifactId>
</dependency>
```

### 基本使用示例

在 Controller 方法上添加 @OpLog 注解：

```java
@OpLog(name = "添加用户", module = "用户管理", type = "CREATE")
@PostMapping("/user")
public ResponseEntity addUser(@RequestBody User user) {
    // 业务逻辑
    return ResponseEntity.ok().build();
}
```

### 自定义持久化实现

实现 OpLogRepository 接口来自定义日志存储逻辑：

```java
@Component
public class CustomOpLogRepository implements OpLogRepository {
    @Override
    public void save(List<OpLogAndDataAuditResultsDTO> opLogAndDataAuditResultsDTOList) {
        // 自定义持久化逻辑
    }
}
```

### 数据审计使用示例

在数据变更的地方使用数据审计功能：

```java
// 发布数据审计事件，自动对象比对
DataAuditCollectTool.publish(oldUser, newUser, userId, "user_table", "User", "UPDATE");
```

## 实现思路

本模块认为，系统业务日志分为两种：

第一种是操作日志如：
1. 添加用户
2. 修改密码
3. 审批通过

第二种日志为操作数据审计日志如：
1. 针对以上添加用户具体涉及了什么数据
2. 修改用户 -> 数据修改前和修改后差异

第二种数据审计日志是对第一种的补充，第一种可以独立存在，第二种必须依附于第一种之上形成子级关系。

另一个考虑是第一种操作日志如果在微服务中，涉及到远程调用，这就比较麻烦，本模块兼容了这种逻辑处理，并使其形成父子级关系即：总有一个根操作是从用户发起或系统定时触发，然后再拆分子操作，第一个操作都还有可能涉及数据审计。

数据审计部分参考了 https://blog.csdn.net/ory001/article/details/124416899

## 自动配置

模块通过以下自动配置类实现功能：
- [DataAuditAuditAutoConfiguration](src/main/java/com/particle/global/dataaudit/DataAuditAuditAutoConfiguration.java)：数据审计自动配置
- [DataAuditOpLogAutoConfiguration](src/main/java/com/particle/global/dataaudit/DataAuditOpLogAutoConfiguration.java)：操作日志自动配置

## 依赖组件

- Spring Boot AOP
- Spring Boot JDBC (用于事务监听)
- global-dto: 全局数据传输对象
- global-tool: 全局工具类
- global-concurrency-boot-starter: 并发处理支持
- Javers (用于对象差异比对)