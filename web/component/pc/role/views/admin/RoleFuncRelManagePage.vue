<script setup name="RoleFuncRelManagePage" lang="ts">
/**
 * 功能菜单角色关系管理页面
 */
import {reactive, ref} from 'vue'
import {page as roleFuncRelPageApi, remove as roleFuncRelRemoveApi} from "../../api/admin/roleFuncRelAdminApi"
import {useRemoteSelectFuncCompItem, remoteSelectFuncProps} from "../../../func/compnents/funcCompItem";
import {remoteSelectRoleCompItem, remoteSelectRoleProps} from "../../components/roleCompItem";

const tableRef = ref(null)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  ...remoteSelectFuncProps,
  ...remoteSelectRoleProps
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: [
    useRemoteSelectFuncCompItem({props,required: false}),
    remoteSelectRoleCompItem({props,required: false}),
  ],
  tableColumns: [
    {
      prop: 'funcName',
      label: '功能菜单名称',
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
  permission: 'admin:web:roleFuncRel:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doRoleFuncRelPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return roleFuncRelPageApi({...reactiveData.form,...pageQuery})
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
  let funcAssignRoleRouteQuery = {funcId: row.funcId,funcName: row.funcName}
  let deleteByFuncIdRouteQuery = {funcId: row.funcId,funcName: row.funcName}

  let roleAssignFuncRouteQuery = {roleId: row.roleId,roleName: row.roleName}
  let deleteByRoleIdRouteQuery = {roleId: row.roleId,roleName: row.roleName}

  let tableRowButtons = [
    {
      txt: '为该功能菜单分配角色',
      text: true,
      permission: 'admin:web:roleFuncRel:funcAssignRole',
      route: {path: '/admin/roleFuncRelManageFuncAssignRole',query: funcAssignRoleRouteQuery}
    },
    {
      txt: '为该角色分配功能菜单',
      text: true,
      permission: 'admin:web:roleFuncRel:roleAssignFunc',
      route: {path: '/admin/roleFuncRelManageRoleAssignFunc',query: roleAssignFuncRouteQuery}
    },
    {
      txt: '为该功能菜单清空角色',
      text: true,
      methodConfirmText: `您将清空功能菜单 ${row.funcName} 所有角色,该功能菜单将不再分配给任何角色，同时拥有涉及对应角色的用户功能将受到影响，请谨慎操作！！！，确定要清空吗？`,
      permission: 'admin:web:roleFuncRel:deleteByFuncId',
      route: {path: '/admin/roleFuncRelManageDeleteByFuncId',query: deleteByFuncIdRouteQuery}
    },
    {
      txt: '为该角色清空功能菜单',
      text: true,
      methodConfirmText: `您将清空角色 ${row.roleName} 所有功能菜单,该角色将不再拥有任何功能菜单，同时拥有该角色的用户功能将受到影响，请谨慎操作！！！，确定要清空吗？`,
      permission: 'admin:web:roleFuncRel:deleteByRoleId',
      route: {path: '/admin/roleFuncRelManageDeleteByRoleId',query: deleteByRoleIdRouteQuery}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:roleFuncRel:delete',
      methodConfirmText: `删除后角色 ${row.roleName} 将不再拥有功能菜单 ${row.funcName}，确定要删除吗？`,
      // 删除操作
      method(){
        return roleFuncRelRemoveApi(idData).then(res => {
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
      <PtButton permission="admin:web:roleFuncRel:create" route="/admin/roleFuncRelManageAdd">添加</PtButton>
      <PtButton permission="admin:web:roleFuncRel:funcAssignRole" route="/admin/roleFuncRelManageFuncAssignRole">功能菜单分配角色</PtButton>
      <PtButton permission="admin:web:roleFuncRel:roleAssignFunc" route="/admin/roleFuncRelManageRoleAssignFunc">角色分配功能菜单</PtButton>

      <PtButton permission="admin:web:roleFuncRel:deleteByFuncId" route="/admin/roleFuncRelManageDeleteByFuncId">清空功能菜单角色</PtButton>
      <PtButton permission="admin:web:roleFuncRel:deleteByRoleId" route="/admin/roleFuncRelManageDeleteByRoleId">清空角色功能菜单</PtButton>
    </template>
  </PtForm>
  <!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doRoleFuncRelPageApi"
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