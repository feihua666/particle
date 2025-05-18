<script setup name="DataCompanyAnnualReportSocialSecurityManagePage" lang="ts">
/**
 * 企业年报社保管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyAnnualReportSocialSecurityPageApi, remove as dataCompanyAnnualReportSocialSecurityRemoveApi} from "../../../api/company/admin/dataCompanyAnnualReportSocialSecurityAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyAnnualReportSocialSecurityManage";


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
      prop: 'endowmentInsurancePeopleNum',
      label: '城镇职工基本养老保险人数',
    },
    {
      prop: 'unemploymentInsurancePeopleNum',
      label: '失业保险人数',
    },
    {
      prop: 'medicalInsurancePeopleNum',
      label: '职工基本医疗保险人数',
    },
    {
      prop: 'employmentInjuryInsurancePeopleNum',
      label: '工伤保险人数',
    },
    {
      prop: 'maternityInsurancePeopleNum',
      label: '生育保险人数',
    },
    {
      prop: 'endowmentInsuranceBaseAmount',
      label: '单位参加城镇职工基本养老保险缴费基数',
    },
    {
      prop: 'unemploymentInsuranceBaseAmount',
      label: '单位参加失业保险缴费基数',
    },
    {
      prop: 'medicalInsuranceBaseAmount',
      label: '单位参加职工基本医疗保险缴费基数',
    },
    {
      prop: 'maternityInsuranceBaseAmount',
      label: '单位参加生育保险缴费基数',
    },
    {
      prop: 'endowmentInsurancePayAmount',
      label: '参加城镇职工基本养老保险本期实际缴费金额',
    },
    {
      prop: 'unemploymentInsurancePayAmount',
      label: '参加失业保险本期实际缴费金额',
    },
    {
      prop: 'medicalInsurancePayAmount',
      label: '参加职工基本医疗保险本期实际缴费金额',
    },
    {
      prop: 'employmentInjuryInsurancePayAmount',
      label: '参加工伤保险本期实际缴费金额',
    },
    {
      prop: 'maternityInsurancePayAmount',
      label: '参加生育保险本期实际缴费金额',
    },
    {
      prop: 'endowmentInsuranceOweAmount',
      label: '单位参加城镇职工基本养老保险累计欠缴金额',
    },
    {
      prop: 'unemploymentInsuranceOweAmount',
      label: '单位参加失业保险累计欠缴金额',
    },
    {
      prop: 'medicalInsuranceOweAmount',
      label: '单位参加职工基本医疗保险累计欠缴金额',
    },
    {
      prop: 'employmentInjuryInsuranceOweAmount',
      label: '单位参加工伤保险累计欠缴金额',
    },
    {
      prop: 'maternityInsuranceOweAmount',
      label: '单位参加生育保险累计欠缴金额',
    },
    {
      prop: 'isEndowmentInsuranceBaseAmountPublic',
      label: '是否单位参加城镇职工基本养老保险缴费基数公示',
    },
    {
      prop: 'isUnemploymentInsuranceBaseAmountPublic',
      label: '是否单位参加失业保险缴费基数公示',
    },
    {
      prop: 'isMedicalInsuranceBaseAmountPublic',
      label: '是否单位参加职工基本医疗保险缴费基数公示',
    },
    {
      prop: 'isMaternityInsuranceBaseAmountPublic',
      label: '是否单位参加生育保险缴费基数公示',
    },
    {
      prop: 'isEndowmentInsurancePayAmountPublic',
      label: '是否参加城镇职工基本养老保险本期实际缴费金额公示',
    },
    {
      prop: 'isUnemploymentInsurancePayAmountPublic',
      label: '是否参加失业保险本期实际缴费金额公示',
    },
    {
      prop: 'isMedicalInsurancePayAmountPublic',
      label: '是否参加职工基本医疗保险本期实际缴费金额公示',
    },
    {
      prop: 'isEmploymentInjuryInsurancePayAmountPublic',
      label: '是否参加工伤保险本期实际缴费金额公示',
    },
    {
      prop: 'isMaternityInsurancePayAmountPublic',
      label: '是否参加生育保险本期实际缴费金额公示',
    },
    {
      prop: 'isEndowmentInsuranceOweAmountPublic',
      label: '是否单位参加城镇职工基本养老保险累计欠缴金额公示',
    },
    {
      prop: 'isUnemploymentInsuranceOweAmountPublic',
      label: '是否单位参加失业保险累计欠缴金额公示',
    },
    {
      prop: 'isMedicalInsuranceOweAmountPublic',
      label: '是否单位参加职工基本医疗保险累计欠缴金额公示',
    },
    {
      prop: 'isEmploymentInjuryInsuranceOweAmountPublic',
      label: '是否单位参加工伤保险累计欠缴金额公示',
    },
    {
      prop: 'isMaternityInsuranceOweAmountPublic',
      label: '是否单位参加生育保险累计欠缴金额公示',
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
  permission: 'admin:web:dataCompanyAnnualReportSocialSecurity:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyAnnualReportSocialSecurityPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyAnnualReportSocialSecurityPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyAnnualReportSocialSecurity:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyAnnualReportSocialSecurityManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyAnnualReportSocialSecurity:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyAnnualReportSocialSecurityRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyAnnualReportSocialSecurity:create" route="/admin/DataCompanyAnnualReportSocialSecurityManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyAnnualReportSocialSecurityPageApi"
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