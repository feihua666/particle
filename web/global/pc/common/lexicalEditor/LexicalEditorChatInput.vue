<script setup name="LexicalEditorChatInput" lang="ts">
/**
 * 针对对话聊天的富文本编辑器，目前主要用于接入ai
 */
import {watch,ref,onMounted} from "vue"
import { $getRoot, $getSelection,$createParagraphNode,$createTextNode } from 'lexical'
import {
  LexicalAutoFocusPlugin,
  LexicalContentEditable,
  LexicalHistoryPlugin,
  LexicalOnChangePlugin,
  LexicalPlainTextPlugin,
} from 'lexical-vue'
import LexicalEditor from './LexicalEditor.vue'
import LexicalEditorEnterHandlerPlugin from './LexicalEditorEnterHandlerPlugin.vue'
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 值绑定
  modelValue: String,
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

})
const editorRef = ref(null)
const textContentRef = ref('')
// 事件
const emit = defineEmits(['change','update:modelValue','enter'])

function onChange(editorState) {
  editorState.read(() => {
    const root = $getRoot()
    const textContent = root.getTextContent();
    textContentRef.value = textContent
    emit("update:modelValue", textContent)
    emit("change", textContent)
  })
}
function onEnter() {
  emit("enter")
}

const handleClearAndSetValue = (initialText) => {
  if (!editorRef.value) {
    return
  }
  editorRef.value.getEditor().update(() => {
    const root = $getRoot();
    root.clear();
    const paragraph = $createParagraphNode();
    const text = $createTextNode(initialText);
    paragraph.append(text);
    root.append(paragraph);
  })

};
// 值变化变更
// 是输入时，由于不断输入导致不断设置值，光标不移动，这里先注释，唯一影响是除了初始化，如果主动设置值无变化
watch(()=> props.modelValue,(value)=>{
  if (value !== textContentRef.value){
    setValue(value)
  }
})

// 方法
// 设置值
const setValue = (value) => {
  handleClearAndSetValue(value)
}

onMounted(()=>{
  setValue(props.modelValue)
})

</script>

<template>
<div class="pt-chat-input-editor-container">
  <LexicalEditor ref="editorRef" :editable="editable" :theme="theme">
    <LexicalPlainTextPlugin>
      <template #contentEditable>
        <LexicalContentEditable class="pt-chat-input-text-content pt-width-100-pc" />
      </template>
      <template #placeholder>
        <div class="pt-chat-input-placeholder">
          请输入对话内容，并发送消息，alt/command + enter 换行，enter 发送
        </div>
      </template>
    </LexicalPlainTextPlugin>
    <LexicalOnChangePlugin @change="onChange" />
    <LexicalAutoFocusPlugin />
    <LexicalEditorEnterHandlerPlugin @enter="onEnter" />
  </LexicalEditor>
</div>
</template>
<style>
.pt-chat-input-editor-container .pt-chat-input-text-content{
  outline: none;
  overflow: hidden;
  box-sizing: border-box;
}
</style>
<style scoped>
.pt-chat-input-editor-container{
  position: relative;
  max-height: 160px;
  scrollbar-width: none;
  overflow: auto;
}

.pt-chat-input-placeholder{
  position: absolute;
  overflow: hidden;
  text-overflow: ellipsis;
  top: 14px;
  left: 1px;
  opacity: .333;
  user-select: none;
  pointer-events: none;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>
