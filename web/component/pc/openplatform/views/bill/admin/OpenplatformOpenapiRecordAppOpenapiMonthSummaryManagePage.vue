<script setup name="OpenplatformOpenapiRecordAppOpenapiMonthSummaryManagePage" lang="ts">
/**
 * 开放平台应用开放接口月汇总管理页面
 */
import {reactive, ref} from 'vue'
import {
  page as openplatformOpenapiRecordAppOpenapiMonthSummaryPageApi,
  remove as openplatformOpenapiRecordAppOpenapiMonthSummaryRemoveApi
} from "../../../api/bill/admin/openplatformOpenapiRecordAppOpenapiMonthSummaryAdminApi"
import {pageFormItems} from "../../../components/bill/admin/openplatformOpenapiRecordAppOpenapiMonthSummaryManage";


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
      prop: 'year',
      label: '年',
    },
    {
      prop: 'month',
      label: '月',
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
  permission: 'admin:web:openplatformOpenapiRecordAppOpenapiMonthSummary:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doOpenplatformOpenapiRecordAppOpenapiMonthSummaryPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return openplatformOpenapiRecordAppOpenapiMonthSummaryPageApi({...reactiveData.form,...pageQuery})
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
      txt: '删除',
      text: true,
      permission: 'admin:web:openplatformOpenapiRecordAppOpenapiMonthSummary:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return openplatformOpenapiRecordAppOpenapiMonthSummaryRemoveApi({id: row.id}).then(res => {
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
          labelWidth="80"
          :comps="reactiveData.formComps">
    <template #buttons>
      <PtButton permission="admin:web:openplatformOpenapiRecordAppOpenapiMonthSummary:lastMonthStatistic" route="/admin/openplatformOpenapiRecordAppOpenapiMonthSummaryLastMonthStatistic">统计上月数据</PtButton>
      <PtButton permission="admin:web:openplatformOpenapiRecordAppOpenapiMonthSummary:thisMonthStatistic" route="/admin/openplatformOpenapiRecordAppOpenapiMonthSummaryThisMonthStatistic">统计本月数据</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doOpenplatformOpenapiRecordAppOpenapiMonthSummaryPageApi"
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
