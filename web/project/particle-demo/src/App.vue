<script setup lang="ts">
import { useRouter } from 'vue-router'
import {useLoginUserStore} from '../../../global/common/security/loginUserStore.ts'

const loginUserStore = useLoginUserStore()
// 刷新后从 localStorage 加载
loginUserStore.loadFromLocal()

// 在刷新页面时，如果没有登录，跳转到登录
if (!loginUserStore.hasLogin && loginUserStore.forceLogin) {
  const router = useRouter()
  router.replace('/login')
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