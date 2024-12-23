<script setup name="SchedulerTempTaskManagePage" lang="ts">
/**
 * 任务计划临时任务管理页面
 */
import {reactive, ref} from 'vue'
import {
  page as schedulerTempTaskPageApi,
  remove as schedulerTempTaskRemoveApi
} from "../../../api/temptask/admin/schedulerTempTaskAdminApi"
import {pageFormItems} from "../../../components/temptask/admin/schedulerTempTaskManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'code',
      label: '临时任务编码',
    },
    {
      prop: 'name',
      label: '临时任务名称',
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
  permission: 'admin:web:schedulerTempTask:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doSchedulerTempTaskPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return schedulerTempTaskPageApi({...reactiveData.form,...pageQuery})
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

  let schedulerTempTaskIdData = {schedulerTempTaskId: row.id}

  let tableRowButtons = [
    {
      txt: '查看运行记录',
      text: true,
      permission: 'admin:web:schedulerTempTaskRunRecord:pageQuery',
      // 跳转到编辑
      route: {path: '/admin/schedulerTempTaskRunRecordManagePage',query: schedulerTempTaskIdData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:schedulerTempTask:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return schedulerTempTaskRemoveApi({id: row.id}).then(res => {
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
           :dataMethod="doSchedulerTempTaskPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"

           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="200">
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
