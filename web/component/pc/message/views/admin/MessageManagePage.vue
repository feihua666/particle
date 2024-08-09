<script setup name="MessageManagePage" lang="ts">
/**
 * 消息管理页面
 */
import {reactive, ref} from 'vue'
import { page as messagePageApi, remove as messageRemoveApi} from "../../api/admin/messageAdminApi"
import {pageFormItems} from "../../components/admin/messageManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'title',
      label: '消息标题',
      showOverflowTooltip: true,
    },
    {
      prop: 'shortContent',
      label: '消息简短内容',
      showOverflowTooltip: true,
    },
    {
      prop: 'content',
      label: '消息内容',
      showOverflowTooltip: true,
    },

    {
      prop: 'typeDictName',
      label: '消息分类',
    },
    {
      prop: 'sendStatusDictName',
      label: '发送状态',
    },
    {
      prop: 'sendUserNickname',
      label: '发送人',
    },
    {
      prop: 'sendAt',
      label: '发送时间',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:message:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doMessagePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return messagePageApi({...reactiveData.form,...pageQuery})
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
  // 已发送和发送中的不能编辑
  let editDisabled = row.sendStatusDictValue == 'sending' || row.sendStatusDictValue == 'sent'
  let tableRowButtons = [
    {
      txt: '编辑',
      text: true,
      permission: 'admin:web:message:update',
      disabled: editDisabled,
      disabledReason: editDisabled ? '已发送和发送中的不能编辑' : null,
      // 跳转到编辑
      route: {path: '/admin/MessageManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:message:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return messageRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:message:create" route="/admin/MessageManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doMessagePageApi"
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