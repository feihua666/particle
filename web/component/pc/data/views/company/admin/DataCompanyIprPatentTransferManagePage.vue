<script setup name="DataCompanyIprPatentTransferManagePage" lang="ts">
/**
 * 企业知识产权专利转让信息管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyIprPatentTransferPageApi, remove as dataCompanyIprPatentTransferRemoveApi} from "../../../api/company/admin/dataCompanyIprPatentTransferAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyIprPatentTransferManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'companyIprPatentId',
      label: '企业知识产权专利表id',
    },
    {
      prop: 'transferType',
      label: '转移类型',
    },
    {
      prop: 'dept',
      label: '部门',
    },
    {
      prop: 'changeBeforeRightHolder',
      label: '变更前权利人',
    },
    {
      prop: 'changeBeforeAddress',
      label: '变更前地址',
    },
    {
      prop: 'changeAfterRightHolder',
      label: '变更后权利人',
    },
    {
      prop: 'changeAfterAddress',
      label: '变更后地址',
    },
    {
      prop: 'currentRightHolder',
      label: '当前权利人',
    },
    {
      prop: 'currentAddress',
      label: '当前地址',
    },
    {
      prop: 'changeEffectiveDate',
      label: '变更生效日期',
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
  permission: 'admin:web:dataCompanyIprPatentTransfer:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyIprPatentTransferPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyIprPatentTransferPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyIprPatentTransfer:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyIprPatentTransferManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyIprPatentTransfer:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyIprPatentTransferRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyIprPatentTransfer:create" route="/admin/DataCompanyIprPatentTransferManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyIprPatentTransferPageApi"
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