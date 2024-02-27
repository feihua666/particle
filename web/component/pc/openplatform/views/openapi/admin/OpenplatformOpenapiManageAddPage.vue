<script setup name="OpenplatformOpenapiManageAddPage" lang="ts">
/**
 * 开放平台开放接口管理添加页面
 */
import {reactive ,ref} from 'vue'
import {create as openplatformOpenapiCreateApi,list as OpenplatformOpenapiListApi} from "../../../api/openapi/admin/openplatformOpenapiAdminApi"
import {addPageFormItems} from "../../../compnents/openapi/admin/openplatformOpenapiManage";
import {cloneObj} from "../../../../../../global/common/tools/ObjectTools";


// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    addPageFormItems
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认添加',
  permission: 'admin:web:openplatformOpenapi:create',
})
// 提交按钮
const submitMethod = (form) => {
  let formTemp = cloneObj(form)
  if(formTemp.openplatformProviderIds){
    formTemp.openplatformProviderIds = formTemp.openplatformProviderIds.join(',')
  }
  return openplatformOpenapiCreateApi(formTemp)
}
// 成功提示语
const submitMethodSuccess = () => {
  return '添加成功，请刷新数据查看'
}

</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="120"
          :method="submitMethod"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          inline
          :layout="[3,1]"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>