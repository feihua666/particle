<script setup name="DataObjectManagePage" lang="ts">
/**
 * 数据对象管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataObjectPageApi, remove as dataObjectRemoveApi} from "../../api/admin/dataObjectAdminApi"
import {pageFormItems} from "../../components/admin/dataObjectManage";


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
      label: '编码',
    },
    {
      prop: 'name',
      label: '名称',
    },
    {
      prop: 'customDataUrl',
      label: '自定义数据url',
      showOverflowTooltip: true
    },
    {
      prop: 'isCustomDataLazy',
      label: '自定义数据是否懒加载',
      formatter: (row: any, column: any, cellValue: any, index: any) => {
        return cellValue ? '懒加载' : '一次性数据'
      }
    },
    {
      prop: 'customDataTypeDictName',
      label: '自定义数据交互方式',
    },
    {
      prop: 'customDataConfigJson',
      label: '数据交互方式内容',
      showOverflowTooltip: true
    },
    {
      prop: 'isDisabled',
      label: '是否禁用',
      width: 80,
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? '禁用' : '启用'
        if(cellValue && row.disabledReason){
          r = r + `(${row.disabledReason})`
        }
        return r
      }
    },
    {
      prop: 'remark',
      label: '描述',
      showOverflowTooltip: true
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:dataObject:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataObjectPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataObjectPageApi({...reactiveData.form,...pageQuery})
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
      txt: '编辑',
      text: true,
      permission: 'admin:web:dataObject:update',
      // 跳转到编辑
      route: {path: '/admin/DataObjectManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataObject:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataObjectRemoveApi({id: row.id}).then(res => {
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
    <template #buttons>
      <PtButton permission="admin:web:dataObject:create" route="/admin/DataObjectManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataObjectPageApi"
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