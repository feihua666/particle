<script setup lang="ts">
import {provide,watch,ref,onMounted} from 'vue'

import { useRouter } from 'vue-router'
import {useLoginUserStore} from '../../../global/common/security/loginUserStore'
import {useLogoStore} from "../../../global/common/api/LogoStore";
import {useWebTitleStore} from "../../../global/common/api/WebTitleStore";
import {
  getFavicon,
  getFaviconUrl,
  getLogo,
  getLogoText,
  getLogoUrl,
  getWebTitle
} from "../../../global/common/api/globalApi";

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


const logoStore = useLogoStore()
// 刷新后从 localStorage 加载
logoStore.loadFromLocal()
getLogoText().then(res => {
  let logoText = res.data.data.logoText
  if (logoText) {
    logoStore.changeLogoText(logoText);
  }
}).catch(error => {
  logoStore.changeLogoText('');
});
getLogo().then(res => {
  logoStore.changeLogoImgUrl(getLogoUrl());
}).catch(error => {
  logoStore.changeLogoImgUrl('');

});


const webTitleStore = useWebTitleStore()

watch(()=> webTitleStore.webTitle,(val)=> {
  if(val){
    document.title = val
  }
})
// 刷新后从 localStorage 加载
webTitleStore.loadFromLocal()
getWebTitle().then(res => {
  let webTitle = res.data.data.webTitle
  if (webTitle) {
    webTitleStore.changeWebTitle(webTitle);
  }
}).catch(error => {
  webTitleStore.changeWebTitle('');
});

getFavicon().then(res => {

  let backendIconElement = document.getElementById("backend-icon")
  backendIconElement && (backendIconElement.href = getFaviconUrl())
}).catch(error => {


});


onMounted(()=>{
  // First we get the viewport height and we multiple it by 1% to get a value for a vh unit
  let vh = window.innerHeight * 0.01
  // Then we set the value in the --vh custom property to the root of the document
  document.documentElement.style.setProperty('--vh', `${vh}px`)

  // We listen to the resize event
  window.addEventListener('resize', () => {
    // We execute the same script as before
    let vh = window.innerHeight * 0.01
    document.documentElement.style.setProperty('--vh', `${vh}px`)
  })
  window.resizeTo(400,300);
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
