<script setup name="WorkflowDefinitionManageAddPage" lang="ts">
/**
 * http 通用配置
 */
import {computed, reactive, ref, watch} from 'vue'
import {useVueFlow} from "@vue-flow/core";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  onFormChange: {
    type: Function,
    default: () => {
    }
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

const dragControl = {
  class: computed(()=> ({nodrag : true, nowheel : true}))
}
// 表单项
const formComps = ref(
    [
      {
        field: {
          name: 'method',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '请求方法',
            labelTips: '请输入请求方法，如：POST,GET,PUT,DELETE等接口支持的值',
            required: true,
          },
          compProps: {
            clearable: true,
            placeholder: 'GET',
            ...dragControl
          }
        }
      },
      {
        field: {
          name: 'url',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '请求地址',
            labelTips: '需以 http(s):// 开关',
            required: true,
          },
          compProps: {
            clearable: true,
            ...dragControl
          }
        }
      },
      {
        field: {
          name: 'headers',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '请求头配置',
            labelTips: '必须是一个 json 对象格式',
          },
          compProps: {
            type: 'textarea',
            resize: "none",
            clearable: true,
            ...dragControl
          }
        }
      },
      {
        field: {
          name: 'connectTimeout',
          value: 1000
        },
        element: {
          comp: 'el-input-number',
          formItemProps: {
            label: '连接超时',
            labelTips: '单位毫秒如：1秒=1000、10秒=10000',
          },
          compProps: {
            type: 'textarea',
            // 最小100毫秒
            min: 100,
            // 最大60秒
            max: 60000,

            ...dragControl
          }
        }
      },
    ]
)
watch(() => reactiveData.form,
    (newForm) => {
      props.onFormChange(newForm)
    },
    {deep: true}
)
defineExpose({
  form: reactiveData.form,
})
</script>
<template>
  <!-- 表单 -->
  <PtForm class="pt-http-config-form"
      :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="100"
          defaultButtonsShow=""
          inline
          size="small"
          :layout="1"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>
<style>
.pt-http-config-form .el-form-item{
  margin-right: 0;
  display: flex;
}
</style>
