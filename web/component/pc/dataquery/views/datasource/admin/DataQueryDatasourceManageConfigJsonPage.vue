<script setup name="DataQueryDatasourceManageConfigJsonPage" lang="ts">
/**
 * 数据查询数据源管理配置Json页面
 */
import {onMounted, reactive, ref} from 'vue'
import {datasourceTypeFormItems} from "../../../components/datasource/admin/dataQueryDatasourceManage";
import {isObject} from "../../../../../../global/common/tools/ObjectTools";
import {isString} from "../../../../../../global/common/tools/StringTools";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  typeDictValue: {
    type: String,
  },
  // 初始化的数据对象
  initData: {
    type: [Object,String],
  },
  // 成功回调
  success: {
    type: Function,
    default: (result: string) => {}
  }
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = datasourceTypeFormItems[props.typeDictValue] ||  datasourceTypeFormItems.datasource_not_support
onMounted(()=>{

  if (!props.initData) {
    return
  }
  // 初始化数据
  let objData = {}
  if(isObject(props.initData)){
    objData = props.initData
  }
  if( isString(props.initData)){

    if (!datasourceTypeFormItems[props.typeDictValue]) {
      objData.configJson = props.initData
    }else{
      try {
        objData = JSON.parse(props.initData)
      } catch (e) {
        // 应该是 configJson组数据
        if (!datasourceTypeFormItems[props.typeDictValue]) {
          objData.configJson = props.initData
        }
      }
    }

  }
  for (let objDataKey in objData) {
    reactiveData.form[objDataKey] = objData[objDataKey]
  }
})
// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认',
})
// 提交按钮,返回一个空函数即可，在成功回调中（submitMethodSuccess）处理数据
const submitMethod = () => {
  return ()=> {}
}
// 成功提示语
const submitMethodSuccess = () => {
  let r = ''
  // 如果支持
  if (datasourceTypeFormItems[props.typeDictValue]) {
    r = JSON.stringify(reactiveData.form)
  }else {
    r = reactiveData.form.configJson
  }
  props.success(r)
  return '配置成功，提交数据后生效'
}

</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="80"
          :method="submitMethod()"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :layout="1"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>