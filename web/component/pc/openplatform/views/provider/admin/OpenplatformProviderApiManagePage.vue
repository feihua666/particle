<script setup name="OpenplatformProviderApiManagePage" lang="ts">
/**
 * 开放平台供应商接口管理页面
 */
import {reactive, ref} from 'vue'
import { page as openplatformProviderApiPageApi, remove as openplatformProviderApiRemoveApi} from "../../../api/provider/admin/openplatformProviderApiAdminApi"
import {pageFormItems} from "../../../components/provider/admin/openplatformProviderApiManage";


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
      label: '供应商名称',
    },
    {
      prop: 'dataQueryDatasourceApiId',
      label: '数据查询数据源接口id',
    },
    {
      prop: 'openplatformOpenapiFeeId',
      label: '计费id',
    },
    {
      prop: 'requestUrl',
      label: '请求地址',
    },
    {
      prop: 'scriptTypeDictValue',
      label: '脚本类型',
    },
    {
      prop: 'scriptContent',
      label: '脚本内容',
    },
    {
      prop: 'readTimeout',
      label: '读取等待时间',
    },
    {
      prop: 'connectTimeout',
      label: '连接等待时间',
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
  permission: 'admin:web:openplatformProviderApi:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doOpenplatformProviderApiPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return openplatformProviderApiPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:openplatformProviderApi:update',
      // 跳转到编辑
      route: {path: '/admin/OpenplatformProviderApiManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:openplatformProviderApi:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return openplatformProviderApiRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:openplatformProviderApi:create" route="/admin/OpenplatformProviderApiManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doOpenplatformProviderApiPageApi"
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