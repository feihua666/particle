<script setup name="DataCompanyVcFinancingInvestInstitutionRelManagePage" lang="ts">
/**
 * 企业融资历史投资机构关系管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyVcFinancingInvestInstitutionRelPageApi, remove as dataCompanyVcFinancingInvestInstitutionRelRemoveApi} from "../../../api/company/admin/dataCompanyVcFinancingInvestInstitutionRelAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyVcFinancingInvestInstitutionRelManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'companyVcFinancingId',
      label: '企业融资表ID',
    },
    {
      prop: 'companyVcInvestInstitutionId',
      label: '企业投资机构表id',
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
  permission: 'admin:web:dataCompanyVcFinancingInvestInstitutionRel:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyVcFinancingInvestInstitutionRelPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyVcFinancingInvestInstitutionRelPageApi({...reactiveData.form,...pageQuery})
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
  let companyVcInvestInstitutionAssignCompanyVcFinancingRouteQuery = {companyVcInvestInstitutionId: row.companyVcInvestInstitutionId,companyVcInvestInstitutionName: row.companyVcInvestInstitutionName}
  let deleteByCompanyVcInvestInstitutionIdRouteQuery = {companyVcInvestInstitutionId: row.companyVcInvestInstitutionId,companyVcInvestInstitutionName: row.companyVcInvestInstitutionName}

  let companyVcFinancingAssignCompanyVcInvestInstitutionRouteQuery = {companyVcFinancingId: row.companyVcFinancingId,companyVcFinancingName: row.companyVcFinancingName}
  let deleteByCompanyVcFinancingIdRouteQuery = {companyVcFinancingId: row.companyVcFinancingId,companyVcFinancingName: row.companyVcFinancingName}

  let tableRowButtons = [
    {
      txt: '为该企业投资机构表分配企业融资表ID',
      text: true,
      permission: 'admin:web:dataCompanyVcFinancingInvestInstitutionRel:companyVcInvestInstitutionAssignCompanyVcFinancing',
      route: {path: '/admin/dataCompanyVcFinancingInvestInstitutionRelManageCompanyVcInvestInstitutionAssignCompanyVcFinancing',query: companyVcInvestInstitutionAssignCompanyVcFinancingRouteQuery}
    },
    {
      txt: '为该企业融资表ID分配企业投资机构表',
      text: true,
      permission: 'admin:web:dataCompanyVcFinancingInvestInstitutionRel:companyVcFinancingAssignCompanyVcInvestInstitution',
      route: {path: '/admin/dataCompanyVcFinancingInvestInstitutionRelManageCompanyVcFinancingAssignCompanyVcInvestInstitution',query: companyVcFinancingAssignCompanyVcInvestInstitutionRouteQuery}
    },
    {
      txt: '为该企业投资机构表清空企业融资表ID',
      text: true,
      methodConfirmText: `您将清空企业投资机构表 ${row.companyVcInvestInstitutionName} 所有企业融资表ID,该企业投资机构表将不再分配给任何企业融资表ID，同时拥有涉及对应企业融资表ID的用户企业投资机构表将受到影响，请谨慎操作！！！，确定要清空吗？`,
      permission: 'admin:web:dataCompanyVcFinancingInvestInstitutionRel:deleteByCompanyVcInvestInstitutionId',
      route: {path: '/admin/dataCompanyVcFinancingInvestInstitutionRelManageDeleteByCompanyVcInvestInstitutionId',query: deleteByCompanyVcInvestInstitutionIdRouteQuery}
    },
    {
      txt: '为该企业融资表ID清空企业投资机构表',
      text: true,
      methodConfirmText: `您将清空企业融资表ID ${row.companyVcFinancingName} 所有企业投资机构表,该企业融资表ID将不再拥有任何企业投资机构表，同时拥有该企业融资表ID的用户企业投资机构表将受到影响，请谨慎操作！！！，确定要清空吗？`,
      permission: 'admin:web:dataCompanyVcFinancingInvestInstitutionRel:deleteByCompanyVcFinancingId',
      route: {path: '/admin/dataCompanyVcFinancingInvestInstitutionRelManageDeleteByCompanyVcFinancingId',query: deleteByCompanyVcFinancingIdRouteQuery}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyVcFinancingInvestInstitutionRel:delete',
      methodConfirmText: `删除后企业融资表ID ${row.companyVcFinancingName} 将不再拥有企业投资机构表 ${row.companyVcInvestInstitutionName}，确定要删除吗？`,
      // 删除操作
      method(){
        return dataCompanyVcFinancingInvestInstitutionRelRemoveApi(idData).then(res => {
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
      <PtButton permission="admin:web:dataCompanyVcFinancingInvestInstitutionRel:create" route="/admin/DataCompanyVcFinancingInvestInstitutionRelManageAdd">添加</PtButton>
      <PtButton permission="admin:web:dataCompanyVcFinancingInvestInstitutionRel:companyVcInvestInstitutionAssignCompanyVcFinancing" route="/admin/dataCompanyVcFinancingInvestInstitutionRelManageCompanyVcInvestInstitutionAssignCompanyVcFinancing">企业投资机构表分配企业融资表ID</PtButton>
      <PtButton permission="admin:web:dataCompanyVcFinancingInvestInstitutionRel:companyVcFinancingAssignCompanyVcInvestInstitution" route="/admin/dataCompanyVcFinancingInvestInstitutionRelManageCompanyVcFinancingAssignCompanyVcInvestInstitution">企业融资表ID分配企业投资机构表</PtButton>

      <PtButton permission="admin:web:dataCompanyVcFinancingInvestInstitutionRel:deleteByCompanyVcInvestInstitutionId" route="/admin/dataCompanyVcFinancingInvestInstitutionRelManageDeleteByCompanyVcInvestInstitutionId">清空企业投资机构表企业融资表ID</PtButton>
      <PtButton permission="admin:web:dataCompanyVcFinancingInvestInstitutionRel:deleteByCompanyVcFinancingId" route="/admin/dataCompanyVcFinancingInvestInstitutionRelManageDeleteByCompanyVcFinancingId">清空企业融资表ID企业投资机构表</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyVcFinancingInvestInstitutionRelPageApi"
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
