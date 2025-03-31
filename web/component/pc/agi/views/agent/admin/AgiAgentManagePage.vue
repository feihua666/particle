<script setup name="AgiAgentManagePage" lang="ts">
/**
 * 智能体管理页面
 */
import {reactive, ref} from 'vue'
import { page as agiAgentPageApi, remove as agiAgentRemoveApi} from "../../../api/agent/admin/agiAgentAdminApi"
import {pageFormItems} from "../../../components/agent/admin/agiAgentManage";
import {v4 as uuidv4} from 'uuid';

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
      label: '智能体名称',
      showOverflowTooltip: true
    },
    {
      prop: 'avatar',
      label: '智能体头像',
      columnView: 'image',
      width: 80
    },
    {
      prop: 'profile',
      label: '智能体简介',
      showOverflowTooltip: true
    },
    {
      prop: 'role',
      label: '角色设定',
      showOverflowTooltip: true
    },
    {
      prop: 'isUsePrologue',
      label: '是否使用开场白',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '使用' : '不使用'
      }

    },
    {
      prop: 'prologue',
      label: '开场白开场文案',
      showOverflowTooltip: true
    },
    {
      prop: 'prologueQuestionsJson',
      label: '开场白问题',
      showOverflowTooltip: true
    },
    {
      prop: 'isAutoPrologue',
      label: '是否自动生成开场白开场文案',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '使用' : '不使用'
      }
    },
    {
      prop: 'isUseAutoAsk',
      label: '是否使用自动追问',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '使用' : '不使用'
      }
    },
    {
      prop: 'presetDialogueJson',
      label: '预设对话',
      showOverflowTooltip: true
    },
    {
      prop: 'autoAskRule',
      label: '自动追问规则',
      showOverflowTooltip: true
    },
    {
      prop: 'quickInstructionsJson',
      label: '快捷指令',
      showOverflowTooltip: true
    },
    {
      prop: 'isUseOnlineSearch',
      label: '是否使用联网搜索',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '使用' : '不使用'
      }
    },
    {
      prop: 'isUseKnowledgeBase',
      label: '是否使用知识库',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '使用' : '不使用'
      }
    },
    {
      prop: 'agiKnowledgeBaseId',
      label: '知识库id',
      showOverflowTooltip: true
    },
    {
      prop: 'isUseMcp',
      label: '是否使用mcp',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '使用' : '不使用'
      }
    },
    {
      prop: 'mcpPluginsJson',
      label: 'mcp插件配置',
      showOverflowTooltip: true
    },
    {
      prop: 'isUseLongTermMemory',
      label: '是否使用长期记忆',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '使用' : '不使用'
      }

    },
    {
      prop: 'isUseVoice',
      label: '是否使用声音',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '使用' : '不使用'
      }
    },
    {
      prop: 'voiceJson',
      label: '声音配置',
      showOverflowTooltip: true
    },
    {
      prop: 'modelJson',
      label: '模型配置',
      showOverflowTooltip: true
    },
    {
      prop: 'historyMessageMaxLength',
      label: '附带历史消息数',
    },
    {
      prop: 'historyMessageCompressionThreshold',
      label: '历史消息长度压缩阈值',
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
  permission: 'admin:web:agiAgent:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doAgiAgentPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return agiAgentPageApi({...reactiveData.form,...pageQuery})
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
  let idChatData = {id: row.id,chatId: uuidv4()}

  let tableRowButtons = [
    {
      txt: '发起对话',
      text: true,
      permission: 'front:web:agiAgent:chatStream',
      // 跳转到对话
      route: {path: '/admin/agiAgentChatPage',query: idChatData}
    },
    {
      txt: '编辑',
      text: true,
      permission: 'admin:web:agiAgent:update',
      // 跳转到编辑
      route: {path: '/admin/AgiAgentManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:agiAgent:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return agiAgentRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:agiAgent:create" route="/admin/AgiAgentManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doAgiAgentPageApi"
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
