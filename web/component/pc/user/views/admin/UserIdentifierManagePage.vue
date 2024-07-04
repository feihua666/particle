<script setup name="UserIdentifierManagePage" lang="ts">
/**
 * 用户登录标识管理页面
 */
import {reactive, ref} from 'vue'
import { page as userIdentifierPageApi, remove as userIdentifierRemoveApi} from "../../api/admin/userIdentifierAdminApi"
import {useRemoteSelectUserCompItem, remoteSelectUserProps} from "../../components/userCompItem";
import {usePageFormItems} from "../../components/admin/userIdentifierManage";
import {userIdentifierColumns} from "../../components/userIdentifierCompItem";

const tableRef = ref(null)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
...remoteSelectUserProps
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: usePageFormItems({props}),
  tableColumns: userIdentifierColumns,

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:userIdentifier:pageQuery',
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doUserIdentifierPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return userIdentifierPageApi({...reactiveData.form,...pageQuery})
}
const tablePaginationProps = {
  permission: submitAttrs.value.permission
}
// 表格操作按钮
const getTableRowButtons = ({row, column, $index}) => {
  if($index < 0){
    return []
  }
  let idData = {id: row.id}

  let routeQuery = {identifierId: row.id,identifier: row.identifier}
  let tableRowButtons = [
    {
      txt: '编辑',
      text: true,
      permission: 'admin:web:userIdentifier:update',
      // 跳转到编辑
      route: {path: '/admin/userIdentifierManageUpdate',query: routeQuery}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:userIdentifier:delete',
      methodConfirmText: `删除用户账号会造成用户当前账号不能登录，同时会删除当前账号的密码数据。确定要删除 ${row.identifier} 吗？`,
      // 删除操作
      method(){
        return userIdentifierRemoveApi(idData).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '重置密码',
      text: true,
      permission: 'admin:web:userIdentifierPwd:identifier:resetPassword',
      position: 'more',
      methodConfirmText: `此操作只影响当前登录标识密码，如果用户还有其它登录标识（其密码不受响应），确定要重置密码吗？`,
      // 跳转到编辑
      route: {path: '/admin/userIdentifierPwdManageResetPassword',query: routeQuery}
    },
    {
      txt: '用户密码',
      text: true,
      permission: 'admin:web:userIdentifierPwd:pageQuery',
      position: 'more',
      icon: 'View',
      type: 'primary',
      // 跳转到编辑
      route: {path: '/admin/userIdentifierPwdManagePage',query: routeQuery},
    },
  ]
  return tableRowButtons
}
const dropdownTriggerButtonOptions = {
  text: true,
  buttonText: '更多',
}
</script>
<template>
  <!-- 查询表单 -->
  <PtForm :form="reactiveData.form"
          :method="submitMethod"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          inline
          labelWidth="80"
          :comps="reactiveData.formComps">
    <template #buttons>
      <PtButton permission="admin:web:userIdentifier:create" route="/admin/userIdentifierManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doUserIdentifierPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"

           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="220">
        <template #default="{row, column, $index}">
          <PtButtonGroup :options="getTableRowButtons({row, column, $index})"  :dropdownTriggerButtonOptions="dropdownTriggerButtonOptions">
          </PtButtonGroup>
        </template>
      </el-table-column>
    </template>
  </PtTable>
<!-- 子级路由 -->
  <PtRouteViewPopover :level="3"></PtRouteViewPopover>
</template>


<style scoped>

</style>