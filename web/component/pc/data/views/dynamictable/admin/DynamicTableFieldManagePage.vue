<script setup name="DynamicTableFieldManagePage" lang="ts">
/**
 * 动态数据表格字段管理页面
 */
import {reactive, ref} from 'vue'
import { page as dynamicTableFieldPageApi, remove as dynamicTableFieldRemoveApi} from "../../../api/dynamictable/admin/dynamicTableFieldAdminApi"
import {pageFormItems} from "../../../components/dynamictable/admin/dynamicTableFieldManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'dynamicTableName',
      label: '动态数据表格名称',
    },
    {
      prop: 'dynamicTableComment',
      label: '动态数据表格注释',
    },
    {
      prop: 'name',
      label: '字段名称',
    },
    {
      prop: 'comment',
      label: '字段注释',
    },
    {
      prop: 'type',
      label: '字段类型',
    },
    {
      prop: 'isRequired',
      label: '是否必填',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '必填' : '选填'
      }
    },
    {
      prop: 'defaultValue',
      label: '默认值',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:dynamicTableField:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDynamicTableFieldPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dynamicTableFieldPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dynamicTableField:update',
      // 跳转到编辑
      route: {path: '/admin/DynamicTableFieldManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dynamicTableField:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？删除后数据（包括导入的数据）无法恢复，请谨慎操作！`,
      // 删除操作
      method(){
        return dynamicTableFieldRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dynamicTableField:create" route="/admin/DynamicTableFieldManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDynamicTableFieldPageApi"
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
