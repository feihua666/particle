<script setup lang="ts">
import { computed, ref } from 'vue'
import { useVueFlow } from '@vue-flow/core'
import { Picture } from '@element-plus/icons-vue'
import BaseNode from '../BaseNode.vue'
import type { WorkflowNodeData } from '../../../workflow'
import {useConstantOutputNodeValue} from "../../../composables/useConstantOutputNodeValue.ts";

const { updateNodeData, onNodeDragStart } = useVueFlow()

const props = withDefaults(defineProps<{
  id: string
  data: WorkflowNodeData
}>(), {})

// 使用统一的节点值管理
const { value } = useConstantOutputNodeValue(props.id, props, updateNodeData, useVueFlow())

const showViewer = ref(false)

const actions = [
  {
    icon: 'ZoomIn',
    // label: '查看图片',
    method: () => {
      showViewer.value = true
    }
  },
]
const isShowActions = computed(() => {
  return !!value.value
})
</script>

<template>
  <BaseNode :id="id" :data="data" :actions="actions" :is-show-actions="isShowActions" class="pt-st-ot-image-node">
    <el-text v-if="!value">将渲染被连接线接管的地址为图片</el-text>
    <template v-else>
      <el-image
          :src="value"
          fit="contain"
          class="pt-st-ot-image-node-img"
      >
        <template #error>
          <div class="pt-st-ot-image-node-error">
            <el-icon :size="30"><Picture /></el-icon>
            <span>加载失败</span>
          </div>
        </template>
      </el-image>
      <el-image-viewer
          v-if="showViewer"
          :url-list="[value]"
          :teleported="true"
          :zoom-rate="1.02"
          @close="showViewer = false"
      />
    </template>
  </BaseNode>
</template>

<style scoped>
.pt-st-ot-image-node-img {
  height: 100%;
  width: 100%;
  max-width: 100%;
  max-height: 100%;
}

.pt-st-ot-image-node-error {
  height: 100%;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
</style>
