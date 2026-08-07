<script setup lang="ts">
import { computed, ref,inject} from 'vue'

import { Panel, useVueFlow } from '@vue-flow/core'
import Logo from '../../../Logo.vue'
import {addNodeToFlow} from "../../tools/nodeTools";
import {getData} from "../../tools/vueFlowTools";
import {withLoading} from "../../../../../common/tools/PromiseTools";
import {NodeRegistry} from "../../registry/NodeRegistry.ts";
import {NodeGroupRegistry} from "../../registry/NodeGroupRegistry.ts";
import type {VueFlowGraphData} from "../../workflow";
const usedVueFlow = useVueFlow()
const { nodes } = useVueFlow()
const executionStore = inject('executionStore')
// 声明属性
const props = defineProps({
  // 节点注册实例
  nodeGroupRegistry: {
    type: NodeGroupRegistry,
    required: true,
  },
  // 节点注册实例
  nodeRegistry: {
    type: NodeRegistry,
    required: true,
  },
  // 在保存时调用
  onSave: {
    type: Function,
    default: ({command,graphData}: {command: string,graphData: VueFlowGraphData}) => {},
  },
  // 在执行时调用
  onRun: {
    type: Function,
    default: ({command, selectedNodeIds,graphData}: {command: string, selectedNodeIds: string[],graphData: VueFlowGraphData}) => {},
  },
  // 是否发布,发布后不能修改
  isPublish: {
    type: Boolean,
    default: false
  },
  // 是否可编辑，主要针对一些操作彬
  editable: {
    type: Boolean,
    default: true
  },
  // 是否可执行
  executable: {
    type: Boolean,
    default: true
  }
})
const nodeGroupConfigs = props.nodeGroupRegistry.getAllConfigs()
const finalNodeGroupConfigs = props.nodeRegistry.getGroupedNodes(nodeGroupConfigs)

// 点击添加节点项，添加对应的节点
const handleCreateNodeCommand = (command: string) => {
  onAdd(command)
}

/**
 * 添加节点
 */
function onAdd(type: string) {
  let nodeConfig = props.nodeRegistry.getConfig(type)
  addNodeToFlow(nodeConfig,usedVueFlow)
}

// 保存
const saveConfigs = [
    {
      type: 'saveDraft',
      label: '保存草稿',
      icon: 'Save',
      disabled: computed(() => props.isPublish || !props.editable),
    },
  {
    type: 'saveAndPublish',
    label: '保存并发布',
    icon: 'Save',
    disabled: computed(() => props.isPublish || !props.editable),
  },
]
const saveLoading = ref(false)
const handleSaveCommand = (command: string) => {
  onSave(command)
}

/**
 * 保存画布
 * 使用 getData 获取 VueFlow 原始数据
 */
function onSave(type: string) {
  withLoading(()=> {
    const graphData = getData(usedVueFlow)
    return props.onSave({command: type, graphData})
  },(loading)=> { saveLoading.value = loading})
}

// 执行控制
const runConfigs = [
  {
    type: 'runAll',
    label: '全部执行',
    icon: 'VideoPlay',
    disabled: computed(() => !props.executable || executionStore.isExecuting()),
  },
  {
    type: 'runFromNode',
    label: '从此节点执行',
    icon: 'CaretRight',
    disabled: computed(() => selectedNodes.value.length === 0 || !props.executable || executionStore.isExecuting()),
  },
  {
    type: 'runToNode',
    label: '执行到此节点',
    icon: 'CaretRight',
    disabled: computed(() => selectedNodes.value.length === 0 || !props.executable || executionStore.isExecuting()),
  },
  {
    type: 'runSingleNode',
    label: '执行此节点',
    icon: 'Pointer',
    disabled: computed(() => selectedNodes.value.length === 0 || !props.executable || executionStore.isExecuting()),
  },
]
const runLoading = ref(false)
const handleRunCommand = (command: string) => {
  onRun(command)
}

/**
 * 执行工作流
 */
function onRun(type: string) {
  // 获取选中的节点 ID
  const selectedNodeIds = selectedNodes.value.map(n => n.id)
  // 获取当前画布数据（业务层可用于草稿模式下先保存再执行）
  const graphData = getData(usedVueFlow)

  withLoading(() => {
    return props.onRun({ command: type, selectedNodeIds, graphData })
  }, (loading) => { runLoading.value = loading })
}

// 选中节点
const selectedNodes = computed(() => {
  return nodes.value.filter(n => n.selected)
})


// 状态
const selectedNodesLabel = computed(() => {
  const count = selectedNodes.value.length
  if (count === 0) return '未选中'
  if (count === 1) {
    const node = selectedNodes.value[0]
    const name = node.data?.typeName + ' ' + (node.data?.name || '未命名')
    return name
  }
  return `${count}个节点`
})


