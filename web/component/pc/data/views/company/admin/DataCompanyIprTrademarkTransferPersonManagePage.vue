<script setup name="DataCompanyIprTrademarkTransferPersonManagePage" lang="ts">
/**
 * 企业知识产权商标转让人管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyIprTrademarkTransferPersonPageApi, remove as dataCompanyIprTrademarkTransferPersonRemoveApi} from "../../../api/company/admin/dataCompanyIprTrademarkTransferPersonAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyIprTrademarkTransferPersonManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'companyIprTrademarkTransferId',
      label: '企业知识产权商标转让表id',
    },
    {
      prop: 'transferName',
      label: '原始转让人名称',
    },
    {
      prop: 'transferNameCn',
      label: '中文转让人名称',
    },
    {
      prop: 'transferNameEn',
      label: '英文转让人名称',
    },
    {
      prop: 'isTransferred',
      label: '是否被转让人',
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
  permission: 'admin:web:dataCompanyIprTrademarkTransferPerson:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyIprTrademarkTransferPersonPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyIprTrademarkTransferPersonPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyIprTrademarkTransferPerson:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyIprTrademarkTransferPersonManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyIprTrademarkTransferPerson:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyIprTrademarkTransferPersonRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyIprTrademarkTransferPerson:create" route="/admin/DataCompanyIprTrademarkTransferPersonManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyIprTrademarkTransferPersonPageApi"
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