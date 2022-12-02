<script setup name="index">
import { useSlots,getCurrentInstance,reactive ,computed,watch,onMounted,inject,ref} from 'vue'
import {aiButtonStyle} from "../../../../../../global/pc/element-plus/ElStyleTools";
const reactiveData = reactive({

})

</script>

<template>
<PtBackendManagementLayout>
  <template #headerAside>
    <PtLogo></PtLogo>
  </template>
  <template #aside>
    <PtFuncLoginMenu></PtFuncLoginMenu>
  </template>
<!-- 默认主工作区为二级路由 -->
  <PtRouteView :level="1"/>
</PtBackendManagementLayout>

</template>
<style scoped>

</style>