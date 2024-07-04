<script setup name="CrmCompanyManagePage" lang="ts">
/**
 * 客户公司管理页面
 */
import {reactive, ref} from 'vue'
import { page as crmCompanyPageApi, remove as crmCompanyRemoveApi} from "../../../api/company/admin/crmCompanyAdminApi"
import {pageFormItems} from "../../../components/company/admin/crmCompanyManage";


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
      label: '公司名称',
    },
    {
      prop: 'code',
      label: '公司编码',
    },

    {
      prop: 'nameSimple',
      label: '公司简称名称',
    },
    {
      prop: 'parentName',
      label: '父级',
    },

    {
      prop: 'seq',
      label: '排序',
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
  permission: 'admin:web:crmCompany:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doCrmCompanyPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return crmCompanyPageApi({...reactiveData.form,...pageQuery})
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
      txt: '添加子级',
      text: true,
      permission: 'admin:web:crmCompany:create',
      // 跳转到编辑
      route: {path: '/admin/CrmCompanyManageAdd',query: idData}
    },
    {
      txt: '编辑',
      text: true,
      position: 'more',
      permission: 'admin:web:crmCompany:update',
      // 跳转到编辑
      route: {path: '/admin/CrmCompanyManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      position: 'more',
      permission: 'admin:web:crmCompany:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return crmCompanyRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:crmCompany:create" route="/admin/CrmCompanyManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doCrmCompanyPageApi"
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