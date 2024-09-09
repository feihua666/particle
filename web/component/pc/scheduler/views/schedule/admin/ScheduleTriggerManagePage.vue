<script setup name="ScheduleTriggerManagePage" lang="ts">
/**
 * 任务计划触发器管理页面
 */
import {reactive, ref} from 'vue'
import {pageFormItems} from "../../../components/schedule/admin/scheduleTriggerManage";
import {getTriggerList, pauseTrigger, resumeTrigger} from "../../../api/admin/scheduleTriggerAdminApi";


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
      prop: 'triggerState',
      label: '状态'
    },
    {
      prop: 'triggerClassName',
      label: '类名称',
      showOverflowTooltip: true,
    },
    {
      prop: 'calendarName',
      label: '日历名称'
    },

    {
      prop: 'priority',
      label: '优先级'
    },
    {
      prop: 'isMayFireAgain',
      label: '是否可以再次触发'
    },
    {
      prop: 'startAt',
      label: '开始于'
    },
    {
      prop: 'endAt',
      label: '结束于'
    },
    {
      prop: 'nextFireAt',
      label: '下一次触发时间'
    },
    {
      prop: 'previousFireAt',
      label: '上一次触发时间'
    },
    {
      prop: 'finalFireAt',
      label: '最后触发时间'
    },
    {
      prop: 'misfireInstruction',
      label: '失火说明'
    },
    {
      prop: 'description',
      label: '描述信息',
      showOverflowTooltip: true,
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'schedule:getTriggerList'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doSchedulerExecuteRecordPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return getTriggerList({...reactiveData.form,...pageQuery})
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
      txt: '暂停',
      text: true,
      permission: 'schedule:trigger:pause',
      disabled: !(row.triggerState == 'NORMAL'),
      methodConfirmText: `确定要暂停 ${row.name} 吗？`,
      // 删除操作
      method(){
        return pauseTrigger(scheduleJobData).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },

    {
      txt: '恢复',
      text: true,
      permission: 'schedule:trigger:resume',
      disabled: !(row.triggerState == 'PAUSED'),
      methodConfirmText: `确定要复制 ${row.name} 吗？`,
      // 删除操作
      method(){
        return resumeTrigger(scheduleJobData).then(res => {
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