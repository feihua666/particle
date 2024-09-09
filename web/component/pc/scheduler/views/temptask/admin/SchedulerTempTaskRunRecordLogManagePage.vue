<script setup name="SchedulerTempTaskRunRecordLogManagePage" lang="ts">
/**
 * 任务计划临时任务运行记录日志管理页面
 */
import {reactive, ref} from 'vue'
import { page as schedulerTempTaskRunRecordLogPageApi, remove as schedulerTempTaskRunRecordLogRemoveApi} from "../../../api/temptask/admin/schedulerTempTaskRunRecordLogAdminApi"
import {pageFormItems} from "../../../components/temptask/admin/schedulerTempTaskRunRecordLogManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'schedulerTempTaskRunRecordId',
      label: '临时任务运行记录id',
    },
    {
      prop: 'level',
      label: '日志级别',
    },
    {
      prop: 'content',
      label: '日志内容',
      showOverflowTooltip: true
    },
    {
      prop: 'createAt',
      label: '创建时间',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:schedulerTempTaskRunRecordLog:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doSchedulerTempTaskRunRecordLogPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return schedulerTempTaskRunRecordLogPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:schedulerTempTaskRunRecordLog:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return schedulerTempTaskRunRecordLogRemoveApi({id: row.id}).then(res => {
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
           :dataMethod="doSchedulerTempTaskRunRecordLogPageApi"
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