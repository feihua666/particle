<script setup name="DataCompanyJudgmentDebtorManagePage" lang="ts">
/**
 * 企业被执行人管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyJudgmentDebtorPageApi, remove as dataCompanyJudgmentDebtorRemoveApi} from "../../../api/company/admin/dataCompanyJudgmentDebtorAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyJudgmentDebtorManage";


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
      prop: 'executedPersonName',
      label: '被执行人名称',
    },
    {
      prop: 'isExecutedPersonNaturalPerson',
      label: '是否被执行人为自然人',
    },
    {
      prop: 'executedPersonCompanyId',
      label: '法人公司id',
    },
    {
      prop: 'executedPersonCompanyPersonId',
      label: '法人个人id',
    },
    {
      prop: 'executedPersonLicenseNo',
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
      prop: 'executeAmount',
      label: '执行标的金额（万元）',
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
  permission: 'admin:web:dataCompanyJudgmentDebtor:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyJudgmentDebtorPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyJudgmentDebtorPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyJudgmentDebtor:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyJudgmentDebtorManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyJudgmentDebtor:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyJudgmentDebtorRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyJudgmentDebtor:create" route="/admin/DataCompanyJudgmentDebtorManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyJudgmentDebtorPageApi"
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