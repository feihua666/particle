<script setup name="TenantManagePage" lang="ts">
/**
 * 租户管理页面
 */
import {reactive, ref} from 'vue'
import { page as TenantPageApi, remove as TenantRemoveApi} from "../../api/admin/tenantAdminApi"
import {pageFormItems} from "../../compnents/admin/tenantManage";


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
      label: '租户名称',
      showOverflowTooltip: true
    },
    {
      prop: 'code',
      label: '租户编码',
      showOverflowTooltip: true
    },

    {
      prop: 'tenantLogoJson',
      label: '租户logo',
      columnView: 'image'
    },
    {
      prop: 'isFormal',
      label: '是否正式',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '正式' : '试用'
      }
    },
    {
      prop: 'userLimitCount',
      label: '用户数限制',
    },
    {
      prop: 'effectiveAt',
      label: '生效日期',
    },
    {
      prop: 'expireAt',
      label: '过期时间',
    },
    {
      prop: 'masterUserNickname',
      label: '主用户昵称',
    },
    {
      prop: 'isDisabled',
      label: '是否禁用',
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? '禁用' : '启用'
        if(cellValue && row.disabledReason){
          r = r + `(${row.disabledReason})`
        }
        return r
      }
    },
    {
      prop: 'userName',
      label: '姓名',
    },
    {
      prop: 'email',
      label: '邮箱',
    },
    {
      prop: 'mobile',
      label: '手机号',
    },
    {
      prop: 'tenantDomain',
      label: '租户域名',
    },
    {
      prop: 'tenantThemeJson',
      label: '租户主题',
    },
    {
      prop: 'tenantDefaultRouteJson',
      label: '默认路由',
    },
    {
      prop: 'configJson',
      label: '额外配置json',
      showOverflowTooltip: true
    },
    {
      prop: 'remark',
      label: '描述',
    },

  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:tenant:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doTenantPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return TenantPageApi({...reactiveData.form,...pageQuery})
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
  let tenantIdData = {tenantId: row.id,masterUserId: row.masterUserId,masterUserNickname: row.masterUserNickname}
  let tableRowButtons = [
    {
      txt: '编辑',
      text: true,
      permission: 'admin:web:tenant:update',
      // 跳转到编辑
      route: {path: '/admin/TenantManageUpdate',query: tenantIdData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:tenant:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return TenantRemoveApi({id: row.id}).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '租户分配应用',
      text: true,
      position: 'more',
      permission: 'admin:web:tenantFuncApplication:tenantAssignFuncApplication',
      // 跳转到编辑
      route: {path: '/admin/tenant/tenantAssignFuncApplication',query: tenantIdData}
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
      <PtButton permission="admin:web:tenant:create" route="/admin/TenantManageAdd">添加</PtButton>
      <PtButton permission="admin:web:tenant:oneClickCreate" route="/admin/tenantManageOneClickAdd">一键添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doTenantPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
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