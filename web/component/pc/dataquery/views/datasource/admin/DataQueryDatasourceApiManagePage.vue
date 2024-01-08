<script setup name="DataQueryDatasourceApiManagePage" lang="ts">
/**
 * 数据查询数据源接口管理页面
 */
import {reactive, ref} from 'vue'
import { page as DataQueryDatasourceApiPageApi, remove as DataQueryDatasourceApiRemoveApi, copy as DataQueryDatasourceApiCopyApi} from "../../../api/datasource/admin/dataQueryDatasourceApiAdminApi"
import {pageFormItems} from "../../../compnents/datasource/admin/dataQueryDatasourceApiManage";


const tableRef = ref(null)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  name: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    name: props.name
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
      prop: 'isSupportTrans',
      label: '翻译支持',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '支持' : '不支持'
      }
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
  permission: 'admin:web:dataQueryDatasourceApi:pageQuery'
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
      permission: 'admin:web:dataQueryDatasourceApi:update',
      // 跳转到编辑
      route: {path: '/admin/DataQueryDatasourceApiManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataQueryDatasourceApi:delete',
      methodConfirmText: `可能存在数据查询引用（包括直连引用或通过编码脚本调用）请确保安全，确定要删除 ${row.name} 吗？`,
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
      txt: '复制',
      text: true,
      permission: 'admin:web:dataQueryDatasourceApi:copy',
      methodConfirmText: `确定要复制 ${row.name} 吗？`,
      methodSuccess: '复制成功，请刷新数据后查看',
      // 复制操作
      method(){
        return DataQueryDatasourceApiCopyApi({id: row.id}).then(res => {
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '接口测试',
      text: true,
      permission: 'admin:web:dataQueryDatasourceApi:test',
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
      <PtButton permission="admin:web:dataQueryDatasourceApi:create" route="/admin/DataQueryDatasourceApiManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataQueryDatasourceApiPageApi"
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