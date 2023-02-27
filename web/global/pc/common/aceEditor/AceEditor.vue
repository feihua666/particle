<script setup name="AceEditor" lang="ts">
/**
 * 代码编辑器
 * 随便找了一个说明：https://cloud.tencent.com/developer/article/2115307
 */
import {watch, onMounted, onBeforeUnmount, ref, reactive} from "vue";
import ace from "ace-builds";
/*
* 需要自行引入需要的模块，一般为主题和支持语法
* 如：引用主题 一般在 ace-builds/src-noconflict/theme-xxxx.js,然后设置主题属性 ace/theme/xxxx
* 如：引用语法 一般在 ace-builds/src-noconflict/mode-xxxx.js,然后设置主题属性 ace/mode/xxxx
*
* */
/*import "ace-builds/src-noconflict/ext-language_tools"
import "ace-builds/src-noconflict/ext-emmet"
import "ace-builds/src-noconflict/snippets/yaml"
import "ace-builds/src-noconflict/mode-yaml"
import 'ace-builds/src-min-noconflict/mode-javascript'
import 'ace-builds/src-min-noconflict/mode-json'
import 'ace-builds/src-min-noconflict/mode-css'*/

const aceEditorRef = ref(null)
let aceEcitorInstance = null

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 值绑定
  modelValue: String,
  // 外部传入的语法类型
  mode: {
    type: String,
    default: "ace/mode/javascript",
  },
  // 编辑器主题色，如：ace/theme/chaos
  theme: {
    type: String,
    default: "ace/theme/eclipse",
  },
  // 只读模式
  readonly:{
    type: Boolean,
    default: false
  },
  // 最大行数，超过会自动出现滚动条
  maxLines: {
    type: Number,
    default: 20
  },
  // 最小行数，还未到最大行数时，编辑器会自动伸缩大小
  minLines: {
    type: Number,
    default: 5
  },
  // 编辑器内字体大小
  fontSize: {
    type: Number,
    default: 14
  },
  // 制表符设置为2个空格大小
  tabSize: {
    type: Number,
    default: 2
  },
})
// 属性
const reactiveData = reactive({
})
// 事件
const emit = defineEmits(['change','update:modelValue'])

onMounted(()=>{
  aceEcitorInstance = ace.edit(aceEditorRef.value, {
    maxLines: props.maxLines,
    minLines: props.minLines,
    fontSize: props.fontSize,
    theme: props.theme, // 默认设置的主题
    mode: props.mode, // 默认设置的语言模式
    tabSize: props.tabSize,
    readOnly: props.readonly,
    highlightActiveLine: true,
    value: props.modelValue
  })
// 支持双向绑定
  aceEcitorInstance.on("change", () => {
    emit("update:modelValue", aceEcitorInstance.getValue());
    emit("change", aceEcitorInstance.getValue());
  });
  aceEcitorInstance.on("blur", () => {
  });
  aceEcitorInstance.on("input", () => {
  });
})
onBeforeUnmount(()=>{
  aceEcitorInstance.destroy();
  aceEcitorInstance.container.remove();
})
// 值变化变更
// 是输入时，由于不断输入导致不断设置值，光标不移动，这里先注释，唯一影响是除了初始化，如果主动设置值无变化
watch(()=> props.modelValue,(value)=>{
  if (value !== aceEcitorInstance.getValue()){
    setValue(value)
  }
})
watch(()=> props.mode,(value)=>{
  setMode(value)
})
watch(()=> props.theme,(value)=>{
  setTheme(value)
})
// 方法
// 设置值 通过setValue可以给编辑器初始化数据
const setValue = (value) => {
  aceEcitorInstance?.getSession().setValue(value)
}
// 设置语法模式，如：ace/mode/markdown
// 默认情况下编辑器为纯文本模式，你可以通过setMode来设置编辑器对应的语言模式，例如你想让其匹配markdown，就可以像下边这样配置，同样需要语言模式的文件存在，文件与ace.js同级，命名规则为mode-语言模式.js
const setMode = (value) => {
  aceEcitorInstance?.getSession().setMode(value)
}
// 你可以通过setTheme来设置主题，需要注意的是主题文件要存在，并且需要与ace.js同级，命名规则为theme-主题名.js
const setTheme = (value) => {
  aceEcitorInstance?.setTheme(value)
}
// 暴露方法
defineExpose({
  setValue,
  setMode,
  setTheme,
})
</script>
<template>
  <div ref="aceEditorRef" style="height: 300px" class="pt-ace-editor pt-height-100-pc"></div>
</template>


<style scoped>
.pt-ace-editor {
  min-height: 350px;
  min-width: 350px;
}

</style>