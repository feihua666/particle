<script setup name="Neo4jApiBasicConfig" lang="ts">
import {onMounted, reactive, ref} from 'vue'


// 表单对象类型
// 类型对应后端参见 {@link com.particle.dataquery.domain.datasource.value.DataQueryDatasourceApiNeo4jBasicConfig}
interface FormType{
  // 类型 enjoy模板、groovyScript模板等
  cqlTemplateType: string,
  // 表示返回的数据是单条、多条、还是分页
  dataType: string,
  // 模板内容
  cqlTemplate: string
  // 计数模板
  cqlCountTemplate: string
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
        dictParam: {groupCode: 'dataquery_datasource_api_neo4j_basic_config_data_type'},
        // 选取value为选重值
        props: {value: 'value'},
      }
    }
  },
  {
    field: {
      name: 'cqlTemplateType',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: 'cql模板类型',
        required: true
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'dataquery_datasource_api_neo4j_basic_config_cql_template_type'},
        // 选取value为选重值
        props: {value: 'value'},
      }
    }
  },
  {
    field: {
      name: 'cqlTemplate',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: 'cql模板内容',
        required: true,
        tips: "1. enjoy模板 最终会渲染为cql语句， 取值示例 where name = '#(data.name)' data为请求参数句柄，<br/>" +
            "2. raw模式 请直接写cql，不具备变量渲染能力<br/>" +
            "3. groovy脚本支持，具有编程的能力最终结果将使用该脚本的返回值直接返回数据,注意：如果返回数据类型为字符串则做为cql直接使用，否则直接做为返回数据，内置句柄有三个为 driver、neo4jClient、neo4jTemplate 和 data，driver、neo4jClient、neo4jTemplate为查询数据服务，data为持有请求参数句柄"
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
      name: 'cqlCountTemplate',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: 'cql总数模板内容',
        required: ({form,formData}) => formData.dataType && formData.dataType.value == 'page',
        tips: "该模板仅在分页时使用，用来统计查询数据的总数，须和[cql模板内容]查询条件保持一致,需要返回一个统计数量，如：match(n) return count(*)"
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