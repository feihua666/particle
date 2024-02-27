<script setup name="OpLogManagePage" lang="ts">
/**
 * 操作日志管理页面
 */
import {reactive, ref} from 'vue'
import { page as opLogPageApi, remove as opLogRemoveApi} from "../../api/admin/opLogAdminApi"
import {pageFormItems} from "../../compnents/admin/opLogManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'name',
      label: '操作名称',
    },
    {
      prop: 'module',
      label: '模块(名称)',
      formatter: (row, column, cellValue, index) => {
        let r = cellValue
        if (row.moduleDictName) {
          r = r + '(' + row.moduleDictName + ')';
        }

        return r
      },
    },

    {
      prop: 'type',
      label: '类型(名称)',
      formatter: (row, column, cellValue, index) => {
        let r = cellValue
        if (row.typeDictName) {
          r = r + '(' + row.typeDictName + ')';
        }

        return r
      },
    },
    {
      prop: 'userName',
      label: '用户姓名(昵称)',
      formatter: (row, column, cellValue, index) => {

        let r = cellValue || ''
        if (row.userNickname) {
          r = r + '(' + row.userNickname + ')';
        }

        return r
      },
      showOverflowTooltip: true
    },
    {
      prop: 'userAvatar',
      label: '用户头像',
      columnView: 'image'
    },
    {
      prop: 'url',
      label: '请求地址',
      showOverflowTooltip: true
    },
    {
      prop: 'ip',
      label: 'ip',
    },
    {
      prop: 'mainDataId',
      label: '主数据id',
      showOverflowTooltip: true
    },
    {
      prop: 'mainDataTable',
      label: '主数据表名',
    },
    {
      prop: 'mainDataEntity',
      label: '主数据载体',
      showOverflowTooltip: true
    },
    {
      prop: 'operateAt',
      label: '操作时间',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:opLog:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doOpLogPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return opLogPageApi({...reactiveData.form,...pageQuery})
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
  let opLogIdData = {opLogId: row.id,opLogName: row.name}
  let tableRowButtons = [
    {
      txt: '审计数据',
      text: true,
      permission: 'admin:web:opLogAuditData:pageQuery',
      // 跳转到编辑
      route: {path: '/admin/opLogAuditDataManagePopoverPage',query: opLogIdData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:opLog:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return opLogRemoveApi({id: row.id}).then(res => {
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
          label-width="120"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          inline
          :comps="reactiveData.formComps">
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doOpLogPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           :dataMethodResultHandleConvertToTree="true"
           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="170">
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