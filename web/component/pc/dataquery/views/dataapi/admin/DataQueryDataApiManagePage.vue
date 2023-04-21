<script setup name="DataQueryDataApiManagePage" lang="ts">
/**
 * 数据查询数据接口管理页面
 */
import {reactive, ref} from 'vue'
import { page as DataQueryDataApiPageApi, remove as DataQueryDataApiRemoveApi} from "../../../api/dataapi/admin/DataQueryDataApiAdminApi"
import {pageFormItems} from "../../../compnents/dataapi/admin/dataQueryDataApiManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'url',
      label: '接口地址',
    },
    {
      prop: 'name',
      label: '接口名称',
    },
    {
      prop: 'dataQueryDatasourceApiName',
      label: '直连数据源接口',
    },
    {
      prop: 'adaptTypeDictName',
      label: '适配类型',
    },

    {
      prop: 'inParamTypeDictName',
      label: '入参类型',
    },

    {
      prop: 'outParamTypeDictName',
      label: '出参类型',
    },

    {
      prop: 'responseTypeDictName',
      label: '输出类型',
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
  permission: 'admin:web:dataQueryDataApi:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataQueryDataApiPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return DataQueryDataApiPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataQueryDataApi:update',
      // 跳转到编辑
      route: {path: '/admin/DataQueryDataApiManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataQueryDataApi:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return DataQueryDataApiRemoveApi({id: row.id}).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '接口测试',
      text: true,
      permission: 'admin:web:dataQueryDataApi:test',
      // 跳转到编辑
      route: {path: '/admin/DataQueryDataApiManageTest',query: idData}
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
      <PtButton permission="admin:web:dataQueryDataApi:create" route="/admin/DataQueryDataApiManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataQueryDataApiPageApi"
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