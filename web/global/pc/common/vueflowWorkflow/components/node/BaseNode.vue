<script setup lang="ts">
/**
 * 这是一个基础节点组件，定义通用的节点样式和行为
 */
import {computed, inject, ref} from 'vue'
import { Handle, Position, useVueFlow } from '@vue-flow/core'
import { NodeResizer } from '@vue-flow/node-resizer'
import {ExecutionStatusEnum} from "../../execution";
import type {WorkflowNodeData} from "../../workflow";

const executionStore: any = inject('executionStore')
const props = withDefaults(defineProps<{
  id: string
  data: WorkflowNodeData,
  // 提供一个操作功能
  actions?: Array<{
    label?: string
    icon: string
    type?: string
    method: () => void
  }>,
  isShowActions?: boolean
  // 这两个很重要，因为一般节点的内容元素不要固定宽高，否则在拖拽改变大小时内容不是自动撑开，设置这两个值会自动在 node 节点容器上添加对应的宽高 style
  resizerMinWidth?: number
  resizerMinHeight?: number,

  resizerMaxWidth?: number
  resizerMaxHeight?: number
  isShowResizer?: boolean
}>(), {
  actions: () => [],
  isShowActions: true,
  resizerMinWidth: 50,
  resizerMinHeight: 50,

  resizerMaxWidth: 800,
  resizerMaxHeight: 800,
  isShowResizer: true
})

const { updateNodeData } = useVueFlow()

// 编辑状态
const isEditing = ref(false)
const editingName = ref('')
const inputRef = ref<HTMLInputElement>()

// 开始编辑
const startEdit = () => {
  editingName.value = props.data.name || ''
  isEditing.value = true
  // 下一帧聚焦输入框
  setTimeout(() => {
    inputRef.value?.focus()
    inputRef.value?.select()
  }, 0)
}

// 完成编辑
const finishEdit = () => {
  isEditing.value = false
  const newName = editingName.value.trim()
  // 只有当名称发生变化时才更新
  if (newName !== props.data.name) {
    updateNodeData(props.id, { name: newName })
  }
}

// 取消编辑
const cancelEdit = () => {
  isEditing.value = false
}

// 监听 Enter 键
const handleKeydown = (e: KeyboardEvent) => {
  if (e.key === 'Enter') {
    finishEdit()
  } else if (e.key === 'Escape') {
    cancelEdit()
  }
}
const executionConfig = computed(() => {
  const node = executionStore.nodeExecution[props.id]
  const isRunning = executionStore.isNodeRunning(props.id)

  const result = {
    type: undefined as string | undefined,
    icon: null as any,
    statusName: node?.statusName || '',
  }

  if (isRunning) {
    result.type = 'primary'
    result.icon = 'Loading'
    result.statusName = node?.statusName
    return result
  }

  switch (node?.statusValue) {
    case ExecutionStatusEnum.COMPLETED:
      result.type = 'success'
      result.icon = 'Check'
      break
    case ExecutionStatusEnum.FAILED:
      result.type = 'danger'
      result.icon = 'Close'
      break
    case ExecutionStatusEnum.PAUSED:
      result.type = 'warning'
      result.icon = 'VideoPause'
      break
    case ExecutionStatusEnum.STOPPED:
      result.type = 'info'
      result.icon = 'Stopwatch'
      break
  }

  return result
})
// 根据 index 自动计算位置（等间距分布）
const getHandleAutoStyle = (index: number, total: number) => {
  if (total === 0 || !total) return {}
  if (total === 1) return { top: '50%' }

  // 使用对称公式：将 index 映射到 [-1, 1] 区间，然后转换到 [30%, 70%]
  // 总范围 30% 到 70%，中心点在 50%
  const center = 50
  const range = 40  // 70 - 30

  // 将 index 映射到 [-1, 1] 区间（对称）
  const t = (index / (total - 1)) * 2 - 1

  // 线性映射到 [center - range/2, center + range/2]
  const percent = center + t * (range / 2)

  return { top: `${percent}%` }
}
</script>

