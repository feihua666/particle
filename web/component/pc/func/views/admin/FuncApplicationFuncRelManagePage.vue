<script setup name="FuncApplicationFuncRelManagePage" lang="ts">
/**
 * 功能应用功能关系管理页面
 */
import {reactive, ref} from 'vue'
import { page as FuncApplicationFuncRelPageApi, remove as FuncApplicationFuncRelRemoveApi} from "../../api/admin/funcApplicationFuncRelAdminApi"
import {pageFormItems} from "../../components/admin/funcApplicationFuncRelManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'funcApplicationName',
      label: '功能应用名称',
      showOverflowTooltip: true,
      width: 120
    },
    {
      prop: 'funcName',
      label: '功能名称',
      showOverflowTooltip: true,
      width: 120
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:funcApplicationFuncRel:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doFuncApplicationFuncRelPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return FuncApplicationFuncRelPageApi({...reactiveData.form,...pageQuery})
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
  let funcAssignFuncApplicationRouteQuery = {funcId: row.funcId,funcName: row.funcName}
  let deleteByFuncIdRouteQuery = {funcId: row.funcId,funcName: row.funcName}
  let funcApplicationAssignFuncRouteQuery = {funcApplicationId: row.funcApplicationId,funcApplicationName: row.funcApplicationName}
  let deleteByFuncApplicationIdRouteQuery = {funcApplicationId: row.funcApplicationId,funcApplicationName: row.funcApplicationName}
  let tableRowButtons = [
    {
      txt: '为该功能分配功能应用',
      text: true,
      permission: 'admin:web:funcApplicationFuncRel:funcAssignFuncApplication',
      route: {path: '/admin/funcApplicationFuncRelManageFuncAssignFuncApplication',query: funcAssignFuncApplicationRouteQuery}
    },
    {
      txt: '为该功能应用分配功能',
      text: true,
      permission: 'admin:web:funcApplicationFuncRel:funcApplicationAssignFunc',
      route: {path: '/admin/funcApplicationFuncRelManageFuncApplicationAssignFunc',query: funcApplicationAssignFuncRouteQuery}
    },
    {
      txt: '为该功能清空功能应用',
      text: true,
      methodConfirmText: `您将清空功能 ${row.funcName} 所有功能应用,该功能将不再拥有任何功能应用，请谨慎操作！！！，确定要清空吗？`,
      permission: 'admin:web:funcApplicationFuncRel:deleteByFuncId',
      route: {path: '/admin/funcApplicationFuncRelManageDeleteByFuncId',query: deleteByFuncIdRouteQuery}
    },
    {
      txt: '为该功能应用清空功能',
      text: true,
      methodConfirmText: `您将清空功能应用 ${row.funcApplicationName} 所有功能应用,该功能应用将不再分配给任何功能，请谨慎操作！！！，确定要清空吗？`,
      permission: 'admin:web:funcApplicationFuncRel:deleteByFuncApplicationId',
      route: {path: '/admin/funcApplicationFuncRelManageDeleteByFuncApplicationId',query: deleteByFuncApplicationIdRouteQuery}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:funcApplicationFuncRel:delete',
      methodConfirmText: `删除后功能应用 ${row.funcApplicationName} 将不再拥有功能 ${row.funcName}，确定要删除吗？`,
      // 删除操作
      method(){
        return FuncApplicationFuncRelRemoveApi(idData).then(res => {
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
      <PtButton permission="admin:web:funcApplicationFuncRel:create" route="/admin/FuncApplicationFuncRelManageAdd">添加</PtButton>

      <PtButton permission="admin:web:funcApplicationFuncRel:funcAssignFuncApplication" route="/admin/funcApplicationFuncRelManageFuncAssignFuncApplication">功能分配功能应用</PtButton>
      <PtButton permission="admin:web:funcApplicationFuncRel:funcApplicationAssignFunc" route="/admin/funcApplicationFuncRelManageFuncApplicationAssignFunc">功能应用分配功能</PtButton>
      <PtButton permission="admin:web:funcApplicationFuncRel:deleteByFuncId" route="/admin/funcApplicationFuncRelManageDeleteByFuncId">清空功能功能应用</PtButton>
      <PtButton permission="admin:web:funcApplicationFuncRel:deleteByFuncApplicationId" route="/admin/funcApplicationFuncRelManageDeleteByFuncApplicationId">清空功能应用功能</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doFuncApplicationFuncRelPageApi"
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