<script setup name="LoginForm"  lang="ts">
import AccountLoginForm from './AccountLoginForm.vue'
import VerificationCodeLoginForm from './VerificationCodeLoginForm.vue'
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 登录成功后，执行，如果为 String，就会被当作路由跳转 replace
  loginSuccess: {
    type: [Function,String]
  },
  // 是否启动验证码
  useCaptcha:{
    type: Boolean,
    default: true
  }
})
</script>
<template>
  <el-tabs>
    <el-tab-pane label="账号密码登录">
      <AccountLoginForm :loginSuccess="loginSuccess" :useCaptcha="useCaptcha"></AccountLoginForm>
    </el-tab-pane>
    <el-tab-pane label="验证码登录">
      <VerificationCodeLoginForm :loginSuccess="loginSuccess" :useCaptcha="useCaptcha"></VerificationCodeLoginForm>
    </el-tab-pane>
  </el-tabs>
</template>

