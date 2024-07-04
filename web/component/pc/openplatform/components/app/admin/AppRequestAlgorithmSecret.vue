<script setup name="AppRequestAlgorithmSecret" lang="ts">
/**
 * 请求摘要与签名算法设置
 */
import {reactive ,ref,onMounted} from 'vue'
import {algorithmList} from "../../../api/app/front/openplatformAppFrontApi.ts";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 初始化数据
  initJsonStr: {
    type: String
  },
  // 表单提交方法
  onSubmit:{
    type: Function,
    default: ()=>({})
  },
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  // 表单数据对象
  formData: {},
})
const checkAuthorizationCodeExist = ({authorizationGrantTypes}) => {
  if(authorizationGrantTypes){
    return authorizationGrantTypes.some(item => item == 'authorization_code')
  }
  return false
}
// 表单项
const formComps = ref(
    [

      {
        field: {
          name: 'digestAlgorithm',
          value: 'SHA_256'
        },
        element: {
          comp: 'PtSelect',
          formItemProps: {
            label: '摘要算法',
            required: true,
          },
          compProps: {
            dataMethod: () => {
              return algorithmList({type: 'digest'})
            },
            props: {
              value: 'value',
              label: 'name'
            }
          },
        }
      },
      {
        field: {
          name: 'isSign',
          value: false
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否签名',
            required: true,
            tips: '签名是指对摘要的签名'
          },
          compProps: {
            activeText: '对摘要进行签名',
            inactiveText: '不对摘要进行签名',
          }
        }
      },
      {
        field: {
          name: 'signatureAlgorithm',
        },
        element: {
          comp: 'PtSelect',
          formItemProps: {
            label: '签名算法',
            required: ({form}) => form.isSign,
            tips: '推荐使用 NONEwithRSA'
          },
          compProps: {
            dataMethod: () => {
              return algorithmList({type: 'signature'})
            },
            props: {
              value: 'value',
              label: 'name'
            }
          }
        }
      },
      {
        field: {
          name: 'publicSignSecret',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '名称公钥',
            required: ({form}) => form.isSign,
            tips: '如果是非对称加密理论上应该由三方提供',
            displayBlock: true
          },
          compProps: {
            type: 'textarea',
            rows: 15
          }
        }
      },
    ],
)
onMounted(()=>{
  // 挂载后初始化数据
  // 挂载后初始化数据
  if(props.initJsonStr){
    let form = JSON.parse(props.initJsonStr)
    for (let formKey in form) {
      reactiveData.form[formKey] = form[formKey]
    }
  }
})
// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认',
})
// 提交按钮
const submitMethod = () => {
  props.onSubmit()

}
// 暴露方法
defineExpose({
  form: reactiveData.form
})
</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="120"
          :method="submitMethod"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          inline
          :layout="[3,1]"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>