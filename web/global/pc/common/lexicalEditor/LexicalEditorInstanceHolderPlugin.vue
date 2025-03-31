<script setup name="LexicalEditorInstanceHolder" lang="ts">
/**
 * 由于 lexical-vue 没有暴露 editor实例，这里使用另一个思路实现
 */
import {
  useLexicalComposer
} from 'lexical-vue'
const editor = useLexicalComposer()
const emit = defineEmits(['editorReady'])
emit('editorReady', editor)
defineExpose({
  editor
})
</script>

<template>
</template>

<style scoped>

</style>
