<script setup name="EsApiBasicConfig" lang="ts">
import {onMounted, reactive, ref} from 'vue'


// 表单对象类型
// 类型对应后端参见 {@link com.particle.dataquery.domain.datasource.value.DataQueryDatasourceApiEsBasicConfig}
interface FormType{
  // 类型 enjoy模板、groovyScript模板等
  dslTemplateType: string,
  // 表示返回的数据是单条、多条、还是分页
  dataType: string,
  // 索引名称，多个以逗号分隔
  indexNames: string,
  // 模板内容
  dslTemplate: string
  // 计数模板
  dslCountTemplate: string
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
        dictParam: {groupCode: 'dataquery_datasource_api_es_basic_config_data_type'},
        // 选取value为选重值
        props: {value: 'value'},
      }
    }
  },
  {
    field: {
      name: 'dslTemplateType',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: 'dsl模板类型',
        required: true
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'dataquery_datasource_api_es_basic_config_dsl_template_type'},
        // 选取value为选重值
        props: {value: 'value'},
      }
    }
  },
  {
    field: {
      name: 'indexNames',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '索引名称',
        tips: "多个以逗号分隔",
      },
      compProps: {
        clearable: true,
      }
    }
  },
  {
    field: {
      name: 'dslTemplate',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: 'dsl模板内容',
        required: true,
        labelTips: '支持与query同级指定 如：indexNames: "xxx", 的索引名称，如果上面索引名称字段指定了索引名称，则该设置不生效，建议使用上面的索引名称字段',
        tips: "1. enjoy模板 最终会渲染为dsl语句， 取值示例 {\"query\":{\"term\":{\"title\":\"#(data.name)\"}}} data为请求参数句柄，<br/>" +
            "2. raw模式 请直接写dsl，不具备变量渲染能力<br/>" +
            "3. groovy脚本支持，具有编程的能力最终结果将使用该脚本的返回值直接返回数据,注意：如果返回数据类型为字符串则做为dsl直接使用，否则直接做为返回数据，内置句柄有三个为 elasticsearchTemplate、elasticsearchClient、restClient 和 data，elasticsearchTemplate、elasticsearchClient、restClient为查询数据服务，data为持有请求参数句柄" +
            "注意：分页查询不支持es的from和size属性查询，设置后请求分页参数无效，请根据分页参数传参使用，单条和多条支持"

      },
      compProps: {
        type: 'textarea',
        rows: 15,
        clearable: true,
        placeholder: "{\n" +
            "    \"query\": {\n" +
            "        \"match\": {\n" +
            "            \"title\": \"测试es0\"\n" +
            "        }\n" +
            "    },\n" +
            "    \"sort\": {\n" +
            "            \"id\": {\n" +
            "                \"order\": \"desc\"\n" +
            "            }\n" +
            "        }\n" +
            "}",

      }
    }
  },
  {
    field: {
      name: 'dslCountTemplate',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: 'dsl总数模板内容',
        tips: "该模板仅在分页时使用，用来统计查询数据的总数，须和[dsl模板内容]查询条件保持一致，注意es在查询时会返回总数，不建议设置该项"
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
