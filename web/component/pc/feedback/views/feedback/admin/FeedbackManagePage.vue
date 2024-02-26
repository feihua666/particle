<script setup name="FeedbackManagePage" lang="ts">
/**
 * 意见反馈管理页面
 */
import {reactive, ref} from 'vue'
import { page as feedbackPageApi, remove as feedbackRemoveApi} from "../../../api/feedback/admin/feedbackAdminApi"
import {pageFormItems} from "../../../compnents/feedback/admin/feedbackManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'contactUsername',
      label: '联系姓名',
    },
    {
      prop: 'contactTelephone',
      label: '联系电话',
      formatter: (row, column, cellValue, index) => {
        let type = row.isContactTelephoneMobile ? '(手机号)' : ''
        return cellValue + type
      }
    },
    {
      prop: 'contactEmail',
      label: '联系邮箱',
    },
    {
      prop: 'feedbackContent',
      label: '问题建议内容',
      showOverflowTooltip: true,
    },
    {
      prop: 'feedbackAt',
      label: '问题建议时间',
    },
    {
      prop: 'feedbackUserNickname',
      label: '问题建议用户',
    },
    {
      prop: 'feedbackUserAvatar',
      label: '问题建议用户头像',
    },
    {
      prop: 'isHandle',
      label: '是否已处理',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '已处理' : '未处理'
      }
    },
    {
      prop: 'handleResult',
      label: '处理结果',
      showOverflowTooltip: true,
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
  permission: 'admin:web:Feedback:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doFeedbackPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return feedbackPageApi({...reactiveData.form,...pageQuery})
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
      txt: '回复',
      text: true,
      permission: 'admin:web:feedbackReply:create',
      disabled: !row.feedbackUserId,
      disabledReason: (!row.feedbackUserId) ? '无用户关联，不支持回复' : undefined,
      // 跳转到编辑
      route: {path: '/admin/feedbackReplyManageAdd',query: idData}
    },
    {
      txt: '回复列表',
      text: true,
      permission: 'admin:web:feedbackReply:pageQuery',
      position: 'more',
      disabled: !row.feedbackUserId,
      disabledReason: (!row.feedbackUserId) ? '无用户关联，无回复列表数据' : undefined,
      // 跳转到编辑
      route: {path: '/admin/feedbackReplyManagePage',query: idData}
    },
    {
      txt: '手动处理',
      text: true,
      permission: 'admin:web:Feedback:manualHandle',
      position: 'more',
      disabled: row.isHandle,
      disabledReason: row.isHandle ? '已处理,不能再次处理' : undefined,
      // 跳转到编辑
      route: {path: '/admin/feedbackManageManualHandle',query: idData}
    },

    {
      txt: '删除',
      text: true,
      permission: 'admin:web:Feedback:delete',
      position: 'more',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return feedbackRemoveApi({id: row.id}).then(res => {
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
           :dataMethod="doFeedbackPageApi"
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