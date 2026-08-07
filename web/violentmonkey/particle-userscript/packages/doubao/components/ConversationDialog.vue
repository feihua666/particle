<script setup>
import { ref, computed } from 'vue'
import Conversation from './Conversation.vue'
// 👇 接收父组件 v-model
const props = defineProps({
  show: Boolean
})

const emit = defineEmits(['update:show'])

// 👇 双向绑定
const computedShow = computed({
  get: () => props.show,
  set: val => emit('update:show', val)
})
</script>
<template>
  <var-popup
      v-model:show="computedShow"
      position="right"
      :lock-scroll="false"
      style="display: flex; flex-direction: column;"
  >
    <var-app-bar title="对话管理" />
    <var-paper style="
      width: 320px;
      flex: 1;
    ">
      <Conversation />
    </var-paper>
  </var-popup>
</template>
<style scoped>
</style>
