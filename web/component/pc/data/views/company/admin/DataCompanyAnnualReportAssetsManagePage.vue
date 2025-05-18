<script setup name="DataCompanyAnnualReportAssetsManagePage" lang="ts">
/**
 * 企业资产状况信息管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyAnnualReportAssetsPageApi, remove as dataCompanyAnnualReportAssetsRemoveApi} from "../../../api/company/admin/dataCompanyAnnualReportAssetsAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyAnnualReportAssetsManage";


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
      prop: 'totalAssets',
      label: '资产总额（万元）',
    },
    {
      prop: 'totalAssetsCurrencyDictName',
      label: '资产总额币种',
    },
    {
      prop: 'totalOwnerEquity',
      label: '所有者权益合计（万元）',
    },
    {
      prop: 'totalOwnerEquityCurrencyDictName',
      label: '所有者权益合计币种',
    },
    {
      prop: 'totalSales',
      label: '销售总额(营业总收入)（万元）',
    },
    {
      prop: 'totalSalesCurrencyDictName',
      label: '销售总额币种',
    },
    {
      prop: 'totalProfit',
      label: '利润总额（万元）',
    },
    {
      prop: 'totalProfitCurrencyDictName',
      label: '利润总额币种',
    },
    {
      prop: 'primeBusProfit',
      label: '主营业务收入（万元）',
    },
    {
      prop: 'primeBusProfitCurrencyDictName',
      label: '主营业务收入币种',
    },
    {
      prop: 'retainedProfit',
      label: '净利润（万元）',
    },
    {
      prop: 'retainedProfitCurrencyDictName',
      label: '净利润币种',
    },
    {
      prop: 'totalTax',
      label: '纳税总额（万元）',
    },
    {
      prop: 'totalTaxCurrencyDictName',
      label: '纳税总额币种',
    },
    {
      prop: 'totalLiability',
      label: '负债总额（万元）',
    },
    {
      prop: 'totalLiabilityCurrencyDictName',
      label: '负债总额币种',
    },
    {
      prop: 'isTotalAssetsPublic',
      label: '是否资产总额公示',
    },
    {
      prop: 'isTotalOwnerEquityPublic',
      label: '是否所有者权益合计公示',
    },
    {
      prop: 'isTotalSalesPublic',
      label: '是否销售总额公示',
    },
    {
      prop: 'isTotalProfitPublic',
      label: '是否利润总额公示',
    },
    {
      prop: 'isPrimeBusProfitPublic',
      label: '是否主营业务收入公示',
    },
    {
      prop: 'isRetainedProfitPublic',
      label: '是否净利润公示',
    },
    {
      prop: 'isTotalTaxPublic',
      label: '是否纳税总额公示',
    },
    {
      prop: 'isTotalLiabilityPublic',
      label: '是否负债总额公示',
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
  permission: 'admin:web:dataCompanyAnnualReportAssets:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyAnnualReportAssetsPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyAnnualReportAssetsPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyAnnualReportAssets:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyAnnualReportAssetsManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyAnnualReportAssets:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyAnnualReportAssetsRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyAnnualReportAssets:create" route="/admin/DataCompanyAnnualReportAssetsManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyAnnualReportAssetsPageApi"
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