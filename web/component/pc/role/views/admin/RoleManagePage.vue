<script setup name="RoleManagePage" lang="ts">
/**
 * 角色管理页面
 */
import {reactive, ref} from 'vue'
import {page as rolePageApi, remove as roleRemoveApi} from "../../api/admin/roleAdminApi"
import {pageFormItems} from "../../components/admin/roleManage";
import {componentEnabled} from "../../../../../common/config/componentsConfig";

const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'name',
      label: '名称',
      width: 150,
      showOverflowTooltip: true
    },
    {
      prop: 'code',
      label: '编码',
      showOverflowTooltip: true
    },
    {
      prop: 'parentName',
      label: '父级',
      showOverflowTooltip: true
    },
    {
      prop: 'isDisabled',
      label: '是否禁用',
      width: 80,
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? '禁用' : '启用'
        if(cellValue && row.disabledReason){
          r = r + `(${row.disabledReason})`
        }
        return r
      }
    },
    {
      prop: 'seq',
      label: '排序',
      width: 60,
    },
    {
      prop: 'remark',
      label: '描述',
      showOverflowTooltip: true
    }
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:role:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doRolePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return rolePageApi({...reactiveData.form,...pageQuery})
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
  let tableRowButtons: Array<any> = [
    {
      txt: '编辑',
      text: true,
      permission: 'admin:web:role:update',
      // 跳转到编辑
      route: {path: '/admin/roleManageUpdate',query: idData}
    },
    {
      txt: '删除',
      position: 'more',
      text: true,
      permission: 'admin:web:role:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return roleRemoveApi({id: row.id}).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    }
  ]
  let roleAssignFuncRouteQuery = {roleId: row.id,roleName: row.name}
  let roleAssignDataScopeRouteQuery = {roleId: row.id,roleName: row.name}

  if (componentEnabled('func')) {
    tableRowButtons.push(
        {
          txt: '角色分配功能菜单',
          position: 'more',
          text: true,
          permission: 'admin:web:roleFuncRel:roleAssignFunc',
          route: {path: '/admin/roleManageRoleAssignFunc',query: roleAssignFuncRouteQuery}
        }
    )
  }
  if (componentEnabled('data-constraint')) {
    tableRowButtons.push(
        {
          txt: '角色分配数据范围',
          position: 'more',
          text: true,
          permission: 'admin:web:roleDataScopeRel:roleAssignDataScope',
          route: {path: '/admin/roleManageRoleAssignDataScope',query: roleAssignDataScopeRouteQuery}
        }
    )
  }
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
      <PtButton permission="admin:web:role:create" route="/admin/roleManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doRolePageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           :dataMethodResultHandleConvertToTree="true"
           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="180">
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
