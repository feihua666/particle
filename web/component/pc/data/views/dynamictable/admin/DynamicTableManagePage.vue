<script setup name="DynamicTableManagePage" lang="ts">
/**
 * 动态数据表格管理页面
 */
import {reactive, ref} from 'vue'
import {
  exportTemplate,
  page as dynamicTablePageApi,
  remove as dynamicTableRemoveApi
} from "../../../api/dynamictable/admin/dynamicTableAdminApi"
import {pageFormItems} from "../../../components/dynamictable/admin/dynamicTableManage";
import {downloadFileByData, extractContentType, extractFileName} from "../../../../../../global/common/tools/FileTools";


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
      label: '表名称',
    },
    {
      prop: 'dynamicTableFieldNum',
      label: '字段数量',
    },
    {
      prop: 'dynamicTableDataNum',
      label: '数据数量',
    },
    {
      prop: 'comment',
      label: '表注释',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:dynamicTable:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDynamicTablePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dynamicTablePageApi({...reactiveData.form,...pageQuery})
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
      txt: '数据管理',
      text: true,
      permission: 'admin:web:dynamicTable:dataPageQuery',
      // 跳转到编辑
      route: {path: '/admin/dynamicTableManageData',query: idData}
    },

    {
      txt: '编辑',
      text: true,
      position: 'more',
      permission: 'admin:web:dynamicTable:update',
      // 跳转到编辑
      route: {path: '/admin/DynamicTableManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      position: 'more',
      permission: 'admin:web:dynamicTable:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？将级联删除关联的字段信息，删除后数据（包括导入的数据）无法恢复，请谨慎操作！`,
      // 删除操作
      method(){
        return dynamicTableRemoveApi({id: row.id}).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '导出动态数据模板',
      text: true,
      position: 'more',
      permission: 'admin:web:dynamicTable:exportTemplate',
      methodConfirmText: `确定要导出动态数据模板 ${row.name} 吗？`,
      // 导出操作
      method(){
        return exportTemplate({id: row.id}).then(res => {
          let data = res.data
          let contentType = extractContentType(res)
          let fileName = extractFileName(res)
          fileName = decodeURIComponent(fileName)
          downloadFileByData(data,contentType,fileName)
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '导入数据',
      text: true,
      position: 'more',
      permission: 'admin:web:dynamicTable:uploadImportData',
      // 跳转到编辑
      route: {path: '/admin/dynamicTableManageImportData',query: idData}
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
      <PtButton permission="admin:web:dynamicTable:create" route="/admin/DynamicTableManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDynamicTablePageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"

           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="180">
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
