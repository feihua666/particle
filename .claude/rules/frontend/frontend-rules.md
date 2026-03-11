
# 前端开发规则

## 技术栈
- Vue 3 (Composition API)
- Vite 4+
- TypeScript 4+
- Element Plus UI
- Node.js 18+
- Axios

## 目录结构
```
web/
├── common/           # 通用前端基础业务组件和功能
│   ├── api/          # API 接口定义，定义通用的接口参数
│   ├── config/       # 配置文件
│   ├── mobile/       # 移动端通用组件
│   ├── pc/           # PC 端通用组件
│   └── route/        # 路由配置
├── component/        # 前端业务组件
│   ├── mobile/       # 移动端业务组件
│   └── pc/           # PC 端业务组件
├── global/           # 全局前端组件和功能
│   ├── common/       # 全局通用组件
│   ├── mobile/       # 移动端全局组件
│   └── pc/           # PC 端全局组件
└── project/          # 项目特定前端代码
    └── particle-project/ # 项目前端代码，聚合、启动
```
