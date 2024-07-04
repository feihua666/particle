<script setup name="RoleDataScopeRelManagePage" lang="ts">
/**
 * 角色数据范围关系管理页面
 */
import {reactive, ref} from 'vue'
import { page as roleDataScopeRelPageApi, remove as roleDataScopeRelRemoveApi} from "../../../api/roledatascoperel/admin/roleDataScopeRelAdminApi"
import {pageFormItems} from "../../../components/roledatascoperel/admin/roleDataScopeRelManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'roleName',
      label: '角色',
      showOverflowTooltip: true,
      width: 120
    },
    {
      prop: 'dataObjectName',
      label: '数据对象',
      showOverflowTooltip: true,
      width: 120
    },
    {
      prop: 'dataScopeName',
      label: '数据范围',
      showOverflowTooltip: true,
      width: 120
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:roleDataScopeRel:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doRoleDataScopeRelPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return roleDataScopeRelPageApi({...reactiveData.form,...pageQuery})
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
  let idUpdateData = {id: row.id,roleId: row.roleId,roleName: row.roleName}
  let dataScopeAssignRoleRouteQuery = {dataScopeId: row.dataScopeId,dataScopeName: row.dataScopeName,dataObjectId: row.dataObjectId}
  let deleteByDataScopeIdRouteQuery = {dataScopeId: row.dataScopeId,dataScopeName: row.dataScopeName}

  let roleAssignDataScopeRouteQuery = {roleId: row.roleId,roleName: row.roleName}
  let deleteByRoleIdRouteQuery = {roleId: row.roleId,roleName: row.roleName}

  let tableRowButtons = [
    {
      txt: '为该数据范围分配角色',
      text: true,
      permission: 'admin:web:roleDataScopeRel:dataScopeAssignRole',
      route: {path: '/admin/roleDataScopeRelManageDataScopeAssignRole',query: dataScopeAssignRoleRouteQuery}
    },
    {
      txt: '为该角色分配数据范围',
      text: true,
      permission: 'admin:web:roleDataScopeRel:roleAssignDataScope',
      route: {path: '/admin/roleDataScopeRelManageRoleAssignDataScope',query: roleAssignDataScopeRouteQuery}
    },
    {
      txt: '为该数据范围清空角色',
      text: true,
      methodConfirmText: `您将清空数据范围 ${row.dataScopeName} 所有角色,该数据范围将不再分配给任何角色，同时拥有涉及对应角色的用户数据范围将受到影响，请谨慎操作！！！，确定要清空吗？`,
      permission: 'admin:web:roleDataScopeRel:deleteByDataScopeId',
      route: {path: '/admin/roleDataScopeRelManageDeleteByDataScopeId',query: deleteByDataScopeIdRouteQuery}
    },
    {
      txt: '为该角色清空数据范围',
      text: true,
      methodConfirmText: `您将清空角色 ${row.roleName} 所有数据范围,该角色将不再拥有任何数据范围，同时拥有该角色的用户数据范围将受到影响，请谨慎操作！！！，确定要清空吗？`,
      permission: 'admin:web:roleDataScopeRel:deleteByRoleId',
      route: {path: '/admin/roleDataScopeRelManageDeleteByRoleId',query: deleteByRoleIdRouteQuery}
    },
    {
      txt: '编辑',
      text: true,
      permission: 'admin:web:roleDataScopeRel:update',
      // 跳转到编辑
      route: {path: '/admin/roleDataScopeRelManageUpdate',query: idUpdateData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:roleDataScopeRel:delete',
      methodConfirmText: `删除后角色 ${row.roleName} 将不再拥有数据范围 ${row.dataScopeName}，确定要删除吗？`,
      // 删除操作
      method(){
        return roleDataScopeRelRemoveApi(idData).then(res => {
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
      <PtButton permission="admin:web:roleDataScopeRel:create" route="/admin/RoleDataScopeRelManageAdd">添加</PtButton>
      <PtButton permission="admin:web:roleDataScopeRel:dataScopeAssignRole" route="/admin/roleDataScopeRelManageDataScopeAssignRole">数据范围分配角色</PtButton>
      <PtButton permission="admin:web:roleDataScopeRel:roleAssignDataScope" route="/admin/roleDataScopeRelManageRoleAssignDataScope">角色分配数据范围</PtButton>

      <PtButton permission="admin:web:roleDataScopeRel:deleteByDataScopeId" route="/admin/roleDataScopeRelManageDeleteByDataScopeId">清空数据范围角色</PtButton>
      <PtButton permission="admin:web:roleDataScopeRel:deleteByRoleId" route="/admin/roleDataScopeRelManageDeleteByRoleId">清空角色数据范围</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doRoleDataScopeRelPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           
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