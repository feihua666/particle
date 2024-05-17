<script setup name="SsqCodeOpenedManagePage" lang="ts">
/**
 * 双色球开奖管理页面
 */
import {reactive, ref} from 'vue'
import { page as ssqCodeOpenedPageApi, remove as ssqCodeOpenedRemoveApi} from "../../../api/ssq/admin/ssqCodeOpenedAdminApi"
import {pageFormItems} from "../../../compnents/ssq/admin/ssqCodeOpenedManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'ssqCodeId',
      label: '双色球号码id',
    },
    {
      prop: 'openedDate',
      label: '开奖日期',
    },
    {
      prop: 'openedPhaseYear',
      label: '开奖期号年份',
    },
    {
      prop: 'openedPhaseNum',
      label: '开奖期号数',
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
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:ssqCodeOpened:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return ssqCodeOpenedRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:ssqCodeOpened:create" route="/admin/SsqCodeOpenedManageAdd">添加</PtButton>
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