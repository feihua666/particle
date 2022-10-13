# 全局并发模块
线程池，多线程相关公共可用方法
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