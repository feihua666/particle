<script setup name="SsqCodeOpenedManagePage" lang="ts">
/**
 * 双色球开奖管理页面
 */
import {reactive, ref} from 'vue'
import { page as ssqCodeOpenedPageApi} from "../../../api/ssq/admin/ssqCodeOpenedAdminApi"
import {pageFormItems} from "../../../components/ssq/admin/ssqCodeOpenedManage";
import {allCodeInit, allCodeStop, allCodeUpdate} from "../../../api/ssq/admin/ssqCodeOpenedAdminApi";


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
      label: '号码序号',
    },
    {
      prop: 'redSeqNo',
      label: '红球号码序号',
    },
    {
      prop: 'openedDate',
      label: '开奖日期',
    },
    {
      prop: 'openedWeekDay',
      label: '开奖星期号',
    },
    {
      prop: 'openedPhase',
      label: '开奖期号',
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
      prop: 'openedRed1',
      label: '开奖红球1',
    },
    {
      prop: 'openedRed2',
      label: '开奖红球2',
    },
    {
      prop: 'openedRed3',
      label: '开奖红球3',
    },
    {
      prop: 'openedRed4',
      label: '开奖红球4',
    },
    {
      prop: 'openedRed5',
      label: '开奖红球5',
    },
    {
      prop: 'openedRed6',
      label: '开奖红球6',
    },
    {
      prop: 'openedBlue',
      label: '开奖蓝球',
    },
    {
      prop: 'win1Num',
      label: '中1等奖注数',
    },
    {
      prop: 'win1Amount',
      label: '中1等奖单注金额',
    },
    {
      prop: 'win1TotalAmount',
      label: '中1等奖总金额',
    },
    {
      prop: 'win2Num',
      label: '中2等奖注数',
    },
    {
      prop: 'win2Amount',
      label: '中2等奖单注金额',
    },
    {
      prop: 'win2TotalAmount',
      label: '中2等奖总金额',
    },
    {
      prop: 'win3Num',
      label: '中3等奖注数',
    },
    {
      prop: 'win3Amount',
      label: '中3等奖单注金额',
    },
    {
      prop: 'win3TotalAmount',
      label: '中3等奖总金额',
    },
    {
      prop: 'win4Num',
      label: '中4等奖注数',
    },
    {
      prop: 'win4Amount',
      label: '中4等奖单注金额',
    },
    {
      prop: 'win4TotalAmount',
      label: '中4等奖总金额',
    },
    {
      prop: 'win5Num',
      label: '中5等奖注数',
    },
    {
      prop: 'win5Amount',
      label: '中5等奖单注金额',
    },
    {
      prop: 'win5TotalAmount',
      label: '中5等奖总金额',
    },
    {
      prop: 'win6Num',
      label: '中6等奖注数',
    },
    {
      prop: 'win6Amount',
      label: '中6等奖单注金额',
    },
    {
      prop: 'win6TotalAmount',
      label: '中6等奖总金额',
    },
    {
      prop: 'winTotalAmount',
      label: '中奖总金额',
    },
    {
      prop: 'prizePoolAmount',
      label: '奖池金额',
    },
    {
      prop: 'nextPrizePoolAmount',
      label: '下期奖池金额',
    },
    {
      prop: 'saleAmount',
      label: '销售额',
    },
    {
      prop: 'redCold2Ratio',
      label: '红冷号个数',
    },
    {
      prop: 'redHot2Ratio',
      label: '红热号个数',
    },
    {
      prop: 'redCold3Ratio',
      label: '红冷号个数',
    },
    {
      prop: 'redHot3Ratio',
      label: '红热号个数',
    },
    {
      prop: 'redCold4Ratio',
      label: '红冷号个数',
    },
    {
      prop: 'redHot4Ratio',
      label: '红热号个数',
    },
    {
      prop: 'redCold5Ratio',
      label: '红冷号个数',
    },
    {
      prop: 'redHot5Ratio',
      label: '红热号个数',
    },
    {
      prop: 'redCold6Ratio',
      label: '红冷号个数',
    },
    {
      prop: 'redHot6Ratio',
      label: '红热号个数',
    },
    {
      prop: 'redCold7Ratio',
      label: '红冷号个数',
    },
    {
      prop: 'redHot7Ratio',
      label: '红热号个数',
    },
    {
      prop: 'redCold8Ratio',
      label: '红冷号个数',
    },
    {
      prop: 'redHot8Ratio',
      label: '红热号个数',
    },
    {
      prop: 'redCold9Ratio',
      label: '红冷号个数',
    },
    {
      prop: 'redHot9Ratio',
      label: '红热号个数',
    },
    {
      prop: 'redCold10Ratio',
      label: '红冷号个数',
    },
    {
      prop: 'redHot10Ratio',
      label: '红热号个数',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:ssqCodeOpened:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doSsqCodeOpenedPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return ssqCodeOpenedPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:ssqCodeOpened:update',
      // 跳转到编辑
      route: {path: '/admin/SsqCodeOpenedManageUpdate',query: idData}
    },

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
          labelWidth="100"
          :comps="reactiveData.formComps">
    <template #buttons>
      <PtButton permission="admin:web:ssqCodeOpened:allCodeInit" methodSuccess="恭喜，初始化添加完成！" :method="allCodeInit" methodConfirmText="确定要初始化所有号码吗？初始化不影响历史已经初始化的数据，当有新数据时会更新到最新">初始化添加所有开奖号码</PtButton>
      <PtButton permission="admin:web:ssqCodeOpened:allCodeStop" methodSuccess="恭喜，停止完成！" :method="allCodeStop" methodConfirmText="确定要停止正在运行的初始化任务吗？停止仍可半继续运行，已经处理过的数据将会跳过">停止初始化所有开奖号码</PtButton>
      <PtButton permission="admin:web:ssqCodeOpened:queryList" route="/admin/SsqCodeOpenedStatisticsPage">开奖统计</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doSsqCodeOpenedPageApi"
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
