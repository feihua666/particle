<script setup lang="ts">
import {provide} from 'vue'

import { useRouter } from 'vue-router'
import {useLoginUserStore} from '../../../global/common/security/loginUserStore.ts'

const loginUserStore = useLoginUserStore()
// 刷新后从 localStorage 加载
loginUserStore.loadFromLocal()

// 在刷新页面时，如果没有登录，跳转到登录
if (!loginUserStore.hasLogin && loginUserStore.forceLogin) {
  const router = useRouter()
  router.replace('/login')
}else {
  provide('permissions',loginUserStore.loginUser.isSuperAdmin? ['*']: loginUserStore.loginUser.permissions)
}
</script>

<template>
  <PtRouteView :level="1"/>
</template>
<style>
html,body,#app {
  height:100%;
}
</style>