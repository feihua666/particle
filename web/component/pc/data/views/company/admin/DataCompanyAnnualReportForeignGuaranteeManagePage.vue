<script setup name="DataCompanyAnnualReportForeignGuaranteeManagePage" lang="ts">
/**
 * 企业年报对外担保管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyAnnualReportForeignGuaranteePageApi, remove as dataCompanyAnnualReportForeignGuaranteeRemoveApi} from "../../../api/company/admin/dataCompanyAnnualReportForeignGuaranteeAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyAnnualReportForeignGuaranteeManage";


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
      prop: 'debtorName',
      label: '债务人名称',
    },
    {
      prop: 'isDebtorNaturalPerson',
      label: '是否债务人为自然人',
    },
    {
      prop: 'debtorCompanyId',
      label: '债务人公司id',
    },
    {
      prop: 'debtorCompanyPersonId',
      label: '债务人个人id',
    },
    {
      prop: 'creditorName',
      label: '债权人名称',
    },
    {
      prop: 'isCreditorNaturalPerson',
      label: '是否债权人为自然人',
    },
    {
      prop: 'creditorCompanyId',
      label: '债权人公司id',
    },
    {
      prop: 'creditorCompanyPersonId',
      label: '债权人个人id',
    },
    {
      prop: 'creditoTypeDictName',
      label: '主债权种类',
    },
    {
      prop: 'creditoAmount',
      label: '主债权金额(万元)',
    },
    {
      prop: 'creditoAmountCurrencyDictName',
      label: '主债权金额币种',
    },
    {
      prop: 'debtFromDate',
      label: '履行债务的期限自',
    },
    {
      prop: 'debtToDate',
      label: '履行债务的期限至',
    },
    {
      prop: 'guaranteeScope',
      label: '保证担保的范围',
    },
    {
      prop: 'guaranteeTermDictName',
      label: '保证的期间',
    },
    {
      prop: 'guaranteeTypeDictName',
      label: '保证的方式',
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
  permission: 'admin:web:dataCompanyAnnualReportForeignGuarantee:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyAnnualReportForeignGuaranteePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyAnnualReportForeignGuaranteePageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyAnnualReportForeignGuarantee:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyAnnualReportForeignGuaranteeManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyAnnualReportForeignGuarantee:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyAnnualReportForeignGuaranteeRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyAnnualReportForeignGuarantee:create" route="/admin/DataCompanyAnnualReportForeignGuaranteeManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyAnnualReportForeignGuaranteePageApi"
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