<script setup name="FeedbackReplyManagePage" lang="ts">
/**
 * 意见反馈回复管理页面
 */
import {reactive, ref} from 'vue'
import {
  page as feedbackReplyPageApi,
  remove as feedbackReplyRemoveApi
} from "../../../api/reply/admin/feedbackReplyAdminApi"
import {pageFormItems} from "../../../components/reply/admin/feedbackReplyManage";


const tableRef = ref(null)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  feedbackId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    feedbackId: props.feedbackId
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'replyContent',
      label: '回复内容',
      showOverflowTooltip: true,
    },
    {
      prop: 'replyAt',
      label: '回复时间',
    },
    {
      prop: 'replyUserNickname',
      label: '回复用户',
    },
    {
      prop: 'replyUserAvatar',
      label: '回复用户头像',
    },
    {
      prop: 'isFeedbackUserRead',
      label: '用户已读',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '已读' : '未读'
      }
    },
    {
      prop: 'feedbackUserReadAt',
      label: '用户读取时间',
    },
    {
      prop: 'feedbackUserRateDictName',
      label: '用户评价',
      showOverflowTooltip: true,
    },
    {
      prop: 'feedbackUserRateAt',
      label: '用户评价时间',
    },
    {
      prop: 'feedbackUserRateMemo',
      label: '用户评价内容',
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
  permission: 'admin:web:feedbackReply:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doFeedbackReplyPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return feedbackReplyPageApi({...reactiveData.form,...pageQuery})
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
      txt: '删除',
      text: true,
      permission: 'admin:web:feedbackReply:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return feedbackReplyRemoveApi({id: row.id}).then(res => {
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
           :dataMethod="doFeedbackReplyPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"

           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="80">
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
