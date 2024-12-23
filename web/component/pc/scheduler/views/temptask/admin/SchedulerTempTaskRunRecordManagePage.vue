<script setup name="SchedulerTempTaskRunRecordManagePage" lang="ts">
/**
 * 任务计划临时任务运行记录管理页面
 */
import {reactive, ref} from 'vue'
import {
  page as schedulerTempTaskRunRecordPageApi,
  remove as schedulerTempTaskRunRecordRemoveApi
} from "../../../api/temptask/admin/schedulerTempTaskRunRecordAdminApi"
import {pageFormItems} from "../../../components/temptask/admin/schedulerTempTaskRunRecordManage";


const tableRef = ref(null)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  schedulerTempTaskId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    schedulerTempTaskId: props.schedulerTempTaskId
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'schedulerTempTaskCode',
      label: '临时任务编码',
    },
    {
      prop: 'schedulerTempTaskName',
      label: '临时任务名称',
    },
    {
      prop: 'statusDictName',
      label: '临时任务状态',
    },
    {
      prop: 'isHasError',
      label: '是否有异常',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '是' : '否'
      }
    },
    {
      prop: 'isAllowRunSwitch',
      label: '是否允许运行',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '是' : '否'
      }
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
      label: 'traceId',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:schedulerTempTaskRunRecord:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doSchedulerTempTaskRunRecordPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return schedulerTempTaskRunRecordPageApi({...reactiveData.form,...pageQuery})
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
  let schedulerTempTaskRunRecordIdData = {schedulerTempTaskRunRecordId: row.id,schedulerTempTaskName: row.schedulerTempTaskName}

  let tableRowButtons = [
    {
      txt: '查看运行记录日志',
      text: true,
      permission: 'admin:web:schedulerTempTaskRunRecordLog:pageQuery',
      // 跳转到编辑
      route: {path: '/admin/schedulerTempTaskRunRecordLogManagePage',query: schedulerTempTaskRunRecordIdData}
    },
    {
      txt: '编辑',
      text: true,
      position: 'more',
      permission: 'admin:web:schedulerTempTaskRunRecord:update',
      // 跳转到编辑
      route: {path: '/admin/SchedulerTempTaskRunRecordManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      position: 'more',
      permission: 'admin:web:schedulerTempTaskRunRecord:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return schedulerTempTaskRunRecordRemoveApi({id: row.id}).then(res => {
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
           :dataMethod="doSchedulerTempTaskRunRecordPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"

           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="220">
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
