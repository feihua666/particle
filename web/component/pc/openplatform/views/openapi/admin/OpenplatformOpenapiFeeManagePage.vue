<script setup name="OpenplatformOpenapiFeeManagePage" lang="ts">
/**
 * 开放平台开放接口费用管理页面
 */
import {reactive, ref} from 'vue'
import {
  page as openplatformOpenapiFeePageApi,
  remove as openplatformOpenapiFeeRemoveApi
} from "../../../api/openapi/admin/openplatformOpenapiFeeAdminApi"
import {pageFormItems} from "../../../components/openapi/admin/openplatformOpenapiFeeManage";


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
      label: '名称',
    },
    {
      prop: 'price',
      label: '单价',
    },
    {
      prop: 'feeTypeDictName',
      label: '计费方式',
    },
    {
      prop: 'feeTypeJson',
      label: '按计费方式配置',
    },
    {
      prop: 'deduplicateTypeDictName',
      label: '去重方式',
    },
    {
      prop: 'isDeduplicateByParameter',
      label: '按入参去重',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '按入参去重' : '按接口去重'
      }
    },
    {
      prop: 'deduplicateCount',
      label: '去重条数',
    },
    {
      prop: 'isCheckHasValue',
      label: '检查返回值',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '检查返回值' : '不检查返回值'
      }
    },
    {
      prop: 'isCheckHandleDuration',
      label: '检查处理时长',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '检查处理时长' : '不检查处理时长'
      }
    },
    {
      prop: 'handleDuration',
      label: '处理时长（ms）',
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
  permission: 'admin:web:openplatformOpenapiFee:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doOpenplatformOpenapiFeePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return openplatformOpenapiFeePageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:openplatformOpenapiFee:update',
      // 跳转到编辑
      route: {path: '/admin/OpenplatformOpenapiFeeManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:openplatformOpenapiFee:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return openplatformOpenapiFeeRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:openplatformOpenapiFee:create" route="/admin/OpenplatformOpenapiFeeManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doOpenplatformOpenapiFeePageApi"
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
