<script setup name="WorkflowDefinitionHistoryManagePage" lang="ts">
/**
 * 工作流定义历史管理页面
 */
import {reactive, ref} from 'vue'
import {
  createDraft,
  page as workflowDefinitionHistoryPageApi,
  remove as workflowDefinitionHistoryRemoveApi
} from "../../../api/definition/admin/workflowDefinitionHistoryAdminApi"
import {pageFormItems} from "../../../components/definition/admin/workflowDefinitionHistoryManage";


const tableRef = ref(null)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  workflowProjectId: {
    type: String
  },
  workflowDefinitionId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    workflowProjectId: props.workflowProjectId,
    workflowDefinitionId: props.workflowDefinitionId,
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'workflowDefinitionName',
      label: '工作流定义名称',
    },
    {
      prop: 'workflowDefinitionVersion',
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
      prop: 'graphDataJson',
      label: '流程图数据',
      showOverflowTooltip: true
    },
    {
      prop: 'configJson',
      label: '工作流级配置json',
      showOverflowTooltip: true
    },
  ],

})
// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:workflowDefinitionHistory:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doWorkflowDefinitionHistoryPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return workflowDefinitionHistoryPageApi({...reactiveData.form,...pageQuery})
}
const tablePaginationProps = {
  permission: submitAttrs.value.permission
}
// 表格操作按钮
const getTableRowButtons = ({row, column, $index}) => {
  if($index < 0){
    return []
  }
    let dt = {__dt: row.workflowDefinitionVersion}
  let idData = {id: row.id,...dt}

  let tableRowButtons = [
    {
      txt: '画布编辑',
      text: true,
      permission: 'admin:web:workflowDefinition:update',
      // 跳转到编辑
      route: {path: '/admin/workflowDefinitionHistoryWorkflowEdit',query: {id: row.id}}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:workflowDefinitionHistory:delete',
      methodConfirmText: `确定要删除版本为 ${row.workflowDefinitionVersion} 流程定义历史吗？，如果对应的流程定义正在使用，可能导致不可预知的错误`,
      // 删除操作
      method(){
        return workflowDefinitionHistoryRemoveApi({id: row.id}).then(res => {
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
           :dataMethod="doWorkflowDefinitionHistoryPageApi"
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
