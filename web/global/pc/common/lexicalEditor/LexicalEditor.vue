<script setup name="LexicalEditor" lang="ts">
/**
 * 富文本编辑器
 * github 地址 https://github.com/facebook/lexical
 * github地址 https://github.com/wobsoriano/lexical-vue
 * 安装说明：
 * 1. 安装 lexical-vue
 */
import {ref} from "vue"
import {
  LexicalComposer
} from 'lexical-vue'
import LexicalEditorInstanceHolderPlugin from "./LexicalEditorInstanceHolderPlugin.vue"
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 主题配置
  theme: {
    type: Object,
    default: () => ({}),
  },
  // 编辑模式
  editable:{
    type: Boolean,
    default: true
  },
  // 编辑器状态
  editorState: {
    default: null
  }

})
const config = {
  editable: props.editable,
  theme: props.theme,
  // editorState: props.editorState
}

// 事件
const emit = defineEmits(['onError'])
function onError(error) {
  emit("onError", error)
}

const editorHolder = ref(null)
// 获取编辑器实例
function getEditor(){
  return editorHolder.value?.editor
}

defineExpose({
  getEditor
})
</script>

<template>
  <LexicalComposer :initial-config="config" @error="onError">
    <LexicalEditorInstanceHolderPlugin ref="editorHolder"></LexicalEditorInstanceHolderPlugin>
    <slot></slot>
  </LexicalComposer>
</template>

<style scoped>

</style>
