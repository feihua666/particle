<script setup name="AgiAgentChatMessageManagePage" lang="ts">
/**
 * 智能体对话消息管理页面
 */
import {reactive, ref} from 'vue'
import { page as agiAgentChatMessagePageApi, remove as agiAgentChatMessageRemoveApi} from "../../../api/chat/admin/agiAgentChatMessageAdminApi"
import {pageFormItems} from "../../../components/chat/admin/agiAgentChatMessageManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'agiAgentChatId',
      label: '智能体对话id',
    },
    {
      prop: 'agiAgentId',
      label: '智能体id',
    },
    {
      prop: 'chatId',
      label: '对话id',
    },
    {
      prop: 'userId',
      label: '用户id',
    },
    {
      prop: 'messageType',
      label: '消息类型',
    },
    {
      prop: 'content',
      label: '消息内容',
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
  permission: 'admin:web:agiAgentChatMessage:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doAgiAgentChatMessagePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return agiAgentChatMessagePageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:agiAgentChatMessage:update',
      // 跳转到编辑
      route: {path: '/admin/AgiAgentChatMessageManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:agiAgentChatMessage:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return agiAgentChatMessageRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:agiAgentChatMessage:create" route="/admin/AgiAgentChatMessageManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doAgiAgentChatMessagePageApi"
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