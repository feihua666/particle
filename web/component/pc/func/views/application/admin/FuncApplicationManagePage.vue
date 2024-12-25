<script setup name="FuncApplicationManagePage" lang="ts">
/**
 * 功能应用管理页面
 */
import {reactive, ref} from 'vue'
import {
  page as FuncApplicationPageApi,
  remove as FuncApplicationRemoveApi
} from "../../../api/application/admin/funcApplicationAdminApi"
import {pageFormItems} from "../../../components/application/admin/funcApplicationManage";


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
      label: '应用编码',
      showOverflowTooltip: true
    },
    {
      prop: 'name',
      label: '应用名称',
      showOverflowTooltip: true
    },
    {
      prop: 'parentName',
      label: '父级',
      showOverflowTooltip: true
    },
    {
      prop: 'isGroup',
      label: '是否分组',
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? '应用组' : '应用'
        return r
      }
    },
    {
      prop: 'applicationLogoUrl',
      label: '应用logo',
      columnView: 'image'
    },
    {
      prop: 'applicationIconUrl',
      label: '应用图标',
      columnView: 'image'
    },
    {
      prop: 'isDisabled',
      label: '是否禁用',
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? '禁用' : '启用'
        if(cellValue && row.disabledReason){
          r = r + `(${row.disabledReason})`
        }
        return r
      }
    },
    {
      prop: 'applicationTheme',
      label: '应用主题',
      showOverflowTooltip: true
    },
    {
      prop: 'applicationDefaultRoute',
      label: '默认路由',
      showOverflowTooltip: true
    },
    {
      prop: 'configJson',
      label: '配置json',
      showOverflowTooltip: true
    },

    {
      prop: 'seq',
      label: '排序',
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
  permission: 'admin:web:funcApplication:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doFuncApplicationPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return FuncApplicationPageApi({...reactiveData.form,...pageQuery})
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
  let funcApplicationAssignFuncRouteQuery = {funcApplicationId: row.id,funcApplicationName: row.name}

  let tableRowButtons = [
    {
      txt: '分配功能',
      text: true,
      permission: 'admin:web:funcApplicationFuncRel:funcApplicationAssignFunc',
      route: {path: '/admin/funcApplicationAssignFunc',query: funcApplicationAssignFuncRouteQuery}
    },
    {
      txt: '编辑',
      position: 'more',
      text: true,
      permission: 'admin:web:funcApplication:update',
      // 跳转到编辑
      route: {path: '/admin/FuncApplicationManageUpdate',query: idData}
    },

    {
      txt: '删除',
      position: 'more',
      text: true,
      permission: 'admin:web:funcApplication:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return FuncApplicationRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:funcApplication:create" route="/admin/FuncApplicationManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doFuncApplicationPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           :dataMethodResultHandleConvertToTree="true"
           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="220">
        <template #default="{row, column, $index}">
          <PtButtonGroup :options="getTableRowButtons({row, column, $index})"  :dropdownTriggerButtonOptions="{  text: true,buttonText: '更多'}">
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
