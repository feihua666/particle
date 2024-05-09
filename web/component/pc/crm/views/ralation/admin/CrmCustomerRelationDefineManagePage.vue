<script setup name="CrmCustomerRelationDefineManagePage" lang="ts">
/**
 * 客户关系定义管理页面
 */
import {reactive, ref} from 'vue'
import { page as crmCustomerRelationDefinePageApi, remove as crmCustomerRelationDefineRemoveApi} from "../../../api/ralation/admin/crmCustomerRelationDefineAdminApi"
import {pageFormItems} from "../../../compnents/ralation/admin/crmCustomerRelationDefineManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'name',
      label: '关系定义名称',
    },
    {
      prop: 'code',
      label: '关系定义编码',
    },
    {
      prop: 'isBidirectional',
      label: '关系类型',
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? '双向关系' : '单向关系'
        return r
      }
    },
    {
      prop: 'bidirectionalName',
      label: '对应双向关系',
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
  permission: 'admin:web:crmCustomerRelationDefine:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doCrmCustomerRelationDefinePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return crmCustomerRelationDefinePageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:crmCustomerRelationDefine:update',
      // 跳转到编辑
      route: {path: '/admin/CrmCustomerRelationDefineManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:crmCustomerRelationDefine:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return crmCustomerRelationDefineRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:crmCustomerRelationDefine:create" route="/admin/CrmCustomerRelationDefineManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doCrmCustomerRelationDefinePageApi"
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