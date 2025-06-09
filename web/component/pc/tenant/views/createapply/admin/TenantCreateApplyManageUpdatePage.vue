<script setup name="TenantCreateApplyManageUpdatePage" lang="ts">
/**
 * 租户创建申请管理更新页面
 */
import {reactive, ref} from 'vue'
import {
  detailForUpdate as detailForUpdateApi,
  update as TenantCreateApplyUpdateApi,
} from "../../../api/createapply/admin/tenantCreateApplyAdminApi"

import {useUpdatePageFormItems} from "../../../components/createapply/admin/tenantCreateApplyManage";
import {ElMessage} from 'element-plus'
import TenantCreateApplyFuncApplication
  from '../../../components/createapply/admin/funcapplication/TenantCreateApplyFuncApplication.vue'

const funcApplicationDialogVisible = ref(false)
const tenantCreateApplyFuncApplicationRef = ref(null)
// 传递已选中数据时不能使用form.extJsonObj,会引起副作用依赖循环，这里单独加一个
const extJsonObjTemp = ref({})
// 审核通过的数据不能修改
const hasAuditPass = ref(false)
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
    version: 1,
    extJsonObj: null
  },
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    useUpdatePageFormItems({props,funcApplicationDialogVisible})
)
const beforeSubmitMethod = ()=>{
  if(hasAuditPass.value == true){
    return '当前数据已审核通过不能修改'
  }
  return true
}
// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认修改',
  permission: 'admin:web:tenantCreateApply:update',
  beforeMethod: beforeSubmitMethod
})
// 提交按钮
const submitMethod = () => {
  return TenantCreateApplyUpdateApi
}

// 初始化加载更新的数据
const dataMethod = () => {
  return detailForUpdateApi({id: props.tenantCreateApplyId})
  .then(res => {
    // 修改一下extJsonObj数据
    if(res.data.data.extJson){
      res.data.data.extJsonObj = JSON.parse(res.data.data.extJson)
      extJsonObjTemp.value = JSON.parse(JSON.stringify(res.data.data.extJsonObj))

      hasAuditPass.value = (res.data.data.auditStatusDictValue == 'audit_pass')
    }
    return Promise.resolve(res)
  })
}
// 成功提示语
const submitMethodSuccess = () => {
  return '修改成功，请刷新数据查看'
}

let alertError = (message)=>{
  ElMessage({
    showClose: true,
    message: message,
    type: 'error',
    showIcon: true,
    grouping: true
  })
}

const funcApplicationSubmit = ()=>{
  let selectedData = tenantCreateApplyFuncApplicationRef.value.getSelectedData()
  if (selectedData.length == 0) {
    alertError('请至少选择一个应用及对应的功能')
    return
  }

  let extJsonObj = {funcApplications : selectedData};
  reactiveData.form.extJsonObj = extJsonObj
  extJsonObjTemp.value = JSON.parse(JSON.stringify(extJsonObj))

  funcApplicationDialogVisible.value = false
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
          :layout="[3,3,2,1]"
          :comps="formComps">
  </PtForm>
  <el-dialog v-model="funcApplicationDialogVisible" width="70%" title="要分配的应用及功能" append-to-body destroy-on-close>

    <TenantCreateApplyFuncApplication ref="tenantCreateApplyFuncApplicationRef" :initSelectedData="extJsonObjTemp.funcApplications"></TenantCreateApplyFuncApplication>
    <template #footer>
      <span>
        <PtButton type="primary" @click="funcApplicationSubmit" >确认</PtButton>
      </span>
    </template>
  </el-dialog>
</template>


<style scoped>

</style>
