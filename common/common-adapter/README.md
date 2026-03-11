# 通用适配层模块

适配层（Adapter Layer）负责处理用户请求和外部系统的交互，如 Web 控制器、RPC 接口等。在 DDD（领域驱动设计） 架构中，该模块作为基础通用的被依赖适配层模块，为各业务组件提供统一的适配器抽象基类。

## 模块职责

- 作为基础适配层，为所有业务组件提供统一的适配器抽象基类
- 定义标准化的接口和抽象类，确保各业务组件适配层的一致性
- 集中管理通用的适配逻辑，避免重复实现
- 为 Web 控制器、RPC 接口、API 调用等提供标准化的基类实现
- 统一处理跨领域的适配需求，如参数校验、异常处理、响应格式等

## 功能特性

- 提供多种接入方式的适配器基类
- 统一的请求处理和响应格式
- 参数校验和异常处理机制
- 支持 RESTful API、RPC、WebSocket 等多种通信协议

## 核心组件

- `AbstractBaseApiAdapter`: API 接口适配器基类
- `AbstractBaseMobileAdapter`: 移动端适配器基类
- `AbstractBaseRpcAdapter`: RPC 接口适配器基类
- `AbstractBaseWapAdapter`: WAP 端适配器基类
- `AbstractBaseWebAdapter`: Web 端适