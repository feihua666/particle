<script setup name="NavigationSiteCategoryRelManagePage" lang="ts">
/**
 * 导航网站分类关系管理页面
 */
import {reactive, ref} from 'vue'
import { page as navigationSiteCategoryRelPageApi, remove as navigationSiteCategoryRelRemoveApi} from "../../api/admin/navigationSiteCategoryRelAdminApi"
import {pageFormItems} from "../../components/admin/navigationSiteCategoryRelManage";


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
      prop: 'navigationCategoryName',
      label: '导航分类',
      width: 150,
      showOverflowTooltip: true
    },
    {
      prop: 'seq',
      label: '排序',
      width: 100
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:navigationSiteCategoryRel:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doNavigationSiteCategoryRelPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return navigationSiteCategoryRelPageApi({...reactiveData.form,...pageQuery})
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
  let idDataEdit = {id: row.id,navigationSiteId: row.navigationSiteId,navigationSiteName: row.navigationSiteName}
  let navigationCategoryAssignNavigationSiteRouteQuery = {navigationCategoryId: row.navigationCategoryId,navigationCategoryName: row.navigationCategoryName}
  let deleteByNavigationCategoryIdRouteQuery = {navigationCategoryId: row.navigationCategoryId,navigationCategoryName: row.navigationCategoryName}

  let navigationSiteAssignNavigationCategoryRouteQuery = {navigationSiteId: row.navigationSiteId,navigationSiteName: row.navigationSiteName}
  let deleteByNavigationSiteIdRouteQuery = {navigationSiteId: row.navigationSiteId,navigationSiteName: row.navigationSiteName}

  let tableRowButtons = [
    {
      txt: '为该导航分类分配导航网站',
      text: true,
      permission: 'admin:web:navigationSiteCategoryRel:navigationCategoryAssignNavigationSite',
      route: {path: '/admin/navigationSiteCategoryRelManageNavigationCategoryAssignNavigationSite',query: navigationCategoryAssignNavigationSiteRouteQuery}
    },
    {
      txt: '为该导航网站分配导航分类',
      text: true,
      permission: 'admin:web:navigationSiteCategoryRel:navigationSiteAssignNavigationCategory',
      route: {path: '/admin/navigationSiteCategoryRelManageNavigationSiteAssignNavigationCategory',query: navigationSiteAssignNavigationCategoryRouteQuery}
    },
    {
      txt: '为该导航分类清空导航网站',
      text: true,
      methodConfirmText: `您将清空导航分类 ${row.navigationCategoryName} 所有导航网站,该导航分类将不再分配给任何导航网站，同时拥有涉及对应导航网站的用户导航分类将受到影响，请谨慎操作！！！，确定要清空吗？`,
      permission: 'admin:web:navigationSiteCategoryRel:deleteByNavigationCategoryId',
      route: {path: '/admin/navigationSiteCategoryRelManageDeleteByNavigationCategoryId',query: deleteByNavigationCategoryIdRouteQuery}
    },
    {
      txt: '为该导航网站清空导航分类',
      text: true,
      methodConfirmText: `您将清空导航网站 ${row.navigationSiteName} 所有导航分类,该导航网站将不再拥有任何导航分类，同时拥有该导航网站的用户导航分类将受到影响，请谨慎操作！！！，确定要清空吗？`,
      permission: 'admin:web:navigationSiteCategoryRel:deleteByNavigationSiteId',
      route: {path: '/admin/navigationSiteCategoryRelManageDeleteByNavigationSiteId',query: deleteByNavigationSiteIdRouteQuery}
    },
    {
      txt: '编辑',
      text: true,
      permission: 'admin:web:navigationSiteCategoryRel:update',
      // 跳转到编辑
      route: {path: '/admin/navigationSiteCategoryRelManageUpdate',query: idDataEdit}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:navigationSiteCategoryRel:delete',
      methodConfirmText: `删除后导航网站 ${row.navigationSiteName} 将不再拥有导航分类 ${row.navigationCategoryName}，确定要删除吗？`,
      // 删除操作
      method(){
        return navigationSiteCategoryRelRemoveApi(idData).then(res => {
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
      <PtButton permission="admin:web:navigationSiteCategoryRel:create" route="/admin/NavigationSiteCategoryRelManageAdd">添加</PtButton>
      <PtButton permission="admin:web:navigationSiteCategoryRel:navigationCategoryAssignNavigationSite" route="/admin/navigationSiteCategoryRelManageNavigationCategoryAssignNavigationSite">导航分类分配导航网站</PtButton>
      <PtButton permission="admin:web:navigationSiteCategoryRel:navigationSiteAssignNavigationCategory" route="/admin/navigationSiteCategoryRelManageNavigationSiteAssignNavigationCategory">导航网站分配导航分类</PtButton>

      <PtButton permission="admin:web:navigationSiteCategoryRel:deleteByNavigationCategoryId" route="/admin/navigationSiteCategoryRelManageDeleteByNavigationCategoryId">清空导航分类导航网站</PtButton>
      <PtButton permission="admin:web:navigationSiteCategoryRel:deleteByNavigationSiteId" route="/admin/navigationSiteCategoryRelManageDeleteByNavigationSiteId">清空导航网站导航分类</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doNavigationSiteCategoryRelPageApi"
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
