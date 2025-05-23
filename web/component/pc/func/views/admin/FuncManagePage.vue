<script setup name="FuncManagePage" lang="ts">
/**
 * 功能菜单管理页面
 */
import {reactive, ref} from 'vue'
import {page as funcPageApi, remove as funcRemoveApi, copy as funcCopyApi} from "../../api/admin/funcAdminApi"
import {pageFormItems} from "../../components/admin/funcManage";
import {funcColumns} from "../../components/funcCompItem";
const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: funcColumns,

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:func:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doFuncPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return funcPageApi({...reactiveData.form,...pageQuery})
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
      txt: '添加子级',
      text: true,
      permission: 'admin:web:func:create',
      // 跳转到编辑
      route: {path: '/admin/funcManageAdd',query: idData}
    },
    {
      txt: '编辑',
      position: 'more',
      text: true,
      permission: 'admin:web:func:update',
      // 跳转到编辑
      route: {path: '/admin/funcManageUpdate',query: idData}
    },
    {
      txt: '复制',
      text: true,
      position: 'more',
      permission: 'admin:web:func:copy',
      methodConfirmText: `确定要复制 ${row.name} 吗？`,
      methodSuccess: (res) => {
        reactiveData.form.name = res.data.data.name
        // 复制成功后刷新一下表格
        submitMethod()
      },
      // 复制操作
      method(){
        return funcCopyApi({id: row.id}).then(res => {
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '删除',

      position: 'more',
      text: true,
      permission: 'admin:web:func:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return funcRemoveApi({id: row.id}).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },

  ]
  let isPage = row.typeDictValue == 'page'
  isPage && tableRowButtons.push(
      {
        txt: '添加Crud',

        position: 'more',
        text: true,
        permission: 'admin:web:func:create',
        // 跳转到编辑
        route: {path: '/admin/funcManageCrudAdd',query: idData}
      },
  )
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
      <PtButton permission="admin:web:func:create" route="/admin/funcManageAdd">添加</PtButton>
      <PtButton permission="admin:web:func:create" route="/admin/funcManageCrudAdd">添加Crud</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doFuncPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           :dataMethodResultHandleConvertToTree="true"
           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="220">
        <template #default="{row, column, $index}">
          <PtButtonGroup :options="getTableRowButtons({row, column, $index})"  :dropdownTriggerButtonOptions="{  text: true,buttonText: '更多'}">
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
