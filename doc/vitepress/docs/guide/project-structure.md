# 项目结构

Particle 项目采用模块化设计，将功能拆分为多个独立的模块，便于维护和扩展。本文档将详细介绍项目的整体结构和各个目录的作用。

## 根目录结构

```
particle/
├── common/                    # 公共模块
├── component/                 # 业务组件模块
├── devops/                    # DevOps相关配置
├── doc/                       # 文档资料
├── global/                    # 全局技术组件
├── project/                   # 项目启动器
├── tools/                     # 工具脚本
├── web/                       # 前端代码
├── pom.xml                    # 父项目Maven配置
└── README.md                  # 项目说明文档
```

## 业务公共模块 (common)

公共模块包含项目的基础架构和通用功能，为实际业务模块提供基础支持。

```
common/
├── common-adapter/            # 适配层通用模块
├── common-app/                # 应用层通用模块
├── common-client/             # 客户端接口模块
├── common-constant/           # 全局常量定义
├── common-domain/             # 领域层通用模块
└── common-infrastructure/     # 基础设施层通用模块
```

### 各层模块说明

- **common-adapter**: 包含适配层的通用功能，如Web控制器基类、RPC接口基类等
- **common-app**: 包含应用层的通用功能，如服务基类、DTO转换工具等
- **common-client**: 定义客户端接口和服务契约
- **common-constant**: 定义项目中使用的全局常量
- **common-domain**: 包含领域层的通用功能，如实体基类、领域事件等
- **common-infrastructure**: 包含基础设施层的通用功能，如仓储基类、工具类等

## 业务组件模块 (component)

业务组件模块是实现具体业务功能的模块集合，每个组件都是相对独立的业务单元。

```
component/
├── user/                      # 用户管理模块
├── role/                      # 角色管理模块
├── dept/                      # 部门管理模块
├── dict/                      # 数据字典模块
├── area/                      # 区域管理模块
├── func/                      # 功能菜单模块
├── op-log/                    # 操作日志模块
├── cms/                       # 内容管理模块
├── crm/                       # 客户关系管理模块
├── scheduler/                 # 任务调度模块
├── data/                      # 数据管理模块
├── config/                    # 配置管理模块
├── tenant/                    # 租户管理模块
├── open-platform/            # 开放平台模块
├── message/                  # 消息管理模块
├── low-code/                 # 低代码平台模块
├── component-admin/          # 组件管理模块
├── agi/                      # AI生成模块
├── data-query/               # 数据查询模块
├── data-constraint/          # 数据权限模块
├── report/                   # 报告管理模块
└── ...                        # 更多业务组件
```

### 核心业务组件

- **user**: 提供用户注册、登录、权限管理等功能
- **role**: 实现角色分配、权限控制等RBAC核心功能
- **dept**: 组织架构管理功能
- **dict**: 系统数据字典维护
- **area**: 地理区域信息管理
- **func**: 系统菜单及权限配置
- **op-log**: 用户行为日志记录和分析

### 扩展业务组件

- **cms**: 文章、资讯等内容管理系统
- **crm**: 客户信息和跟进管理
- **scheduler**: 分布式任务调度系统
- **data**: 数据维护和管理功能
- **config**: 系统参数配置管理
- **tenant**: 多租户支持功能
- **open-platform**: 对外提供开放接口支持
- **message**: 站内信、通知等消息系统

### 特色功能组件

- **low-code**: 可视化代码生成工具，支持前后端代码同步生成
- **component-admin**: 组件注册、发现和状态监控
- **agi**: 人工智能生成功能
- **data-query**: 灵活的数据查询构建器
- **data-constraint**: 细粒度数据访问控制
- **report**: 数据报告生成

## 全局技术组件 (global)

全局技术组件是一系列根据技术要点建立的全局子模块，提供系统级的规范和功能。

```
global/
├── global-actuator-boot-starter/      # 系统监控和管理端点
├── global-security-boot-starter/      # 安全认证和授权功能
├── global-mybatis-plus-boot-starter/  # MyBatis Plus数据访问支持
├── global-datasource-boot-starter/    # 数据源管理
├── global-redis-boot-starter/         # Redis缓存支持
├── global-cache-boot-starter/         # 多级缓存方案
├── global-swagger-boot-starter/       # API文档生成功能
├── global-messaging-boot-starter/     # 异步消息处理机制
├── global-scheduler-boot-starter/     # 定时任务调度功能
├── global-logging-boot-starter/       # 日志管理
├── global-catchlog-boot-starter/      # 异常日志捕获
├── global-exception-handle-boot-starter/ # 统一异常处理机制
└── ...                                 # 更多全局技术组件
```

