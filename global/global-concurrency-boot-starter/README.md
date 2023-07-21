# 全局并发模块
线程池，多线程相关公共可用方法
关于shedlock更多的锁还没自动配置，@EnableSchedulerLock可以开启任务计划加锁,并在 标注了 @Sheduled的方法上加 @SchedulerLock注解，可以自动加锁
在本项目中建议使用注入的LockExecutor加锁，以待后期有更新时避免修改代码，而且可以根据配置指定什么技术实现（目前仅支持shedlock）
实现了shedlock依赖数据库的分布式锁封装，默认是内存锁，如果在分布式环境下需要添加以下依赖(依赖版本已在particle pom中全局添加)：
```xml
        <!-- 分布式锁 shedlock jdbc支持 -->
<dependency>
    <groupId>net.javacrumbs.shedlock</groupId>
    <artifactId>shedlock-provider-jdbc-template</artifactId>
    <scope>provided</scope>
</dependency>
```

shedlock建表语句 参见：https://github.com/lukas-krecan/ShedLock#jdbctemplate
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