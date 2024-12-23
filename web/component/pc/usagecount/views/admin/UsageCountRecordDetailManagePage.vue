<script setup name="UsageCountRecordDetailManagePage" lang="ts">
/**
 * 使用次数记录明细管理页面
 */
import {reactive, ref} from 'vue'
import {
  page as usageCountRecordDetailPageApi,
  remove as usageCountRecordDetailRemoveApi
} from "../../api/admin/usageCountRecordDetailAdminApi"
import {pageFormItems} from "../../components/admin/usageCountRecordDetailManage";


const tableRef = ref(null)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  usageCountRecordId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    // 如果有传递参数，使用该传参
    usageCountRecordId: props.usageCountRecordId
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'usageCountRecordId',
      label: '使用次数记录主键',
    },
    {
      prop: 'usageCountRecordUsageCountKey',
      label: '使用次数key',
      showOverflowTooltip: true
    },
    {
      prop: 'usageCountDefineName',
      label: '使用次数定义名称',
    },
    {
      prop: 'usageUserNickname',
      label: '用户昵称',
    },
    {
      prop: 'usageTenantName',
      label: '租户名称',
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
  permission: 'admin:web:usageCountRecordDetail:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doUsageCountRecordDetailPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return usageCountRecordDetailPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:usageCountRecordDetail:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return usageCountRecordDetailRemoveApi({id: row.id}).then(res => {
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
           :dataMethod="doUsageCountRecordDetailPageApi"
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
</template>


<style scoped>

</style>
