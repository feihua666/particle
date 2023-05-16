<script setup name="UserinfoCenter" lang="ts">
/**
 * 个人中心入口页
 * 在登录首页，下拉 点击个人中心 进入
 */
import {computed, ref} from 'vue'
import {useLoginUserStore} from "../../../../../../global/common/security/loginUserStore"

import UserinfoCard from '../UserinfoCard.vue'
import UserinfoTenant from './UserinfoTenant.vue'
import UserinfoRole from './UserinfoRole.vue'
import UserinfoApplication from './UserinfoApplication.vue'
import UserinfoFunc from './UserinfoFunc.vue'
import {componentEnabled} from "../../../../../../common/config/componentsConfig";

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
const tenants = computed(() => {
  let r = []
  let loginUser = loginUserStore.loginUser
  if (loginUser) {
    r = loginUser.tenants
  }
  return r
})
const currentTenant = computed(() => {
  let r = {}
  let loginUser = loginUserStore.loginUser
  if (loginUser) {
    r = loginUser.currentTenant
  }
  return r
})
const roles = computed(() => {
  let r = []
  let loginUser = loginUserStore.loginUser
  if (loginUser) {
    r = loginUser.roles
  }
  return r
})
const currentRole = computed(() => {
  let r = {}
  let loginUser = loginUserStore.loginUser
  if (loginUser) {
    r = loginUser.currentRole
  }
  return r
})
const activeName = ref('tenant')

</script>
<template>
  <div  class="pt-userinfo-center">
    <el-tabs v-model="activeName" stretch tab-position="left">
      <el-tab-pane  disabled name="pt-userinfo-center-userinfocard">
        <template #label>
          <UserinfoCard style="display: inline-block;" :avatar="avatar" :nickname="nickname"></UserinfoCard>
        </template>
      </el-tab-pane>
      <el-tab-pane label="租户" name="tenant" v-if="componentEnabled('tenant')">
        <UserinfoTenant :data="tenants" :currentTenant="currentTenant"></UserinfoTenant>
      </el-tab-pane>
      <el-tab-pane label="角色" name="role"  v-if="componentEnabled('role')">
        <UserinfoRole :data="roles" :currentRole="currentRole"></UserinfoRole>
      </el-tab-pane>
      <el-tab-pane label="租户应用" name="application"  v-if="componentEnabled('tenant')">
        <div>以下数据为您当前租户已获取的应用</div>
        <UserinfoApplication></UserinfoApplication>
      </el-tab-pane>
      <el-tab-pane label="功能权限" name="permission"  v-if="componentEnabled('func')"  class="pt-height-100-pc">
        <div>以下数据为您当前角色已获取的所以功能权限</div>
        <UserinfoFunc></UserinfoFunc>
      </el-tab-pane>
    </el-tabs>

  </div>

</template>

<style scoped>

</style>
<style>
.pt-userinfo-center{
  background: #f9f9fa;
}

.pt-userinfo-center .el-tabs{
  background: #ffffff;
}
.pt-userinfo-center #tab-pt-userinfo-center-userinfocard{
  height: auto;
  cursor: auto;
}
.pt-userinfo-center .el-tabs__nav-wrap::after{
  background-color: #ffffff;
}
.pt-userinfo-center .el-tabs--left .el-tabs__item.is-left{
  text-align: center;
}
</style>