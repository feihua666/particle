<script setup lang="ts">
import { ref } from 'vue'
import { useVueFlow } from '@vue-flow/core'
import BaseNode from '../BaseNode.vue'
import type { WorkflowNodeData } from '../../../workflow'
import {useConstantOutputNodeValue} from "../../../composables/useConstantOutputNodeValue.ts";
import {isObject} from "../../../../../../common/tools/ObjectTools.ts";

const { updateNodeData, onNodeDragStart } = useVueFlow()

const props = withDefaults(defineProps<{
  id: string
  data: WorkflowNodeData
}>(), {})

const getFormatter = (value) => {
  return isObject( value) ? JSON.stringify(value): value
}
// 使用统一的节点值管理
const { value } = useConstantOutputNodeValue(props.id, props, updateNodeData, useVueFlow(),getFormatter)

const isFocused = ref(false)
const inputRef = ref()
onNodeDragStart(() => {
  isFocused.value = false
  inputRef.value?.blur()
})

</script>

<template>
  <BaseNode :id="id" :data="data" class="pt-text-node">
    <el-text v-if="!value">将展示被连接线接管的数据</el-text>
    <el-input v-else
        ref="inputRef"
        v-model="value"
        type="textarea"
        resize="none"
        readonly
        placeholder="将展示被连接线接管的数据"
        class="pt-text-node-el-input"
        @focus="isFocused = true"
        @blur="isFocused = false"
        :class="{nodrag : isFocused,nowheel : isFocused}"
    />
  </BaseNode>
</template>

<style scoped>

.pt-text-node-el-input{
  height: 100%;
  width: 100%;
  display: block;
}
.pt-text-node-el-input :deep(textarea) {
  height: 100%;
  width: 100%;
  scrollbar-width: thin;
  scrollbar-color: var(--vf-connection-path) transparent;
}
</style>
