<script setup>
import { ref } from 'vue'
import logo from '../../src/assets/logo.png'
import doubaoLogo from '../../src/assets/doubao_avatar.png'

import ConversationDialog from './components/ConversationDialog.vue'
const show = ref(false)
const drag = ref(true)
const logoImg = ref(logo)
function handleHover(hovering) {
  logoImg.value = hovering ? doubaoLogo : logo
}
</script>
<template>
  <var-fab :drag="drag" :elevation="10">
    <template #trigger="{ active }">

      <var-button type="primary" round v-hover="handleHover">
        <var-avatar size="small" class="no-drag"  :src="logoImg" />
      </var-button>
    </template>
    <var-button @click="show = !show">会话</var-button>
  </var-fab>

  <ConversationDialog v-model:show="show"></ConversationDialog>
</template>
<style scoped>
/* 防止拖拽专用工具类 */
.no-drag {
  pointer-events: none;
}
</style>
