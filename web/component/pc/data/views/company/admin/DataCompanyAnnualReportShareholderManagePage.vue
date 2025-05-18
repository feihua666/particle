<script setup name="DataCompanyAnnualReportShareholderManagePage" lang="ts">
/**
 * 企业年报股东管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyAnnualReportShareholderPageApi, remove as dataCompanyAnnualReportShareholderRemoveApi} from "../../../api/company/admin/dataCompanyAnnualReportShareholderAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyAnnualReportShareholderManage";


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
      prop: ' serialNumber',
      label: '序号',
    },
    {
      prop: 'shareholderName',
      label: '股东名称',
    },
    {
      prop: 'isShareholderNaturalPerson',
      label: '是否股东名称为自然人',
    },
    {
      prop: 'shareholderCompanyId',
      label: '股东名称公司id',
    },
    {
      prop: 'shareholderCompanyPersonId',
      label: '股东名称个人id',
    },
    {
      prop: 'shareholdingPercent',
      label: '持股比例',
    },
    {
      prop: 'subCapital',
      label: '认缴出资金额（万元）',
    },
    {
      prop: 'subCapitalCurrencyDictName',
      label: '认缴出资金额币种',
    },
    {
      prop: 'subCapitalTypeDictName',
      label: '认缴出资方式',
    },
    {
      prop: 'subCapitalDate',
      label: '认缴出资日期',
    },
    {
      prop: 'actualCapital',
      label: '实缴出资金额（万元）',
    },
    {
      prop: 'actualCapitalCurrencyDictName',
      label: '实缴出资金额币种',
    },
    {
      prop: 'actualCapitalDate',
      label: '实缴出资日期',
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
  permission: 'admin:web:dataCompanyAnnualReportShareholder:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyAnnualReportShareholderPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyAnnualReportShareholderPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyAnnualReportShareholder:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyAnnualReportShareholderManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyAnnualReportShareholder:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyAnnualReportShareholderRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyAnnualReportShareholder:create" route="/admin/DataCompanyAnnualReportShareholderManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyAnnualReportShareholderPageApi"
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
