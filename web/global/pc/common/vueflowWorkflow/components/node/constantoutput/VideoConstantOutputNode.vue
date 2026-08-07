<script setup lang="ts">
import { computed, ref } from 'vue'
import { useVueFlow } from '@vue-flow/core'
import BaseNode from '../BaseNode.vue'
import VideoNodeVideo from "../VideoNodeVideo.vue";
import type { WorkflowNodeData } from '../../../workflow'
import {useConstantOutputNodeValue} from "../../../composables/useConstantOutputNodeValue.ts";

const { updateNodeData, onNodeDragStart } = useVueFlow()

const props = withDefaults(defineProps<{
  id: string
  data: WorkflowNodeData
}>(), {})

// 使用统一的节点值管理
const { value } = useConstantOutputNodeValue(props.id, props, updateNodeData, useVueFlow())

const videoRef = ref<HTMLVideoElement>()


</script>

<template>
  <BaseNode :id="id" :data="data" class="pt-ct-ot-video-node">
    <el-text v-if="!value">将渲染被连接线接管的地址为视频</el-text>
    <VideoNodeVideo
        v-else
        ref="videoRef"
        :src="value"
        class="pt-ct-ot-video-node-video"
    />
  </BaseNode>
</template>

<style scoped>
.pt-ct-ot-video-node-video {
  height: 100%;
  width: 100%;
}
</style>
