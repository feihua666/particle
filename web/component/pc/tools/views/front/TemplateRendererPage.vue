<script setup name="TemplateRendererPage" lang="ts">
/**
 * 模板渲染工具，主要是用于根据参数渲染为文本
 */
import {createApp, getCurrentInstance, reactive, ref} from 'vue'
import {lowerFirst, replace, upperFirst} from "../../../../../global/common/tools/StringTools";
import {loadScriptCode} from "../../../../../global/common/tools/DocumentTools";
import {showMsg} from "../../../../../global/pc/element-plus/ElmessageTools.ts";
import {newMarked, renderMarkdown as renderMarkdownMethod} from "../../../../../global/common/tools/MarkdownMarkedTools.ts";
import {renderTemplate} from "../../../../../global/common/tools/VueTemplateRender.ts";

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    [
      {
        field: {
          name: 'jsonObjStr'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: 'json文本',
            required: true
          },
          compProps: {
            clearable: true,
            rows: 10,
            type: 'textarea',
            placeholder: '输入json字符串'
          }
        }
      },
      {
        field: {
          name: 'template'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '模板',
            required: true,
            tips: '模板字符串，这是vue语法，访问数据以 data. 开关'
          },
          compProps: {
            clearable: true,
            rows: 10,
            type: 'textarea',
            placeholder: '模板字符串'
          }
        }
      },
      {
        field: {
          name: 'text'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '结果'
          },
          compProps: {
            clearable: true,
            readonly: true,
            placeholder: '结果这里显示完成的结果',
            rows: 10,
            type: 'textarea'
          }
        }
      },
    ]
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认渲染',
})
// 提交按钮
const submitMethod = (form) => {
  // 表单数据
  let jsonObjStr = form.jsonObjStr
  let template = form.template
  try {
    let jsonData = JSON.parse(jsonObjStr)
    let result = renderTemplate(jsonData, template, {renderMarkdown})
    form.text = result
  } catch (error: any) {
    showMsg(`模板渲染失败: ${error.message}`, 'error')
  }
}

const marked =  newMarked()
marked.setOptions({
  breaks: true,      // 将换行符转换为 <br>
  gfm: true,         // 启用 GitHub 风格的 Markdown
})

const renderMarkdown = (markdown: string): string | null => {
  if (!markdown) {
    return ''
  }
  return renderMarkdownMethod(marked, markdown)
}
// 成功提示语
const submitMethodSuccess = () => {
  return '转换成功'
}
</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="80"
          :method="submitMethod"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :layout="1"
          label-position="top"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>
