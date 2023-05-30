<script setup name="UserManageAddPage" lang="ts">
/**
 * 用户管理添加页面
 */
import {reactive, ref} from 'vue'
import {create as userCreateApi, list as userListApi} from "../../api/admin/userAdminApi"
import {resetPasswordCompItems} from "../../compnents/userCompItem";
import {addPageFormItems} from "../../compnents/admin/userManage";
import {componentEnabled} from "../../../../../common/config/componentsConfig";

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    addPageFormItems.filter(item => !!item)
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认添加',
  permission: 'admin:web:user:create',
})
// 提交按钮
const submitMethod = (form) => {
  form.identifiers = []
  form.identifiers.push({
    identifier: form.identifier,
    identityTypeDictId: form.identityTypeDictId
  })
  return userCreateApi(form)
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
          labelWidth="80"
          :method="submitMethod"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          inline
          :layout="[1,3,3,3,3,componentEnabled('dept') ? 2 : 1]"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>