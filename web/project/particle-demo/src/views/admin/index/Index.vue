<script setup name="index" lang="ts">
import { reactive,ref,watch} from 'vue'
import {getLogoText, getLogoUrl} from "../../../../../../global/common/api/globalApi";
import {useLogoTextStore} from "../../../../../../global/common/api/LogoTextStore";

const logoTextStore = useLogoTextStore()

// 左侧菜单展示/折叠控制变量
const isCollapse = ref(false)
// 左侧菜单展示/折叠相关配置
const leftAsideCollStatus = {
  expand: {
    headerAsideWidth: 300 + 'px',
    asideWidth: 300 + 'px',
    toggleBtnTips: '点击收起左侧菜单',
    headerAsideLogo:{
      showText: true,
      imgAttr: {
        style: 'padding-left: 20px'
      },
      imgSrc: getLogoUrl()
    }
  },
  collapse: {
    headerAsideWidth: 'calc(var(--el-menu-icon-width) + var(--el-menu-base-level-padding) * 2)',
    asideWidth: 'calc(var(--el-menu-icon-width) + var(--el-menu-base-level-padding) * 2)',
    toggleBtnTips: '点击展开左侧菜单',
    headerAsideLogo:{
      showText: false,
      imgAttr: {
        style: 'padding-left: 0',
      },
      imgSrc: getLogoUrl(),
      class: 'pt-width-100-pc'
    }
  }
}

const reactiveData = reactive({
  headerAside: {show: true, attr: {style: {width: leftAsideCollStatus.expand.headerAsideWidth,transition: 'width .3s ease',overflow: 'hidden',borderRight: 'solid 1px var(--el-menu-border-color)'}}},
  aside: { show: true, attr: {style: {width: leftAsideCollStatus.expand.asideWidth,transition: 'width .3s ease',borderRight: 'solid 1px var(--el-menu-border-color)'}} }
})
watch(()=> isCollapse.value,(val)=>{
  reactiveData.headerAside.attr.style.width = leftAsideCollStatus[val ? 'collapse' : 'expand'].headerAsideWidth
  reactiveData.aside.attr.style.width = leftAsideCollStatus[val ? 'collapse' : 'expand'].asideWidth
})
</script>

<template>
<PtBackendManagementLayout
    :headerAside="reactiveData.headerAside"
    :aside="reactiveData.aside"
    :header="{ show: true, attr: {class: 'pt-padding-0'} }"
    :headerMain="{ show: true, attr: {style: 'padding-top: 0;padding-bottom:0'} }"
    :footer="{ show: false, attr: {} }"
>
  <template #headerAside>
    <PtLogo :text="logoTextStore.logoText || undefined" v-bind="leftAsideCollStatus[isCollapse ? 'collapse' : 'expand'].headerAsideLogo"></PtLogo>
  </template>
  <template #headerMain>
    <el-container class="pt-height-100-pc">
      <el-aside width="500px" class="pt-padding-0 pt-flex-align-cross-center">
        <el-button type="primary" link size="large" @click="isCollapse = !isCollapse" :title="leftAsideCollStatus[isCollapse ? 'collapse' : 'expand'].toggleBtnTips">
          <el-icon><Expand v-if="isCollapse"/><Fold v-else/></el-icon>
        </el-button>
        <PtBreadcrumb></PtBreadcrumb>
      </el-aside>
      <el-main class="pt-padding-0 pt-flex-align-end">
        <PtUserUserinfoDropdown placement="bottom-end"></PtUserUserinfoDropdown>
      </el-main>
    </el-container>
  </template>
  <template #aside>
    <el-scrollbar  class="pt-height-100-pc pt-el-scroll-vertical">
    <PtFuncLoginMenu class="pt-height-100-pc" style="border-right: 0" :collapse="isCollapse"></PtFuncLoginMenu>
    </el-scrollbar>
  </template>
<!-- 默认主工作区为二级路由 -->
  <PtRouteView :level="1"/>
</PtBackendManagementLayout>

</template>
<style scoped>

</style>