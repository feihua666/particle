<script setup name="AreaManageUpdatePage" lang="ts">
/**
 * oauth2客户端管理更新页面
 */
import {reactive, ref} from 'vue'
import {
  update as oauth2RegisteredClientUpdateApi,
  detailForUpdate as detailForUpdateApi,
} from "../../../api/client/admin/oauth2RegisteredClientAdminApi"

import {
  convertLoadedData,
  convertSubmitForm,
  useUpdatePageFormItems
} from "../../../components/client/admin/oauth2RegisteredClientManage";
import Oauth2RegisteredClientManageSettingConfigs from '../../../components/client/admin/Oauth2RegisteredClientManageSettingConfigs.vue'

const oauth2RegisteredClientManageSettingConfigsRef = ref(null)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  oauth2RegisteredClientId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    id: props.oauth2RegisteredClientId,
    version: 1
  },
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    useUpdatePageFormItems({oauth2RegisteredClientManageSettingConfigsRef,isForAdd: false})
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认修改',
  permission: 'admin:web:oauth2RegisteredClient:update',
})
// 提交按钮
const submitMethod = (form) => {
  return oauth2RegisteredClientUpdateApi(convertSubmitForm(form))
}
// 初始化加载更新的数据
const dataMethod = () => {
  return detailForUpdateApi({id: props.oauth2RegisteredClientId}).then(res=>{
    let data = res.data.data
    res.data.data = convertLoadedData(data)
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
          :layout="[2,1,1,1,1]"
          :comps="formComps">
  </PtForm>
  <Oauth2RegisteredClientManageSettingConfigs  :form="reactiveData.form" :formData="reactiveData.formData" ref="oauth2RegisteredClientManageSettingConfigsRef"></Oauth2RegisteredClientManageSettingConfigs>

</template>


<style scoped>

</style>