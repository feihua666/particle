<script setup name="InParamExtConfig" lang="ts">
import {onMounted, reactive, ref} from 'vue'

// 表单对象
// props.initJsonStr 需要符合该类型
interface InitJson{
  groovyScript: string
}


const tableRef = ref(null)

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
  }
})
// 属性
const reactiveData = reactive({
  initJson: {groovyScript: ''},
// 表单初始查询第一页
  form: {
  },
  formComps: [
    {
      field: {
        name: 'groovyScript',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: 'groovy脚本',
          tips: '仅支持groovy脚本，对参数的处理，可以返回一个新参数，或对原来参数进行改写，如果对参数进行改写，请返回null，否则将返回值作为新参数，data变量为参数句柄'
        },
        compProps: {
          type: 'textarea',
          rows: 15,
          clearable: true,
        }
      }
    },

  ],

})
onMounted(()=>{
  // 挂载后初始化数据
  if(props.initJsonStr){
    reactiveData.initJson = JSON.parse(props.initJsonStr)
    reactiveData.form.groovyScript = reactiveData.initJson.groovyScript
  }
})
// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认',
})
// 添加验证按钮
const submitMethod = ():void => {
  reactiveData.initJson.groovyScript = reactiveData.form.groovyScript
  props.onSubmit()
}

// 暴露方法
defineExpose({
  getInitJson: () => reactiveData.initJson
})
</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form" class="pt-wdith-100-pc"
          :method="submitMethod"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :layout="1"
          :comps="reactiveData.formComps">
  </PtForm>

</template>


<style scoped>

</style>