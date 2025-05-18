<script setup name="HttpApiBasicConfig" lang="ts">
import {onMounted, reactive, ref} from 'vue'


// 表单对象类型
// 类型对应后端参见 {@link com.particle.dataquery.domain.datasource.value.DataQueryDatasourceApiHttpBasicConfig}
interface FormType{
  // 请求方法：post，get等
  requestMethod: string,
  // 请求内容类型，application/json等
  requestContentType: string,
  // 响应内容类型，application/json等
  responseContentType: string,
  // 请求地址渲染类型
  requestUrlRenderType: string
  // 请求地址，须以 / 开头，最终请求地址会与数据源域名拼接
  requestUrlTemplate: string
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
      name: 'requestMethod',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '请求方法',
        required: true
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'dataquery_datasource_api_http_basic_config_request_method'},
        // 选取value为选重值
        props: {value: 'value'},
      }
    }
  },
  {
    field: {
      name: 'requestContentType',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '请求内容类型',
        required: true,
        tips: 'get请求可以请求没有使用一，随便选一个'
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'dataquery_datasource_api_http_basic_config_content_type'},
        // 选取value为选重值
        props: {value: 'value'},
      }
    }
  },
  {
    field: {
      name: 'responseContentType',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '响应内容类型',
        required: true
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'dataquery_datasource_api_http_basic_config_content_type'},
        // 选取value为选重值
        props: {value: 'value'},
      }
    }
  },
  {
    field: {
      name: 'requestUrlRenderType',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '请求地址渲染类型',
        required: true,
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'dataquery_datasource_api_http_basic_config_request_url_render_type'},
        // 选取value为选重值
        props: {value: 'value'},
      }
    }
  },
  {
    field: {
      name: 'requestUrlTemplate',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '请求地址',
        required: true,
        displayBlock: true,
        tips: '必须返回地址字符串<br/>' +
            'data为请求参数句柄<br/>' +
            'queryString 为查询参数句柄<br/>' +
            'username和password为用户名和密码来源为数据源配置<br/>' +
            'authMap 为额外认证参数，在数据源认证脚本中设置后可使用'
      },
      compProps: {
        clearable: true,
        placeholder: '如：/getUserList',
        type: 'textarea',
        rows: 10
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
          labelWidth="150"
          inline
          :layout="[3,1,1]"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>
