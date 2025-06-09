<script setup name="OpenplatformAppManageUpdatePage" lang="ts">
/**
 * 开放平台应用管理更新页面
 */
import {reactive, ref} from 'vue'
import {
  detailForUpdate as detailForUpdateApi,
  update as openplatformAppUpdateApi
} from "../../../api/app/admin/openplatformAppAdminApi"

import {useUpdatePageFormItems} from "../../../components/app/admin/openplatformAppManage";
import AppAlgorithmSecretConfigs from '../../../components/app/admin/AppAlgorithmSecretConfigs.vue'

const appAlgorithmSecretConfigsRef = ref(null)

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  openplatformAppId: {
    type: String
  },
  // 加载数据初始化参数,路由传参,用于用户字段回显
  ownerUserId: {
    type: String
  },
  // 加载数据初始化参数,路由传参,用于用户字段回显
  ownerUserNickname: String
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    id: props.openplatformAppId,
    version: 1
  },
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    useUpdatePageFormItems({props,appAlgorithmSecretConfigsRef})
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认修改',
  permission: 'admin:web:openplatformApp:update',
  // 由于开放接口缓存，提示一下，参见后端 com.particle.global.openapi.api.AbstractGlobalOpenapi.getOpenapiClient

})
// 提交按钮
const submitMethod = () => {
  return openplatformAppUpdateApi
}
// 初始化加载更新的数据
const dataMethod = () => {
  return detailForUpdateApi({id: props.openplatformAppId})
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
  <AppAlgorithmSecretConfigs :form="reactiveData.form" :formData="reactiveData.formData" ref="appAlgorithmSecretConfigsRef"></AppAlgorithmSecretConfigs>

</template>


<style scoped>

</style>
