<script setup name="DataCompanyIprPlantVarietyChangeManagePage" lang="ts">
/**
 * 企业知识产权植物新品种变更信息管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyIprPlantVarietyChangePageApi, remove as dataCompanyIprPlantVarietyChangeRemoveApi} from "../../../api/company/admin/dataCompanyIprPlantVarietyChangeAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyIprPlantVarietyChangeManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'companyIprPlantVarietyId',
      label: '企业知识产权植物新品种表id',
    },
    {
      prop: 'changeDate',
      label: '变更日期',
    },
    {
      prop: 'changeBefore',
      label: '变更前',
    },
    {
      prop: 'changeAfter',
      label: '变更后',
    },
    {
      prop: 'changeReason',
      label: '原因',
    },
    {
      prop: 'isName',
      label: '是否名称变更',
    },
    {
      prop: 'isTransfer',
      label: '是否转让变更',
    },
    {
      prop: 'isCultivate',
      label: '是否培育人变更',
    },
    {
      prop: 'isApplicant',
      label: '是否申请人变更',
    },
    {
      prop: 'isAgent',
      label: '是否代理人变更',
    },
    {
      prop: 'isAgency',
      label: '是否代理机构变更',
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
  permission: 'admin:web:dataCompanyIprPlantVarietyChange:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyIprPlantVarietyChangePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyIprPlantVarietyChangePageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyIprPlantVarietyChange:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyIprPlantVarietyChangeManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyIprPlantVarietyChange:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyIprPlantVarietyChangeRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyIprPlantVarietyChange:create" route="/admin/DataCompanyIprPlantVarietyChangeManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyIprPlantVarietyChangePageApi"
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