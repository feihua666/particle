<script setup>
import { ref } from 'vue'
import logo from '../../src/assets/logo.png'
import deepseekLogo from '../../src/assets/deepseek_logo.png'

import BatchDeleteConversationDialog from './components/BatchDeleteConversationDialog.vue'
import DownloadConversationToHtml from './components/DownloadConversationToHtml.vue'
import DownloadConversationToMd from './components/DownloadConversationToMd.vue'
const batchDeleteDialogshow = ref(false)
const drag = ref(true)
const logoImg = ref(logo)

function handleHover(hovering) {
  logoImg.value = hovering ? deepseekLogo : logo
}

const downloadConversionToHtml = ref()
const downloadConversionToMd = ref()

</script>
<template>
  <var-fab :drag="drag" :elevation="10">
    <template #trigger="{ active }">

      <var-button type="primary" round v-hover="handleHover">
        <var-avatar size="small" class="no-drag"  :src="logoImg" />
      </var-button>
    </template>
    <var-tooltip content="批量删除会话" placement="left">
      <var-button @click="batchDeleteDialogshow = !batchDeleteDialogshow" icon-container>
        <var-icon name="delete" />
      </var-button>
    </var-tooltip>
    <var-tooltip content="下载html文件对话" placement="left">
      <var-button @click="downloadConversionToHtml?.exportFile()" icon-container>
        <var-icon name="arrow-down" />
      </var-button>
    </var-tooltip>
    <var-tooltip content="下载md文件对话" placement="left">
      <var-button @click="downloadConversionToMd?.exportFile()" icon-container>
        <var-icon name="arrow-down" />
      </var-button>
    </var-tooltip>

  </var-fab>

  <BatchDeleteConversationDialog v-model:show="batchDeleteDialogshow"></BatchDeleteConversationDialog>
  <DownloadConversationToHtml ref="downloadConversionToHtml"/>
  <DownloadConversationToMd ref="downloadConversionToMd"/>
</template>
<style scoped>
/* 防止拖拽专用工具类 */
.no-drag {
  pointer-events: none;
}
</style>
