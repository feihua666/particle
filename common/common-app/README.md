# 基础应用模块

应用层（Application Layer）负责实现业务流程编排，处理用例和业务规则，但不包含具体业务逻辑。

## 功能特性

- 业务流程编排
- 用例处理
- 应用服务实现
- 执行器基类定义
- 查询执行器基类定义

## 核心组件

- `AbstractBaseApplicationServiceImpl`: 应用服务实现基类
- `AbstractBaseExecutor`: 执行器基类
- `AbstractBaseQueryExecutor`: 查询执行器基类

## 依赖组件

- common-client: 依赖客户端定义的命令和查询对象
- common-domain: 依赖领域层提供的领域服务和仓储接口

## 使用说明

应用层模块通常由具体的业务模块依赖，负责协调领域层的各种操作，实现完整的业务用例。应用层不包含具体的业务逻辑，只负责业务流程的编排。