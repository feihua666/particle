<script setup name="CrmCustomerManagePage" lang="ts">
/**
 * 客户管理页面
 */
import {reactive, ref} from 'vue'
import { page as crmCustomerPageApi, remove as crmCustomerRemoveApi} from "../../../api/customer/admin/crmCustomerAdminApi"
import {pageFormItems} from "../../../compnents/customer/admin/crmCustomerManage";


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
      label: '客户名称',
    },
    {
      prop: 'code',
      label: '客户编码',
    },
    {
      prop: 'appellation',
      label: '客户称呼',
    },
    {
      prop: 'typeDictName',
      label: '客户类型',
    },
    {
      prop: 'genderDictName',
      label: '客户性别',
    },
    {
      prop: 'age',
      label: '客户年龄',
    },
    {
      prop: 'birthDay',
      label: '客户生日',
    },
    {
      prop: 'crmCompanyName',
      label: '客户公司',
    },
    {
      prop: 'crmDeptName',
      label: '客户部门',
    },
    {
      prop: 'isBlack',
      label: '黑名单',
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? '是' : '否'
        if(!cellValue && row.blackReason){
          r = r + `(${row.blackReason})`
        }
        return r
      },
    },
    {
      prop: 'categoryDictName',
      label: '客户分类',
    },
    {
      prop: 'belongUserNickname',
      label: '归属用户',
    },
    {
      prop: 'belongCompName',
      label: '归属公司',
    },
    {
      prop: 'belongDeptName',
      label: '归属部门',
    },
    {
      prop: 'unionId',
      label: '合并到',
      formatter: (row, column, cellValue, index) => {
        let r = cellValue || row.appellation
        return r
      },
    },
    {
      prop: 'remark',
      label: '备注',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:crmCustomer:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doCrmCustomerPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return crmCustomerPageApi({...reactiveData.form,...pageQuery})
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
      txt: '联系方式管理',
      text: true,
      permission: 'admin:web:crmCustomerContact:pageQuery',
      // 跳转到编辑
      route: {path: '/admin/crmCustomerContactManagePage',query: {crmCustomerId: row.id}}
    },
    {
      txt: '编辑',
      text: true,
      permission: 'admin:web:crmCustomer:update',
      // 跳转到编辑
      route: {path: '/admin/CrmCustomerManageUpdate',query: {id: row.id,belongUserId: row.belongUserId,belongUserNickname: row.belongUserNickname}}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:crmCustomer:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return crmCustomerRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:crmCustomer:create" route="/admin/CrmCustomerManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doCrmCustomerPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           
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