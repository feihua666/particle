<script setup name="SchedulerExecuteRecordManagePage" lang="ts">
/**
 * 任务计划执行记录管理页面
 */
import {reactive, ref} from 'vue'
import { page as schedulerExecuteRecordPageApi, remove as schedulerExecuteRecordRemoveApi} from "../../../api/schedule/admin/schedulerExecuteRecordAdminApi"
import {pageFormItems} from "../../../components/schedule/admin/schedulerExecuteRecordManage";


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
  name: {
    type: String
  },
  group: {
    type: String
  },
})

const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    schedulerName: props.schedulerName,
    schedulerInstanceId: props.schedulerInstanceId,
    name: props.name,
    groupName: props.group,
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'schedulerName',
      label: 'schedulerName',
    },
    {
      prop: 'schedulerInstanceId',
      label: 'schedulerInstanceId',
    },
    {
      prop: 'name',
      label: '任务名称',
    },
    {
      prop: 'groupName',
      label: '任务组',
    },
    {
      prop: 'params',
      label: '执行参数',
      showOverflowTooltip: true,
    },
    {
      prop: 'executeStatusDictName',
      label: '执行状态',
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
      showOverflowTooltip: true,
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:schedulerExecuteRecord:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doSchedulerExecuteRecordPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return schedulerExecuteRecordPageApi({...reactiveData.form,...pageQuery})
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
      txt: '删除',
      text: true,
      permission: 'admin:web:schedulerExecuteRecord:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return schedulerExecuteRecordRemoveApi({id: row.id}).then(res => {
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
          labelWidth="110"
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
      <el-table-column label="操作" width="80">
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
