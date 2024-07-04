<script setup name="MessageTemplateContentDetailItem" lang="ts">
/**
 * 消息模板内容详情配置项
 */
import {reactive ,ref} from 'vue'

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 和后端 com.particle.message.domain.MessageTemplate.ContentDetail 数据结构一致
  initForm: {
    type: Object,
    default: ()=>({})
  },
  type:{
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    [

      {
        field: {
          name: 'contentTpl',
          value: props.initForm.contentTpl
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '消息内容模板',
            displayBlock: true,
            tips: '仅支持enjoy模板，覆盖通用的模板',
          },
          compProps: {
            clearable: true,
            type: 'textarea',
            rows: 15
          }
        }
      },
      {
        field: {
          name: 'isContentHtml',
          value: props.initForm.isContentHtml || false
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '内容类型',
            tips: '当模板有值时，覆盖通用的相同字段',
          },
          compProps: {
            activeText: 'html文本',
            inactiveText: '纯文本',
          }
        }
      },

      {
        field: {
          name: 'thirdTemplateCode',
          value: props.initForm.thirdTemplateCode
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '三方模板编码',
          },
          compProps: {
            clearable: true,
          }
        }
      },
    ]
)

defineExpose({
  form: reactiveData.form,
  type: props.type,
})
</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="80"
          defaultButtonsShow=""
          inline
          :layout="[1,2]"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>