<script setup name="CrawlerDefinitionManagePage" lang="ts">
/**
 * 爬虫定义管理页面
 */
import {reactive, ref} from 'vue'
import { page as crawlerDefinitionPageApi, remove as crawlerDefinitionRemoveApi} from "../../../api/definition/admin/crawlerDefinitionAdminApi"
import {pageFormItems} from "../../../components/definition/admin/crawlerDefinitionManage";
import {createDraft} from "../../../../crawler/api/definition/admin/crawlerDefinitionHistoryAdminApi.ts";
import { useRouter } from 'vue-router'
const router = useRouter()

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
      label: '爬虫名称',
    },
    {
      prop: 'crawlerProjectName',
      label: '项目名称',
    },
    {
      prop: 'latestPublishCrawlerDefinitionHistoryVersion',
      label: '最新发布版本',
    },
    {
      prop: 'draftCrawlerDefinitionHistoryVersion',
      label: '草稿版本',
    },
    {
      prop: 'remark',
      label: '描述',
      showOverflowTooltip: true
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:crawlerDefinition:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doCrawlerDefinitionPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return crawlerDefinitionPageApi({...reactiveData.form,...pageQuery})
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
      txt: '规则编辑',
      text: true,
      permission: 'admin:web:crawlerDefinition:update',
      methodConfirmText: !row.draftCrawlerDefinitionHistoryId ? `确定要进入 ${row.name} 的规则编辑吗？当前爬虫定义没有草稿，点击确认自动创建草稿并跳转到规则编辑`: undefined,
      // 跳转到编辑
      method(){
        // 如果没有草稿，需要先建草稿
        if(!row.draftCrawlerDefinitionHistoryId){
          return createDraft({
            crawlerDefinitionId: row.id
          }).then(res => {
            let draftCrawlerDefinitionHistoryId = res.data.data.id
            // 添加成功后跳转到画布编辑页面
            router.push({path: '/admin/definition/crawlerDefinitionHistoryManageUpdate',query: {
              crawlerDefinitionHistoryId: draftCrawlerDefinitionHistoryId,
                crawlerDefinitionName: row.name,
                ...dt
            }})
            return Promise.resolve(res)
          })
        }else{
          // 跳转到画布编辑页面
          router.push({path: '/admin/definition/crawlerDefinitionHistoryManageUpdate',query: {
            crawlerDefinitionHistoryId: row.draftCrawlerDefinitionHistoryId,
              crawlerDefinitionName: row.name,
              ...dt
          }})
        }
      }
    },
    {
      txt: '手动执行',
      text: true,
      position: 'more',
      permission: 'admin:web:crawlerExecution:execute',
      // 跳转到编辑
      route: {path: '/admin/definition/crawlerExecutionExecutePage',query: {
          crawlerDefinitionHistoryId: row.draftCrawlerDefinitionHistoryId,
          crawlerDefinitionName: row.name,
          ...dt
        }}
    },
    {
      txt: '编辑',
      text: true,
      position: 'more',
      permission: 'admin:web:crawlerDefinition:update',
      // 跳转到编辑
      route: {path: '/admin/CrawlerDefinitionManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      position: 'more',
      permission: 'admin:web:crawlerDefinition:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？删除后所有的历史版本和执行实例将一并删除`,
      // 删除操作
      method(){
        return crawlerDefinitionRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:crawlerDefinition:create" route="/admin/CrawlerDefinitionManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doCrawlerDefinitionPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"

           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="180">
        <template #default="{row, column, $index}">
          <PtButtonGroup :options="getTableRowButtons({row, column, $index})" :dropdownTriggerButtonOptions="{  text: true,buttonText: '更多'}">
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
