<script setup name="TenantUserManagePage" lang="ts">
/**
 * 租户用户管理页面
 */
import {reactive, ref} from 'vue'
import { page as TenantUserPageApi, remove as TenantUserRemoveApi} from "../../api/admin/TenantUserAdminApi"
import {pageFormItems, usePageFormItems} from "../../compnents/admin/tenantUserManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: usePageFormItems({props: {}}),
  tableColumns: [
    {
      prop: 'nickname',
      label: '昵称',
      showOverflowTooltip: true
    },
    {
      prop: 'name',
      label: '用户姓名',
    },
    {
      prop: 'avatar',
      label: '头像',
      columnView: 'image'
    },
    {
      prop: 'joinAt',
      label: '加入时间',
    },
    {
      prop: 'isExpired',
      label: '是否过期',
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? '已过期' : '正常'
        if(cellValue && row.expiredReason){
          r = r + `(${row.expiredReason})`
        }
        return r
      }
    },
    {
      prop: 'expireAt',
      label: '到期时间',
    },
    {
      prop: 'isLeave',
      label: '是否离职/退出',
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? '离职/退出' : '正常'
        if(cellValue && row.leaveReason){
          r = r + `(${row.leaveReason})`
        }
        return r
      }
    },
    {
      prop: 'leaveAt',
      label: '离职/退出时间',
    },

    {
      prop: 'tenantName',
      label: '租户名称',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:tenantUser:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doTenantUserPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return TenantUserPageApi({...reactiveData.form,...pageQuery})
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
  let tableRowButtons = [
    {
      txt: '编辑',
      text: true,
      permission: 'admin:web:tenantUser:update',
      // 跳转到编辑
      route: {path: '/admin/TenantUserManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:tenantUser:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return TenantUserRemoveApi({id: row.id}).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
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
      <PtButton permission="admin:web:tenantUser:create" route="/admin/TenantUserManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doTenantUserPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           :dataMethodResultHandleConvertToTree="true"
           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="180">
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