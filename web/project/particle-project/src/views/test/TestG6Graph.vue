<template>
  <div class="example-container">
    <h3>组件依赖关系图示例</h3>
    <PtG6Graph
        style="border: red 1px solid;"
        :data="graphData"
        :layout="layoutConfig"
        :nodeConfig="nodeConfig"
        :edgeConfig="edgeConfig"
        :fitView="true"
        @ready="onGraphReady"
        @nodeClick="onNodeClick"
        width="100%"
        height="600px"
    />
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';

// 图表数据
const graphData = ref({
  nodes: [
    { id: 'node1', label: 'user', x: 100, y: 100 },
    { id: 'node2', label: 'dict', x: 300, y: 100 },
    { id: 'node3', label: 'area', x: 500, y: 100 },
    { id: 'node4', label: 'data', x: 200, y: 250 },
    { id: 'node5', label: 'data-query', x: 400, y: 250 },
  ],
  edges: [
    { source: 'node1', target: 'node2', label: '可选' },
    { source: 'node4', target: 'node2', label: '必须' },
    { source: 'node4', target: 'node3', label: '必须' },
    { source: 'node5', target: 'node2', label: '可选' },
  ],
});

// 布局配置
const layoutConfig = {
  type: 'dagre',
  rankdir: 'TB', // 从上到下布局
  align: 'UL',
  nodesep: 30,
  ranksep: 50,
};

// 节点配置
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

// 边配置
const edgeConfig = {
  type: 'polyline',
  style: {
    radius: 20,
    offset: 20,
    endArrow: true,
  },
};

// 图表就绪事件
const onGraphReady = (graph: any) => {
  console.log('G6 Graph is ready', graph);
};

// 节点点击事件
const onNodeClick = (item: any) => {
  console.log('Node clicked:', item);
};
</script>

<style scoped>
.example-container {
  padding: 20px;
}
</style>
