<script setup name="OpLogAuditDataManagePage" lang="ts">
/**
 * 操作日志审计数据管理页面
 */
import {reactive, ref} from 'vue'
import { page as opLogAuditDataPageApi, remove as opLogAuditDataRemoveApi} from "../../api/admin/opLogAuditDataAdminApi"
import { usePageFormItems} from "../../compnents/admin/opLogAuditDataManage";
import {remoteSelectOpLogProps} from "../../compnents/opLogCompItem";


const tableRef = ref(null)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  ...remoteSelectOpLogProps
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    opLogId: props.opLogId
  },
  formComps: usePageFormItems({props}),
  tableColumns: [
    {
      prop: 'name',
      label: '字段名称',
    },
    {
      prop: 'propertyName',
      label: '数据字段',
    },
    {
      prop: 'oldValue',
      label: '旧值',
    },
    {
      prop: 'newValue',
      label: '新值',
    },
    {
      prop: 'changeTypeDictName',
      label: '值改变类型字典',
    },
//      该类型同操作日志类型
/*    {
      prop: 'type',
      label: '类型(名称)',
      formatter: (row, column, cellValue, index) => {
        let r = cellValue
        if (row.typeDictName) {
          r = cellValue + '(' + row.typeDictName + ')';
        }

        return r
      },
    },*/
    {
      prop: 'dataId',
      label: '数据id',
      showOverflowTooltip: true
    },
    {
      prop: 'dataTable',
      label: '数据表名',
      showOverflowTooltip: true
    },
    {
      prop: 'dataEntity',
      label: '数据载体',
      showOverflowTooltip: true
    },
    {
      prop: 'opLogName',
      label: '操作日志',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:opLogAuditData:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doOpLogAuditDataPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return opLogAuditDataPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:opLogAuditData:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return opLogAuditDataRemoveApi({id: row.id}).then(res => {
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
          label-width="120"
          :comps="reactiveData.formComps">
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doOpLogAuditDataPageApi"
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
<!--  <PtRouteViewPopover :level="3"></PtRouteViewPopover>-->
</template>


<style scoped>

</style>