<script setup lang="ts">
import { computed, ref } from 'vue'
import { useVueFlow } from '@vue-flow/core'
import { ElMessage } from 'element-plus'
import BaseNode from '../BaseNode.vue'
import { getFinalDownloadUrl, getUploadUrl } from "../../../../../../common/api/globalApi"
import VideoNodeVideo from "../VideoNodeVideo.vue";
import type { WorkflowNodeData } from '../../../workflow'
import {useConstantInputNodeValue} from "../../../composables/useConstantInputNodeValue.ts";

const { updateNodeData, onNodeDragStart } = useVueFlow()

const props = withDefaults(defineProps<{
  id: string
  data: WorkflowNodeData
  // 上传地址，如果不传默认按 axiosRequest.ts 中的get getUploadUrl
  action?: String,
}>(), {})

// 使用统一的节点值管理
const { value } = useConstantInputNodeValue(props.id, props, updateNodeData, useVueFlow())

// 计算属性
// 这里和 props.loading 重名了，但在模板是使用 loading 变量是这个值，也就是说这里会覆盖在模板中的值
const action = computed(() => {
  return props.action || getUploadUrl()
})

const videoRef = ref<HTMLVideoElement>()



function onSuccess(response: any) {
  value.value = getFinalDownloadUrl(response.absoluteHttpUrl)
}

function onError() {
  ElMessage.error('视频上传失败')
}
const actions = [
  {
    icon: 'Delete',
    type: 'danger',
    // label: '删除视频',
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
  <BaseNode :id="id" :data="data" :actions="actions" :isShowActions="isShowActions" class="pt-ct-it-video-node">
    <el-upload
        class="pt-ct-it-video-node-upload"
        drag
        :action="action"
        accept=".mp4"
        :show-file-list="false"
        :on-success="onSuccess"
        :on-error="onError"
    >
      <!-- 已上传 -->
      <template v-if="value">
        <VideoNodeVideo
            ref="videoRef"
            :src="value"
            class="pt-ct-it-video-node-video"
        />
      </template>

      <!-- 未上传 -->
      <template v-else>
        <div class="pt-ct-it-video-node-placeholder">
          <el-icon class="el-icon--upload"><upload-filled /></el-icon>
          <div class="el-upload__text">
            拖拽到这里 或 <em>点击上传</em>
          </div>
          <div class="el-upload__tip">仅支持 MP4 格式</div>
        </div>
      </template>
    </el-upload>
  </BaseNode>
</template>

<style scoped>
.pt-ct-it-video-node-upload {
  height: 100%;
  width: 100%;
  position: relative;
}

.pt-ct-it-video-node-upload :deep(.el-upload) {
  height: 100%;
  width: 100%;
}

.pt-ct-it-video-node-upload :deep(.el-upload-dragger) {
  height: 100%;
  width: 100%;
  padding: 0;
  border: none;
}

.pt-ct-it-video-node-placeholder {
  height: 100%;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 4px;
}

.pt-ct-it-video-node-video {
  height: 100%;
  width: 100%;
}

</style>