<template>
  <div class="pt-node">
    <!-- 左侧输入端口 -->
    <el-tooltip
      v-for="(port, portName, index) in props.data.inputPorts"
      :key="portName"
      :content="`${portName}${port.description ? ': ' + port.description : ''}`"
      placement="right"
    >
      <Handle
          type="target"
          :position="Position.Left"
          :id="portName"
          :class="'pt-node-handle-left-' + port.portType?.toLowerCase()"
          :style="getHandleAutoStyle(index, Object.keys(props.data.inputPorts||{}).length)"
      />
    </el-tooltip>

    <!-- 标题区域 -->
    <div class="pt-node-header">
      <!-- 节点类型名称（左侧） -->
      <el-text class="pt-node-type-name">{{ props.data.typeName }}</el-text>

      <!-- 用户自定义名称（右侧，可编辑） -->
      <div class="pt-node-custom-name-wrapper">
        <!-- 编辑模式 -->
        <el-input
          v-if="isEditing"
          ref="inputRef"
          v-model="editingName"
          class="pt-node-name-input"
          @blur="finishEdit"
          @keydown="handleKeydown"
        />
        <!-- 显示模式 -->
        <el-tooltip
          v-else
          :content="props.data.name || '未命名'"
          placement="top-start"
        >
          <el-text
            class="pt-node-custom-name"
            @click="startEdit"
          >
            {{ props.data.name || '未命名' }}
          </el-text>
        </el-tooltip>
      </div>
    </div>

    <div class="pt-node-execution" v-if="executionStore.lastExecutionId">
      <el-text :type="executionConfig.type">
        <el-icon v-if="executionConfig.icon">
          <component :is="executionConfig.icon" />
        </el-icon>
        {{ executionConfig.statusName }}
      </el-text>
    </div>

    <slot></slot>
    <div class="pt-node-actions" v-if="actions && actions.length > 0 && isShowActions">
      <el-button v-for="(action,index) in actions" :key="(action.label || '') + index" :type="action.type" circle size="small" @click.stop="action.method">
        <el-icon>
          <component :is="action.icon"/>
        </el-icon>
        <template v-if="action.label">{{action.label}}</template>
      </el-button>
    </div>
    <!-- 右侧输出端口 -->
    <el-tooltip
      v-for="(port, portName, index) in props.data.outputPorts"
      :key="portName"
      :content="`${portName}${port.description ? ': ' + port.description : ''}`"
      placement="left"
    >
      <Handle
          type="source"
          :position="Position.Right"
          :id="portName"
          :class="'pt-node-handle-right-' + port.portType?.toLowerCase()"
          :style="getHandleAutoStyle(index, Object.keys(props.data.outputPorts||{}).length)"
      />
    </el-tooltip>
    <NodeResizer
        v-if="isShowResizer"
        :min-width="resizerMinWidth" :min-height="resizerMinHeight"
        :max-width="resizerMaxWidth" :max-height="resizerMaxHeight"
                 handle-class-name="pt-node-bottom-right-resizer-handle"
                 line-class-name="pt-node-bottom-right-resizer-line"/>
  </div>
</template>

<style scoped>
</style>

<style>

/* 自定义节点窗口 */
.pt-node{
  height: 100%;
  width: 100%;
}

/* 节点标题区域 */
.pt-node-header {
  position: absolute;
  top: -2rem;
  left: 0;
  right: 0;
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 0 4px;
}



/* 用户自定义名称容器（右侧） */
.pt-node-custom-name-wrapper {
  flex: 1;
  overflow: hidden;
  display: flex;
  align-items: center;
}

/* 用户自定义名称文本 */
.pt-node-custom-name {
  display: block;
  text-overflow: ellipsis;
  white-space: nowrap;
}

/* 名称输入框 */
.pt-node-name-input {
  width: 100%;
}

.pt-node-execution{
  position: absolute;
  top: -1rem;
  left: 0;
  right: 0;
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 0 4px;
}

.pt-node-actions {
  position: absolute;
  top: 12px;
  right: 12px;
  display: flex;
  opacity: 0;
  transition: opacity 0.2s;
}
.pt-node:hover .pt-node-actions {
  opacity: 1;
}
.pt-node-handle-left-control, .pt-node-handle-right-control{
  background: #1c6ca1;
}

/* 节点拖拽改变大小 开始 */
.pt-node-bottom-right-resizer-line {
  display: none;
}

.pt-node-bottom-right-resizer-handle.top.left,
.pt-node-bottom-right-resizer-handle.top.right,
.pt-node-bottom-right-resizer-handle.bottom.left {
  display: none;
}
.pt-node-bottom-right-resizer-handle.bottom.right{
  width: 0;
  height: 0;
  border: none;
  background: transparent;
  border-left: 4px solid transparent;
  border-top: 4px solid transparent;
  border-right: 4px solid var(--vf-node-text);
  border-bottom: 4px solid var(--vf-node-text);
  transform: translate(-130%, -130%);
  border-radius: 2px;
}

</style>
