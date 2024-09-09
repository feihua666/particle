<script setup name="ScheduleManagePage" lang="ts">
/**
 * 任务计划管理页面
 */
import {reactive, ref} from 'vue'
import {pageFormItems} from "../../../components/schedule/admin/scheduleManage";
import {getScheduleList, shutdown, standby, start} from "../../../api/admin/scheduleAdminApi";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'schedulerName',
      label: '任务计划名称'
    },
    {
      prop: 'schedulerInstanceId',
      label: '任务计划实例id'
    },
    {
      prop: 'isStarted',
      label: '是否开启'
    },
    {
      prop: 'isInStandbyMode',
      label: '是否挂起'
    },
    {
      prop: 'isShutdown',
      label: '是否停止'
    },
    {
      prop: 'scheduleMetaData.startAt',
      label: '启动时间'
    },
    {
      prop: 'scheduleMetaData.version',
      label: '版本'
    },
    {
      prop: 'scheduleMetaData.schedulerClassName',
      label: '任务计划实例类',
      showOverflowTooltip: true,
    },
    {
      prop: 'scheduleMetaData.numberOfJobsExecuted',
      label: '已执行任务数',
    },
    {
      prop: 'scheduleMetaData.jobStoreClassName',
      label: '任务存储类',
      showOverflowTooltip: true,
    },
    {
      prop: 'scheduleMetaData.isJobStoreSupportsPersistence',
      label: '任务存储是否支持持久化',
    },
    {
      prop: 'scheduleMetaData.isJobStoreSupportsPersistence',
      label: '任务存储是否集群模式',
    },
    {
      prop: 'scheduleMetaData.threadPoolClassName',
      label: '线程池类',
      showOverflowTooltip: true,
    },
    {
      prop: 'scheduleMetaData.threadPoolSize',
      label: '当前线程池线程数量',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'schedule:getScheduleList'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doSchedulerExecuteRecordPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return getScheduleList({...reactiveData.form,...pageQuery})
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
  let scheduleShutdownData = {schedulerName: row.schedulerName,schedulerInstanceId: row.schedulerInstanceId,isWaitForJobsToComplete: true}

  let tableRowButtons:Array<any> = [
    {
      txt: '任务管理',
      text: true,
      permission: 'schedule:getJobDetailList',
      // 跳转到编辑
      route: {path: '/admin/scheduleJobManagePage',query: scheduleData}
    },
    {
      txt: '触发器管理',
      text: true,
      position: 'more',
      permission: 'schedule:getTriggerList',
      // 跳转到编辑
      route: {path: '/admin/scheduleTriggerManagePage',query: scheduleData}
    },
    {
      txt: '强制停止',
      text: true,
      position: 'more',
      permission: 'schedule:shutdown',
      disabled: !row.isStarted,
      methodConfirmText: `确定要强制停止 ${row.name} 吗？正在执行中的任务会被中断`,
      // 删除操作
      method(){
        scheduleShutdownData.isWaitForJobsToComplete = false
        return shutdown(scheduleShutdownData).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '停止',
      text: true,
      position: 'more',
      permission: 'schedule:shutdown',
      disabled: !row.isStarted,
      methodConfirmText: `确定要强制停止 ${row.name} 吗？操作后会等待任务完成后才能停止`,
      // 删除操作
      method(){
        scheduleShutdownData.isWaitForJobsToComplete = true
        return shutdown(scheduleShutdownData).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '挂起',
      text: true,
      position: 'more',
      permission: 'schedule:standby',
      disabled: row.isInStandbyMode,
      methodConfirmText: `确定要挂起 ${row.name} 吗？`,
      // 删除操作
      method(){
        return standby(scheduleData).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '启动|恢复',
      text: true,
      position: 'more',
      permission: 'schedule:start',
      disabled: row.isStarted,
      methodConfirmText: `确定要启动或恢复 ${row.name} 吗？当任务停止或挂起后，需要重新启动才能再次执行任务`,
      // 删除操作
      method(){
        return start(scheduleData).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    }
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