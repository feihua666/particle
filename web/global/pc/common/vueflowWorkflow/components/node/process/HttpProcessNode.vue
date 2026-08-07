<script setup lang="ts">
import { useVueFlow } from '@vue-flow/core'
import BaseNode from '../BaseNode.vue'
import type { WorkflowNodeData } from '../../../workflow'
import {useConstantInputNodeValue} from "../../../composables/useConstantInputNodeValue.ts";
import HttpConfig from '../HttpConfig.vue'
import {computed, ref, watch} from "vue";
import {getPortData, updatePortData} from "../../../tools/workflowTools.ts";
import {PortDefinitionName} from "../../../workflow/enums.ts";
const usedVueFlow = useVueFlow()
const { updateNodeData, onNodeDragStart } = usedVueFlow

const inputRef = ref()
const props = withDefaults(defineProps<{
  id: string
  data: WorkflowNodeData
}>(), {})

// 表单变更时，更新节点值
const onFormChange = (form) => {
  const oldVal = getPortData(props.data.valuePorts, PortDefinitionName.CONTENT)
  // 序列化后比较内容是否真正变化
  if (form === oldVal) {
    return
  }
  updateNodeData(props.id, {
    // 本地值（UI状态）
    valuePorts: updatePortData(props.data.valuePorts, PortDefinitionName.CONTENT, form),
  })
}

watch(() => getPortData(props.data.inputPorts, 'httpConfig'),
    (val) => {
      updateNodeData(props.id, {
        // 本地值（UI状态）
        valuePorts: updatePortData(props.data.valuePorts, PortDefinitionName.CONTENT, val),
      })
    }
)
watch(() => getPortData(props.data.valuePorts, PortDefinitionName.CONTENT),
    (val) => {
      if (!inputRef.value || !val) return
      // 更新form
      Object.assign(inputRef.value.form, val)
    }
)

const disabled = computed(() => {
  return props.data.inputPorts?.['httpConfig']?.hasIncomingEdge
})

</script>

<template>
  <BaseNode :id="id" :data="data" class="pt-ct-it-http-config-node" :is-show-resizer="false">
    <HttpConfig ref="inputRef"
                :disabled="disabled"
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
