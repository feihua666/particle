# 配置指南

Particle 项目采用模块化的配置管理方式，通过主配置文件激活 Global 模块提供的预设配置。这种设计使得配置管理更加灵活和可维护。

## 配置工作机制

Particle 项目的配置机制基于 Spring Boot 的 Profile 特性：

1. 主配置文件 (`application.yml`) 通过 `spring.profiles.include` 引入所需功能模块
2. 每个 Global 模块提供对应的配置文件 (`application-{profile}.yml`)
3. Spring Boot 自动加载激活的 Profile 对应的配置
4. 业务组件通过 SQL 脚本初始化数据库

这种方式实现了配置的模块化管理，每个功能模块只需关注自己的配置，避免了配置文件过于庞大复杂的问题。

## 目录结构

```
project/particle-project/particle-project-start/src/main/resources/
├── application.yml                 # 主配置文件
└── db/
    ├── data.*.sql                 # 初始化数据脚本
    └── schema.*.sql               # 数据库表结构脚本
```

## 主配置文件详解

### 应用基本信息

```yaml
spring:
  application:
    name: particle-project-start    # 应用名称
```

### 功能模块配置

Particle 项目通过 `spring.profiles.include` 方式激活所需的 Global 功能模块。每个 Profile 名称对应一个 Global 模块提供的配置文件。

```yaml
spring:
  profiles:
    include:
      - knife4j                    # API文档增强工具
      - swagger                    # API文档生成工具
      - log4j2                     # 日志框架
      - datasource                 # 数据源配置
      - mybatis-plus               # ORM框架
      - mybatis-plus-tenant        # 多租户支持
      - actuator                   # 监控端点
      - boot-admin-client-local    # Spring Boot Admin客户端
      - boot-admin-server          # Spring Boot Admin服务端
      - session                    # Session管理
      - resourceserver-local       # 资源服务器
      - quartz                     # 定时任务
      - freemarker                 # 模板引擎
      - freemarker-dev             # Freemarker开发配置
      - ai                         # AI功能支持
```

当项目启动时，Spring Boot 会自动查找并加载这些 Profile 对应的配置文件。例如：
- 当激活 `datasource` Profile 时，会加载 `global-datasource-boot-starter` 模块中的 `application-datasource.yml`
- 当激活 `mybatis-plus` Profile 时，会加载 `global-mybatis-plus-boot-starter` 模块中的 `application-mybatis-plus.yml`

这种机制允许开发者根据项目需求灵活选择所需的功能模块，而无需手动编写复杂的配置。

### 数据源配置

```yaml
spring:
  datasource:
    dynamic:
      primary: master              # 默认数据源
      strict: false                # 严格匹配数据源
      datasource:
        master:
          url: ${particle.datasource.master.url}?${particle.datasource.mysql.url-query}
          username: root
          password: rootroot
          driver-class-name: ${particle.datasource.mysql.driver}
```

### 数据库初始化配置

```yaml
spring:
  sql:
    init:
      schema-locations:
        - classpath:db/schema.particle-project.sql
        # 更多表结构脚本...
      data-locations:
        - classpath:db/data.particle-project.sql
        # 更多初始化数据脚本...
      mode: NEVER                  # 初始化模式 NEVER/ALWAYS/EMBEDDED
```

### 文件上传配置

```yaml
spring:
  servlet:
    multipart:
      max-request-size: 500MB     # 最大请求大小
      max-file-size: 50MB         # 单个文件最大大小
```

### Particle 特定配置

#### Session 配置

```yaml
particle:
  session:
    store-type: jdbc             # Session存储类型 jdbc/none
```

#### 项目信息配置

```yaml
particle:
  project-info:
    component:
      enable:
        all: true                # 启用所有组件
```

#### 验证码配置

```yaml
particle:
  captcha:
    filter:
      enabled: true              # 是否启用验证码过滤器
      uris:
        - /login                 # 需要验证码的URI
```

#### 动态验证码配置

```yaml
particle:
  dynamic-captcha:
    filter:
      enabled: true
      uris:
        - /loginCaptcha
```

#### 对象存储配置

```yaml
particle:
  oss:
    defaultClient: localClient
    local:
      localClient:
        endpoint: http://localhost:8080/oss/download
        basePath: /Users/yw/temp/localosstest
        bucketName: localClientBucket
```

#### 通知配置

```yaml
particle:
  notification:
    email:
      host: smtp.163.com
      port: 25
      from: particle_particle@163.com
      user: particle_particle
      pass: xxxx
```

#### 数据查询开放接口配置

```yaml
particle:
  dataquery:
    openapi:
      config:
        enabled: false
        remoteDomain: http://localhost:8080
        appId: 1688811472965656576
        appSecret: 03b109817ddc4269a40d30ca12459511
```

## Global 模块配置详解

Global 模块是 Particle 项目的技术基石，每个模块都提供特定功能的预设配置。这些配置文件位于各模块的 `src/main/resources` 目录下。

典型的 Global 模块配置结构：

```
global/{module-name}/src/main/resources/
├── application-{profile-name}.yml          # 主配置文件
├── META-INF/
│   └── spring-configuration-metadata.json  # 配置元数据
└── db/
    ├── schema.{module-name}.sql            # 模块数据库表结构
    └── data.{module-name}.sql              # 模块初始化数据
```

例如 `global-datasource-boot-starter` 模块提供数据源相关配置：
- 配置文件：`global-datasource-boot-starter/src/main/resources/application-datasource.yml`
- 包含数据源、连接池、事务等相关配置

当在主配置文件中包含 `datasource` Profile 时，这些配置会自动生效。

## 业务组件 SQL 脚本

Particle 的业务组件采用模块化设计，每个组件都包含自己的数据库脚本，确保组件的独立性和可插拔性。

业务组件 SQL 脚本结构：

```
component/{component-name}/{component-name}-infrastructure/src/main/resources/db/
├── schema.{component-name}.sql    # 表结构定义
└── data.{component-name}.sql      # 基础数据
```

例如 `user` 组件的脚本位于：
- 表结构：`component/user/user-infrastructure/src/main/resources/db/schema.component_user.sql`
- 初始化数据：`component/user/user-infrastructure/src/main/resources/db/data.user.sql`

项目启动时通过 `spring.sql.init` 配置自动执行这些脚本，完成数据库初始化。

## 配置管理策略

### 模块化配置
Particle 项目采用模块化配置策略，每个 Global 模块提供独立的配置文件，通过 Profile 机制灵活组合。

**优势：**
- 配置职责清晰，便于维护
- 功能模块可插拔，按需启用
- 减少配置冲突，提高稳定性

### 数据库初始化策略
项目通过 SQL 脚本自动初始化数据库，遵循以下原则：

1. **分而治之**：每个业务组件维护自己的脚本
2. **版本控制**：脚本纳入 Git 管理，支持变更追溯

### 配置安全
1. **敏感信息处理**
   - 不要在配置文件中硬编码密码等敏感信息
   - 使用环境变量或外部配置中心管理敏感配置

2. **配置优先级**
   - 命令行参数 > 环境变量 > 配置文件 > 默认值

3. **配置验证**
   - 启动时验证必要配置项是否存在
   - 对配置值进行合理性检查

::: tip 提示
配置文件的修改可能需要重启应用才能生效。
:::
