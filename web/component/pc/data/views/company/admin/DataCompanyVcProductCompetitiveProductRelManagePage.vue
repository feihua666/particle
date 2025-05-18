<script setup name="DataCompanyVcProductCompetitiveProductRelManagePage" lang="ts">
/**
 * 企业融资产品竞品关系管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyVcProductCompetitiveProductRelPageApi, remove as dataCompanyVcProductCompetitiveProductRelRemoveApi} from "../../../api/company/admin/dataCompanyVcProductCompetitiveProductRelAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyVcProductCompetitiveProductRelManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'companyVcProductId',
      label: '企业融资产品表ID',
    },
    {
      prop: 'companyVcCompetitiveProductId',
      label: '企业竞品id',
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
  permission: 'admin:web:dataCompanyVcProductCompetitiveProductRel:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyVcProductCompetitiveProductRelPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyVcProductCompetitiveProductRelPageApi({...reactiveData.form,...pageQuery})
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
  let companyVcCompetitiveProductAssignCompanyVcProductRouteQuery = {companyVcCompetitiveProductId: row.companyVcCompetitiveProductId,companyVcCompetitiveProductName: row.companyVcCompetitiveProductName}
  let deleteByCompanyVcCompetitiveProductIdRouteQuery = {companyVcCompetitiveProductId: row.companyVcCompetitiveProductId,companyVcCompetitiveProductName: row.companyVcCompetitiveProductName}

  let companyVcProductAssignCompanyVcCompetitiveProductRouteQuery = {companyVcProductId: row.companyVcProductId,companyVcProductName: row.companyVcProductName}
  let deleteByCompanyVcProductIdRouteQuery = {companyVcProductId: row.companyVcProductId,companyVcProductName: row.companyVcProductName}

  let tableRowButtons = [
    {
      txt: '为该企业竞品分配企业融资产品表ID',
      text: true,
      permission: 'admin:web:dataCompanyVcProductCompetitiveProductRel:companyVcCompetitiveProductAssignCompanyVcProduct',
      route: {path: '/admin/dataCompanyVcProductCompetitiveProductRelManageCompanyVcCompetitiveProductAssignCompanyVcProduct',query: companyVcCompetitiveProductAssignCompanyVcProductRouteQuery}
    },
    {
      txt: '为该企业融资产品表ID分配企业竞品',
      text: true,
      permission: 'admin:web:dataCompanyVcProductCompetitiveProductRel:companyVcProductAssignCompanyVcCompetitiveProduct',
      route: {path: '/admin/dataCompanyVcProductCompetitiveProductRelManageCompanyVcProductAssignCompanyVcCompetitiveProduct',query: companyVcProductAssignCompanyVcCompetitiveProductRouteQuery}
    },
    {
      txt: '为该企业竞品清空企业融资产品表ID',
      text: true,
      methodConfirmText: `您将清空企业竞品 ${row.companyVcCompetitiveProductName} 所有企业融资产品表ID,该企业竞品将不再分配给任何企业融资产品表ID，同时拥有涉及对应企业融资产品表ID的用户企业竞品将受到影响，请谨慎操作！！！，确定要清空吗？`,
      permission: 'admin:web:dataCompanyVcProductCompetitiveProductRel:deleteByCompanyVcCompetitiveProductId',
      route: {path: '/admin/dataCompanyVcProductCompetitiveProductRelManageDeleteByCompanyVcCompetitiveProductId',query: deleteByCompanyVcCompetitiveProductIdRouteQuery}
    },
    {
      txt: '为该企业融资产品表ID清空企业竞品',
      text: true,
      methodConfirmText: `您将清空企业融资产品表ID ${row.companyVcProductName} 所有企业竞品,该企业融资产品表ID将不再拥有任何企业竞品，同时拥有该企业融资产品表ID的用户企业竞品将受到影响，请谨慎操作！！！，确定要清空吗？`,
      permission: 'admin:web:dataCompanyVcProductCompetitiveProductRel:deleteByCompanyVcProductId',
      route: {path: '/admin/dataCompanyVcProductCompetitiveProductRelManageDeleteByCompanyVcProductId',query: deleteByCompanyVcProductIdRouteQuery}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyVcProductCompetitiveProductRel:delete',
      methodConfirmText: `删除后企业融资产品表ID ${row.companyVcProductName} 将不再拥有企业竞品 ${row.companyVcCompetitiveProductName}，确定要删除吗？`,
      // 删除操作
      method(){
        return dataCompanyVcProductCompetitiveProductRelRemoveApi(idData).then(res => {
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
      <PtButton permission="admin:web:dataCompanyVcProductCompetitiveProductRel:create" route="/admin/DataCompanyVcProductCompetitiveProductRelManageAdd">添加</PtButton>
      <PtButton permission="admin:web:dataCompanyVcProductCompetitiveProductRel:companyVcCompetitiveProductAssignCompanyVcProduct" route="/admin/dataCompanyVcProductCompetitiveProductRelManageCompanyVcCompetitiveProductAssignCompanyVcProduct">企业竞品分配企业融资产品表ID</PtButton>
      <PtButton permission="admin:web:dataCompanyVcProductCompetitiveProductRel:companyVcProductAssignCompanyVcCompetitiveProduct" route="/admin/dataCompanyVcProductCompetitiveProductRelManageCompanyVcProductAssignCompanyVcCompetitiveProduct">企业融资产品表ID分配企业竞品</PtButton>

      <PtButton permission="admin:web:dataCompanyVcProductCompetitiveProductRel:deleteByCompanyVcCompetitiveProductId" route="/admin/dataCompanyVcProductCompetitiveProductRelManageDeleteByCompanyVcCompetitiveProductId">清空企业竞品企业融资产品表ID</PtButton>
      <PtButton permission="admin:web:dataCompanyVcProductCompetitiveProductRel:deleteByCompanyVcProductId" route="/admin/dataCompanyVcProductCompetitiveProductRelManageDeleteByCompanyVcProductId">清空企业融资产品表ID企业竞品</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyVcProductCompetitiveProductRelPageApi"
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