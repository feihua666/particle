<script setup lang="ts">
import { useVueFlow } from '@vue-flow/core'
import { onMounted } from 'vue'
import type { VueFlowStore } from '@vue-flow/core'
const emit = defineEmits<{
  'usedVueFlowReady': [usedVueFlow: VueFlowStore]
}>()

// ✅ 在 VueFlow 内部，可以安全调用
const usedVueFlow = useVueFlow()

// 实例准备好后通知父组件
onMounted(() => {
  emit('usedVueFlowReady', usedVueFlow)
})

// 也可以直接暴露给父组件通过 ref 获取
defineExpose({
  usedVueFlow: usedVueFlow
})
</script>

<template>
  <!-- 这是一个功能组件，不需要渲染任何 UI -->
</template>
