<script setup name="WorkflowExecutionNodeManagePage" lang="ts">
/**
 * 工作流节点执行实例管理页面
 */
import {reactive, ref} from 'vue'
import { page as workflowExecutionNodePageApi, remove as workflowExecutionNodeRemoveApi} from "../../../api/execution/admin/workflowExecutionNodeAdminApi"
import {pageFormItems} from "../../../components/execution/admin/workflowExecutionNodeManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'workflowExecutionId',
      label: '工作流执行ID',
    },
    {
      prop: 'nodeId',
      label: '节点ID',
    },
    {
      prop: 'statusDictName',
      label: '执行状态',
    },
    {
      prop: 'inputJson',
      label: '节点输入',
      showOverflowTooltip: true
    },
    {
      prop: 'outputJson',
      label: '节点输出',
      showOverflowTooltip: true
    },

    {
      prop: 'retryCount',
      label: '重试次数',
    },
    {
      prop: 'startAt',
      label: '运行开始时间',
      showOverflowTooltip: true
    },
    {
      prop: 'finishAt',
      label: '运行结束时间',
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
  permission: 'admin:web:workflowExecutionNode:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doWorkflowExecutionNodePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return workflowExecutionNodePageApi({...reactiveData.form,...pageQuery})
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
      txt: '编辑',
      text: true,
      permission: 'admin:web:workflowExecutionNode:update',
      // 跳转到编辑
      route: {path: '/admin/WorkflowExecutionNodeManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:workflowExecutionNode:delete',
      methodConfirmText: `确定要删除当前工作流实例节点数据吗？如果工作流正在运行可能导致未知的错误`,
      // 删除操作
      method(){
        return workflowExecutionNodeRemoveApi({id: row.id}).then(res => {
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
           :dataMethod="doWorkflowExecutionNodePageApi"
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
