<script setup name="G6Graph" lang="ts">
/**
 * 自定义封装G6 图表
 * 官方文档：https://g6.antv.antgroup.com/api/
 * G6 v5 版本
 */
import { onMounted, onUnmounted, watch, ref, nextTick } from 'vue';
import { Graph} from '@antv/g6';
import type { GraphData, NodeData, EdgeData, GraphOptions } from '@antv/g6';
// 定义组件属性
interface Props {
  width?: string;
  height?: string;
  data?: {
    nodes: NodeData[];
    edges: EdgeData[];
  };
  layout?: {
    type: string;
    [key: string]: any;
  };
  nodeConfig?: {
    [key: string]: any;
  };
  edgeConfig?: {
    [key: string]: any;
  };
  graphConfig?: Partial<GraphOptions>;
  fitView?: boolean;
  fitCenter?: boolean;
  minimapConfig?: {
    [key: string]: any;
  };
  onReady?: (graph: Graph) => void;
  onNodeClick?: (item: any) => void;
  onEdgeClick?: (item: any) => void;
  onNodePointerEnter?: (item: any) => void;
  onNodePointerLeave?: (item: any) => void;
}

const props = withDefaults(defineProps<Props>(), {
  width: '100%',
  height: '500px',
  data: () => ({ nodes: [], edges: [] }),
  layout: () => ({ type: 'dagre', rankdir: 'LR' }),
  nodeConfig: () => ({ type: 'circle', size: 20, color: '#5B8FF9' }),
  edgeConfig: () => ({ type: 'polyline', style: { radius: 20, endArrow: true } }),
  graphConfig: () => ({}),
  fitView: true,
  fitCenter: false,
});

// 定义事件
const emit = defineEmits<{
  ready: [graph: Graph];
  nodeClick: [item: any];
  edgeClick: [item: any];
  nodePointerEnter: [item: any];
  nodePointerLeave: [item: any];
}>();
// 容器 DOM
const graphContainer = ref<HTMLElement | null>(null);
let graph: Graph | null = null;

// 初始化图表
const initGraph = async () => {
  await nextTick();

  if (!graphContainer.value) return;

  const graphConfig: GraphOptions = {
    container: graphContainer.value,
    width: graphContainer.value?.offsetWidth || 500,
    height: graphContainer.value?.offsetHeight || 500,
    layout: props.layout,
    behaviors: ['drag-canvas', 'zoom-canvas', 'drag-element'],
    ...props.graphConfig,
  };

  // 创建图表实例 - G6 v5 使用 new Graph()
  graph = new Graph(graphConfig);

  // 设置节点和边的默认样式
  if (props.nodeConfig) {
    graph.setNode(props.nodeConfig);
  }
  if (props.edgeConfig) {
    graph.setEdge(props.edgeConfig);
  }

  // 设置数据
  graph.setData(props.data);

  // 渲染图表
  graph.render();

  // 适应视窗
  if (props.fitView) {
    graph.fitView();
  } else if (props.fitCenter) {
    graph.fitCenter();
  }

  // 绑定事件
  if (props.onNodeClick || emit) {
    graph.on('node:click', (evt) => {
      if (props.onNodeClick) props.onNodeClick(evt);
      emit('nodeClick', evt);
    });
  }

  if (props.onEdgeClick || emit) {
    graph.on('edge:click', (evt) => {
      if (props.onEdgeClick) props.onEdgeClick(evt);
      emit('edgeClick', evt);
    });
  }

  if (props.onNodePointerEnter || emit) {
    graph.on('node:pointerenter', (evt) => {
      if (props.onNodePointerEnter) props.onNodePointerEnter(evt);
      emit('nodePointerEnter', evt);
    });
  }

  if (props.onNodePointerLeave || emit) {
    graph.on('node:pointerleave', (evt) => {
      if (props.onNodePointerLeave) props.onNodePointerLeave(evt);
      emit('nodePointerLeave', evt);
    });
  }

  // 触发就绪事件
  if (props.onReady) props.onReady(graph);
  emit('ready', graph);
};

// 更新图表数据
const updateGraph = () => {
  if (graph) {
    graph.changeData(props.data as GraphData);
    if (props.fitView) {
      graph.fitView();
    } else if (props.fitCenter) {
      graph.fitCenter();
    }
  }
};

// 监听数据变化
watch(() => props.data, () => {
  updateGraph();
}, { deep: true });

// 监听布局变化
watch(() => props.layout, () => {
  if (graph) {
    graph.updateLayout(props.layout);
  }
}, { deep: true });

// 组件挂载时初始化图表
onMounted(() => {
  initGraph();
});

// 组件卸载时销毁图表
onUnmounted(() => {
  if (graph) {
    graph.destroy();
    graph = null;
  }
});

// 暴露方法
defineExpose({
  graph,
  updateGraph,
  fitView: () => {
    if (graph) graph.fitView();
  },
  fitCenter: () => {
    if (graph) graph.fitCenter();
  },
  refresh: () => {
    updateGraph();
  },
});
</script>
<template>
  <div ref="graphContainer" class="g6-graph-container" :style="{ width: width, height: height }"></div>
</template>
<style scoped>
.g6-graph-container {
  position: relative;
  overflow: hidden;
}
</style>
