# Particle 项目 Claude CLI 总体开发规范

## 1. 项目概述

Particle 是一个基于 Spring Boot 的模块化开发框架，包含多个组件和模块。

## 2. 项目结构

```
particle/
├── common/                 # 通用后端业务组件基础规范模块
├── component/              # 后端业务组件模块
├── global/                 # 全局后端功能模块
├── project/                # 项目应用模块，集成后端和前端项目启动
├── web/                    # Web 相关模块
├── devops/                 # 运维部署模块
└── tools/                  # 工具脚本
```
