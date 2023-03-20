<script setup name="JdbcApiBasicConfig" lang="ts">
import {onMounted, reactive, ref} from 'vue'


// 表单对象类型
// 类型对应后端参见 {@link com.particle.dataquery.domain.datasource.value.DataQueryDatasourceApiJdbcBasicConfig}
interface FormType{
  // 类型 enjoy模板、mybatisScript模板等
  type: string,
  // 模板内容
  content: string
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
      name: 'dataType',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '数据类型',
        required: true
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'dataquery_datasource_api_jdbc_basic_config_data_type'},
        // 选取value为选重值
        props: {value: 'value'},
      }
    }
  },
  {
    field: {
      name: 'sqlTemplateType',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: 'sql模板类型',
        required: true
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'dataquery_datasource_api_jdbc_basic_config_sql_template_type'},
        // 选取value为选重值
        props: {value: 'value'},
      }
    }
  },
  {
    field: {
      name: 'sqlTemplate',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: 'sql模板内容',
        required: true,
        tips: "1. enjoy模板 取值示例 where name = '#(data.name)' data为请求参数句柄，<br/>" +
            "2. mybatisScript 需要以&lt;script&gt;开头，注意前面不能有空格 取值示例&lt;script&gt;&lt;if test=\"data.data.name != null\"&gt;where name = #{data.data.name}&lt;/if&gt;&lt;/script&gt; 注意：data.data(两个data)为请求参数句柄<br/>" +
            "3. raw模式请直接写sql，不具备变量渲染能力"
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