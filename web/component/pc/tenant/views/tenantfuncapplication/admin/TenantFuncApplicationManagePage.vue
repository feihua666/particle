<script setup name="TenantFuncApplicationManagePage" lang="ts">
/**
 * 租户功能应用管理页面
 */
import {reactive, ref} from 'vue'
import { page as TenantFuncApplicationPageApi, remove as TenantFuncApplicationRemoveApi} from "../../../api/tenantfuncapplication/admin/tenantFuncApplicationAdminApi"
import {pageFormItems} from "../../../components/tenantfuncapplication/admin/tenantFuncApplicationManage";
import {listToTree} from "../../../../../../global/common/tools/ArrayTools";
import {tenantFuncApplicationColumns} from "../../../components/tenantCompItem";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: tenantFuncApplicationColumns,

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:tenantFuncApplication:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doTenantFuncApplicationPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return TenantFuncApplicationPageApi({...reactiveData.form,...pageQuery})
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
  let tenantIdData = {tenantId: row.tenantId}
  let tenantIdAndFuncApplicationIdData = {tenantId: row.tenantId,funcApplicationId: row.funcApplicationId}

  // 添加 Array<any> 仅为了不提示错误语法
  let tableRowButtons:Array<any> = [
    {
      txt: '编辑',
      text: true,
      permission: 'admin:web:tenantFuncApplication:update',
      // 跳转到编辑
      route: {path: '/admin/TenantFuncApplicationManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:tenantFuncApplication:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return TenantFuncApplicationRemoveApi({id: row.id}).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
  ]

  // 只有应用才能分配功能菜单
  !row.isGroup && tableRowButtons.push({
    txt: '租户应用分配功能菜单',
    text: true,
    position: 'more',
    permission: 'admin:web:tenantFunc:tenantAssignFunc',
    // 跳转
    route: {path: '/admin/tenantFuncApplication/tenantFuncTenantAssignFunc',query: tenantIdAndFuncApplicationIdData}
  })
  return tableRowButtons
}
const listToTreeMethod = (data)=>{
  return listToTree(data,null,'funcApplicationId','parentFuncApplicationId')
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
      <PtButton permission="admin:web:tenantFuncApplication:create" route="/admin/TenantFuncApplicationManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doTenantFuncApplicationPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           :dataMethodResultHandleConvertToTree="true"
           :dataMethodResultHandleListToTreeMethod="listToTreeMethod"
           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="220">
        <template #default="{row, column, $index}">
          <PtButtonGroup :options="getTableRowButtons({row, column, $index})" :dropdownTriggerButtonOptions="{  text: true,buttonText: '更多'}">
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