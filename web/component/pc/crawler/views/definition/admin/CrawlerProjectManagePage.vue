<script setup name="CrawlerProjectManagePage" lang="ts">
/**
 * 爬虫项目管理页面
 */
import {reactive, ref} from 'vue'
import { page as crawlerProjectPageApi, remove as crawlerProjectRemoveApi} from "../../../api/definition/admin/crawlerProjectAdminApi"
import {pageFormItems} from "../../../components/definition/admin/crawlerProjectManage";


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
      label: '项目名称',
    },
    {
      prop: 'userNickname',
      label: '归属用户昵称',
    },
    {
      prop: 'isPublic',
      label: '是否公开',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '已公开' : '用户私有'
      }
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
  permission: 'admin:web:crawlerProject:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doCrawlerProjectPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return crawlerProjectPageApi({...reactiveData.form,...pageQuery})
}
const tablePaginationProps = {
  permission: submitAttrs.value.permission
}
// 表格操作按钮
const getTableRowButtons = ({row, column, $index}) => {
  if($index < 0){
    return []
  }
  let dt = {__dt: row.name}
  let idData = {id: row.id,...dt}

  let tableRowButtons = [
    {
      txt: '编辑',
      text: true,
      permission: 'admin:web:crawlerProject:update',
      // 跳转到编辑
      route: {path: '/admin/CrawlerProjectManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:crawlerProject:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？删除后项目下的爬虫定义和运行实例将一并删除`,
      // 删除操作
      method(){
        return crawlerProjectRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:crawlerProject:create" route="/admin/CrawlerProjectManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doCrawlerProjectPageApi"
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
