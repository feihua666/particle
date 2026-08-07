<script setup lang="ts">
import { ref,computed } from 'vue'
import { useVueFlow } from '@vue-flow/core'
import BaseNode from '../BaseNode.vue'
import type { WorkflowNodeData } from '../../../workflow'
import {useConstantInputNodeValue} from "../../../composables/useConstantInputNodeValue.ts";

const { updateNodeData, onNodeDragStart } = useVueFlow()

const props = withDefaults(defineProps<{
  id: string
  data: WorkflowNodeData
}>(), {})

// 使用统一的节点值管理
const { value } = useConstantInputNodeValue(props.id, props, updateNodeData, useVueFlow())

const isFocused = ref(false)
const inputRef = ref()
onNodeDragStart(() => {
  isFocused.value = false
  inputRef.value?.blur()
})

</script>

<template>
  <BaseNode :id="id" :data="data" class="pt-ct-it-text-node">
    <el-input
        ref="inputRef"
        v-model="value"
        type="textarea"
        resize="none"
        placeholder="请输入内容"
        class="pt-ct-it-text-node-el-input"
        @focus="isFocused = true"
        @blur="isFocused = false"
        :class="{nodrag : isFocused,nowheel : isFocused}"
    />
  </BaseNode>
</template>

<style scoped>

.pt-ct-it-text-node-el-input{
  height: 100%;
  width: 100%;
  display: block;
}
.pt-ct-it-text-node-el-input :deep(textarea) {
  height: 100%;
  width: 100%;
  scrollbar-width: thin;
  scrollbar-color: var(--vf-connection-path) transparent;
}
</style>
