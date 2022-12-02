<script setup name="LoginMenu" lang="ts">
/**
 * 登录用户自己的功能菜单组件
 */
import PtMenu from '../../../../global/pc/element-plus/Menu.vue'
import {loginGetList} from "../api/funcLoginApi";
import {listToTree} from "../../../../global/common/tools/ArrayTools";

const dataMethodResultHandle = ({success,error,emptyData}) => {
  // success 为 res
  if (success) {
    let data = success.data.data
    // 将数据转为树
    return listToTree(data)
  }
  if (error) {
    return emptyData
  }
}
</script>
<template>
  <PtMenu :dataMethod="loginGetList"
          router
          :props="{type: 'typeDictValue',index: 'url'}"
          :dataMethodResultHandle="dataMethodResultHandle">

  </PtMenu>
</template>

<style scoped>
</style>