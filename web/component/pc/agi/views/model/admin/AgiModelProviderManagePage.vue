<script setup name="AgiModelProviderManagePage" lang="ts">
/**
 * AI模型提供商管理页面
 */
import {reactive, ref} from 'vue'
import { page as agiModelProviderPageApi, remove as agiModelProviderRemoveApi} from "../../../api/model/admin/agiModelProviderAdminApi"
import {pageFormItems} from "../../../components/model/admin/agiModelProviderManage";


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
      label: '提供商编码',
    },
    {
      prop: 'name',
      label: '提供商名称',
    },
    {
      prop: 'typeDictName',
      label: '提供商类型',
    },
    {
      prop: 'baseUrl',
      label: '基础地址',
      showOverflowTooltip: true,
    },
    {
      prop: 'isDisabled',
      label: '是否禁用',
      width: 80,
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? '禁用' : '启用'
        return r
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
  permission: 'admin:web:agiModelProvider:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doAgiModelProviderPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return agiModelProviderPageApi({...reactiveData.form,...pageQuery})
}
const tablePaginationProps = {
  permission: submitAttrs.value.permission
}
// 表格操作按钮
const getTableRowButtons = ({row, column, $index}) => {
  if($index < 0){
    return []
  }
    let dt = {__dt: row.name}
  let idData = {id: row.id,...dt}

  let tableRowButtons = [
    {
      txt: '编辑',
      text: true,
      permission: 'admin:web:agiModelProvider:update',
      // 跳转到编辑
      route: {path: '/admin/AgiModelProviderManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:agiModelProvider:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return agiModelProviderRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:agiModelProvider:create" route="/admin/AgiModelProviderManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doAgiModelProviderPageApi"
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
