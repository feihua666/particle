<script setup name="ScheduleJobManagePage" lang="ts">
/**
 * 任务计划任务管理页面
 */
import {reactive, ref} from 'vue'
import {pageFormItems} from "../../../components/schedule/admin/scheduleJobManage";
import {
  copyJob,
  deleteJob,
  executeOnce,
  getJobDetailList,
  pauseJob,
  resumeJob
} from "../../../api/admin/scheduleJobAdminApi";


const tableRef = ref(null)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  schedulerName: {
    type: String
  },
  schedulerInstanceId: {
    type: String
  },
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    schedulerName: props.schedulerName,
    schedulerInstanceId: props.schedulerInstanceId
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'schedulerName',
      label: '任务计划名称'
    },
    {
      prop: 'schedulerInstanceId',
      label: '任务计划实例id',
    },
    {
      prop: 'name',
      label: '任务名称'
    },
    {
      prop: 'group',
      label: '任务组'
    },
    {
      prop: 'cronExpression',
      label: 'cronExpression'
    },
    {
      prop: 'isDurable',
      label: '如果没有关联触发器是否持久化',
    },
    {
      prop: 'isPersistJobDataAfterExecution',
      label: '执行完成是否持久化',
    },
    {
      prop: 'isConcurrentExectionDisallowed',
      label: '是否不允许并行',
    },
    {
      prop: 'isRecovery',
      label: '是否可恢复'
    },
    {
      prop: 'jobClassName',
      label: '类名称',
      showOverflowTooltip: true
    },
    {
      prop: 'description',
      label: '描述'
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'schedule:getJobDetailList'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doSchedulerExecuteRecordPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return getJobDetailList({...reactiveData.form,...pageQuery})
}
const tablePaginationProps = {
  permission: submitAttrs.value.permission
}
// 表格操作按钮
const getTableRowButtons = ({row, column, $index}) => {
  if($index < 0){
    return []
  }
  let scheduleData = {schedulerName: row.schedulerName,schedulerInstanceId: row.schedulerInstanceId}
  let scheduleJobData = {schedulerName: row.schedulerName,schedulerInstanceId: row.schedulerInstanceId,name: row.name,group: row.group}
  let scheduleShutdownData = {schedulerName: row.schedulerName,schedulerInstanceId: row.schedulerInstanceId,isWaitForJobsToComplete: true}

  let tableRowButtons:Array<any> = [
    {
      txt: '编辑',
      text: true,
      permission: 'schedule:job:update',
      // 跳转到编辑
      route: {path: '/admin/scheduleJobManageUpdatePage',query: scheduleJobData}
    },
    {
      txt: '删除',
      text: true,
      position: 'more',
      permission: 'schedule:job:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return deleteJob(scheduleJobData).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '查看执行记录',
      text: true,
      position: 'more',
      permission: 'admin:web:schedulerExecuteRecord:pageQuery',
      // 跳转到编辑
      route: {path: '/admin/schedulerExecuteRecordManagePage',query: scheduleJobData}
    },
    {
      txt: '复制',
      text: true,
      position: 'more',
      permission: 'schedule:job:copy',
      methodConfirmText: `确定要复制 ${row.name} 吗？`,
      // 删除操作
      method(){
        return copyJob(scheduleJobData).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '暂停',
      text: true,
      position: 'more',
      permission: 'schedule:job:pause',
      methodConfirmText: `确定要暂停 ${row.name} 吗？任务关联的所有的触发器都会被暂停`,
      // 删除操作
      method(){
        return pauseJob(scheduleJobData).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '恢复',
      text: true,
      position: 'more',
      permission: 'schedule:job:resume',
      methodConfirmText: `确定要恢复 ${row.name} 吗？任务关联的所有的触发器都会被恢复`,
      // 删除操作
      method(){
        return resumeJob(scheduleJobData).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '手动执行一次',
      text: true,
      position: 'more',
      permission: 'schedule:job:executeOnce',
      methodConfirmText: `确定要手动执行一次 ${row.name} 吗？`,
      // 删除操作
      method(){
        return executeOnce(scheduleJobData).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
  ]

  return tableRowButtons;
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
      <PtButton permission="schedule:job:add" :route="{path: '/admin/scheduleJobManageAddPage',query: {schedulerName: reactiveData.form.schedulerName,schedulerInstanceId: reactiveData.form.schedulerInstanceId}}">添加任务</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doSchedulerExecuteRecordPageApi"
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