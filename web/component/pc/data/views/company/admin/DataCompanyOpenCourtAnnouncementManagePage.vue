<script setup name="DataCompanyOpenCourtAnnouncementManagePage" lang="ts">
/**
 * 企业开庭公告管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyOpenCourtAnnouncementPageApi, remove as dataCompanyOpenCourtAnnouncementRemoveApi} from "../../../api/company/admin/dataCompanyOpenCourtAnnouncementAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyOpenCourtAnnouncementManage";


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
      prop: 'caseNo',
      label: '案号',
    },
    {
      prop: 'caseReason',
      label: '案由',
    },
    {
      prop: 'courtName',
      label: '法院名称',
    },
    {
      prop: 'courtRoom',
      label: '法庭',
    },
    {
      prop: 'undertakeDept',
      label: '承办部门',
    },
    {
      prop: 'openDate',
      label: '开庭日期',
    },
    {
      prop: 'planDate',
      label: '排期日期',
    },
    {
      prop: 'presidingJudge',
      label: '审判长/主审人',
    },
    {
      prop: 'content',
      label: '公告内容',
    },
    {
      prop: 'videoUrl',
      label: '观看链接/视频链接',
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
  permission: 'admin:web:dataCompanyOpenCourtAnnouncement:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyOpenCourtAnnouncementPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyOpenCourtAnnouncementPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyOpenCourtAnnouncement:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyOpenCourtAnnouncementManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyOpenCourtAnnouncement:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyOpenCourtAnnouncementRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyOpenCourtAnnouncement:create" route="/admin/DataCompanyOpenCourtAnnouncementManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyOpenCourtAnnouncementPageApi"
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