<script setup name="DataQueryDatasourceManagePage" lang="ts">
/**
 * 数据查询数据源管理页面
 */
import {reactive, ref} from 'vue'
import {
  copy as DataQueryDatasourceCopyApi,
  page as DataQueryDatasourcePageApi,
  remove as DataQueryDatasourceRemoveApi
} from "../../../api/datasource/admin/dataQueryDatasourceAdminApi"
import {pageFormItems} from "../../../components/datasource/admin/dataQueryDatasourceManage";

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
      showOverflowTooltip: true
    },
    {
      prop: 'typeDictName',
      label: '类型',
    },
    {
      prop: 'configJson',
      label: 'json配置',
      showOverflowTooltip: true
    },
    {
      prop: 'username',
      label: '用户名',
      showOverflowTooltip: true
    },
    {
      prop: 'password',
      label: '密码',
      showOverflowTooltip: true
    },
    {
      prop: 'dataQueryProviderName',
      label: '数据查询供应商',
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
  permission: 'admin:web:dataQueryDatasource:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataQueryDatasourcePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return DataQueryDatasourcePageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataQueryDatasource:update',
      // 跳转到编辑
      route: {path: '/admin/DataQueryDatasourceManageUpdate',query: idData}
    },
    {
      txt: '复制',
      text: true,
      position: 'more',
      permission: 'admin:web:dataQueryDatasource:copy',
      methodConfirmText: `确定要复制 ${row.name} 吗？`,
      methodSuccess: (res) => {
        reactiveData.form.name = res.data.data.name
        // 复制成功后刷新一下表格
        submitMethod()
      },
      // 复制操作
      method(){
        return DataQueryDatasourceCopyApi({id: row.id}).then(res => {
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '重新加载',
      text: true,
      position: 'more',
      permission: 'admin:web:dataQueryDatasource:reload',
      // 跳转到重新加载数据源页面
      route: {path: '/admin/DataQueryDatasourceManageReload',query: idData}
    },
    {
      txt: '删除',
      text: true,
      position: 'more',
      permission: 'admin:web:dataQueryDatasource:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return DataQueryDatasourceRemoveApi({id: row.id}).then(res => {
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
          labelWidth="100"
          :comps="reactiveData.formComps">
    <template #buttons>
      <PtButton permission="admin:web:dataQueryDatasource:create" route="/admin/DataQueryDatasourceManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataQueryDatasourcePageApi"
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
