<script setup name="DataCompanyAnnualReportChangeManagePage" lang="ts">
/**
 * 企业年报变更管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyAnnualReportChangePageApi, remove as dataCompanyAnnualReportChangeRemoveApi} from "../../../api/company/admin/dataCompanyAnnualReportChangeAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyAnnualReportChangeManage";


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
      prop: 'changeItemDictName',
      label: '变更事项',
    },
    {
      prop: 'changeItemName',
      label: '变更事项',
    },
    {
      prop: 'contentBefore',
      label: '变更前内容',
    },
    {
      prop: 'contentAfter',
      label: '变更后内容',
    },
    {
      prop: 'changeDate',
      label: '变更日期',
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
  permission: 'admin:web:dataCompanyAnnualReportChange:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyAnnualReportChangePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyAnnualReportChangePageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyAnnualReportChange:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyAnnualReportChangeManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyAnnualReportChange:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyAnnualReportChangeRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyAnnualReportChange:create" route="/admin/DataCompanyAnnualReportChangeManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyAnnualReportChangePageApi"
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