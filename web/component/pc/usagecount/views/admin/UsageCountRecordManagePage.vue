<script setup name="UsageCountRecordManagePage" lang="ts">
/**
 * 使用次数记录管理页面
 */
import {reactive, ref} from 'vue'
import {
  page as usageCountRecordPageApi,
  remove as usageCountRecordRemoveApi
} from "../../api/admin/usageCountRecordAdminApi"
import {pageFormItems} from "../../components/admin/usageCountRecordManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'usageCountKey',
      label: '使用次数key',
      showOverflowTooltip: true
    },
    {
      prop: 'usageCountDefineName',
      label: '使用次数定义',
    },
    {
      prop: 'usageCountConfigName',
      label: '使用次数配置',
    },
    {
      prop: 'usageCount',
      label: '已使用次数',
    },
    {
      prop: 'usageUserNickname',
      label: '使用用户昵称',
    },
    {
      prop: 'usageTenantName',
      label: '使用租户名称',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:usageCountRecord:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doUsageCountRecordPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return usageCountRecordPageApi({...reactiveData.form,...pageQuery})
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
      txt: '查看明细',
      text: true,
      permission: 'admin:web:usageCountRecordDetail:pageQuery',
      // 跳转到编辑
      route: {path: '/admin/usageCountRecordDetailPage',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:usageCountRecord:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return usageCountRecordRemoveApi({id: row.id}).then(res => {
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
          labelWidth="100"
          :comps="reactiveData.formComps">
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doUsageCountRecordPageApi"
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
