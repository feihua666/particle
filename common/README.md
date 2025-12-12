# 四层架构基础模块

基于四层架构，参考 COLA 架构，包含分层基础通用模块。

## 架构说明

Particle 项目采用四层架构模式，参考了 COLA 架构思想，将系统划分为以下四个层次：

1. **适配层（Adapter Layer）** - [common-adapter](./common-adapter/README.md)
   负责处理用户请求和外部系统的交互，如 Web 控制器、RPC 接口等。

2. **应用层（Application Layer）** - [common-app](./common-app/README.md)
   实现业务流程编排，处理用例和业务规则，但不包含具体业务逻辑。

3. **领域层（Domain Layer）** - [common-domain](./common-domain/README.md)
   核心业务逻辑层，包含实体、值对象、领域服务和仓储接口等。

4. **基础设施层（Infrastructure Layer）** - [common-infrastructure](./common-infrastructure/README.md)
   提供技术实现细节，如数据库访问、消息队列、缓存等。

## 公共模块

- [common-client](./common-client/README.md) - 定义客户端接口和服务契约
- [common-constant](./common-constant/README.md) - 提供全局常量定义

## 设计理念

各层之间遵循依赖倒置原则，高层不直接依赖低层的具体实现，而是通过接口进行交互，保证了良好的可维护性和可扩展性。