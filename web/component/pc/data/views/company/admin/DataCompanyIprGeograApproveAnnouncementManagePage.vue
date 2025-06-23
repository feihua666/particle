<script setup name="DataCompanyIprGeograApproveAnnouncementManagePage" lang="ts">
/**
 * 企业知识产权地理标识核准公告管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyIprGeograApproveAnnouncementPageApi, remove as dataCompanyIprGeograApproveAnnouncementRemoveApi} from "../../../api/company/admin/dataCompanyIprGeograApproveAnnouncementAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyIprGeograApproveAnnouncementManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'companyIprGeograId',
      label: '企业知识产权地理标识id',
    },
    {
      prop: 'approvePublicNo',
      label: '核准公告编号',
    },
    {
      prop: 'companyName',
      label: '企业名称',
    },
    {
      prop: 'approveAddress',
      label: '核准地址',
    },
    {
      prop: 'approveLegalPersonName',
      label: '核准法人代表',
    },
    {
      prop: 'productName',
      label: '产品名称',
    },
    {
      prop: 'approveTrademark',
      label: '核准商标',
    },
    {
      prop: 'approveDate',
      label: '核准日期',
    },
    {
      prop: 'approveRemark',
      label: '核准备注',
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
  permission: 'admin:web:dataCompanyIprGeograApproveAnnouncement:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyIprGeograApproveAnnouncementPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyIprGeograApproveAnnouncementPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyIprGeograApproveAnnouncement:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyIprGeograApproveAnnouncementManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyIprGeograApproveAnnouncement:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyIprGeograApproveAnnouncementRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyIprGeograApproveAnnouncement:create" route="/admin/DataCompanyIprGeograApproveAnnouncementManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyIprGeograApproveAnnouncementPageApi"
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