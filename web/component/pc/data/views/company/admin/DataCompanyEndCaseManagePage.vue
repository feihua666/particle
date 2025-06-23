<script setup name="DataCompanyEndCaseManagePage" lang="ts">
/**
 * 企业终本案件管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyEndCasePageApi, remove as dataCompanyEndCaseRemoveApi} from "../../../api/company/admin/dataCompanyEndCaseAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyEndCaseManage";


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
      prop: 'courtName',
      label: '法院名称',
    },
    {
      prop: 'courtCompanyId',
      label: '法院名称公司id',
    },
    {
      prop: 'fileCaseDate',
      label: '立案日期',
    },
    {
      prop: 'finishedCaseDate',
      label: '结束日期',
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
      prop: 'unPerformAmount',
      label: '未履行金额（万元）',
    },
    {
      prop: 'unPerformAmountCurrencyDictName',
      label: '未履行金额币种',
    },
    {
      prop: 'dataMd5',
      label: '数据md5',
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
  permission: 'admin:web:dataCompanyEndCase:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyEndCasePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyEndCasePageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyEndCase:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyEndCaseManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyEndCase:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyEndCaseRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyEndCase:create" route="/admin/DataCompanyEndCaseManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyEndCasePageApi"
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