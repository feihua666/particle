<script setup name="DataCompanyPunishmentManagePage" lang="ts">
/**
 * 企业行政处罚管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyPunishmentPageApi, remove as dataCompanyPunishmentRemoveApi} from "../../../api/company/admin/dataCompanyPunishmentAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyPunishmentManage";


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
      prop: 'companyName',
      label: '企业名称',
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
      prop: 'punishNo',
      label: '行政处罚决定书文号',
    },
    {
      prop: 'illegalType',
      label: '违法行为类型 ',
    },
    {
      prop: 'punishBasis',
      label: '处罚依据',
    },
    {
      prop: 'illegalFact',
      label: '违法事实',
    },
    {
      prop: 'punishType',
      label: '处罚类别',
    },
    {
      prop: 'punishContent',
      label: '处罚内容',
    },
    {
      prop: 'fineAmount',
      label: '罚款金额（万元）',
    },
    {
      prop: 'fineAmountCurrencyDictName',
      label: '罚款金额币种',
    },
    {
      prop: 'confiscateAmount',
      label: '没收金额（万元）',
    },
    {
      prop: 'confiscateAmountCurrencyDictName',
      label: '没收金额币种',
    },
    {
      prop: 'instituteCompanyId',
      label: '作出行政处罚决定机关公司id',
    },
    {
      prop: 'instituteName',
      label: '作出行政处罚决定机关名称',
    },
    {
      prop: 'suspendOrRevokeLicenseNameCode',
      label: '暂扣或吊销证照名称及编号',
    },
    {
      prop: 'punishDecisionStartDate',
      label: '作出行政处罚决定开始日期',
    },
    {
      prop: 'punishDecisionEndDate',
      label: '作出行政处罚决定结束日期',
    },
    {
      prop: 'publishStartDate',
      label: '发布开始日期',
    },
    {
      prop: 'publishEndDate',
      label: '发布结束日期',
    },
    {
      prop: 'dataFrom',
      label: '数据来源',
    },
    {
      prop: 'dataFromCompanyId',
      label: '数据来源公司id',
    },
    {
      prop: 'dataFromName',
      label: '数据来源名称',
    },
    {
      prop: 'remark',
      label: '备注',
    },
    {
      prop: 'isDataFlagGs',
      label: '是否数据标识为工商公示',
    },
    {
      prop: 'isDataFlagXyzg',
      label: '是否数据标识为信用中国',
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
  permission: 'admin:web:dataCompanyPunishment:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyPunishmentPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyPunishmentPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyPunishment:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyPunishmentManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyPunishment:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyPunishmentRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyPunishment:create" route="/admin/DataCompanyPunishmentManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyPunishmentPageApi"
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