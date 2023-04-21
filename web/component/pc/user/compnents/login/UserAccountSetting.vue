<script setup name="UserAccountSetting" lang="ts">

import {computed, ref} from 'vue'
import type { TabsPaneContext } from 'element-plus'
import {useLoginUserStore} from "../../../../../global/common/security/loginUserStore"

import UserinfoCard from './UserinfoCard.vue'
const loginUserStore = useLoginUserStore()

const nickname = computed(() => {
  let r = ''
  let loginUser = loginUserStore.loginUser
  if (loginUser) {
    r = loginUser.nickname || loginUser.username
  }
  return r
})
const avatar = computed(() => {
  let r = ''
  let loginUser = loginUserStore.loginUser
  if (loginUser) {
    r = loginUser.avatar
  }
  return r
})
const activeName = ref('myAccount')


</script>
<template>
  <div  class="pt-userinfo-account-setting">
    <el-tabs v-model="activeName" stretch tab-position="left">
      <el-tab-pane  disabled name="pt-userinfo-account-setting-userinfocard">
        <template #label>
          <UserinfoCard style="display: inline-block;" :avatar="avatar" :nickname="nickname"></UserinfoCard>

        </template>
        Route
      </el-tab-pane>
      <el-tab-pane label="我的账号" name="myAccount">我的账号</el-tab-pane>
      <el-tab-pane label="绑定手机号" name="bindMobile">绑定手机号</el-tab-pane>
      <el-tab-pane label="登录密码" name="loginPassword">登录密码</el-tab-pane>
      <el-tab-pane label="绑定邮箱" name="bindEmail">绑定邮箱</el-tab-pane>
      <el-tab-pane label="登录设备" name="loginDevice">登录设备</el-tab-pane>
      <el-tab-pane label="登录记录" name="loginRecord">登录记录</el-tab-pane>
    </el-tabs>

  </div>

</template>

<style scoped>

</style>
<style>
.pt-userinfo-account-setting{
  background: #f9f9fa;
}

.pt-userinfo-account-setting .el-tabs{
  background: #ffffff;
}
.pt-userinfo-account-setting #tab-pt-userinfo-account-setting-userinfocard{
  height: auto;
  cursor: auto;
}
.pt-userinfo-account-setting .el-tabs__nav-wrap::after{
  background-color: #ffffff;
}
.pt-userinfo-account-setting .el-tabs--left .el-tabs__item.is-left{
  text-align: center;
}
</style>