<script setup name="AreaManageUpdatePage" lang="ts">
/**
 * 开放平台开放接口管理更新页面
 */
import {reactive, ref} from 'vue'
import {
  detailForUpdate as detailForUpdateApi,
  update as openplatformOpenapiUpdateApi
} from "../../../api/openapi/admin/openplatformOpenapiAdminApi"

import {updatePageFormItems} from "../../../components/openapi/admin/openplatformOpenapiManage";
import {cloneObj} from "../../../../../../global/common/tools/ObjectTools";


// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  openplatformOpenapiId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    id: props.openplatformOpenapiId,
    version: 1
  },
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    updatePageFormItems
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认修改',
  permission: 'admin:web:openplatformOpenapi:update',
  // 由于开放接口缓存，提示一下，参见后端 com.particle.global.openapi.api.AbstractGlobalOpenapi.getApiInfo
  methodConfirmText: `修改后大概3分钟后生效，确认修改吗？`,
})
// 提交按钮
const submitMethod = (form) => {
  let formTemp = cloneObj(form)
  if(formTemp.openplatformProviderIds){
    formTemp.openplatformProviderIds = formTemp.openplatformProviderIds.join(',')
  }
  return openplatformOpenapiUpdateApi(formTemp)
}
// 初始化加载更新的数据
const dataMethod = () => {
  return detailForUpdateApi({id: props.openplatformOpenapiId}).then(res => {
    let data = res.data.data
    if(data.openplatformProviderIds){
      data.openplatformProviderIds = data.openplatformProviderIds.split(',')
    }
    return Promise.resolve(res)
  })
}
// 成功提示语
const submitMethodSuccess = () => {
  return '修改成功，请刷新数据查看'
}

</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="120"
          :dataMethod="dataMethod"
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
