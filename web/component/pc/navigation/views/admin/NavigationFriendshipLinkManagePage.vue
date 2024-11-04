<script setup name="NavigationFriendshipLinkManagePage" lang="ts">
/**
 * 导航友情链接管理页面
 */
import {reactive, ref} from 'vue'
import { page as navigationFriendshipLinkPageApi, remove as navigationFriendshipLinkRemoveApi} from "../../api/admin/navigationFriendshipLinkAdminApi"
import {pageFormItems} from "../../components/admin/navigationFriendshipLinkManage";


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
      prop: 'collectionAt',
      label: '收录时间',
    },
    {
      prop: 'isPublished',
      label: '是否已发布',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '已发布' : '未发布'
      }
    },
    {
      prop: 'unpublishedReason',
      label: '下架原因',
    },
    {
      prop: 'remark',
      label: '描述',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:navigationFriendshipLink:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doNavigationFriendshipLinkPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return navigationFriendshipLinkPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:navigationFriendshipLink:update',
      // 跳转到编辑
      route: {path: '/admin/NavigationFriendshipLinkManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:navigationFriendshipLink:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return navigationFriendshipLinkRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:navigationFriendshipLink:create" route="/admin/NavigationFriendshipLinkManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doNavigationFriendshipLinkPageApi"
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
