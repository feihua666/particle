<script setup name="AreaManageUpdatePage" lang="ts">
/**
 * 开放平台应用与开放接口配置管理更新页面
 */
import {reactive, ref} from 'vue'
import {
  update as openplatformAppOpenapiUpdateApi,
  detailForUpdate as detailForUpdateApi,
  list as OpenplatformAppOpenapiListApi
} from "../../../api/app/admin/openplatformAppOpenapiAdminApi"

import {useUpdatePageFormItems} from "../../../components/app/admin/openplatformAppOpenapiManage";


// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  openplatformAppOpenapiId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    id: props.openplatformAppOpenapiId,
    version: 1
  },
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    useUpdatePageFormItems({props})
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认修改',
  permission: 'admin:web:openplatformAppOpenapi:update',
  // 由于开放接口缓存，提示一下，参见后端 com.particle.global.openapi.api.AbstractGlobalOpenapi.getOpenapiClient
  methodConfirmText: `修改后大概5分钟后生效，确认修改吗？`,
})
// 提交按钮
const submitMethod = () => {
  return openplatformAppOpenapiUpdateApi
}
// 初始化加载更新的数据
const dataMethod = () => {
  return detailForUpdateApi({id: props.openplatformAppOpenapiId})
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
          labelWidth="80"
          :dataMethod="dataMethod"
          :method="submitMethod()"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          inline
          :comps="formComps">
  </PtForm>

</template>


<style scoped>

</style>