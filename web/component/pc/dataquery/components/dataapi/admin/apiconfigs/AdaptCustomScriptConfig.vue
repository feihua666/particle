<script setup name="AdaptCustomScriptConfig" lang="ts">
import {onMounted, reactive, ref} from 'vue'


// 表单对象类型
// 类型对应后端参见 {@link com.particle.dataquery.domain.dataapi.enums.DataQueryDataApiCustomScriptType}
interface PageableAdapterType{
  // 自定义脚本类型 enjoy模板模板等
  customScriptType: string,
  // 脚本模板内容
  scriptTemplate: string,

}

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
// 表单初始查询第一页
  form: {
  },

})
const formComps = [
  {
    field: {
      name: 'customScriptType',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '脚本类型',
        required: true
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'dataquery_data_api_custom_script_type'},
        // 选取value为选重值
        props: {value: 'value'},
      }
    }
  },
  {
    field: {
      name: 'scriptTemplate',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '脚本内容',
        required: true,
        tips: 'groovy示例：def map = new HashMap();map.put("result1",datasourceApi.invoke("datasourceApiCode",data,queryStr));map; 以上为接口聚合示例，其中 datasourceApi 包括三个参数 参数1=数据查询数据源接口编码，2=请求参数，data变量为请求参数句柄,3=查询字符串参数，最后 map 需要返回处理结果,支持使用dataQueryDataApiExecutor句柄引用线程池加速处理'
      },
      compProps: {
        type: 'textarea',
        rows: 15,
        clearable: true,
      }
    }
  },
]
onMounted(()=>{
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
// 添加入参文档按钮
const submitMethod = ():void => {
  props.onSubmit()
}

// 暴露方法
defineExpose({
  form: reactiveData.form
})
</script>
<template>
  <!-- 添加表单 -->
  <PtForm ref="selfFormRef" :form="reactiveData.form" class="pt-wdith-100-pc"
          :method="submitMethod"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :layout="1"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>