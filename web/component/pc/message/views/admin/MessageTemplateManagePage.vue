<script setup name="MessageTemplateManagePage" lang="ts">
/**
 * 消息模板管理页面
 */
import {reactive, ref} from 'vue'
import { page as messageTemplatePageApi, remove as messageTemplateRemoveApi} from "../../api/admin/messageTemplateAdminApi"
import {pageFormItems} from "../../components/admin/messageTemplateManage";


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
      label: '模板名称',
    },
    {
      prop: 'code',
      label: '编码',
    },

    {
      prop: 'titleTpl',
      label: '消息标题模板',
      showOverflowTooltip: true
    },
    {
      prop: 'contentTpl',
      label: '消息内容模板',
      showOverflowTooltip: true
    },
    {
      prop: 'typeDictName',
      label: '消息模板分类',
    },
    {
      prop: 'isGroup',
      label: '分组/模板',
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? '分组' : '模板'
        return r
      }
    },
    {
      prop: 'remark',
      label: '描述',
    },
    {
      prop: 'seq',
      label: '排序',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:messageTemplate:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doMessageTemplatePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return messageTemplatePageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:messageTemplate:update',
      // 跳转到编辑
      route: {path: '/admin/MessageTemplateManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:messageTemplate:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return messageTemplateRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:messageTemplate:create" route="/admin/MessageTemplateManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doMessageTemplatePageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           :dataMethodResultHandleConvertToTree="true"
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