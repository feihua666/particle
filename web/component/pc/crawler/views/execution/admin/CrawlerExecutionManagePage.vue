<script setup name="CrawlerExecutionManagePage" lang="ts">
/**
 * 爬虫执行实例管理页面
 */
import {reactive, ref} from 'vue'
import { page as crawlerExecutionPageApi, remove as crawlerExecutionRemoveApi} from "../../../api/execution/admin/crawlerExecutionAdminApi"
import {pageFormItems} from "../../../components/execution/admin/crawlerExecutionManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'id',
      label: '爬虫执行ID',
    },
    {
      prop: 'crawlerDefinitionName',
      label: '爬虫定义名称',
    },
    {
      prop: 'crawlerDefinitionHistoryVersion',
      label: '执行时使用的版本',
    },
    {
      prop: 'statusDictName',
      label: '执行状态',
    },
    {
      prop: 'triggerTypeDictName',
      label: '触发方式',
    },
    {
      prop: 'contextJson',
      label: '上下文json',
      showOverflowTooltip: true
    },
    {
      prop: 'startAt',
      label: '开始时间',
      showOverflowTooltip: true
    },
    {
      prop: 'finishAt',
      label: '结束时间',
      showOverflowTooltip: true
    },
    {
      prop: 'errorMsg',
      label: '错误信息',
      showOverflowTooltip: true
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:crawlerExecution:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doCrawlerExecutionPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return crawlerExecutionPageApi({...reactiveData.form,...pageQuery})
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
      txt: '删除',
      text: true,
      permission: 'admin:web:crawlerExecution:delete',
      methodConfirmText: `确定要删除当前爬虫实例数据吗？如果爬虫正在运行，可能导致未知错误，删除后不影响已经爬取的数据`,

      // 删除操作
      method(){
        return crawlerExecutionRemoveApi({id: row.id}).then(res => {
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
           :dataMethod="doCrawlerExecutionPageApi"
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
