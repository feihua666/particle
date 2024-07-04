<script setup name="TenantCreateApplyManageAuditPage" lang="ts">
/**
 * 租户创建申请管理审核页面
 */
import {reactive, ref} from 'vue'
import {
  audit as TenantCreateApplyAuditApi,
} from "../../../api/createapply/admin/tenantCreateApplyAdminApi"

import {
  useAuditPageFormItems,
} from "../../../components/createapply/admin/tenantCreateApplyManage";


// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  applyUserId: {
    type: String
  },
  // 加载数据初始化参数,路由传参
  applyUserNickname: String,
  // 加载数据初始化参数,路由传参
  tenantCreateApplyId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    id: props.tenantCreateApplyId,
    version: 1
  },
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    useAuditPageFormItems({props})
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认审核',
  permission: 'admin:web:tenantCreateApply:audit',
})
// 提交按钮
const submitMethod = () => {
  return TenantCreateApplyAuditApi
}

// 成功提示语
const submitMethodSuccess = () => {
  return '审核成功，请刷新数据查看'
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
          :layout="1"
          :comps="formComps">
  </PtForm>

</template>


<style scoped>

</style>