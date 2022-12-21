<script setup name="LoginMenu" lang="ts">
/**
 * 登录用户自己的功能菜单组件
 */
import PtMenu from '../../../../global/pc/element-plus/Menu.vue'
import {loginGetList} from "../api/funcLoginApi";
import { useRoute } from 'vue-router'
const route = useRoute()
</script>
<template>
  <PtMenu :dataMethod="loginGetList"
          :default-active="route.path"
          router
          :props="{type: 'typeDictValue',index: 'url'}"
          :dataMethodResultHandleConvertToTree="true">

  </PtMenu>
</template>

<style scoped>
</style>