<script setup name="DataCompanyDiscreditedJudgmentDebtorManagePage" lang="ts">
/**
 * 企业失信被执行人管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyDiscreditedJudgmentDebtorPageApi, remove as dataCompanyDiscreditedJudgmentDebtorRemoveApi} from "../../../api/company/admin/dataCompanyDiscreditedJudgmentDebtorAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyDiscreditedJudgmentDebtorManage";


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
      prop: 'caseNo',
      label: '案号',
    },
    {
      prop: 'dishonestExecutedPersonName',
      label: '被执行人名称',
    },
    {
      prop: 'isDishonestExecutedPersonNaturalPerson',
      label: '是否被执行人为自然人',
    },
    {
      prop: 'dishonestExecutedPersonCompanyId',
      label: '被执行人公司id',
    },
    {
      prop: 'dishonestExecutedPersonCompanyPersonId',
      label: '被执行人个人id',
    },
    {
      prop: 'legalPersonName',
      label: '法人名称',
    },
    {
      prop: 'isLegalPersonNaturalPerson',
      label: '是否法人为自然人',
    },
    {
      prop: 'legalPersonCompanyId',
      label: '法人公司id',
    },
    {
      prop: 'legalPersonCompanyPersonId',
      label: '法人个人id',
    },
    {
      prop: 'dishonestExecutedPersonLicenseNo',
      label: '被执行人证照/证件号码',
    },
    {
      prop: 'executeCourtCompanyId',
      label: '执行法院公司id',
    },
    {
      prop: 'executeCourtName',
      label: '执行法院名称',
    },
    {
      prop: 'fileCaseDate',
      label: '立案日期',
    },
    {
      prop: 'finishedCaseDate',
      label: '结案日期',
    },
    {
      prop: 'isFinished',
      label: '是否已结案',
    },
    {
      prop: 'publishDate',
      label: '发布日期',
    },
    {
      prop: 'documentNo',
      label: '执行依据文号',
    },
    {
      prop: 'decisionBasisDeptCompanyId',
      label: '做出执行的依据单位公司id',
    },
    {
      prop: 'decisionBasisDeptName',
      label: '做出执行的依据单位',
    },
    {
      prop: 'obligation',
      label: '生效法律文书确定的义务',
    },
    {
      prop: 'performance',
      label: '履行情况',
    },
    {
      prop: 'performPart',
      label: '已履行',
    },
    {
      prop: 'unPerformPart',
      label: '未履行',
    },
    {
      prop: 'dishonestExecutedPersonBehavior',
      label: '失信被执行人行为具体情形',
    },
    {
      prop: 'executeAmount',
      label: '执行标的金额(万元)',
    },
    {
      prop: 'executeAmountCurrencyDictName',
      label: '执行标的金额币种',
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
  permission: 'admin:web:dataCompanyDiscreditedJudgmentDebtor:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyDiscreditedJudgmentDebtorPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyDiscreditedJudgmentDebtorPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyDiscreditedJudgmentDebtor:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyDiscreditedJudgmentDebtorManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyDiscreditedJudgmentDebtor:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyDiscreditedJudgmentDebtorRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyDiscreditedJudgmentDebtor:create" route="/admin/DataCompanyDiscreditedJudgmentDebtorManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyDiscreditedJudgmentDebtorPageApi"
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
