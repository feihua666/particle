# 任务计划管理组件

注意：些任务计划管理业务组件和 [global-scheduler-boot-starter](..%2F..%2Fglobal%2Fglobal-scheduler-boot-starter) 没有什么关系，全局任务计划一般用于系统内置任务执行。
本任务计划业务组件旨在提供动态界面管理功能的任务计划，用于执行简单的业务逻辑任务。

## 使用方法

在项目的 pom.xml 中引入依赖：

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>scheduler-boot-starter</artifactId>
    <version>${particle.version}</version>
</dependency>
```

## 核心组件

- 临时任务管理：管理临时性任务的创建和执行
- 临时任务运行记录：记录临时任务的执行历史
- 临时任务运行日志：记录临时任务的详细执行日志
- 任务计划执行记录：记录任务计划的执行历史
- 任务计划管理：管理任务计划的启停和状态
- 任务计划任务管理：管理具体的任务定义和操作
- 任务计划触发器管理：管理任务触发器的配置和控制

## 菜单功能

### 任务计划管理
- 临时任务管理
  - 查询、删除临时任务

- 临时任务运行记录
  - 查询、删除、修改运行记录

- 临时任务运行日志
  - 查询、删除运行日志

- 任务计划管理
  - 查询任务计划列表
  - 启动/恢复任务计划
  - 停止任务计划
  - 挂起任务计划

- 任务计划任务管理
  - 查询任务列表
  - 添加、删除、修改任务
  - 暂停、恢复任务
  - 手动执行一次任务
  - 复制任务

- 任务计划触发器管理
  - 查询触发器列表
  - 暂停、恢复触发器

## 关于temptask
模板中有一个包是temptask，称之临时任务
其主要用来临时性的执行一些简单的操作，但又不方便在代码或脚本中记录日志，可以使用临时任务来记录日志，进行可视化管理和监视

## 自动配置

组件通过 [SchedulerAutoConfiguration](scheduler-boot-starter/src/main/java/com/particle/scheduler/SchedulerAutoConfiguration.java) 实现自动配置，
并扫描 `com.particle.scheduler` 包下的组件，包括：
- Mapper接口：任务计划、临时任务、任务执行记录、数据任务相关的数据访问层接口
- 应用服务：scheduler-app模块的应用服务类

该组件还集成了以下功能：
1. 提供自定义的日志记录调度工厂Bean定制器
2. 集成了Swagger文档功能，提供了后端管理接口文档分组
