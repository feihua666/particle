<script setup name="index">
import { useSlots,getCurrentInstance,reactive ,computed,watch,onMounted,inject,ref} from 'vue'
import {aiButtonStyle} from "../../../../../../global/pc/element-plus/ElStyleTools";
import Test from './Test.vue'
const reactiveData = reactive({

})

</script>

<template>
<PtBackendManagementLayout>
  <template #headerAside>
    <PtLogo></PtLogo>
  </template>

</PtBackendManagementLayout>

</template>
<style scoped>

</style>