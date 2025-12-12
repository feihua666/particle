# 基础客户端模块

客户端层（Client Layer）定义服务契约，提供对外接口和服务规范定义。

## 功能特性

- 服务接口定义
- 数据传输对象（DTO）定义
- 命令对象定义
- 查询对象定义
- 事件对象定义

## 核心组件

### API 接口
- `IBaseApplicationService`: 基础应用服务接口

### 命令对象
- `AbstractBaseCommand`: 基础命令对象
- `AbstractBasePageQueryCommand`: 基础分页查询命令
- `AbstractBaseQueryCommand`: 基础查询命令
- `AbstractBaseUpdateCommand`: 基础更新命令
- `AbstractBatchIdCommand`: 批量 ID 命令
- `AbstractIdCommand`: ID 命令
- `BatchIdCommand`: 批量 ID 命令

### 数据传输对象
- `AbstractBaseDTO`: 基础 DTO 对象
- `AbstractBaseIdTreeVO`: 基础树形结构 VO
- `AbstractBaseIdVO`: 基础 ID VO
- `AbstractBaseVO`: 基础 VO 对象

### 事件对象
- `AbstractBaseEvent`: 基础事件对象

## 依赖组件

- 无强依赖，主要作为服务契约定义模块

## 使用说明

客户端模块被其他各层广泛依赖，定义了服务接口和数据传输规范。它是系统各层之间通信的桥梁，确保各层之间的松耦合。