<script setup name="LoginMenu" lang="ts">
/**
 * 登录用户自己的功能菜单组件
 */
import PtMenu from '../../../../../global/pc/element-plus/Menu.vue'
import {loginUserFuncList} from "../../api/funcLoginApi";
import {useLoginUserStore} from "../../../../../global/common/security/loginUserStore"

import {useRoute} from 'vue-router'
import {ref, watch} from "vue";

const loginUserStore = useLoginUserStore()
const menuRef = ref(null)
const route = useRoute()
// 如果切换租户或角色刷新功能菜单
watch(()=> loginUserStore.loginUser?.currentTenant,(val)=>{!!val && (menuRef.value?.refreshData())})
watch(()=> loginUserStore.loginUser?.currentRole,(val)=>{!!val && (menuRef.value?.refreshData())})
// 仅查询显示的数据
const getFuncList = () =>{
  return loginUserFuncList({isShow: true,funcGroupCode: 'backend_pc'})
}
</script>
<template>
  <PtMenu ref="menuRef" :dataMethod="getFuncList"
          :default-active="route.path"
          router
          :props="{type: 'typeDictValue',index: 'url'}"
          :dataMethodResultHandleConvertToTree="true">

  </PtMenu>
</template>

<style scoped>
</style>
