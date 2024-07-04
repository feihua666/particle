<script setup name="PageableAdapterConfig" lang="ts">
import {onMounted, reactive, ref} from 'vue'


// 表单对象类型
// 类型对应后端参见 {@link com.particle.dataquery.domain.datasource.value.DataQueryDatasourceApiPageableAdapterConfig}
interface PageableAdapterType{
  // 类型 enjoy模板模板等
  inParamTemplateType: string,
  // 模板内容
  inParamTemplate: string,
  // 类型 enjoy模板模板等
  outParamTemplateType: string,
  // 模板内容
  outParamTemplate: string
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
      name: 'inParamTemplateType',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '入参请求参数解析脚本类型',
        required: true
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'dataquery_datasource_api_pageable_adapter_type'},
        // 选取value为选重值
        props: {value: 'value'},
      }
    }
  },
  {
    field: {
      name: 'inParamTemplate',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '入参请求参数解析脚本',
        required: true,
        tips: 'enjoy示例：#(pageQueryCommand.setPageNo(data.pageNo)) #(pageQueryCommand.setPageSize(data.pageSize)) 其中 pageQueryCommand 为固定变量可以设置页码和页大小，data变量为请求参数句柄<br/>' +
            'groovy示例：pageQueryCommand.setPageNo(data.pageNo); pageQueryCommand.setPageSize(data.pageSize);pageQueryCommand; 其中 pageQueryCommand 为固定变量可以设置页码和页大小，data变量为请求参数句柄,最后需要返回 pageQueryCommand'
      },
      compProps: {
        type: 'textarea',
        rows: 15,
        clearable: true,
      }
    }
  },
  {
    field: {
      name: 'outParamTemplateType',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '出参响应参数解析脚本类型',
        required: true,
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'dataquery_datasource_api_pageable_adapter_type'},
        // 选取value为选重值
        props: {value: 'value'},
      }
    }
  },
  {
    field: {
      name: 'outParamTemplate',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '出参响应参数解析脚本',
        required: true,
        tips: 'enjoy示例：#(pageResponse.setPageNo(data.pageNo)) #(pageResponse.setPageSize(data.pageSize)) #(pageResponse.setTotalCount(data.totalCount)) 其中 pageResponse 为固定变量可以设置页码和页大小和总数量，data变量为响应数据参数句柄<br/>' +
            'groovy示例：pageResponse.setPageNo(data.pageNo); pageResponse.setPageSize(data.pageSize); pageResponse.setTotalCount(data.totalCount); 其中 pageResponse 为固定变量可以设置页码和页大小和总数量，data变量为响应数据参数句柄,最后需要返回pageResponse'
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