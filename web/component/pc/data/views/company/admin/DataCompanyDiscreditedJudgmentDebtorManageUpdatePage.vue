<script setup name="DataCompanyDiscreditedJudgmentDebtorManageUpdatePage" lang="ts">
/**
 * 企业失信被执行人管理更新页面
 */
import {reactive, ref} from 'vue'
import {
  update as dataCompanyDiscreditedJudgmentDebtorUpdateApi,
  detailForUpdate as detailForUpdateApi
} from "../../../api/company/admin/dataCompanyDiscreditedJudgmentDebtorAdminApi"

import {updatePageFormItems} from "../../../components/company/admin/dataCompanyDiscreditedJudgmentDebtorManage";


// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  dataCompanyDiscreditedJudgmentDebtorId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    id: props.dataCompanyDiscreditedJudgmentDebtorId,
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
  permission: 'admin:web:dataCompanyDiscreditedJudgmentDebtor:update',
})
// 提交按钮
const submitMethod = () => {
  return dataCompanyDiscreditedJudgmentDebtorUpdateApi
}
// 初始化加载更新的数据
const dataMethod = () => {
  return detailForUpdateApi({id: props.dataCompanyDiscreditedJudgmentDebtorId})
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