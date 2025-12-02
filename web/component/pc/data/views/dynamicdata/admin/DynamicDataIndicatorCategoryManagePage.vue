<script setup name="DynamicDataIndicatorCategoryManagePage" lang="ts">
/**
 * 动态数据指标分类管理页面
 */
import {reactive, ref} from 'vue'
import { page as dynamicDataIndicatorCategoryPageApi, remove as dynamicDataIndicatorCategoryRemoveApi} from "../../../api/dynamicdata/admin/dynamicDataIndicatorCategoryAdminApi"
import {pageFormItems} from "../../../components/dynamicdata/admin/dynamicDataIndicatorCategoryManage";
import {exportTemplate} from "../../../api/dynamicdata/admin/dynamicDataIndicatorCategoryAdminApi";
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
      prop: 'dynamicDataCategoryName',
      label: '动态数据分类名称',
    },
    {
      prop: 'name',
      label: '动态数据指标分类名称',
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
      prop: 'typeDictName',
      label: '动态数据指标分类类型',
    },
    {
      prop: 'dynamicDataIndicatorNum',
      label: '指标数量',
    },
    {
      prop: 'dynamicDataIndicatorCategoryDataNum',
      label: '数据数量',
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
  permission: 'admin:web:dynamicDataIndicatorCategory:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDynamicDataIndicatorCategoryPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dynamicDataIndicatorCategoryPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dynamicDataIndicatorCategory:dataPageQuery',
      // 跳转到编辑
      route: {path: '/admin/dynamicDataIndicatorCategoryManageData',query: idData}
    },
    {
      txt: '编辑',
      text: true,
      position: 'more',
      permission: 'admin:web:dynamicDataIndicatorCategory:update',
      // 跳转到编辑
      route: {path: '/admin/DynamicDataIndicatorCategoryManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      position: 'more',
      permission: 'admin:web:dynamicDataIndicatorCategory:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？将级联删除关联的指标信息，删除后数据（包括导入的数据）无法恢复，请谨慎操作！`,
      // 删除操作
      method(){
        return dynamicDataIndicatorCategoryRemoveApi({id: row.id}).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '导出指标分类模板',
      text: true,
      position: 'more',
      permission: 'admin:web:dynamicDataIndicatorCategory:exportTemplate',
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
      permission: 'admin:web:dynamicDataIndicatorCategory:uploadImportData',
      // 跳转到编辑
      route: {path: '/admin/dynamicDataIndicatorCategoryManageImportData',query: idData}
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
      <PtButton permission="admin:web:dynamicDataIndicatorCategory:create" route="/admin/DynamicDataIndicatorCategoryManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDynamicDataIndicatorCategoryPageApi"
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