const handleStatusClick = (command: string) => {
  console.log(command)
  if ('nodeData' == command) {
    const node = selectedNodes.value[0]
    console.log(node)
  }
}
// 状态
const statusGroupConfig = [
  {
    type: 'interaction',
    label: '交互相关',
    statusConfigs: [
      {
        type: 'selectedNodes',
        label: '选中节点',
        disabled: computed(() => selectedNodes.value.length === 0),
        content: computed(() => selectedNodesLabel.value),
      },
    ],
  },
  {
    type: 'exectuion',
    label: '执行相关',
    statusConfigs: [
      {
        type: 'lastExecutionId',
        label: '执行ID',
        disabled: computed(() => !executionStore.lastExecutionId),
        content: computed(() => executionStore.lastExecutionId || '无'),
      },
      {
        type: 'executionStatusName',
        label: '执行状态',
        disabled: computed(() => true),
        content: computed(() => executionStore.execution.statusName),
      },
    ]
  },
    {
      type: 'definitionHistory',
      label: '定义相关',
      statusConfigs: [
        {
          type: 'isPublish',
          label: '草稿/发布',
          disabled: computed(() => true),
          content: computed(() => props.isPublish ? '发布' : '草稿'),
        },
        {
          type: 'editable',
          label: '编辑/预览',
          disabled: computed(() => true),
          content: computed(() => props.editable ? '编辑' : '预览'),
        },
        {
          type: 'nodeData',
          label: '节点数据',
          disabled: computed(() => selectedNodes.value.length === 0),
          content: computed(() => '点击查看'),
        },
      ],
    },
]


</script>

<template>
  <Panel position="top-left" class="pt-flow-toolbar">
    <Logo :show-text="false"></Logo>
    <!--  创建节点  -->
    <el-tooltip
        content="创建节点"
        placement="top"
    >
    <el-dropdown trigger="click" placement="bottom-start" @command="handleCreateNodeCommand" :hide-on-click="false">
        <el-button icon="Plus" size="default" circle />
      <template #dropdown>
        <el-dropdown-menu>
          <template v-for="(group,index) in finalNodeGroupConfigs" :key="group.group">
            <el-dropdown-item :divided="index > 0" disabled>
              <el-text type="info">{{ group.label }}</el-text>
            </el-dropdown-item>
            <el-dropdown-item
                v-for="item in group.nodes"
                :key="item.type"
                :command="item.type"
                :disabled="isPublish  || !props.editable"
            >
              {{ item.ui.typeName }}
            </el-dropdown-item>
          </template>

        </el-dropdown-menu>
      </template>
    </el-dropdown>
    </el-tooltip>
    <!--  保存  -->
    <el-tooltip
        content="保存"
        placement="top"
    >
      <el-dropdown trigger="click" placement="bottom-start" @command="handleSaveCommand" :hide-on-click="false">
        <el-button icon="Document" size="default" circle :loading="saveLoading"/>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item
                v-for="item in saveConfigs"
                :key="item.type"
                :command="item.type"
                :disabled="saveLoading || item.disabled?.value"
            >
              {{ item.label }}
            </el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </el-tooltip>
    <!--  执行  -->
    <el-tooltip
        content="执行"
        placement="top"
    >
      <el-dropdown trigger="click" placement="bottom-start" @command="handleRunCommand" :hide-on-click="false">
        <el-button icon="VideoPlay" size="default" circle :loading="runLoading"/>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item
                v-for="item in runConfigs"
                :key="item.type"
                :command="item.type"
                :disabled="runLoading || item.disabled?.value"
            >
              {{ item.label }}
            </el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </el-tooltip>
    <!--  状态栏  -->
    <el-tooltip
        content="状态"
        placement="top"
    >
      <el-dropdown placement="bottom-start" @command="handleStatusClick" :hide-on-click="false">
        <el-button icon="InfoFilled" size="default" circle/>
        <template #dropdown>
          <el-dropdown-menu>
            <template v-for="(group,index) in statusGroupConfig" :key="group.type">
              <el-dropdown-item :divided="index > 0" disabled>
                <el-text type="info">{{ group.label }}</el-text>
              </el-dropdown-item>
              <el-dropdown-item
                  v-for="item in group.statusConfigs"
                  :key="item.type || item.label"
                  :command="item.type"
                  :disabled="item.disabled?.value"
              >
                {{ item.label }}: {{ item.content?.value }}
              </el-dropdown-item>
            </template>

          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </el-tooltip>
  </Panel>
</template>

<style scoped>
.pt-flow-toolbar {
  display: flex;
  gap: 12px;
}
</style>
