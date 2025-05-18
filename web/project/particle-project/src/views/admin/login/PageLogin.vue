<script setup name="PageLogin"  lang="ts">
import {useLogoStore} from "../../../../../../global/common/api/LogoStore";

const logoStore = useLogoStore()
// 页面级别的登录页面
</script>
<template>
<PtLoginPageTemplate :logoAttr="{text: logoStore.logoText || undefined,imgSrc: logoStore.logoImgUrl || undefined}">
  <PtUserLoginForm loginSuccess="/index"></PtUserLoginForm>
</PtLoginPageTemplate>
</template>

<style scoped>

</style>
