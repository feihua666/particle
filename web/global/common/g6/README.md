# G6 Graph 组件

一个通用的G6图表Vue组件，用于展示各种关系图、流程图、依赖图等。

## 特性

- 通用的G6图表封装
- 支持多种布局方式
- 支持自定义节点和边的样式
- 支持事件监听
- 支持小地图
- 响应式设计

## 安装

```bash
npm install @antv/g6
```

## 使用方法

### 基本使用

```vue
<template>
  <G6Graph :data="graphData" />
</template>

<script setup>
import { G6Graph } from '@/global/common/g6';

const graphData = {
  nodes: [
    { id: 'node1', label: '节点1' },
    { id: 'node2', label: '节点2' },
  ],
  edges: [
    { source: 'node1', target: 'node2' },
  ],
};
</script>
```

### 高级配置

```vue
<template>
  <G6Graph
    :data="graphData"
    :layout="layoutConfig"
    :nodeConfig="nodeConfig"
    :edgeConfig="edgeConfig"
    :fitView="true"
    @ready="onGraphReady"
    @nodeClick="onNodeClick"
  />
</template>

<script setup>
import { G6Graph } from '@/global/common/g6';

const graphData = {
  nodes: [
    { id: 'node1', label: '用户模块', x: 100, y: 100 },
    { id: 'node2', label: '字典模块', x: 300, y: 100 },
  ],
  edges: [
    { source: 'node1', target: 'node2', label: '依赖' },
  ],
};

const layoutConfig = {
  type: 'dagre',
  rankdir: 'TB',
  align: 'UL',
  nodesep: 30,
  ranksep: 50,
};

const nodeConfig = {
  type: 'rect',
  size: [80, 40],
  style: {
    fill: '#9EC9FF',
    stroke: '#5B8FF9',
  },
  labelCfg: {
    style: {
      fill: '#000',
      fontSize: 12,
    },
  },
};

const edgeConfig = {
  type: 'polyline',
  style: {
    radius: 20,
    offset: 20,
    endArrow: true,
  },
};

const onGraphReady = (graph) => {
  console.log('图表已就绪', graph);
};

const onNodeClick = (item) => {
  console.log('节点被点击', item);
};
</script>
```

## Props

| 属性 | 类型 | 默认值 | 说明 |
|------|------|--------|------|
| width | string | '100%' | 图表容器宽度 |
| height | string | '500px' | 图表容器高度 |
| data | object | `{nodes: [], edges: []}` | 图表数据 |
| layout | object | `{type: 'dagre', rankdir: 'LR'}` | 布局配置 |
| nodeConfig | object | `{type: 'circle', size: 20, color: '#5B8FF9'}` | 节点配置 |
| edgeConfig | object | `{type: 'polyline', style: {radius: 20, endArrow: true}}` | 边配置 |
| graphConfig | object | `{}` | 图表配置 |
| fitView | boolean | true | 是否适应视窗 |
| fitCenter | boolean | false | 是否居中显示 |
| showMiniMap | boolean | false | 是否显示小地图 |
| minimapConfig | object | `{}` | 小地图配置 |
| onReady | function | - | 图表就绪回调 |
| onNodeClick | function | - | 节点点击回调 |
| onEdgeClick | function | - | 边点击回调 |
| onNodeMouseEnter | function | - | 节点鼠标进入回调 |
| onNodeMouseLeave | function | - | 节点鼠标离开回调 |

## Events

| 事件名 | 说明 | 回调参数 |
|--------|------|----------|
| ready | 图表初始化完成 | graph: G6 Graph 实例 |
| nodeClick | 节点被点击 | item: 节点信息 |
| edgeClick | 边被点击 | item: 边信息 |
| nodeMouseEnter | 鼠标进入节点 | item: 节点信息 |
| nodeMouseLeave | 鼠标离开节点 | item: 节点信息 |

## 方法

通过 `ref` 可以调用以下方法：

- `graph`: 获取 G6 Graph 实例
- `updateGraph()`: 更新图表数据
- `fitView()`: 适应视窗
- `fitCenter()`: 居中显示
- `refresh()`: 刷新图表

## 示例

参考 `G6Graph.example.vue` 文件获取更详细的使用示例。