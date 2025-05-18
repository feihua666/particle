<script setup name="DataCompanyVcProductCompetitiveProductRelManageDeleteByCompanyVcCompetitiveProductIdPage" lang="ts">
/**
 * 清空企业竞品企业竞品
 */
import {reactive ,ref} from 'vue'
import {deleteByCompanyVcCompetitiveProductId} from "../../../api/company/admin/dataCompanyVcProductCompetitiveProductRelAdminApi"
// 以下两项为自动生成，不准确，请手动根据实际情况修改
import {useRemoteSelectCompanyVcCompetitiveProductCompItem, remoteSelectCompanyVcCompetitiveProductProps} from "../../../companyVcCompetitiveProduct/components/companyVcCompetitiveProductCompItem";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  ...remoteSelectCompanyVcCompetitiveProductProps,
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    [
      useRemoteSelectCompanyVcCompetitiveProductCompItem({props,required: true}),
    ]
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认',
  permission: 'admin:web:dataCompanyVcProductCompetitiveProductRel:deleteByCompanyVcCompetitiveProductId',
})
// 提交按钮
const submitMethod = (form) => {
  return deleteByCompanyVcCompetitiveProductId({id: form.companyVcCompetitiveProductId})
}
// 成功提示语
const submitMethodSuccess = () => {
  return '删除成功，请刷新数据查看'
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
          :layout="1"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>