<script setup name="TestCompAdapter" lang="ts">



import PtCompAdapter from '../../../../../global/common/CompAdapter.vue'
import {ref,watch} from "vue";

const modelValue = ref('')


const buttonClick = ()=>{
  console.log('buttonClick')
}
const onUpdateModelValue = (val)=>{
  console.log('onUpdateModelValue',val)
}
watch(()=> modelValue.value,(val)=>{

  console.log('modelValue.value',val)
})

const slots = {
  prepend: ()=> 'sssss'
}


</script>
<template>

  <PtCompAdapter v-model="modelValue" is="el-input" :slots="slots" @update:modelValue="onUpdateModelValue"></PtCompAdapter>
</template>

<style scoped>

</style>