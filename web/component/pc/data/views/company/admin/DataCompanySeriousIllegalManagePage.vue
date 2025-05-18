<script setup name="DataCompanySeriousIllegalManagePage" lang="ts">
/**
 * 企业严重违法管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanySeriousIllegalPageApi, remove as dataCompanySeriousIllegalRemoveApi} from "../../../api/company/admin/dataCompanySeriousIllegalAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanySeriousIllegalManage";


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
      prop: 'type',
      label: '类别',
    },
    {
      prop: 'putReason',
      label: '列入原因',
    },
    {
      prop: 'putDate',
      label: '列入日期',
    },
    {
      prop: 'putInstituteCompanyId',
      label: '作出列入决定机关公司id',
    },
    {
      prop: 'putInstituteName',
      label: '作出列入决定机关名称',
    },
    {
      prop: 'removeReason',
      label: '移除原因',
    },
    {
      prop: 'removeDate',
      label: '移除日期',
    },
    {
      prop: 'removeInstituteCompanyId',
      label: '作出移除决定机关公司id',
    },
    {
      prop: 'removeInstituteName',
      label: '作出移除决定机关名称',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:dataCompanySeriousIllegal:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanySeriousIllegalPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanySeriousIllegalPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanySeriousIllegal:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanySeriousIllegalManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanySeriousIllegal:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanySeriousIllegalRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanySeriousIllegal:create" route="/admin/DataCompanySeriousIllegalManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanySeriousIllegalPageApi"
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