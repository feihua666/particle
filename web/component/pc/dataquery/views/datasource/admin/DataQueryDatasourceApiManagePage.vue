<script setup name="DataQueryDatasourceApiManagePage" lang="ts">
/**
 * 数据查询数据源接口管理页面
 */
import {reactive, ref} from 'vue'
import { page as DataQueryDatasourceApiPageApi, remove as DataQueryDatasourceApiRemoveApi} from "../../../api/datasource/admin/DataQueryDatasourceApiAdminApi"
import {pageFormItems} from "../../../compnents/datasource/admin/dataQueryDatasourceApiManage";


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
      prop: 'categoryDictName',
      label: '分类',
    },
    {
      prop: 'dataQueryProviderName',
      label: '数据查询供应商',
    },
    {
      prop: 'dataQueryProviderDocLinkUrl',
      label: '文档链接',
    },
    {
      prop: 'dataQueryDatasourceName',
      label: '数据查询数据源',
    },
    {
      prop: 'readTimeout',
      label: '读取等待时间(ms)',
    },
    {
      prop: 'connectTimeout',
      label: '连接等待时间(ms)',
    },
    {
      prop: 'sameTag',
      label: '等同标签',
    },
    {
      prop: 'remark',
      label: '描述',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:DataQueryDatasourceApi:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataQueryDatasourceApiPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return DataQueryDatasourceApiPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:DataQueryDatasourceApi:update',
      // 跳转到编辑
      route: {path: '/admin/DataQueryDatasourceApiManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:DataQueryDatasourceApi:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return DataQueryDatasourceApiRemoveApi({id: row.id}).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '接口测试',
      text: true,
      permission: 'admin:web:DataQueryDatasourceApi:test',
      // 跳转到编辑
      route: {path: '/admin/DataQueryDatasourceApiManageTest',query: idData}
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
          labelWidth="100"
          inline
          :comps="reactiveData.formComps">
    <template #buttons>
      <PtButton permission="admin:web:DataQueryDatasourceApi:create" route="/admin/DataQueryDatasourceApiManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataQueryDatasourceApiPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           :dataMethodResultHandleConvertToTree="true"
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