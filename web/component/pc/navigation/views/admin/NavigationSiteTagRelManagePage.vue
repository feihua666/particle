<script setup name="NavigationSiteTagRelManagePage" lang="ts">
/**
 * 导航网站标签关系管理页面
 */
import {reactive, ref} from 'vue'
import { page as navigationSiteTagRelPageApi, remove as navigationSiteTagRelRemoveApi} from "../../api/admin/navigationSiteTagRelAdminApi"
import {pageFormItems} from "../../components/admin/navigationSiteTagRelManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'navigationSiteName',
      label: '导航网站',
      width: 150,
      showOverflowTooltip: true
    },
    {
      prop: 'navigationSiteTagName',
      label: '网站标签',
      width: 150,
      showOverflowTooltip: true
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:navigationSiteTagRel:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doNavigationSiteTagRelPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return navigationSiteTagRelPageApi({...reactiveData.form,...pageQuery})
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
  let navigationSiteTagAssignNavigationSiteRouteQuery = {navigationSiteTagId: row.navigationSiteTagId,navigationSiteTagName: row.navigationSiteTagName}
  let deleteByNavigationSiteTagIdRouteQuery = {navigationSiteTagId: row.navigationSiteTagId,navigationSiteTagName: row.navigationSiteTagName}

  let navigationSiteAssignNavigationSiteTagRouteQuery = {navigationSiteId: row.navigationSiteId,navigationSiteName: row.navigationSiteName}
  let deleteByNavigationSiteIdRouteQuery = {navigationSiteId: row.navigationSiteId,navigationSiteName: row.navigationSiteName}

  let tableRowButtons = [
    {
      txt: '为该网站标签分配网站',
      text: true,
      permission: 'admin:web:navigationSiteTagRel:navigationSiteTagAssignNavigationSite',
      route: {path: '/admin/navigationSiteTagRelManageNavigationSiteTagAssignNavigationSite',query: navigationSiteTagAssignNavigationSiteRouteQuery}
    },
    {
      txt: '为该网站分配网站标签',
      text: true,
      permission: 'admin:web:navigationSiteTagRel:navigationSiteAssignNavigationSiteTag',
      route: {path: '/admin/navigationSiteTagRelManageNavigationSiteAssignNavigationSiteTag',query: navigationSiteAssignNavigationSiteTagRouteQuery}
    },
    {
      txt: '为该网站标签清空网站',
      text: true,
      methodConfirmText: `您将清空网站标签 ${row.navigationSiteTagName} 所有网站,该网站标签将不再分配给任何网站，同时拥有涉及对应网站的用户网站标签将受到影响，请谨慎操作！！！，确定要清空吗？`,
      permission: 'admin:web:navigationSiteTagRel:deleteByNavigationSiteTagId',
      route: {path: '/admin/navigationSiteTagRelManageDeleteByNavigationSiteTagId',query: deleteByNavigationSiteTagIdRouteQuery}
    },
    {
      txt: '为该网站清空网站标签',
      text: true,
      methodConfirmText: `您将清空网站 ${row.navigationSiteName} 所有网站标签,该网站将不再拥有任何网站标签，同时拥有该网站的用户网站标签将受到影响，请谨慎操作！！！，确定要清空吗？`,
      permission: 'admin:web:navigationSiteTagRel:deleteByNavigationSiteId',
      route: {path: '/admin/navigationSiteTagRelManageDeleteByNavigationSiteId',query: deleteByNavigationSiteIdRouteQuery}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:navigationSiteTagRel:delete',
      methodConfirmText: `删除后网站 ${row.navigationSiteName} 将不再拥有网站标签 ${row.navigationSiteTagName}，确定要删除吗？`,
      // 删除操作
      method(){
        return navigationSiteTagRelRemoveApi(idData).then(res => {
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
      <PtButton permission="admin:web:navigationSiteTagRel:create" route="/admin/NavigationSiteTagRelManageAdd">添加</PtButton>
      <PtButton permission="admin:web:navigationSiteTagRel:navigationSiteTagAssignNavigationSite" route="/admin/navigationSiteTagRelManageNavigationSiteTagAssignNavigationSite">网站标签分配网站</PtButton>
      <PtButton permission="admin:web:navigationSiteTagRel:navigationSiteAssignNavigationSiteTag" route="/admin/navigationSiteTagRelManageNavigationSiteAssignNavigationSiteTag">网站分配网站标签</PtButton>

      <PtButton permission="admin:web:navigationSiteTagRel:deleteByNavigationSiteTagId" route="/admin/navigationSiteTagRelManageDeleteByNavigationSiteTagId">清空网站标签网站</PtButton>
      <PtButton permission="admin:web:navigationSiteTagRel:deleteByNavigationSiteId" route="/admin/navigationSiteTagRelManageDeleteByNavigationSiteId">清空网站网站标签</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doNavigationSiteTagRelPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"

           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作">
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
