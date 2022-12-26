<script setup name="UserResetPassowordPage" lang="ts">
/**
 * 用户重置密码页面
 */
import {reactive, ref} from 'vue'
import {userResetPassword} from "../../api/admin/userIdentifierPwdAdminApi"
import {resetPasswordCompItems} from "../../compnents/userCompItem";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  userId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    userId: props.userId
  },
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    resetPasswordCompItems
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认重置',
  permission: 'admin:web:userIdentifierPwd:user:resetPassword',
})
// 提交按钮
const submitMethod = () => {
  return userResetPassword
}
// 成功提示语
const submitMethodSuccess = () => {
  return '密码重置成功'
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
          inline
          :layout="[1,2]"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>