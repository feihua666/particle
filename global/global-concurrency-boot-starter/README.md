# 全局并发模块

全局并发模块提供线程池和多线程相关公共可用方法，基于 Shedlock 实现分布式锁功能。

## 功能特性

1. **线程池管理**：提供统一的线程池配置和管理
2. **多线程工具**：封装常用的多线程操作方法
3. **分布式锁**：基于 Shedlock 实现分布式锁机制
4. **任务调度锁**：支持定时任务的分布式锁保护
5. **可配置锁实现**：支持通过配置指定锁技术实现

## 核心组件

### 线程池组件
- 线程池配置管理器
- 多线程任务执行器
- 异步任务处理工具

### 分布式锁组件
- [LockExecutor](src/main/java/com/particle/global/concurrency/lock/LockExecutor.java)：锁执行器接口
- Shedlock 分布式锁实现
- 内存锁默认实现

### 任务调度锁
- @SchedulerLock 注解支持
- @EnableSchedulerLock 配置支持

## 配置选项

### 分布式锁配置

在分布式环境下，需要添加以下依赖(依赖版本已在 particle pom 中全局添加)：

```xml
<!-- 分布式锁 shedlock jdbc支持 -->
<dependency>
    <groupId>net.javacrumbs.shedlock</groupId>
    <artifactId>shedlock-provider-jdbc-template</artifactId>
    <scope>provided</scope>
</dependency>
```

### 数据库建表语句

参考：https://github.com/lukas-krecan/ShedLock#jdbctemplate

```sql
# MySQL, MariaDB
CREATE TABLE shedlock(name VARCHAR(64) NOT NULL, lock_until TIMESTAMP(3) NOT NULL,
    locked_at TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3), locked_by VARCHAR(255) NOT NULL, PRIMARY KEY (name));

# Postgres
CREATE TABLE shedlock(name VARCHAR(64) NOT NULL, lock_until TIMESTAMP NOT NULL,
    locked_at TIMESTAMP NOT NULL, locked_by VARCHAR(255) NOT NULL, PRIMARY KEY (name));

# Oracle
CREATE TABLE shedlock(name VARCHAR(64) NOT NULL, lock_until TIMESTAMP(3) NOT NULL,
    locked_at TIMESTAMP(3) NOT NULL, locked_by VARCHAR(255) NOT NULL, PRIMARY KEY (name));

# MS SQL
CREATE TABLE shedlock(name VARCHAR(64) NOT NULL, lock_until datetime2 NOT NULL,
    locked_at datetime2 NOT NULL, locked_by VARCHAR(255) NOT NULL, PRIMARY KEY (name));

# DB2
CREATE TABLE shedlock(name VARCHAR(64) NOT NULL PRIMARY KEY, lock_until TIMESTAMP NOT NULL,
    locked_at TIMESTAMP NOT NULL, locked_by VARCHAR(255) NOT NULL);
```

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-concurrency-boot-starter</artifactId>
</dependency>
```

### 启用任务调度锁

在配置类上添加注解启用任务调度锁：

```java
@EnableSchedulerLock
@Configuration
public class SchedulerConfig {
    // 配置内容
}
```

### 使用分布式锁

建议使用注入的 LockExecutor 加锁，以待后期有更新时避免修改代码：

```java
@Autowired
private LockExecutor lockExecutor;

public void doSomething() {
    lockExecutor.executeWithLock("lockKey", () -> {
        // 执行需要加锁的业务逻辑
        return result;
    });
}
```

### 定时任务加锁

在标注了 @Scheduled 的方法上加 @SchedulerLock 注解，可以自动加锁：

```java
@Scheduled(cron = "0 0 1 * * ?")
@SchedulerLock(name = "scheduledTaskLock")
public void scheduledTask() {
    // 定时任务逻辑
}
```

## 自动配置

模块通过自动配置机制，提供默认的线程池和分布式锁实现。

## 依赖组件

- Shedlock core
- Shedlock provider jdbc-template (可选)
- Spring Boot 相关并发组件