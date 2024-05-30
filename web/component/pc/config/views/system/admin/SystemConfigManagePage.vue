<script setup name="SystemConfigManagePage" lang="ts">
/**
 * 系统参数配置管理页面
 */
import {reactive, ref} from 'vue'
import { page as systemConfigPageApi, remove as systemConfigRemoveApi} from "../../../api/system/admin/systemConfigAdminApi"
import {pageFormItems} from "../../../compnents/system/admin/systemConfigManage";


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
      label: '参数配置编码',
    },
    {
      prop: 'name',
      label: '参数配置名称',
    },
    {
      prop: 'value',
      label: '参数配置值',
      showOverflowTooltip: true,
    },
    {
      prop: 'isBuiltIn',
      label: '是否内置',
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? '是' : '否'
        return r
      },
    },
    {
      prop: 'isDisabled',
      label: '是否禁用',
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? '是' : '否'
        if(!cellValue && row.blackReason){
          r = r + `(${row.blackReason})`
        }
        return r
      },
    },
    {
      prop: 'tag',
      label: '标签',
      showOverflowTooltip: true,
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
  permission: 'admin:web:systemConfig:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doSystemConfigPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return systemConfigPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:systemConfig:update',
      // 跳转到编辑
      route: {path: '/admin/SystemConfigManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:systemConfig:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return systemConfigRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:systemConfig:create" route="/admin/SystemConfigManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doSystemConfigPageApi"
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