<script setup name="DynamicDataIndicatorManagePage" lang="ts">
/**
 * 动态数据指标管理页面
 */
import {reactive, ref} from 'vue'
import { page as dynamicDataIndicatorPageApi, remove as dynamicDataIndicatorRemoveApi} from "../../../api/dynamicdata/admin/dynamicDataIndicatorAdminApi"
import {pageFormItems} from "../../../components/dynamicdata/admin/dynamicDataIndicatorManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'dynamicDataCategoryName',
      label: '动态数据分类名称',
    },
    {
      prop: 'dynamicDataIndicatorCategoryName',
      label: '动态数据指标分类名称',
    },
    {
      prop: 'name',
      label: '指标名称',
    },
    {
      prop: 'isDisabled',
      label: '是否禁用',
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
      label: '备注信息',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:dynamicDataIndicator:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDynamicDataIndicatorPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dynamicDataIndicatorPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dynamicDataIndicator:update',
      // 跳转到编辑
      route: {path: '/admin/DynamicDataIndicatorManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dynamicDataIndicator:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？删除后数据（包括导入的数据）无法恢复，请谨慎操作！`,
      // 删除操作
      method(){
        return dynamicDataIndicatorRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dynamicDataIndicator:create" route="/admin/DynamicDataIndicatorManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDynamicDataIndicatorPageApi"
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
