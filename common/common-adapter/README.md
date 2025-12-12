# 通用适配层模块

适配层（Adapter Layer）负责处理用户请求和外部系统的交互，如 Web 控制器、RPC 接口等。

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