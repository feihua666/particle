# 业务组件模块

业务组件是 Particle 项目中实现具体业务功能的模块集合，每个组件都是一个相对独立的业务单元。

## 组件列表

- [agi](./agi/README.md) - AGI（人工智能生成智能）组件
- [area](./area/README.md) - 区域管理组件
- [cms](./cms/README.md) - 内容管理系统组件
- [component-admin](./component-admin/README.md) - 组件管理组件
- [component-autoconfigure-boot-starter](./component-autoconfigure-boot-starter/README.md) - 业务组件自动配置模块
- [component-common](./component-common/README.md) - 业务组件轻量级共享模块
- [config](./config/README.md) - 配置管理组件
- [crm](./crm/README.md) - 客户关系管理组件
- [data](./data/README.md) - 数据管理组件
- [data-constraint](./data-constraint/README.md) - 数据约束/数据权限组件
- [data-query](./data-query/README.md) - 数据查询组件
- [dept](./dept/README.md) - 部门管理组件
- [dict](./dict/README.md) - 字典管理组件
- [dream](./dream/README.md) - Dream组件
- [feedback](./feedback/README.md) - 意见反馈组件
- [func](./func/README.md) - 功能菜单组件
- [low-code](./low-code/README.md) - 低代码平台组件
- [message](./message/README.md) - 消息管理组件
- [navigation](./navigation/README.md) - 导航管理组件
- [oauth2authorization](./oauth2authorization/README.md) - OAuth2授权管理组件
- [op-log](./op-log/README.md) - 操作日志组件
- [open-platform](./open-platform/README.md) - 开放平台组件
- [report](./report/README.md) - 报告管理组件
- [role](./role/README.md) - 角色管理组件
- [scheduler](./scheduler/README.md) - 任务计划管理组件
- [tenant](./tenant/README.md) - 租户管理组件
- [tools](./tools/README.md) - 工具组件
- [tracking](./tracking/README.md) - 埋点记录组件
- [usage-count](./usage-count/README.md) - 使用次数组件
- [user](./user/README.md) - 用户管理组件

## 端口列表说明

每个业务组件模块都包含一个以 `-start` 结尾的启动模块，这些模块运行在不同的端口上：

| 端口号 | 组件名称 | 启动模块 |
|--------|---------|----------|
| 8010 | dict | dict-start |
| 8011 | area | area-start |
| 8012 | agi | agi-start |
| 8013 | audit | audit-start |
| 8014 | cms | cms-start |
| 8015 | component-admin | component-admin-start |
| 8016 | config | config-start |
| 8017 | crm | crm-start |
| 8018 | data | data-start |
| 8019 | data-constraint | data-constraint-start |
| 8020 | data-query | data-query-start |
| 8021 | dept | dept-start |
| 8022 | dream | dream-start |
| 8023 | feedback | feedback-start |
| 8024 | func | func-start |
| 8025 | low-code | low-code-start |
| 8026 | message | message-start |
| 8027 | navigation | navigation-start |
| 8028 | oauth2authorization | oauth2authorization-start |
| 8029 | op-log | op-log-start |
| 8030 | open-platform | open-platform-start |
| 8031 | report | report-start |
| 8032 | role | role-start |
| 8033 | scheduler | scheduler-start |
| 8034 | tenant | tenant-start |
| 8035 | tools | tools-start |
| 8036 | tracking | tracking-start |
| 8037 | usage-count | usage-count-start |
| 8038 | user | user-start |
| 8050 | gateway-webmvc | gateway-webmvc-start |
| 8051 | gateway-webflux | gateway-webflux-start |

## 设计理念

业务组件模块采用模块化设计，每个组件都遵循统一的四层架构模式：
1. adapter - 适配层
2. app - 应用层
3. domain - 领域层
4. infrastructure - 基础设施层

这种设计使得每个业务组件都可以独立开发、测试和部署，同时又能很好地协同工作。
