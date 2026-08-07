<script setup name="WorkflowDefinitionManagePage" lang="ts">
/**
 * 工作流定义管理页面
 */
import {reactive, ref} from 'vue'
import { page as workflowDefinitionPageApi, remove as workflowDefinitionRemoveApi} from "../../../api/definition/admin/workflowDefinitionAdminApi"
import {pageFormItems} from "../../../components/definition/admin/workflowDefinitionManage";
import {createDraft} from "../../../api/definition/admin/workflowDefinitionHistoryAdminApi";
import { useRouter } from 'vue-router'
const router = useRouter()

const tableRef = ref(null)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  workflowProjectId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    workflowProjectId: props.workflowProjectId
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'name',
      label: '工作流名称',
    },
    {
      prop: 'coverImageUrl',
      label: '封面',
      columnView: 'image'
    },
    {
      prop: 'workflowProjectName',
      label: '项目名称',
    },
    {
      prop: 'latestPublishWorkflowDefinitionHistoryVersion',
      label: '最新发布版本',
    },
    {
      prop: 'draftWorkflowDefinitionHistoryVersion',
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
  permission: 'admin:web:workflowDefinition:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doWorkflowDefinitionPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return workflowDefinitionPageApi({...reactiveData.form,...pageQuery})
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
      txt: '画布编辑',
      text: true,
      permission: 'admin:web:workflowDefinition:update',
      methodConfirmText: !row.draftWorkflowDefinitionHistoryId ? `确定要进入 ${row.name} 的画布编辑吗？当前流程定义没有草稿，点击确认自动创建草稿并跳转到画布`: undefined,
      // 跳转到编辑
      method(){
        // 如果没有草稿，需要先建草稿
        if(!row.draftWorkflowDefinitionHistoryId){
          return createDraft({
            workflowDefinitionId: row.id
          }).then(res => {
            let draftWorkflowDefinitionHistoryId = res.data.data.id
            // 添加成功后跳转到画布编辑页面
            router.push({path: '/admin/workflowDefinitionHistoryWorkflowEdit',query: {id: draftWorkflowDefinitionHistoryId}})
            return Promise.resolve(res)
          })
        }else{
          // 跳转到画布编辑页面
          router.push({path: '/admin/workflowDefinitionHistoryWorkflowEdit',query: {id: row.draftWorkflowDefinitionHistoryId}})
        }
      }
    },
    {
      txt: '编辑',
      text: true,
      position: 'more',
      permission: 'admin:web:workflowDefinition:update',
      // 跳转到编辑
      route: {path: '/admin/WorkflowDefinitionManageUpdate',query: idData}
    },
    {
      txt: '历史版本管理',
      text: true,
      position: 'more',
      permission: 'admin:web:workflowDefinitionHistory:pageQuery',
      // 跳转到编辑
      route: {path: '/admin/workflowDefinitionHistoryManagePage',query: {workflowProjectId: row.workflowProjectId,workflowDefinitionId: row.id}}
    },
    {
      txt: '删除',
      text: true,
      position: 'more',
      permission: 'admin:web:workflowDefinition:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？删除后所有的历史版本和执行实例将一并删除`,
      // 删除操作
      method(){
        return workflowDefinitionRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:workflowDefinition:create" route="/admin/WorkflowDefinitionManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doWorkflowDefinitionPageApi"
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
