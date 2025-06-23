<script setup name="DataCompanyAdministrativeLicenseManagePage" lang="ts">
/**
 * 企业行政许可管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyAdministrativeLicensePageApi, remove as dataCompanyAdministrativeLicenseRemoveApi} from "../../../api/company/admin/dataCompanyAdministrativeLicenseAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyAdministrativeLicenseManage";


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
      prop: 'licenceNo',
      label: '许可编号 ',
    },
    {
      prop: 'licenceName',
      label: '许可证书名称',
    },
    {
      prop: 'decisionDocumentNo',
      label: '行政许可决定文书号',
    },
    {
      prop: 'decisionDocumentName',
      label: '许可证决定文书名称',
    },
    {
      prop: 'decisionDate',
      label: '许可决定日期',
    },
    {
      prop: 'fromDate',
      label: '许可开始日期',
    },
    {
      prop: 'endDate',
      label: '许可截止日期',
    },
    {
      prop: 'licenceContent',
      label: '许可内容',
    },
    {
      prop: 'institute',
      label: '许可机关',
    },
    {
      prop: 'instituteUscc',
      label: '许可机关统一社会信用代码 ',
    },
    {
      prop: 'instituteCompanyId',
      label: '许可机关公司id',
    },
    {
      prop: 'dataFrom',
      label: '数据来源单位 ',
    },
    {
      prop: 'dataFromUscc',
      label: '数据来源单位统一社会信用代码',
    },
    {
      prop: 'dataFromCompanyId',
      label: '数据来源单位公司id',
    },
    {
      prop: 'licenceType',
      label: '许可类型',
    },
    {
      prop: 'licenceRemark',
      label: '许可备注',
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
  permission: 'admin:web:dataCompanyAdministrativeLicense:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyAdministrativeLicensePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyAdministrativeLicensePageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyAdministrativeLicense:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyAdministrativeLicenseManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyAdministrativeLicense:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyAdministrativeLicenseRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyAdministrativeLicense:create" route="/admin/DataCompanyAdministrativeLicenseManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyAdministrativeLicensePageApi"
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