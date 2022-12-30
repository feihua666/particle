<script setup name="RoleUserRelUserRelManagePage" lang="ts">
/**
 * 用户角色关系管理页面
 */
import {reactive, ref} from 'vue'
import {page as roleUserRelPageApi, remove as roleUserRelRemoveApi} from "../../api/admin/roleUserRelAdminApi"
import {remoteSelectUserCompItem, remoteSelectUserProps} from "../../../user/compnents/userCompItem";
import {remoteSelectRoleCompItem, remoteSelectRoleProps} from "../../components/roleCompItem";

const tableRef = ref(null)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  ...remoteSelectUserProps,
  ...remoteSelectRoleProps
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: [
    remoteSelectUserCompItem({props,required: false}),
    remoteSelectRoleCompItem({props,required: false}),
  ],
  tableColumns: [
    {
      prop: 'userNickname',
      label: '用户昵称',
      showOverflowTooltip: true,
      width: 120
    },
    {
      prop: 'roleName',
      label: '角色名称',
      showOverflowTooltip: true,
      width: 120
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:roleUserRel:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doRoleUserRelPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return roleUserRelPageApi({...reactiveData.form,...pageQuery})
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
  let userAssignRoleRouteQuery = {userId: row.userId,userNickname: row.userNickname}
  let deleteByUserIdRouteQuery = {userId: row.userId,userNickname: row.userNickname}
  let roleAssignUserRouteQuery = {roleId: row.roleId,roleName: row.roleName}
  let deleteByRoleIdRouteQuery = {roleId: row.roleId,roleName: row.roleName}
  let tableRowButtons = [
    {
      txt: '为该用户分配角色',
      text: true,
      permission: 'admin:web:roleUserRel:userAssignRole',
      route: {path: '/admin/roleUserRelManageUserAssignRole',query: userAssignRoleRouteQuery}
    },
    {
      txt: '为该角色分配用户',
      text: true,
      permission: 'admin:web:roleUserRel:roleAssignUser',
      route: {path: '/admin/roleUserRelManageRoleAssignUser',query: roleAssignUserRouteQuery}
    },
    {
      txt: '为该用户清空角色',
      text: true,
      methodConfirmText: `您将清空用户 ${row.userNickname} 所有角色,该用户将不再拥有任何角色，请谨慎操作！！！，确定要清空吗？`,
      permission: 'admin:web:roleUserRel:deleteByUserId',
      route: {path: '/admin/roleUserRelManageDeleteByUserId',query: deleteByUserIdRouteQuery}
    },
    {
      txt: '为该角色清空用户',
      text: true,
      methodConfirmText: `您将清空角色 ${row.roleName} 所有角色,该角色将不再分配给任何用户，请谨慎操作！！！，确定要清空吗？`,
      permission: 'admin:web:roleUserRel:deleteByRoleId',
      route: {path: '/admin/roleUserRelManageDeleteByRoleId',query: deleteByRoleIdRouteQuery}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:roleUserRel:delete',
      methodConfirmText: `删除后用户 ${row.userNickname} 将不再拥有角色 ${row.roleName} 及角色所赋予的功能，确定要删除吗？`,
      // 删除操作
      method(){
        return roleUserRelRemoveApi(idData).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    }
  ]
  return tableRowButtons
}
</script>
<template>
  <!-- 查询表单 -->
  <PtForm :form="reactiveData.form"
          :method="submitMethod"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          inline
          :comps="reactiveData.formComps">
    <template #buttons>
      <PtButton permission="admin:web:roleUserRel:create" route="/admin/roleUserRelManageAdd">添加</PtButton>
      <PtButton permission="admin:web:roleUserRel:userAssignRole" route="/admin/roleUserRelManageUserAssignRole">用户分配角色</PtButton>
      <PtButton permission="admin:web:roleUserRel:roleAssignUser" route="/admin/roleUserRelManageRoleAssignUser">角色分配用户</PtButton>
      <PtButton permission="admin:web:roleUserRel:deleteByUserId" route="/admin/roleUserRelManageDeleteByUserId">清空用户角色</PtButton>
      <PtButton permission="admin:web:roleUserRel:deleteByRoleId" route="/admin/roleUserRelManageDeleteByRoleId">清空角色用户</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doRoleUserRelPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           :dataMethodResultHandleConvertToTree="true"
           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作">
        <template #default="{row, column, $index}">
          <PtButtonGroup :options="getTableRowButtons({row, column, $index})">
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