<script setup name="AgiAgentChatHistory" lang="ts">
/**
 * 智能体对话历史
 */
import {reactive, ref} from 'vue'
import {pageHistoryFormItems} from "../../../components/chat/admin/agiAgentChatManage";
import {page as agiAgentChatPageApi,remove as agiAgentChatRemoveApi} from "../../../api/chat/front/agiAgentChatFrontApi";

const tableRef = ref(null)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  agiAgentId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    agiAgentId: props.agiAgentId,
    orderBy: 'createAt-0'
  },
  formComps: pageHistoryFormItems,
  tableColumns: [

    {
      prop: 'title',
      label: '对话标题',
    },
    {
      prop: 'titleMemo',
      label: '对话标题说明',
    },
    {
      prop: 'createAt',
      label: '创建时间',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'front:web:agiAgentChat:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doAgiAgentChatPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return agiAgentChatPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'front:web:agiAgentChat:update',
      // 跳转到编辑
      route: {path: '/front/agiAgentChatUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'front:web:agiAgentChat:delete',
      methodConfirmText: `确定要删除 ${row.title} 吗？`,
      // 删除操作
      method(){
        return agiAgentChatRemoveApi({id: row.id}).then(res => {
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
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doAgiAgentChatPageApi"
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

</template>


<style scoped>

</style>
