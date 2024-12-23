<script setup name="TenantManageOneClickAddPage" lang="ts">
/**
 * 一键添加租户，自动默认租户申请审批通过，支持已存在用户直接关系租户，支持不存在用户一键添加
 */
import {reactive, ref} from 'vue'

import {ElMessage} from 'element-plus'
import TenantCreateApplyFuncApplication
  from '../../components/createapply/admin/funcapplication/TenantCreateApplyFuncApplication.vue'
import {useOneClickAddPageFormItems} from "../../components/createapply/admin/tenantCreateApplyManage";
import {oneClickCreate} from "../../api/admin/tenantAdminApi";

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
    useOneClickAddPageFormItems({props,funcApplicationDialogVisible})
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认添加',
  permission: 'admin:web:tenantCreateApply:create',
})
// 提交按钮
const submitMethod = () => {
  return oneClickCreate
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
