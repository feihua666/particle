<script setup name="OpenplatformDocApiDocResponseCodeManagePage" lang="ts">
/**
 * 开放接口文档响应码管理页面
 */
import {reactive, ref} from 'vue'
import {
  page as openplatformDocApiDocResponseCodePageApi,
  remove as openplatformDocApiDocResponseCodeRemoveApi
} from "../../../api/doc/admin/openplatformDocApiDocResponseCodeAdminApi"
import {pageFormItems} from "../../../components/doc/admin/openplatformDocApiDocResponseCodeManage";


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
      label: '业务编码',
    },
    {
      prop: 'codeStatus',
      label: '业务状态码',
    },
    {
      prop: 'httpCode',
      label: 'http状态码',
    },
    {
      prop: 'isCharge',
      label: '是否计费',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '是' : '否'
      }
    },
    {
      prop: 'explanation',
      label: '字段说明',
    },
    {
      prop: 'openplatformDocApiName',
      label: '文档接口',
      showOverflowTooltip: true
    },
    {
      prop: 'openplatformDocApiCode',
      label: '文档接口编码',
      showOverflowTooltip: true
    },
    {
      prop: 'openplatformDocApiDocRequestUrl',
      label: '文档内容',
      showOverflowTooltip: true
    },
    {
      prop: 'isGlobal',
      label: '是否全局通用码',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '是' : '否'
      }
    },
    {
      prop: 'seq',
      label: '排序',
    },
    {
      prop: 'remark',
      label: '描述',
      showOverflowTooltip: true,
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:openplatformDocApiDocResponseCode:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doOpenplatformDocApiDocResponseCodePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return openplatformDocApiDocResponseCodePageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:openplatformDocApiDocResponseCode:update',
      // 跳转到编辑
      route: {path: '/admin/OpenplatformDocApiDocResponseCodeManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:openplatformDocApiDocResponseCode:delete',
      methodConfirmText: `确定要删除 ${row.code} 吗？`,
      // 删除操作
      method(){
        return openplatformDocApiDocResponseCodeRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:openplatformDocApiDocResponseCode:create" route="/admin/OpenplatformDocApiDocResponseCodeManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doOpenplatformDocApiDocResponseCodePageApi"
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
