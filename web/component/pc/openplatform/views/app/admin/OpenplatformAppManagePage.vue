<script setup name="OpenplatformAppManagePage" lang="ts">
/**
 * 开放平台应用管理页面
 */
import {reactive, ref} from 'vue'
import { page as openplatformAppPageApi, remove as openplatformAppRemoveApi} from "../../../api/app/admin/openplatformAppAdminApi"
import {pageFormItems} from "../../../compnents/app/admin/openplatformAppManage";


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
      showOverflowTooltip: true,
    },
    {
      prop: 'appId',
      label: 'appId',
      showOverflowTooltip: true,
    },
    {
      prop: 'ownerUserNickname',
      label: '归属用户昵称',
    },
    {
      prop: 'ownerCustomerName',
      label: '归属客户',
    },
    {
      prop: 'requestAlgorithmSecretJson',
      label: '请求配置',
      showOverflowTooltip: true,
    },
    {
      prop: 'responseAlgorithmSecretJson',
      label: '响应配置',
      showOverflowTooltip: true,
    },
    {
      prop: 'scopes',
      label: '访问范围配置',
      showOverflowTooltip: true,
    },
    {
      prop: 'openplatformOpenapiFeeName',
      label: '计费规则',
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
      prop: 'isCheckSignature',
      label: '是否检查签名',
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? '检查' : '忽略'
        return r
      }
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:OpenplatformApp:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doOpenplatformAppPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return openplatformAppPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:OpenplatformApp:update',
      // 跳转到编辑
      route: {path: '/admin/OpenplatformAppManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:OpenplatformApp:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return openplatformAppRemoveApi({id: row.id}).then(res => {
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
          labelWidth="120"
          :method="submitMethod"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          inline
          :comps="reactiveData.formComps">
    <template #buttons>
      <PtButton permission="admin:web:OpenplatformApp:create" route="/admin/OpenplatformAppManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doOpenplatformAppPageApi"
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