<script setup lang="ts">
/**
 * 主要负责边的逻辑处理
 * 因为在 vueFlow 中使用 useVueFlow() 只能在 VueFlow 组件内部使用
 */
import { Panel, useVueFlow } from '@vue-flow/core'
import { ref } from 'vue'
import type { VueFlowStore ,Connection} from '@vue-flow/core'

import {addEdgeToFlow} from "../../tools/edgeTools";
const usedVueFlow = useVueFlow()
const {
  onConnect,
} = useVueFlow()

// 添加连接线
onConnect((connection:Connection) => {
  addEdgeToFlow(null,connection,usedVueFlow)
})
</script>
<template>
  <!-- 这是一个功能组件，不需要渲染任何 UI -->
</template>
