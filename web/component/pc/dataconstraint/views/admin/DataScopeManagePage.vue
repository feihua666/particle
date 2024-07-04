<script setup name="DataScopeManagePage" lang="ts">
/**
 * 数据范围管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataScopePageApi, remove as dataScopeRemoveApi} from "../../api/admin/dataScopeAdminApi"
import {pageFormItems} from "../../compnents/admin/dataScopeManage";


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
      prop: 'dataObjectName',
      label: '数据对象',
    },
    {
      prop: 'constraintContent',
      label: '约束条件',
      showOverflowTooltip: true,
    },
    {
      prop: 'isCustom',
      label: '自定义数据',
      formatter: (row: any, column: any, cellValue: any, index: any) => {
        return cellValue ? '是' : '否'
      }
    },
    {
      prop: 'isForAdd',
      label: '用于添加',
      formatter: (row: any, column: any, cellValue: any, index: any) => {
        return cellValue ? '是' : '否'
      }
    },
    {
      prop: 'isForDelete',
      label: '用于删除',
      formatter: (row: any, column: any, cellValue: any, index: any) => {
        return cellValue ? '是' : '否'
      }
    },
    {
      prop: 'isForUpdate',
      label: '用于修改',
      formatter: (row: any, column: any, cellValue: any, index: any) => {
        return cellValue ? '是' : '否'
      }
    },
    {
      prop: 'isForQuery',
      label: '用于查询',
      formatter: (row: any, column: any, cellValue: any, index: any) => {
        return cellValue ? '是' : '否'
      }
    },
    {
      prop: 'isForOther',
      label: '用于其它',
      formatter: (row: any, column: any, cellValue: any, index: any) => {
        return cellValue ? '是' : '否'
      }
    },
    {
      prop: 'remark',
      label: '描述',
      showOverflowTooltip: true,
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:dataScope:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataScopePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataScopePageApi({...reactiveData.form,...pageQuery})
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
  let dataScopeAssignCustomDataRouteQuery = {dataScopeId: row.id,dataScopeName: row.name,dataObjectId: row.dataObjectId}
  let tableRowButtons: Array<any> = [
    {
      txt: '编辑',
      text: true,
      permission: 'admin:web:dataScope:update',
      // 跳转到编辑
      route: {path: '/admin/DataScopeManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataScope:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataScopeRemoveApi({id: row.id}).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '数据范围分配自定义数据',
      position: 'more',
      disabled: !row.isCustom,
      disabledReason: row.isCustom ? undefined : '只有自定义数据范围才能分配自定义数据',
      text: true,
      permission: 'admin:web:dataScopeCustomDataRel:dataScopeAssignCustomData',
      route: {path: '/admin/dataScopeManageDataScopeAssignCustomData',query: dataScopeAssignCustomDataRouteQuery}
    },
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
      <PtButton permission="admin:web:dataScope:create" route="/admin/DataScopeManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataScopePageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           
           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="210">
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