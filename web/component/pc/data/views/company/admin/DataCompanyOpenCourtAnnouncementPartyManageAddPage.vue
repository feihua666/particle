<script setup name="DataCompanyOpenCourtAnnouncementPartyManageAddPage" lang="ts">
/**
 * 企业开庭公告当事人管理添加页面
 */
import {reactive ,ref} from 'vue'
import {create as dataCompanyOpenCourtAnnouncementPartyCreateApi} from "../../../api/company/admin/dataCompanyOpenCourtAnnouncementPartyAdminApi"
import {addPageFormItems} from "../../../components/company/admin/dataCompanyOpenCourtAnnouncementPartyManage";


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
  permission: 'admin:web:dataCompanyOpenCourtAnnouncementParty:create',
})
// 提交按钮
const submitMethod = () => {
  return dataCompanyOpenCourtAnnouncementPartyCreateApi
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