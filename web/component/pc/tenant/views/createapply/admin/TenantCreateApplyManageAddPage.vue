<script setup name="TenantCreateApplyManageAddPage" lang="ts">
/**
 * 租户创建申请管理添加页面
 */
import {reactive ,ref} from 'vue'

import {create as TenantCreateApplyCreateApi,list as TenantCreateApplyListApi} from "../../../api/createapply/admin/tenantCreateApplyAdminApi"
import {useAddPageFormItems} from "../../../components/createapply/admin/tenantCreateApplyManage";
import { ElMessage } from 'element-plus'
import TenantCreateApplyFuncApplication from '../../../components/createapply/admin/funcapplication/TenantCreateApplyFuncApplication.vue'
const funcApplicationDialogVisible = ref(false)
const tenantCreateApplyFuncApplicationRef = ref(null)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    extJsonObj: null
  },
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    useAddPageFormItems({props,funcApplicationDialogVisible})
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认添加',
  permission: 'admin:web:tenantCreateApply:create',
})
// 提交按钮
const submitMethod = () => {
  return TenantCreateApplyCreateApi
}

// 成功提示语
const submitMethodSuccess = () => {
  return '添加成功，请刷新数据查看'
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
// 传递已选中数据时不能使用form.extJsonObj,会引起副作用依赖循环，这里单独加一个
const extJsonObjTemp = ref({})
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