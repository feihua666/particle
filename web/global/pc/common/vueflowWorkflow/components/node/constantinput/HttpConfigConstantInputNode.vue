<script setup lang="ts">
import { useVueFlow } from '@vue-flow/core'
import BaseNode from '../BaseNode.vue'
import type { WorkflowNodeData } from '../../../workflow'
import {useConstantInputNodeValue} from "../../../composables/useConstantInputNodeValue.ts";
import HttpConfig from '../HttpConfig.vue'
const { updateNodeData, onNodeDragStart } = useVueFlow()

const props = withDefaults(defineProps<{
  id: string
  data: WorkflowNodeData
}>(), {})

// 使用统一的节点值管理
const { value } = useConstantInputNodeValue(props.id, props, updateNodeData, useVueFlow())

const onFormChange = (form) => {
  value.value = {...form}
}

</script>

<template>
  <BaseNode :id="id" :data="data" class="pt-ct-it-http-config-node" :is-show-resizer="false">
    <HttpConfig ref="inputRef"
                :onFormChange="onFormChange"
                class="pt-ct-it-http-config-node-http-config"
    />

  </BaseNode>
</template>

<style scoped>

.pt-ct-it-http-config-node-http-config{
  height: 100%;
  width: 100%;
  display: block;
}

</style>
