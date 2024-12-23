<script setup name="NavigationStaticDeployManagePage" lang="ts">
/**
 * 导航网站静态部署管理页面
 */
import {reactive, ref} from 'vue'
import {
  page as navigationStaticDeployPageApi,
  remove as navigationStaticDeployRemoveApi
} from "../../api/admin/navigationStaticDeployAdminApi"
import {pageFormItems} from "../../components/admin/navigationStaticDeployManage";


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
      label: '部署编码',
    },
    {
      prop: 'name',
      label: '部署名称',
    },
    {
      prop: 'frontDomain',
      label: '域名地址',
      showOverflowTooltip: true,
    },
    {
      prop: 'frontContextPath',
      label: '子一级路径',
    },
    {
      prop: 'frontSubContextPath',
      label: '子二级路径',
    },
    {
      prop: 'isPureStaticDeploy',
      label: '是否纯静态部署',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '纯静态部署' : '半静态部署'
      }
    },
    {
      prop: 'deployPath',
      label: '部署路径',
      showOverflowTooltip: true,
    },
    {
      prop: 'deployPostGroovyScript',
      label: '部署后脚本',
      showOverflowTooltip: true,
    },
    {
      prop: 'lastDeployAt',
      label: '上一次部署时间',
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
  permission: 'admin:web:navigationStaticDeploy:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doNavigationStaticDeployPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return navigationStaticDeployPageApi({...reactiveData.form,...pageQuery})
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
      txt: '部署',
      text: true,
      disabled: row.code == 'backend_static_deploy',
      disabledReason: row.code == 'backend_static_deploy' ? '该数据为系统后端动态配置，不支持部署' : undefined,
      permission: 'admin:web:navigationStaticDeploy:deploy',
      // 跳转到编辑
      route: {path: '/admin/NavigationStaticDeployManageDeploy',query: idData}
    },
    {
      txt: '编辑',
      text: true,
      position: 'more',
      permission: 'admin:web:navigationStaticDeploy:update',
      // 跳转到编辑
      route: {path: '/admin/NavigationStaticDeployManageUpdate',query: idData}
    },

    {
      txt: '删除',
      text: true,
      position: 'more',
      permission: 'admin:web:navigationStaticDeploy:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return navigationStaticDeployRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:navigationStaticDeploy:create" route="/admin/NavigationStaticDeployManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doNavigationStaticDeployPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"

           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="180">
        <template #default="{row, column, $index}">
          <PtButtonGroup :options="getTableRowButtons({row, column, $index})" :dropdownTriggerButtonOptions="{  text: true,buttonText: '更多'}">
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
