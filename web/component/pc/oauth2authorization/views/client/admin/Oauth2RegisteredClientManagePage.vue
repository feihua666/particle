<script setup name="Oauth2RegisteredClientManagePage" lang="ts">
/**
 * oauth2客户端管理页面
 */
import {reactive, ref} from 'vue'
import { page as oauth2RegisteredClientPageApi, remove as oauth2RegisteredClientRemoveApi} from "../../../api/client/admin/oauth2RegisteredClientAdminApi"
import {pageFormItems} from "../../../compnents/client/admin/oauth2RegisteredClientManage";
import {copy} from "../../../../../../global/pc/element-plus/ElClipboardTools";
const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'clientName',
      label: '客户端名称',
    },
    {
      prop: 'clientId',
      label: '客户端ID',
      showOverflowTooltip: true
    },
    {
      prop: 'clientSecret',
      label: '秘钥',
      showOverflowTooltip: true
    },
    {
      prop: 'clientSecretExpiresAt',
      label: '秘钥过期时间',
      showOverflowTooltip: true
    },
    {
      prop: 'clientIdIssuedAt',
      label: '发布时间',
      showOverflowTooltip: true
    },
    {
      prop: 'clientAuthenticationMethods',
      label: '身份验证方法',
      showOverflowTooltip: true
    },
    {
      prop: 'authorizationGrantTypes',
      label: '授权类型',
      showOverflowTooltip: true
    },
    {
      prop: 'redirectUris',
      label: '定向URI',
      showOverflowTooltip: true
    },
    {
      prop: 'scopes',
      label: '访问范围',
      showOverflowTooltip: true
    },
    {
      prop: 'clientSettings',
      label: '客户端设置',
      showOverflowTooltip: true
    },
    {
      prop: 'tokenSettings',
      label: '令牌设置',
      showOverflowTooltip: true
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:oauth2RegisteredClient:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doOauth2RegisteredClientPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return oauth2RegisteredClientPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:oauth2RegisteredClient:update',
      // 跳转到编辑
      route: {path: '/admin/Oauth2RegisteredClientManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      position: 'more',
      permission: 'admin:web:oauth2RegisteredClient:delete',
      methodConfirmText: `确定要删除 ${row.clientName} 吗？`,
      // 删除操作
      method(){
        return oauth2RegisteredClientRemoveApi({id: row.id}).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '复制',
      text: true,
      position: 'more',
      // 删除操作
      method(){
        copy(`clientId: ${row.clientId}\n clientSecret: ${row.clientSecret}`)
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
      <PtButton permission="admin:web:oauth2RegisteredClient:create" route="/admin/Oauth2RegisteredClientManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doOauth2RegisteredClientPageApi"
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