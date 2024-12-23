<script setup name="FeedbackManageManualHandlePage" lang="ts">
/**
 * 意见反馈手动处理页面
 */
import {reactive, ref} from 'vue'
import {manualHandle as feedbackManualHandleApi} from "../../../api/feedback/admin/feedbackAdminApi"

import {manualHandlePageFormItems} from "../../../components/feedback/admin/feedbackManage";


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
  // 表单
  form: {
    id: props.feedbackId,
    version: 1
  },
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    manualHandlePageFormItems
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认处理',
  permission: 'admin:web:feedback:manualHandle',
})
// 提交按钮
const submitMethod = () => {
  return feedbackManualHandleApi
}

// 成功提示语
const submitMethodSuccess = () => {
  return '处理成功，请刷新数据查看'
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
