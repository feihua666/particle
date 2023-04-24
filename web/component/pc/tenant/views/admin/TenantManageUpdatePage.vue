<script setup name="AreaManageUpdatePage" lang="ts">
/**
 * 租户管理更新页面
 */
import {reactive, ref} from 'vue'
import {
  update as TenantUpdateApi,
  detailForUpdate as detailForUpdateApi,
  list as TenantListApi
} from "../../api/admin/tenantAdminApi"

import {updatePageFormItems} from "../../compnents/admin/tenantManage";


// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  tenantId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    id: props.tenantId,
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
  permission: 'admin:web:tenant:update',
})
// 提交按钮
const submitMethod = () => {
  return TenantUpdateApi
}
// 初始化加载更新的数据
const dataMethod = () => {
  return detailForUpdateApi({id: props.tenantId})
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
          labelWidth="100"
          :dataMethod="dataMethod"
          :method="submitMethod()"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          inline
          :layout="[2,2,3,3,1,2]"
          :comps="formComps">
  </PtForm>

</template>


<style scoped>

</style>