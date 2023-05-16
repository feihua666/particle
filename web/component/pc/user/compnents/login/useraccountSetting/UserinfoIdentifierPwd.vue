<script setup name="UserinfoIdentifierPwd" lang="ts">

import { getIdentifierPwd} from "../../../api/userLoginApi";
import { userIdentifierPwdForLoginColumns} from "../../userIdentifierPwdCompItem"
import LoginUserIdentifierUpdatePassword from '../LoginUserIdentifierUpdatePassword.vue'
import {ref} from "vue";
import {remove as userIdentifierPwdRemoveApi} from "../../../api/admin/userIdentifierPwdAdminApi";
import {ElMessage} from 'element-plus'

let alert = (message,type='success')=>{
  ElMessage({
    showClose: true,
    message: message,
    type: type,
    showIcon: true,
    grouping: true
  })
}
// 弹窗引用
const updatePasswordDialogVisible = ref(false)
// 表格引用
const tableRef = ref(null)

const props = defineProps({

})
const dropdownTriggerButtonOptions = {
  text: true,
  buttonText: '更多',
}
const tableColumns = userIdentifierPwdForLoginColumns
const selectedUserIdentifierId = ref('')
// 表格操作按钮
const getTableRowButtons = ({row, column, $index}) => {
  if($index < 0){
    return []
  }
  let idData = {id: row.id}
  let tableRowButtons = [
    {
      txt: '修改密码',
      text: true,
      methodConfirmText: `修改密码只对您点击的当前登录账号有效。确定要 ${row.userIdentifier} 的密码吗？`,
      // 修改密码,弹窗
      method(){
        selectedUserIdentifierId.value = row.identifierId
        updatePasswordDialogVisible.value = true
      }
    },
  ]

  return tableRowButtons
}
const updateSuccess = ()=>{
  alert('修改成功')
  updatePasswordDialogVisible.value = false
  // 刷新表格数据
  tableRef.value.refreshData()

}
</script>
<template>
  <PtTable ref="tableRef"
           :columns="tableColumns"
           :dataMethod="getIdentifierPwd"
  >

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="220">
        <template #default="{row, column, $index}">
          <PtButtonGroup :options="getTableRowButtons({row, column, $index})" :dropdownTriggerButtonOptions="dropdownTriggerButtonOptions">
          </PtButtonGroup>
        </template>
      </el-table-column>
    </template>
  </PtTable>

  <el-dialog  v-model="updatePasswordDialogVisible" title="密码修改" append-to-body destroy-on-close>
    <LoginUserIdentifierUpdatePassword :updateSuccess="updateSuccess" :userIdentifierId="selectedUserIdentifierId"></LoginUserIdentifierUpdatePassword>
  </el-dialog>
</template>


<style scoped>

</style>