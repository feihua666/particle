<script setup name="LexicalEditorInstanceHolder" lang="ts">
/**
 * 回车事件处理插件
 */
import {
  useLexicalComposer
} from 'lexical-vue'
import { KEY_DOWN_COMMAND } from 'lexical';
const editor = useLexicalComposer()
const emit = defineEmits(['enter'])


editor.registerCommand(
    KEY_DOWN_COMMAND,
    (event) => {
      const isEnterKey = event.key === 'Enter';
      const isAltOrCommand = event.altKey || event.metaKey; // Alt 或 Command 键

      if (isEnterKey) {
        if (isAltOrCommand) {
          // Alt + Enter 或 Command + Enter：换行
          // 表示事件未处理
          return false;
        } else {
          // 纯回车键：触发发送消息
          emit('enter')
          // 阻止默认行为
          event.preventDefault();
        }
        // 表示事件已处理
        return true;
      }
      // 表示事件未处理
      return false;
    },
    // 优先级
    1
);

</script>

<template>
</template>

<style scoped>

</style>
