<script setup lang="ts">
import { ref,computed } from 'vue'
import { useVueFlow } from '@vue-flow/core'
import BaseNode from '../BaseNode.vue'
import type { WorkflowNodeData } from '../../../workflow'
import {PortDefinitionName} from "../../../workflow/enums.ts";
import {getPortData, updatePortData} from "../../../tools/workflowTools.ts";

const { updateNodeData, onNodeDragStart } = useVueFlow()

const props = withDefaults(defineProps<{
  id: string
  data: WorkflowNodeData
}>(), {})

// 使用统一的节点值管理
const value = computed({
  get: () => {
    return getPortData(props.data.valuePorts, PortDefinitionName.CONTENT) ?? ''

  },
  set: (val) => {
    // 避免重复更新（非常关键）
    const oldVal = getPortData(props.data.valuePorts, PortDefinitionName.CONTENT)
    if (oldVal === val) return
    updateNodeData(props.id, {
      // 本地值（UI状态）
      valuePorts: updatePortData(props.data.valuePorts, PortDefinitionName.CONTENT, val)
    })
    // 数据变更后，没有自动更新下游节点的 inputPorts，需要执行节点，或者尝试删除连接线再重新连接
  }
})
const isFocused = ref(false)
const inputRef = ref()
onNodeDragStart(() => {
  isFocused.value = false
  inputRef.value?.blur()
})

</script>

<template>
  <BaseNode :id="id" :data="data" class="pt-ps-groovy-script-node">
    <el-input
        ref="inputRef"
        v-model="value"
        type="textarea"
        resize="none"
        placeholder="请编辑Groovy脚本"
        class="pt-ps-groovy-script-node-el-input"
        @focus="isFocused = true"
        @blur="isFocused = false"
        :class="{nodrag : isFocused,nowheel : isFocused}"
    />
  </BaseNode>
</template>

<style scoped>
.pt-ps-groovy-script-node-el-input{
  height: 100%;
  width: 100%;
  display: block;
}
.pt-ps-groovy-script-node-el-input :deep(textarea) {
  height: 100%;
  width: 100%;
  scrollbar-width: thin;
  scrollbar-color: var(--vf-connection-path) transparent;
}
</style>
