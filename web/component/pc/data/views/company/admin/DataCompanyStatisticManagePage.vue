<script setup name="DataCompanyStatisticManagePage" lang="ts">
/**
 * 企业统计管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyStatisticPageApi, remove as dataCompanyStatisticRemoveApi} from "../../../api/company/admin/dataCompanyStatisticAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyStatisticManage";


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
      prop: 'establishYearNum',
      label: '成立年限数量',
    },
    {
      prop: 'historyNameNum',
      label: '历史名称数量',
    },
    {
      prop: 'listedNum',
      label: '上市板块数量',
    },
    {
      prop: 'listedDetailJson',
      label: '上市板块详情json',
    },
    {
      prop: 'annualReportNum',
      label: '年报数量',
    },
    {
      prop: 'abnormalNum',
      label: '经营异常数量',
    },
    {
      prop: 'abnormalDetailJson',
      label: '经营异常详情json',
    },
    {
      prop: 'caseFilingNum',
      label: '立案信息数量',
    },
    {
      prop: 'caseFilingDetailJson',
      label: '立案信息详情json',
    },
    {
      prop: 'courtAnnouncementNum',
      label: '法院公告数量',
    },
    {
      prop: 'courtAnnouncementDetailJson',
      label: '法院公告详情json',
    },
    {
      prop: 'honorQualificationNum',
      label: '荣誉资质数量',
    },
    {
      prop: 'honorQualificationDetailJson',
      label: '荣誉资质详情json',
    },
    {
      prop: 'iprPatentApplicantNum',
      label: '申请专利数量',
    },
    {
      prop: 'iprPatentRightNum',
      label: '权利专利数量',
    },
    {
      prop: 'discreditedJudgmentDebtorNum',
      label: '失信被执行人数量',
    },
    {
      prop: 'discreditedJudgmentDebtorDetailJson',
      label: '失信被执行人详情json',
    },
    {
      prop: 'judgmentDebtorNum',
      label: '被执行人数量',
    },
    {
      prop: 'judgmentDebtorDetailJson',
      label: '被执行人详情json',
    },
    {
      prop: 'judgmentDocumentNum',
      label: '判断文书数量',
    },
    {
      prop: 'judgmentDocumentDetailJson',
      label: '判断文书详情json',
    },
    {
      prop: 'openCourtAnnouncementNum',
      label: '开庭公告数量',
    },
    {
      prop: 'openCourtAnnouncementDetailJson',
      label: '开庭公告详情json',
    },
    {
      prop: 'punishmentNum',
      label: '行政处罚数量',
    },
    {
      prop: 'punishmentDetailJson',
      label: '行政处罚详情json',
    },
    {
      prop: 'restrictHighConsumeNum',
      label: '限制高消费数量',
    },
    {
      prop: 'restrictHighConsumeDetailJson',
      label: '限制高消费详情json',
    },
    {
      prop: 'seriousIllegalNum',
      label: '严重违法数量',
    },
    {
      prop: 'seriousIllegalDetailJson',
      label: '严重违法详情json',
    },
    {
      prop: 'shareholderNum',
      label: '股东数量',
    },
    {
      prop: 'shareholderDetailJson',
      label: '股东详情json',
    },
    {
      prop: 'vcFinancingNum',
      label: '融资数量',
    },
    {
      prop: 'vcFinancingDetailJson',
      label: '融资详情json',
    },
    {
      prop: 'vcProductNum',
      label: '融资产品数量',
    },
    {
      prop: 'trademarkNum',
      label: '商标数量',
    },
    {
      prop: 'trademarkDetailJson',
      label: '商标详情json',
    },
    {
      prop: 'softwareCopyrightNum',
      label: '软件著作权数量',
    },
    {
      prop: 'softwareCopyrightDetailJson',
      label: '软件著作权详情json',
    },
    {
      prop: 'workCopyrightNum',
      label: '作品著作权数量',
    },
    {
      prop: 'workCopyrightDetailJson',
      label: '作品著作权详情json',
    },
    {
      prop: 'geographicalIndicationNum',
      label: '地理标识数量',
    },
    {
      prop: 'geographicalIndicationDetailJson',
      label: '地理标识详情json',
    },
    {
      prop: 'integratedCircuitNum',
      label: '集成电路数量',
    },
    {
      prop: 'integratedCircuitDetailJson',
      label: '集成电路详情json',
    },
    {
      prop: 'plantVarietyNum',
      label: '植物新品种数量',
    },
    {
      prop: 'plantVarietyDetailJson',
      label: '植物新品种详情json',
    },
    {
      prop: 'administrativeLicenseNum',
      label: '行政许可数量',
    },
    {
      prop: 'administrativeLicenseDetailJson',
      label: '行政许可详情json',
    },
    {
      prop: 'deliveryAnnouncementNum',
      label: '送达公告数量',
    },
    {
      prop: 'deliveryAnnouncementDetailJson',
      label: '送达公告详情json',
    },
    {
      prop: 'endCaseNum',
      label: '终本案件数量',
    },
    {
      prop: 'endCaseDetailJson',
      label: '终本案件详情json',
    },
    {
      prop: 'equityPledgeNum',
      label: '股权出质数量',
    },
    {
      prop: 'equityPledgeDetailJson',
      label: '股权出质详情json',
    },
    {
      prop: 'iprPledgeNum',
      label: '知识产权出质数量',
    },
    {
      prop: 'iprPledgeDetailJson',
      label: '知识产权出质详情json',
    },
    {
      prop: 'mortgageChattelNum',
      label: '动产抵押数量',
    },
    {
      prop: 'mortgageChattelDetailJson',
      label: '动产抵押详情json',
    },
    {
      prop: 'primeStaffNum',
      label: '主要人员数量',
    },
    {
      prop: 'primeStaffDetailJson',
      label: '主要人员详情json',
    },
    {
      prop: 'spotCheckNum',
      label: '抽查检查数量',
    },
    {
      prop: 'spotCheckDetailJson',
      label: '抽查检查详情json',
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
  permission: 'admin:web:dataCompanyStatistic:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyStatisticPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyStatisticPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyStatistic:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyStatisticManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyStatistic:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyStatisticRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyStatistic:create" route="/admin/DataCompanyStatisticManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyStatisticPageApi"
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