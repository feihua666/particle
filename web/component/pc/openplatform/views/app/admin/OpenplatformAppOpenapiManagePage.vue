<script setup name="OpenplatformAppOpenapiManagePage" lang="ts">
/**
 * 开放平台应用与开放接口配置管理页面
 */
import {reactive, ref} from 'vue'
import {
  page as openplatformAppOpenapiPageApi,
  refreshCache as openplatformAppOpenapiRefreshCacheApi,
  remove as openplatformAppOpenapiRemoveApi
} from "../../../api/app/admin/openplatformAppOpenapiAdminApi"
import {pageFormItems} from "../../../components/app/admin/openplatformAppOpenapiManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'openplatformAppName',
      label: 'app名称',
    },
    {
      prop: 'openplatformOpenapiName',
      label: '开放接口名称',
    },
    {
      prop: 'openplatformOpenapiFeeName',
      label: '计费规则名称',
    },
    {
      prop: 'openplatformOpenapiLimitRuleName',
      label: '限制规则名称',
    },
    {
      prop: 'openplatformProviderName',
      label: '指定供应商名称',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:openplatformAppOpenapi:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doOpenplatformAppOpenapiPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return openplatformAppOpenapiPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:openplatformAppOpenapi:update',
      // 跳转到编辑
      route: {path: '/admin/OpenplatformAppOpenapiManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      position: 'more',
      permission: 'admin:web:openplatformAppOpenapi:delete',
      methodConfirmText: `确定要删除 ${row.openplatformAppName} 和 ${row.openplatformOpenapiName} 吗？`,
      // 删除操作
      method(){
        return openplatformAppOpenapiRemoveApi({id: row.id}).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '刷新缓存',
      text: true,
      position: 'more',
      permission: 'admin:web:openplatformAppOpenapi:refreshCache',
      methodSuccess: (res) => '刷新缓存成功,如果部署多个实例可能要多次执行。 ' + res.data.data,
      // 刷新缓存
      method(){
        return openplatformAppOpenapiRefreshCacheApi({id: row.id}).then(res => {
          return Promise.resolve(res)
        })
      }
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
      <PtButton permission="admin:web:openplatformAppOpenapi:create" route="/admin/OpenplatformAppOpenapiManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doOpenplatformAppOpenapiPageApi"
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