<script setup name="LowcodeSegmentGenGenericDesignAndGeneratePage" lang="ts">

import LowcodeSegmentGenGenericDesignAndGenerate from '../../../../../components/admin/segmentgen/designandgenerate/LowcodeSegmentGenGenericDesignAndGenerate.vue'
import {genericFormComps} from "../../../../../components/admin/segmentgen/designandgenerate/lowcodeSegmentGenGenericDesignAndGenerate";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  lowcodeSegmentGenId: {
    type: String
  }
})

</script>
<template>
  <LowcodeSegmentGenGenericDesignAndGenerate
      :lowcodeSegmentGenId="lowcodeSegmentGenId"
      :formComps="genericFormComps"
      :formLayout="[1,1,1]"
  ></LowcodeSegmentGenGenericDesignAndGenerate>
</template>


<style scoped>

</style>