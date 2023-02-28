<script setup lang="ts">
import {provide,watch,ref,onMounted} from 'vue'

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

onMounted(()=>{
  // First we get the viewport height and we multiple it by 1% to get a value for a vh unit
  let vh = window.innerHeight * 0.01
  // Then we set the value in the --vh custom property to the root of the document
  document.documentElement.style.setProperty('--vh', `${vh}px`)

  // We listen to the resize event
  window.addEventListener('resize', () => {
    // We execute the same script as before
    let vh = window.innerHeight * 0.01
    console.log(vh);
    document.documentElement.style.setProperty('--vh', `${vh}px`)
  })

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