<script setup name="Oauth2RegisteredClientManageAddPage" lang="ts">
/**
 * oauth2客户端管理添加页面
 */
import {reactive ,ref} from 'vue'
import {create as oauth2RegisteredClientCreateApi,list as Oauth2RegisteredClientListApi} from "../../../api/client/admin/oauth2RegisteredClientAdminApi"
import {convertSubmitForm, useAddPageFormItems} from "../../../compnents/client/admin/oauth2RegisteredClientManage";
import Oauth2RegisteredClientManageSettingConfigs from '../../../compnents/client/admin/Oauth2RegisteredClientManageSettingConfigs.vue'

const oauth2RegisteredClientManageSettingConfigsRef = ref(null)
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    useAddPageFormItems({oauth2RegisteredClientManageSettingConfigsRef,isForAdd: true})
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认添加',
  permission: 'admin:web:oauth2RegisteredClient:create',
})
// 提交按钮
const submitMethod = (form) => {
  return oauth2RegisteredClientCreateApi(convertSubmitForm(form))
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
          :layout="[2,1,1,1,1]"
          :comps="formComps">
  </PtForm>
  
  <Oauth2RegisteredClientManageSettingConfigs  :form="reactiveData.form" :formData="reactiveData.formData" ref="oauth2RegisteredClientManageSettingConfigsRef"></Oauth2RegisteredClientManageSettingConfigs>
  
</template>


<style scoped>

</style>