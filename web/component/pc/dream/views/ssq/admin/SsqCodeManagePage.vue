<script setup name="SsqCodeManagePage" lang="ts">
/**
 * 双色球号码管理页面
 */
import {reactive, ref} from 'vue'
import { page as ssqCodePageApi,allCodeInit,allCodeUpdate,allCodeStop} from "../../../api/ssq/admin/ssqCodeAdminApi"
import {pageFormItems} from "../../../components/ssq/admin/ssqCodeManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'seqNo',
      label: '序号',
    },
    {
      prop: 'redSeqNo',
      label: '红球序号',
    },
    {
      prop: 'red1',
      label: '红球1',
    },
    {
      prop: 'red2',
      label: '红球2',
    },
    {
      prop: 'red3',
      label: '红球3',
    },
    {
      prop: 'red4',
      label: '红球4',
    },
    {
      prop: 'red5',
      label: '红球5',
    },
    {
      prop: 'red6',
      label: '红球6',
    },
    {
      prop: 'blue',
      label: '蓝球',
    },
    {
      prop: 'redSum',
      label: '红球和值',
    },
    {
      prop: 'redSumLast',
      label: '红球和尾值',
    },
    {
      prop: 'redBlueSum',
      label: '总和值',
    },
    {
      prop: 'redBlueSumLast',
      label: '总和尾值',
    },
    {
      prop: 'redSpan',
      label: '红球跨度',
    },
    {
      prop: 'redAc',
      label: '红球ac值',
    },
    {
      prop: 'redRegion1Ratio',
      label: '红1区个数',
    },
    {
      prop: 'redRegion2Ratio',
      label: '红2区个数',
    },
    {
      prop: 'redRegion3Ratio',
      label: '红3区个数',
    },
    {
      prop: 'redOddRatio',
      label: '红奇数个数',
    },
    {
      prop: 'redEvenRatio',
      label: '红偶数个数',
    },
    {
      prop: 'isHasSerialNum',
      label: '包含连号',
      formatter: (row: any, column: any, cellValue: any, index: any) => {
        return cellValue ? '连号' : ''
      }
    },
    {
      prop: 'serialTimes',
      label: '红连号个数',
    },
    {
      prop: 'maxSerialLength',
      label: '连号最大长度',
    },
    {
      prop: 'isHasEvenSerialNum',
      label: '包含偶连号',
      formatter: (row: any, column: any, cellValue: any, index: any) => {
        return cellValue ? '偶连号' : ''
      }
    },
    {
      prop: 'evenSerialTimes',
      label: '偶红连号个数',
    },
    {
      prop: 'evenMaxSerialLength',
      label: '偶连号最大长度',
    },
    {
      prop: 'isRedIncludeBlue',
      label: '是否红蓝重号',
      formatter: (row: any, column: any, cellValue: any, index: any) => {
        return cellValue ? '红蓝重号' : ''
      }
    },
    {
      prop: 'isBlueOdd',
      label: '是否蓝球为奇数',
      formatter: (row: any, column: any, cellValue: any, index: any) => {
        return cellValue ? '奇' : ''
      }
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:ssqCode:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doSsqCodePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return ssqCodePageApi({...reactiveData.form,...pageQuery})
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
      <PtButton permission="admin:web:ssqCode:allCodeInit" type="primary" methodSuccess="恭喜，初始化添加完成！" :method="allCodeInit" methodConfirmText="确定要初始化所有号码吗？初始化目的是数据为空的情况下执行，否则可能需要执行更新操作，一般时间较长，页面会断开，但不影响数据去重，当总条数为17721008时代表完成">初始化添加所有号码</PtButton>
      <PtButton permission="admin:web:ssqCode:allCodeUpdate" type="primary" methodSuccess="恭喜，更新完成！" :method="allCodeUpdate" methodConfirmText="确定要更新所有号码吗？仅用于逻辑有误或新加字段情况下使用，否则不要随意更新，无意义！">更新所有号码</PtButton>
      <PtButton permission="admin:web:ssqCode:allCodeStop" type="primary" methodSuccess="恭喜，停止完成！" :method="allCodeStop" methodConfirmText="确定要停止正在运行的初始化或更新任务吗？停止仍可半继续运行，已经处理过的数据将会跳过">停止初始化或更新所有号码</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doSsqCodePageApi"
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