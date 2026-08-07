<script setup name="WorkflowExecutionManagePage" lang="ts">
/**
 * 工作流执行实例管理页面
 */
import {reactive, ref} from 'vue'
import { page as workflowExecutionPageApi, remove as workflowExecutionRemoveApi} from "../../../api/execution/admin/workflowExecutionAdminApi"
import {pageFormItems} from "../../../components/execution/admin/workflowExecutionManage";


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
      label: '工作流执行ID',
    },
    {
      prop: 'workflowDefinitionName',
      label: '工作流定义名称',
    },
    {
      prop: 'workflowDefinitionHistoryVersion',
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
      prop: 'nodeId',
      label: '当前执行节点ID',
    },
    {
      prop: 'contextJson',
      label: '上下文json',
      showOverflowTooltip: true
    },
    {
      prop: 'copiedWorkflowExecutionId',
      label: '来源工作流执行ID',
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
  permission: 'admin:web:workflowExecution:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doWorkflowExecutionPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return workflowExecutionPageApi({...reactiveData.form,...pageQuery})
}
const tablePaginationProps = {
  permission: submitAttrs.value.permission
}
// 表格操作按钮
const getTableRowButtons = ({row, column, $index}) => {
  if($index < 0){
    return []
  }
    let dt = {__dt: null}
  let idData = {id: row.id,...dt}

  let tableRowButtons = [
    {
      txt: '画布编辑',
      text: true,
      permission: 'admin:web:workflowDefinition:update',
      methodConfirmText: `确定以当前工作流实例数据进入画布编辑吗？注意实例数据和画布数据有可能对不上，因为由于后期又编辑了同一个版本，可能会有问题，进入画布编辑加载的流程图始终是同一版本的最新数据`,
      // 跳转到编辑
      route: {path: '/admin/workflowDefinitionHistoryWorkflowEdit',query: {id: row.workflowDefinitionHistoryId,workflowExecutionId:row.id}}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:workflowExecution:delete',
      methodConfirmText: `确定要删除当前工作流实例数据吗？如果工作流正在运行，可能导致未知错误，删除后对应的节点执行数据将一并删除`,
      // 删除操作
      method(){
        return workflowExecutionRemoveApi({id: row.id}).then(res => {
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
           :dataMethod="doWorkflowExecutionPageApi"
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