### 主要技术组件

- **global-actuator-boot-starter**: 提供系统监控和管理端点
- **global-security-boot-starter**: 提供安全认证和授权功能
- **global-mybatis-plus-boot-starter**: 提供MyBatis Plus数据访问支持
- **global-datasource-boot-starter**: 提供数据源管理和动态切换
- **global-redis-boot-starter**: 提供Redis缓存支持
- **global-cache-boot-starter**: 提供多级缓存方案
- **global-swagger-boot-starter**: 提供API文档自动生成
- **global-messaging-boot-starter**: 提供异步消息处理机制
- **global-scheduler-boot-starter**: 提供定时任务调度功能
- **global-logging-boot-starter**: 提供全面的日志管理
- **global-catchlog-boot-starter**: 提供异常日志捕获
- **global-exception-handle-boot-starter**: 提供统一异常处理机制

## 项目启动器 (project)

项目启动器是服务运行的入口模块。

```
project/
└── particle-project/                  # 主项目
    └── particle-project-start/       # 启动模块
```

### 启动模块结构

```
particle-project-start/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/particle/particleproject/
│   │   │       └── ParticleProjectApplication.java  # 启动类
│   │   └── resources/
│   │       ├── application.yml                      # 主配置文件
│   │       ├── application-dev.yml                  # 开发环境配置
│   │       ├── application-prod.yml                 # 生产环境配置
│   │       └── db/                                  # 数据库脚本
│   └── test/
└── pom.xml                                          # Maven配置
```

## 前端代码 (web)

前端代码采用Vue.js框架，按照模块化方式进行组织。

```
web/
├── common/                      # 前端公共模块
├── component/                   # 前端业务组件
├── global/                      # 前端全局组件
└── project/                     # 前端项目
    └── particle-project/        # 主前端项目
        ├── public/              # 静态资源
        ├── src/                 # 源代码
        │   ├── assets/          # 静态资源
        │   ├── components/      # 组件
        │   ├── views/           # 页面视图
        │   ├── router/          # 路由配置
        │   ├── store/           # 状态管理
        │   ├── utils/           # 工具函数
        │   └── App.vue          # 根组件
        ├── package.json         # 依赖配置
        └── vite.config.ts       # 构建配置
```

## DevOps 配置 (devops)

DevOps配置包含本地开发和部署相关的环境配置。

```
devops/
├── local/                       # 本地开发环境
│   ├── mysql/                   # MySQL配置
│   ├── redis/                   # Redis配置
│   ├── nginx/                   # Nginx配置
│   └── ...                      # 其他服务配置
└── remote/                      # 远程部署环境
    └── vpn/                     # VPN配置
```

## 文档资料 (doc)

文档资料包含项目相关的各类文档。

```
doc/
├── vitepress/                   # VitePress文档站点
├── 开发平台功能说明.md           # 开发平台功能说明
└── README.md                    # 文档说明
```

## 模块依赖关系

Particle项目采用分层架构设计，模块间的依赖关系遵循以下原则：

1. **上层模块不能直接依赖下层具体实现**
2. **通过接口进行交互，保证良好的可维护性和可扩展性**
3. **公共模块为基础，业务组件为核心，全局组件为支撑**

### 依赖方向

```
project (启动器)
    ↓ 依赖
component (业务组件)
    ↓ 依赖
common (公共模块)
    ↓ 依赖
global (全局组件)
```

### 同层依赖

在同一层级中，模块间通过接口进行交互，避免直接依赖具体实现：

```
component/user -> common/client -> common/domain
component/role -> common/client -> common/domain
```

## 架构设计原则

1. **高内聚低耦合**: 每个模块像粒子一样内聚，模块之间保持松耦合
2. **DDD驱动设计**: 基于领域驱动设计理念，每个功能模块、聚合根、方法都经过精心考量和打磨
3. **四层架构**: 采用经典的四层架构（适配层、应用层、领域层、基础设施层）
4. **组件化开发**: 将业务功能拆分成可重用的组件，便于快速组装和部署

## 最佳实践

1. **模块独立性**: 每个业务组件都可以独立开发、测试和部署
2. **插件化集成**: 通过组件管理模式，可以按需启用或禁用特定功能
3. **依赖管理**: 组件管理器可以清晰地展示组件之间的依赖关系
4. **统一规范**: 建立完整的开发规范体系，包括代码风格、接口文档、参数校验等

::: tip 提示
了解项目结构有助于更好地理解Particle的设计理念和使用方式。在开发新功能时，应根据功能性质选择合适的模块进行开发。
:::
