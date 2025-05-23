<script setup name="SchedulerJobDataTaskManagePage" lang="ts">
/**
 * 任务计划任务数据管理页面
 */
import {reactive, ref} from 'vue'
import { page as schedulerJobDataTaskPageApi, remove as schedulerJobDataTaskRemoveApi} from "../../../api/datatask/admin/schedulerJobDataTaskAdminApi"
import {pageFormItems} from "../../../components/datatask/admin/schedulerJobDataTaskManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'groupIdentifier',
      label: '任务分组标识',
    },
    {
      prop: 'uniqueIdentifier',
      label: '唯一标识',
    },
    {
      prop: 'params',
      label: '执行参数',
    },
    {
      prop: 'executeStatusDictName',
      label: '执行状态',
    },
    {
      prop: 'errorMessage',
      label: '执行错误时提示信息',
    },
    {
      prop: 'startAt',
      label: '运行开始时间',
    },
    {
      prop: 'finishAt',
      label: '运行结束时间',
    },
    {
      prop: 'localHostIp',
      label: '本地主机ip',
    },
    {
      prop: 'localHostName',
      label: '本地主机名称',
    },
    {
      prop: 'traceId',
      label: '链路追踪id',
    },
    {
      prop: 'result',
      label: '运行结果',
    },
    {
      prop: 'dataExpireAt',
      label: '数据过期时间',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:schedulerJobDataTask:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doSchedulerJobDataTaskPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return schedulerJobDataTaskPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:schedulerJobDataTask:update',
      // 跳转到编辑
      route: {path: '/admin/SchedulerJobDataTaskManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:schedulerJobDataTask:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return schedulerJobDataTaskRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:schedulerJobDataTask:create" route="/admin/SchedulerJobDataTaskManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doSchedulerJobDataTaskPageApi"
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