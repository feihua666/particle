<script setup name="DataCompanyIprPatentCitedManagePage" lang="ts">
/**
 * 企业知识产权专利被引证信息管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyIprPatentCitedPageApi, remove as dataCompanyIprPatentCitedRemoveApi} from "../../../api/company/admin/dataCompanyIprPatentCitedAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyIprPatentCitedManage";


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
      prop: 'applyNo',
      label: '原始申请号',
    },
    {
      prop: 'applyDate',
      label: '申请日期',
    },
    {
      prop: 'publicNo',
      label: '原始公布号',
    },
    {
      prop: 'publicDate',
      label: '公布日',
    },
    {
      prop: 'title',
      label: '标题',
    },
    {
      prop: 'quoteFrom',
      label: '引证来源',
    },
    {
      prop: 'quoteFromType',
      label: '引证来源类型',
    },
    {
      prop: 'citedFrom',
      label: '被引证来源',
    },
    {
      prop: 'applicantCitedNo',
      label: '被申请人引证标准号码',
    },
    {
      prop: 'examinerCitedNo',
      label: '被审查员引证标准号码',
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
  permission: 'admin:web:dataCompanyIprPatentCited:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyIprPatentCitedPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyIprPatentCitedPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyIprPatentCited:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyIprPatentCitedManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyIprPatentCited:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyIprPatentCitedRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyIprPatentCited:create" route="/admin/DataCompanyIprPatentCitedManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyIprPatentCitedPageApi"
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
