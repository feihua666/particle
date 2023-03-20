<script setup name="TinymceEditor" lang="ts">
/**
 * 组件支持 v-modal 指令
 * 安装说明
 * 官方文档：https://www.tiny.cloud/
 * 1. 安装 tinymce 和 @tinymce/tinymce-vue
 * 2. 将 node_modules 下的 tinymce 复制到 public 目录下，这是因为 tinymce会动态加载相关插件和其它组件
 * 3. 在 配置 init.base_url
 * 4. 下载语言包 https://www.tiny.cloud/get-tiny/language-packages/ 放到 public/tinymce/langs下，配置init.language属性为语言包不带扩展名的纯文件名称
 */
import {onMounted, reactive, watch, onBeforeUnmount, ref, computed} from "vue";
import tinymce from 'tinymce/tinymce'
import Editor from '@tinymce/tinymce-vue'

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  init: {
    type: Object,
    default: () => ({})
  },
  tinymceScriptSrc: {
    type: String,
  },
  plugins: {
    type: [String,Array],
    default: 'advlist anchor autolink autoresize autosave charmap code codesample directionality emoticons fullscreen help image importcss insertdatetime link lists media nonbreaking pagebreak preview quickbars save searchreplace table template visualblocks visualchars wordcount'
  },
  toolbar: {
    type: [String,Array],
    default: () => ['bold italic underline strikethrough alignleft aligncenter alignright outdent indent  blockquote undo redo removeformat subscript superscript code codesample', 'hr bullist numlist link image charmap preview anchor pagebreak insertdatetime media table emoticons forecolor backcolor fullscreen']
  }
})

// 本地部署默认根路径
// BASE_URL 默认为 / ，如果指定了就是 /xxx/,总之以/结尾
const base_url = import.meta.env.BASE_URL + 'tinymce'
// 默认初始化配置
const initDefault = {
  base_url: base_url,
  language: 'zh-Hans',
  // 去掉右上角的 upgrade 按钮 https://www.tiny.cloud/docs/tinymce/6/editor-premium-upgrade-promotion/
  promotion: false,
}

const init = Object.assign(initDefault,props.init)

// 经查看源码，指定了 该脚本路径，无需指定 api-key 属性
const tinymceScriptSrc = computed(()=>{
  if(props.tinymceScriptSrc){
    return tinymceScriptSrc
  }
  return base_url + '/tinymce.min.js'
})

</script>

<template>
  <Editor :tinymceScriptSrc="tinymceScriptSrc" :plugins="plugins" :toolbar="toolbar" :init="init"></Editor>
</template>