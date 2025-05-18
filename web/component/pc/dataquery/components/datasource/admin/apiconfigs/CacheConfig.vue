<script setup name="cacheConfig" lang="ts">
import {onMounted, reactive, ref} from 'vue'


// 表单对象类型
// 类型对应后端参见 {@link com.particle.dataquery.domain.datasource.value.DataQueryDatasourceApiCacheConfig}
interface CacheConfigType{
  // 缓存类型，memory jdbc 等
  cacheType: string,
  // 缓存时间，单位秒
  ttl: number,
  // 缓存键脚本
  cacheKeyGroovyScript: string
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
      name: 'cacheType',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '缓存类型',
        required: true
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'cache_type'},
        // 选取value为选重值
        props: {value: 'value'},
      }
    }
  },
  {
    field: {
      name: 'ttl',
      value: 86400
    },
    element: {
      comp: 'el-input-number',
      formItemProps: {
        label: '缓存时间',
        required: true,
        tips: '单位为秒，1分钟=60s，1小时=3600s，1天=86400s，1周=604800s，1月=2592000s，1年=31536000s'
      },
      compProps: {
      }
    }
  },
  {
    field: {
      name: 'cacheKeyGroovyScript',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '缓存键groovy脚本',
        displayBlock: true,
        tips: '请务必返回一个字符串作为缓存key，如果不填写将自动计算<br/>' +
            '脚本中可使用的变量为 identifier，command，queryString，其中 identifier为接口唯一标识字符串类型，command为请求参数，queryString为请求参数查询字符串'
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
          :layout="[2,1]"
          inline
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>
