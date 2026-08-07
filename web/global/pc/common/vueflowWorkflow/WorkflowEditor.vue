<script setup name="WorkflowEditor" lang="ts">
import {ref, watch, provide, onMounted} from 'vue';
import type {Connection,VueFlowStore,NodeChange} from '@vue-flow/core'
import VueFlowGraph from "../../../common/vueflow/VueFlowGraph.vue"
import FlowToolbar from "./components/toolbar/FlowToolbar.vue"
import EdgeControl from "./components/edge/EdgeControl.vue";
import { createNodeRegistry } from './tools/nodeTools'
import {builtInNodeConfigs, builtInEdgeConfigs, builtInGroups} from "./registry/BuiltInConfigs";
import {createEdgeRegistry} from "./tools/edgeTools";
import {createIsValidConnectionValidator, onEdgeConnect, onNodesChanges,onEdgesChanges} from "./tools/workflowTools";
import type {EdgeChange} from "@vue-flow/core/dist/types/changes";
import {createNodeGroupRegistry} from "./registry/NodeGroupRegistry.ts";
import type {VueFlowGraphData} from "./workflow";
import {createExecutionStore} from "./composables/useExecutionStore.ts";
const createdExecutionStore = createExecutionStore('execution')
const executionStore = createdExecutionStore()
// 向所有子组件提供 store
provide('executionStore', executionStore)
const vueflowGraph = ref(null);
const props = defineProps({
  // 在保存时调用
  onSave: {
    type: Function,
    default: ({command,graphData} : {command: string,graphData: VueFlowGraphData}) => {},
  },
  // 在执行时调用
  onRun: {
    type: Function,
    default: ({command, selectedNodeIds}: {command: string, selectedNodeIds: string[]}) => {},
  },
  // 实例创建完成时调用
  onUsedVueFlowReady: {
    type: Function,
    default: (instance: VueFlowStore) => {},
  },
  // 是否发布,发布后不能修改
  isPublish: {
    type: Boolean,
    default: false
  },
  // 是否可编辑，主要争对画布上的节点
  editable: {
    type: Boolean,
    default: true
  },
  // 是否可执行
  executable: {
    type: Boolean,
    default: true
  },
})
// 节点分组
const nodeGroupRegistry = createNodeGroupRegistry()
nodeGroupRegistry.registerAll(builtInGroups)
const nodeGroupConfigs = nodeGroupRegistry.getAllConfigs()
// 节点
const nodeRegistry = createNodeRegistry()
nodeRegistry.registerAll(builtInNodeConfigs)
const nodeTypes = nodeRegistry.getNodeTypes();
// 边
const edgeRegistry = createEdgeRegistry()
edgeRegistry.registerAll(builtInEdgeConfigs)
const edgeTypes = edgeRegistry.getEdgeTypes();

const isValidConnection = ref()

// VueFlowGraph 实例准备好时创建校验器
const onUsedVueFlowReady = (instance: VueFlowStore) => {
  let allConfigs = nodeRegistry.getAllConfigs()
  isValidConnection.value = createIsValidConnectionValidator(allConfigs, instance)
  props.onUsedVueFlowReady(instance)

  const { onConnect, onNodesChange,onEdgesChange, deleteKeyCode} = instance

  const defaultDeleteKeyCode = deleteKeyCode.value
  // 监听连线事件
  onConnect((connection: Connection) => {
    onEdgeConnect(connection, instance)
  })

  // 监听节点变化
  onNodesChange((changes: NodeChange[]) => {
    onNodesChanges(changes, instance)
  })
  // 监听边变化
  onEdgesChange((changes: EdgeChange[]) => {
    onEdgesChanges(changes, instance)
  })
  const deleteKeyControl = (editable: boolean)=>{
    if (editable) {
      deleteKeyCode.value =  defaultDeleteKeyCode
    }else{
      deleteKeyCode.value =  null
    }
  }
  deleteKeyControl(props.editable)
  watch(() => props.editable, (editable) => {
    deleteKeyControl(editable)
  })
}

</script>

<template>
  <div class="pt-workflow-editor">
    <VueFlowGraph ref="vueflowGraph"
                  :nodeTypes="nodeTypes"
                  :edgeTypes="edgeTypes"
                  @usedVueFlowReady="onUsedVueFlowReady"
                  :isValidConnection="isValidConnection"
                  :nodesConnectable="editable"
                  :edgesUpdatable="editable">
      <FlowToolbar :onSave="onSave"
                   :on-run="onRun"
                   :nodeRegistry="nodeRegistry"
                   :nodeGroupRegistry="nodeGroupRegistry"
                   :isPublish="isPublish"
                   :editable="editable"/>
      <EdgeControl />
    </VueFlowGraph>
  </div>
</template>

<style scoped>

</style>
<style>
.pt-workflow-editor{
  width: 100%;
  height: 100%;
}

/* 节点鼠标移动到节点上边框效果 */
@media (hover: hover) {
  .pt-workflow-editor .vue-flow__node:hover {
    border-color: var(--vf-node-text);
  }
}
.pt-workflow-editor .vue-flow__node.selected {
  border-color: var(--vf-node-text);
}
.pt-workflow-editor .vue-flow__node{
  border: 1px solid var(--vf-connection-path);
  border-radius: 5px;
  padding: 0.2rem;
  background-color: var(--vf-node-bg);
}
</style>
