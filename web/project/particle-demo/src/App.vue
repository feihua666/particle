<script setup lang="ts">
import {provide,watch,ref} from 'vue'

import { useRouter } from 'vue-router'
import {useLoginUserStore} from '../../../global/common/security/loginUserStore.ts'

const loginUserStore = useLoginUserStore()
// 刷新后从 localStorage 加载
loginUserStore.loadFromLocal()
const permissions = ref([])
// 在刷新页面时，如果没有登录，跳转到登录
if (!loginUserStore.hasLogin && loginUserStore.forceLogin) {
  const router = useRouter()
  router.replace('/login')
}else {
  permissions.value = loginUserStore.loginUser.isSuperAdmin? ['*']: loginUserStore.loginUser.permissions
}
provide('permissions',permissions)
watch(()=> loginUserStore.loginUser,(val)=> {
  permissions.value = val.isSuperAdmin? ['*']: val.permissions
})
</script>

<template>
  <PtRouteView :level="1"/>
</template>
<style>
html,body,#app {
  height:100%;
}
</style>