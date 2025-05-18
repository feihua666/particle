<script setup name="DataCompanyAnnualReportAdministrativeLicenseManagePage" lang="ts">
/**
 * 企业年报行政许可管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyAnnualReportAdministrativeLicensePageApi, remove as dataCompanyAnnualReportAdministrativeLicenseRemoveApi} from "../../../api/company/admin/dataCompanyAnnualReportAdministrativeLicenseAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyAnnualReportAdministrativeLicenseManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'companyId',
      label: '企业表ID',
    },
    {
      prop: 'companyAnnualReportId',
      label: '企业年报表ID',
    },
    {
      prop: 'year',
      label: '年报年度',
    },
    {
      prop: 'serialNumber',
      label: '序号',
    },
    {
      prop: 'fileName',
      label: '许可文件名称',
    },
    {
      prop: 'validToDate',
      label: '许可文件到期日期',
    },
    {
      prop: 'latestHandleAt',
      label: '最后处理时间',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:dataCompanyAnnualReportAdministrativeLicense:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyAnnualReportAdministrativeLicensePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyAnnualReportAdministrativeLicensePageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyAnnualReportAdministrativeLicense:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyAnnualReportAdministrativeLicenseManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyAnnualReportAdministrativeLicense:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyAnnualReportAdministrativeLicenseRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyAnnualReportAdministrativeLicense:create" route="/admin/DataCompanyAnnualReportAdministrativeLicenseManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyAnnualReportAdministrativeLicensePageApi"
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
