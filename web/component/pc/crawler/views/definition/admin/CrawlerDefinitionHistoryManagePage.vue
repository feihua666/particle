<script setup name="CrawlerDefinitionHistoryManagePage" lang="ts">
/**
 * 爬虫定义历史管理页面
 */
import {reactive, ref} from 'vue'
import { page as crawlerDefinitionHistoryPageApi, remove as crawlerDefinitionHistoryRemoveApi} from "../../../api/definition/admin/crawlerDefinitionHistoryAdminApi"
import {pageFormItems} from "../../../components/definition/admin/crawlerDefinitionHistoryManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'crawlerDefinitionName',
      label: '爬虫定义名称',
    },
    {
      prop: 'crawlerDefinitionVersion',
      label: '版本号',
    },
    {
      prop: 'isPublish',
      label: '是否发布',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '已发布' : '草稿'
      }
    },
    {
      prop: 'definitionJson',
      label: '规则数据',
      showOverflowTooltip: true
    },
    {
      prop: 'configJson',
      label: '爬虫级配置json',
      showOverflowTooltip: true
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:crawlerDefinitionHistory:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doCrawlerDefinitionHistoryPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return crawlerDefinitionHistoryPageApi({...reactiveData.form,...pageQuery})
}
const tablePaginationProps = {
  permission: submitAttrs.value.permission
}
// 表格操作按钮
const getTableRowButtons = ({row, column, $index}) => {
  if($index < 0){
    return []
  }
    let dt = {__dt: row.crawlerDefinitionName}
  let idData = {id: row.id,crawlerDefinitionName: row.crawlerDefinitionName, ...dt}

  let tableRowButtons = [
    {
      txt: '规则编辑',
      text: true,
      permission: 'admin:web:crawlerDefinitionHistory:update',
      // 跳转到编辑
      route: {path: '/admin/CrawlerDefinitionHistoryManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:crawlerDefinitionHistory:delete',
      methodConfirmText: `确定要删除版本为 ${row.crawlerDefinitionVersion} 爬虫定义历史吗？，如果对应的臭虫定义正在使用，可能导致不可预知的错误`,

      // 删除操作
      method(){
        return crawlerDefinitionHistoryRemoveApi({id: row.id}).then(res => {
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
           :dataMethod="doCrawlerDefinitionHistoryPageApi"
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
