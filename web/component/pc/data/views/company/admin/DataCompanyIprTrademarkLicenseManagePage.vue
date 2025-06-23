<script setup name="DataCompanyIprTrademarkLicenseManagePage" lang="ts">
/**
 * 企业知识产权商标许可信息管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyIprTrademarkLicensePageApi, remove as dataCompanyIprTrademarkLicenseRemoveApi} from "../../../api/company/admin/dataCompanyIprTrademarkLicenseAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyIprTrademarkLicenseManage";


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
      prop: 'licenseIssueNo',
      label: '许可期号',
    },
    {
      prop: 'licensePageNo',
      label: '许可页码',
    },
    {
      prop: 'licenseFilingNo',
      label: '许可备案号',
    },
    {
      prop: 'licenseFilingPublicDate',
      label: '许可备案公告日期',
    },
    {
      prop: 'licenseTerm',
      label: '许可期限',
    },
    {
      prop: 'licenseGoodService',
      label: '许可使用的商品服务',
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
  permission: 'admin:web:dataCompanyIprTrademarkLicense:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyIprTrademarkLicensePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyIprTrademarkLicensePageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyIprTrademarkLicense:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyIprTrademarkLicenseManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyIprTrademarkLicense:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyIprTrademarkLicenseRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyIprTrademarkLicense:create" route="/admin/DataCompanyIprTrademarkLicenseManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyIprTrademarkLicensePageApi"
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