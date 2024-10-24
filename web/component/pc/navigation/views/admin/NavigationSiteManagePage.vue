<script setup name="NavigationSiteManagePage" lang="ts">
/**
 * 导航网站管理页面
 */
import {reactive, ref} from 'vue'
import { page as navigationSitePageApi, remove as navigationSiteRemoveApi} from "../../api/admin/navigationSiteAdminApi"
import {pageFormItems} from "../../components/admin/navigationSiteManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'name',
      label: '网站名称',
    },
    {
      prop: 'title',
      label: '网站标题',
      showOverflowTooltip: true,
    },
    {
      prop: 'logoUrl',
      label: '网站logo图标地址',
      showOverflowTooltip: true,
    },
    {
      prop: 'url',
      label: '网站地址',
      showOverflowTooltip: true,
    },
    {
      prop: 'screenshotUrl',
      label: '网站截屏地址',
      showOverflowTooltip: true,
    },
    {
      prop: 'screenshotThumbnailUrl',
      label: '网站截屏缩略图地址',
      showOverflowTooltip: true,
    },
    {
      prop: 'content',
      label: '简短描述',
      showOverflowTooltip: true,
    },
    {
      prop: 'contentDetail',
      label: '详细描述',
      showOverflowTooltip: true,
    },
    {
      prop: 'feeSituationDictName',
      label: '收费情况',
      showOverflowTooltip: true,
    },
    {
      prop: 'collectionAt',
      label: '收录时间',
      showOverflowTooltip: true,
    },
    {
      prop: 'remark',
      label: '描述',
      showOverflowTooltip: true,
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:navigationSite:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doNavigationSitePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return navigationSitePageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:navigationSite:update',
      // 跳转到编辑
      route: {path: '/admin/NavigationSiteManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:navigationSite:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return navigationSiteRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:navigationSite:create" route="/admin/NavigationSiteManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doNavigationSitePageApi"
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
