<script setup lang="ts">
import { computed, ref } from 'vue'
import { useVueFlow } from '@vue-flow/core'
import { Picture} from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import BaseNode from '../BaseNode.vue'
import {getFinalDownloadUrl, getUploadUrl} from "../../../../../../common/api/globalApi";
import type { WorkflowNodeData } from '../../../workflow'
import {useConstantInputNodeValue} from "../../../composables/useConstantInputNodeValue.ts";

const { updateNodeData, onNodeDragStart } = useVueFlow()

const props = withDefaults(defineProps<{
  id: string
  data: WorkflowNodeData
  // 上传地址，如果不传默认按 axiosRequest.ts 中的get getUploadUrl
  action?: string,
}>(), {})

// 使用统一的节点值管理
const { value } = useConstantInputNodeValue(props.id, props, updateNodeData, useVueFlow())
// 计算属性
// 这里和 props.loading 重名了，但在模板是使用 loading 变量是这个值，也就是说这里会覆盖在模板中的值
const action = computed(() => {
  return props.action || getUploadUrl()
})

const showViewer = ref(false)

function onSuccess(response: any) {
  value.value = getFinalDownloadUrl(response.absoluteHttpUrl)
}

function onError() {
  ElMessage.error('图片上传失败')
}
const actions = [
    {
      icon: 'ZoomIn',
      // label: '查看图片',
      method: () => {
        showViewer.value = true
      }
    },
    {
      icon: 'Delete',
      type: 'danger',
      // label: '删除图片',
      method: () => {
        value.value = ''
      }
    }
]
const isShowActions = computed(() => {
  return !!value.value
})
</script>

<template>
  <BaseNode :id="id" :data="data" :actions="actions" :isShowActions="isShowActions" class="pt-ct-it-image-node">
    <el-upload
        class="pt-ct-it-image-node-upload"
        drag
        :action="action"
        accept="image/*"
        :show-file-list="false"
        :on-success="onSuccess"
        :on-error="onError"
    >
      <!-- 已上传 -->
      <template v-if="value">
        <el-image
            :src="value"
            fit="contain"
            class="pt-ct-it-image-node-img"
        >
          <template #error>
            <div class="pt-ct-it-image-node-error">
              <el-icon :size="30"><Picture /></el-icon>
              <span>加载失败</span>
            </div>
          </template>
        </el-image>
      </template>

      <!-- 未上传 -->
      <template v-else>
        <div class="pt-ct-it-image-node-placeholder">
          <el-icon class="el-icon--upload"><upload-filled /></el-icon>
          <div class="el-upload__text">
            拖拽到这里 或 <em>点击上传</em>
          </div>
        </div>
      </template>

    </el-upload>

    <el-image-viewer
        v-if="showViewer"
        :url-list="[value]"
        :teleported="true"
        :zoom-rate="1.02"
        @close="showViewer = false"
    />
  </BaseNode>
</template>

<style scoped>
.pt-ct-it-image-node-upload {
  height: 100%;
  width: 100%;
  position: relative;
  overflow: hidden;
}

.pt-ct-it-image-node-upload :deep(.el-upload) {
  height: 100%;
  width: 100%;
}
.pt-ct-it-image-node-upload :deep(.el-upload-dragger) {
  height: 100%;
  width: 100%;
  padding: 0;
  border: none;
}
.pt-ct-it-image-node-placeholder {
  height: 100%;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.pt-ct-it-image-node-img {
  height: 100%;
  width: 100%;
  max-width: 100%;
  max-height: 100%;
}



.pt-ct-it-image-node-error {
  height: 100%;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.pt-ct-it-image-node-actions {
  position: absolute;
  top: 12px;
  right: 12px;
  display: flex;
  opacity: 0;
  transition: opacity 0.2s;
}
.pt-ct-it-image-node-upload:hover .pt-ct-it-image-node-actions {
  opacity: 1;
}
</style>
