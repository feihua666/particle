<script setup name="DataCompanyIprTrademarkPledgeManagePage" lang="ts">
/**
 * 企业知识产权商标质押信息管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyIprTrademarkPledgePageApi, remove as dataCompanyIprTrademarkPledgeRemoveApi} from "../../../api/company/admin/dataCompanyIprTrademarkPledgeAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyIprTrademarkPledgeManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'companyIprTrademarkId',
      label: '企业知识产权商标表id',
    },
    {
      prop: 'pledgor',
      label: '出质人',
    },
    {
      prop: 'pledgee',
      label: '质权人',
    },
    {
      prop: 'pledgeRegStartDate',
      label: '质权登记起始日期',
    },
    {
      prop: 'pledgeRegEndDate',
      label: '质权登记截止日期',
    },
    {
      prop: 'pledgePublicPageNo',
      label: '质权公告页号',
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
  permission: 'admin:web:dataCompanyIprTrademarkPledge:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyIprTrademarkPledgePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyIprTrademarkPledgePageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyIprTrademarkPledge:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyIprTrademarkPledgeManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyIprTrademarkPledge:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyIprTrademarkPledgeRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyIprTrademarkPledge:create" route="/admin/DataCompanyIprTrademarkPledgeManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyIprTrademarkPledgePageApi"
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