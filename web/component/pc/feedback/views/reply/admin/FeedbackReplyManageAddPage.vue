<script setup name="FeedbackReplyManageAddPage" lang="ts">
/**
 * 意见反馈回复管理添加页面
 */
import {reactive ,ref} from 'vue'
import {create as feedbackReplyCreateApi,list as FeedbackReplyListApi} from "../../../api/reply/admin/feedbackReplyAdminApi"
import {addPageFormItems} from "../../../compnents/reply/admin/feedbackReplyManage";

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
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    addPageFormItems
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认回复',
  permission: 'admin:web:feedbackReply:create',
})
// 提交按钮
const submitMethod = () => {
  return feedbackReplyCreateApi
}
// 成功提示语
const submitMethodSuccess = () => {
  return '回复成功，请刷新数据查看'
}

</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="80"
          :method="submitMethod()"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          inline
          :layout="[1]"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>