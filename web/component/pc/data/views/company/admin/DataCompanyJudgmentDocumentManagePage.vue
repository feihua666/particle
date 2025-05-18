<script setup name="DataCompanyJudgmentDocumentManagePage" lang="ts">
/**
 * 企业裁判文书管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyJudgmentDocumentPageApi, remove as dataCompanyJudgmentDocumentRemoveApi} from "../../../api/company/admin/dataCompanyJudgmentDocumentAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyJudgmentDocumentManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'caseNo',
      label: '案号',
    },
    {
      prop: 'caseReason',
      label: '案由',
    },
    {
      prop: 'caseCourtCompanyId',
      label: '案件审理法院公司id',
    },
    {
      prop: 'caseCourtName',
      label: '案件审理法院名称',
    },
    {
      prop: 'caseJudgeDate',
      label: '案件裁判日期',
    },
    {
      prop: 'caseTrialProcedure',
      label: '案件审理程序',
    },
    {
      prop: 'caseLegalBasis',
      label: '法律依据',
    },
    {
      prop: 'caseTypeDictName',
      label: '案件类型',
    },
    {
      prop: 'caseAmount',
      label: '案件涉及金额（万元）',
    },
    {
      prop: 'caseAmountCurrencyDictName',
      label: '案件涉及金额币种',
    },
    {
      prop: 'documentTypeDictName',
      label: '文书类型',
    },
    {
      prop: 'documentPublishDate',
      label: '文书发布日期',
    },
    {
      prop: 'documentPublishTitle',
      label: '文书发布标题',
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
  permission: 'admin:web:dataCompanyJudgmentDocument:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyJudgmentDocumentPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyJudgmentDocumentPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyJudgmentDocument:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyJudgmentDocumentManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyJudgmentDocument:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyJudgmentDocumentRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyJudgmentDocument:create" route="/admin/DataCompanyJudgmentDocumentManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyJudgmentDocumentPageApi"
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