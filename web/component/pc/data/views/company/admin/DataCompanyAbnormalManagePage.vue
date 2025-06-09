<script setup name="DataCompanyAbnormalManagePage" lang="ts">
/**
 * 企业经营异常管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyAbnormalPageApi, remove as dataCompanyAbnormalRemoveApi} from "../../../api/company/admin/dataCompanyAbnormalAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyAbnormalManage";


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
      prop: 'putNo',
      label: '列入决定书文号',
    },
    {
      prop: 'putReason',
      label: '列入异常名录原因',
    },
    {
      prop: 'putDate',
      label: '列入异常名录日期',
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
      prop: 'removeNo',
      label: '移出决定书文号',
    },
    {
      prop: 'removeReason',
      label: '移出异常名录原因',
    },
    {
      prop: 'removeDate',
      label: '移出异常名录日期',
    },
    {
      prop: 'removeInstituteCompanyId',
      label: '作出移出决定机关公司id',
    },
    {
      prop: 'removeInstituteName',
      label: '作出移出决定机关名称',
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
  permission: 'admin:web:dataCompanyAbnormal:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyAbnormalPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyAbnormalPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyAbnormal:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyAbnormalManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyAbnormal:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyAbnormalRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyAbnormal:create" route="/admin/DataCompanyAbnormalManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyAbnormalPageApi"
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