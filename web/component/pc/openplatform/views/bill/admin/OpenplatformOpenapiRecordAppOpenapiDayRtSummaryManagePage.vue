<script setup name="OpenplatformOpenapiRecordAppOpenapiDayRtSummaryManagePage" lang="ts">
/**
 * 开放平台应用开放接口日实时汇总管理页面
 */
import {reactive, ref} from 'vue'
import { page as openplatformOpenapiRecordAppOpenapiDayRtSummaryPageApi, remove as openplatformOpenapiRecordAppOpenapiDayRtSummaryRemoveApi} from "../../../api/bill/admin/openplatformOpenapiRecordAppOpenapiDayRtSummaryAdminApi"
import {pageFormItems} from "../../../components/bill/admin/openplatformOpenapiRecordAppOpenapiDayRtSummaryManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'openplatformAppName',
      label: '应用名称',
      showOverflowTooltip: true
    },
    {
      prop: 'appId',
      label: 'appId',
      showOverflowTooltip: true
    },
    {
      prop: 'openplatformOpenapiName',
      label: '开放平台接口名称',
      showOverflowTooltip: true
    },
    {
      prop: 'dayAt',
      label: '日期',
    },
    {
      prop: 'customerName',
      label: '客户名称',
      showOverflowTooltip: true
    },
    {
      prop: 'totalCall',
      label: '调用总量',
    },
    {
      prop: 'totalFeeCall',
      label: '调用计费总量',
    },
    {
      prop: 'averageUnitPriceAmount',
      label: '平均单价金额（分）',
    },
    {
      prop: 'totalFeeAmount',
      label: '总消费金额（分）',
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
  permission: 'admin:web:openplatformOpenapiRecordAppOpenapiDayRtSummary:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doOpenplatformOpenapiRecordAppOpenapiDayRtSummaryPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return openplatformOpenapiRecordAppOpenapiDayRtSummaryPageApi({...reactiveData.form,...pageQuery})
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
  //  注意这里不支持删除，删除后实时汇总的数据将会计算错误，因为都是累加上来的
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

  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doOpenplatformOpenapiRecordAppOpenapiDayRtSummaryPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"

           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">
  </PtTable>
<!-- 子级路由 -->
  <PtRouteViewPopover :level="3"></PtRouteViewPopover>
</template>


<style scoped>

</style>
