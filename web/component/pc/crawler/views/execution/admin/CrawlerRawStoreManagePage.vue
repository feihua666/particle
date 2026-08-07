<script setup name="CrawlerRawStoreManagePage" lang="ts">
/**
 * 爬虫原始数据存储管理页面
 */
import {reactive, ref} from 'vue'
import { page as crawlerRawStorePageApi, remove as crawlerRawStoreRemoveApi} from "../../../api/execution/admin/crawlerRawStoreAdminApi"
import {pageFormItems} from "../../../components/execution/admin/crawlerRawStoreManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'crawlerExecutionId',
      label: '爬虫执行实例ID',
    },
    {
      prop: 'crawlerDefinitionId',
      label: '爬虫定义ID',
    },
    {
      prop: 'crawlerDefinitionIdName',
      label: '爬虫定义名称',
    },
    {
      prop: 'crawlerDefinitionHistoryId',
      label: '执行时使用的版本ID',
    },
    {
      prop: 'crawlerDefinitionHistoryVersion',
      label: '执行时使用的版本号',
    },
    {
      prop: 'crawlerDefinitionName',
      label: '执行时爬虫定义名称',
    },
    {
      prop: 'pageUrl',
      label: '页面地址',
    },
    {
      prop: 'pageTitle',
      label: '页面标题',
    },
    {
      prop: 'statusDictName',
      label: '状态',
    },
    {
      prop: 'createAt',
      label: '创建时间',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:crawlerRawStore:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doCrawlerRawStorePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return crawlerRawStorePageApi({...reactiveData.form,...pageQuery})
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
      txt: '查看数据',
      text: true,
      permission: 'admin:web:crawlerRawStore:update',
      // 跳转到编辑
      route: {path: '/admin/CrawlerRawStoreManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:crawlerRawStore:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return crawlerRawStoreRemoveApi({id: row.id}).then(res => {
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
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doCrawlerRawStorePageApi"
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
