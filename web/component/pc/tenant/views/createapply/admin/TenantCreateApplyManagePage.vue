<script setup name="TenantCreateApplyManagePage" lang="ts">
/**
 * 租户创建申请管理页面
 */
import {reactive, ref} from 'vue'
import { page as TenantCreateApplyPageApi, remove as TenantCreateApplyRemoveApi} from "../../../api/createapply/admin/TenantCreateApplyAdminApi"
import {pageFormItems} from "../../../compnents/createapply/admin/tenantCreateApplyManage";


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
    },
    {
      prop: 'contactUserName',
      label: '联系人姓名',
    },
    {
      prop: 'contactUserEmail',
      label: '联系人邮箱',
    },
    {
      prop: 'contactUserPhone',
      label: '联系人电话',
    },
    {
      prop: 'tenantTypeDictName',
      label: '租户类型字典id',
    },
    {
      prop: 'applyUserId',
      label: '申请用户',
    },
    {
      prop: 'auditStatusDictName',
      label: '审核状态',
    },
    {
      prop: 'auditStatusComment',
      label: '审核意见',
    },
    {
      prop: 'auditUserId',
      label: '审核用户id',
    },
    {
      prop: 'appliedTenantId',
      label: '审核通过后创建的租户id',
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
  permission: 'admin:web:TenantCreateApply:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doTenantCreateApplyPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return TenantCreateApplyPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:TenantCreateApply:update',
      // 跳转到编辑
      route: {path: '/admin/TenantCreateApplyManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:TenantCreateApply:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return TenantCreateApplyRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:TenantCreateApply:create" route="/admin/TenantCreateApplyManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doTenantCreateApplyPageApi"
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