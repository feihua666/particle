<script setup name="VueFlowGraph" lang="ts">
import { ref } from 'vue'
import type { Node, Edge ,VueFlowStore,ConnectionLineOptions,Connection} from '@vue-flow/core'
import { VueFlow, MarkerType} from '@vue-flow/core'
import { Background } from '@vue-flow/background'
import { ControlButton, Controls } from '@vue-flow/controls'
import { MiniMap } from '@vue-flow/minimap'
import VueFlowStoreProvider from './VueFlowStoreProvider.vue'
const props = defineProps({
  // 节点和边
  nodes: {
    type: Array as () => Node[],
    default: () => []
  },
  edges: {
    type: Array as () => Edge[],
    default: () => []
  },
  // 节点类型，自定义节点需要传该参数，以预定义节点
  nodeTypes: {
    type: Object as () => Record<string, any>,
    default: () => ({})
  },
  // 边类型，自定义边需要传该参数，以预定义边
  edgeTypes: {
    type: Object as () => Record<string, any>,
    default: () => ({})
  },
  // 全局连接线校验函数
  isValidConnection: {
    type: Function,
    default: (connection: Connection) => true
  },
  // 控制节点是否可以作为连线的终点或起点，无法从节点手柄拖出新的连线，无法将连线连接到该节点
  nodesConnectable: {
    type: Boolean,
    default: true
  },
  // 控制已存在的边是否可以被选中或修改，无法点击/选中边，无法拖拽调整边的连接点，无法删除边
  edgesUpdatable: {
    type: Boolean,
    default: true
  },
})
// 定义事件
const emit = defineEmits<{
  'connect': [params: any],
  'usedVueFlowReady': [usedVueFlow: VueFlowStore]
}>();

// 处理内部连线（可选：提供默认处理或完全交给父组件）
const onConnect = (params: any) => {
  // 直接透传事件给父组件
  emit('connect', params)
}

const flowInstance = ref()
const onInstanceReady = (instance: VueFlowStore) => {
  flowInstance.value = instance
  emit('usedVueFlowReady', instance)
}
defineExpose({
  usedVueFlow: flowInstance
})

</script>

<template>
  <VueFlow v-bind="props" @connect="onConnect">
    <slot></slot>
    <!-- 添加背景网格，增强无限画布的空间感 -->
    <Background :gap="20" :size="1" variant="lines" />
    <!-- 添加控制面板，方便用户缩放和锁定视图 -->
    <Controls position="bottom-right" />
    <!-- 添加小地图，在无限画布上快速定位 -->
    <MiniMap position="top-right" :zoomable="true" :pannable="true" />
    <VueFlowStoreProvider @usedVueFlowReady="onInstanceReady"></VueFlowStoreProvider>
  </VueFlow>
</template>

<style>
/* these are necessary styles for vue flow */
@import '@vue-flow/core/dist/style.css';

/* this contains the default theme, these are optional styles */
@import '@vue-flow/core/dist/theme-default.css';

@import '@vue-flow/controls/dist/style.css';
@import '@vue-flow/minimap/dist/style.css';

@import '@vue-flow/node-resizer/dist/style.css';
</style>
<style scoped>

</style>
