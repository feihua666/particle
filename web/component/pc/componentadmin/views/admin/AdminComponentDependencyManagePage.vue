<script setup name="AdminComponentDependencyManagePage" lang="ts">
/**
 * 组件依赖关系管理页面
 */
import {reactive, ref} from 'vue'
import { page as adminComponentDependencyPageApi, remove as adminComponentDependencyRemoveApi} from "../../api/admin/adminComponentDependencyAdminApi"
import {pageFormItems} from "../../components/admin/adminComponentDependencyManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'componentCode',
      label: '源组件名称',
    },
    {
      prop: 'componentName',
      label: '源组件中文名称',
    },
    {
      prop: 'dependComponentCode',
      label: '依赖组件名称',
    },
    {
      prop: 'dependComponentName',
      label: '依赖组件名称',
    },
    {
      prop: 'isRequired',
      label: '是否必需',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '必须' : '可选'
      }
    },
    {
      prop: 'remark',
      label: '备注',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:adminComponentDependency:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doAdminComponentDependencyPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return adminComponentDependencyPageApi({...reactiveData.form,...pageQuery})
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
  let dependComponentAssignComponentRouteQuery = {dependComponentId: row.dependComponentId,dependComponentName: row.dependComponentName}
  let deleteByDependComponentIdRouteQuery = {dependComponentId: row.dependComponentId,dependComponentName: row.dependComponentName}

  let componentAssignDependComponentRouteQuery = {componentId: row.componentId,componentName: row.componentName}
  let deleteByComponentIdRouteQuery = {componentId: row.componentId,componentName: row.componentName}

  let tableRowButtons = [
    {
      txt: '为该依赖组件分配源组件',
      text: true,
      position: 'more',
      permission: 'admin:web:adminComponentDependency:dependComponentAssignComponent',
      route: {path: '/admin/adminComponentDependencyManageDependComponentAssignComponent',query: dependComponentAssignComponentRouteQuery}
    },
    {
      txt: '为该源组件分配依赖组件',
      text: true,
      position: 'more',
      permission: 'admin:web:adminComponentDependency:componentAssignDependComponent',
      route: {path: '/admin/adminComponentDependencyManageComponentAssignDependComponent',query: componentAssignDependComponentRouteQuery}
    },
    {
      txt: '为该依赖组件清空源组件',
      text: true,
      position: 'more',
      methodConfirmText: `您将清空依赖组件 ${row.dependComponentName} 所有源组件,该依赖组件将不再分配给任何源组件，同时拥有涉及对应源组件的用户依赖组件将受到影响，请谨慎操作！！！，确定要清空吗？`,
      permission: 'admin:web:adminComponentDependency:deleteByDependComponentId',
      route: {path: '/admin/adminComponentDependencyManageDeleteByDependComponentId',query: deleteByDependComponentIdRouteQuery}
    },
    {
      txt: '为该源组件清空依赖组件',
      text: true,
      position: 'more',
      methodConfirmText: `您将清空源组件 ${row.componentName} 所有依赖组件,该源组件将不再拥有任何依赖组件，同时拥有该源组件的用户依赖组件将受到影响，请谨慎操作！！！，确定要清空吗？`,
      permission: 'admin:web:adminComponentDependency:deleteByComponentId',
      route: {path: '/admin/adminComponentDependencyManageDeleteByComponentId',query: deleteByComponentIdRouteQuery}
    },
    {
      txt: '编辑',
      text: true,
      permission: 'admin:web:adminComponentDependency:update',
      // 跳转到编辑
      route: {path: '/admin/adminComponentDependencyManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      position: 'more',
      permission: 'admin:web:adminComponentDependency:delete',
      methodConfirmText: `删除后源组件 ${row.componentName} 将不再拥有依赖组件 ${row.dependComponentName}，确定要删除吗？`,
      // 删除操作
      method(){
        return adminComponentDependencyRemoveApi(idData).then(res => {
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
      <PtButton permission="admin:web:adminComponentDependency:create" route="/admin/AdminComponentDependencyManageAdd">添加</PtButton>
      <PtButton permission="admin:web:adminComponentDependency:dependComponentAssignComponent" route="/admin/adminComponentDependencyManageDependComponentAssignComponent">依赖组件分配源组件</PtButton>
      <PtButton permission="admin:web:adminComponentDependency:componentAssignDependComponent" route="/admin/adminComponentDependencyManageComponentAssignDependComponent">源组件分配依赖组件</PtButton>

      <PtButton permission="admin:web:adminComponentDependency:deleteByDependComponentId" route="/admin/adminComponentDependencyManageDeleteByDependComponentId">清空依赖组件源组件</PtButton>
      <PtButton permission="admin:web:adminComponentDependency:deleteByComponentId" route="/admin/adminComponentDependencyManageDeleteByComponentId">清空源组件依赖组件</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doAdminComponentDependencyPageApi"
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
