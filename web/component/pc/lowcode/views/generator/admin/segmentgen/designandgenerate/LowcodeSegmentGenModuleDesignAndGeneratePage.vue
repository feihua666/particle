<script setup name="LowcodeSegmentGenModuleDesignAndGeneratePage" lang="ts">
/**
 * 低代码片段生成设计和渲染页面
 */
import LowcodeSegmentGenGenericDesignAndGenerate from '../../../../../components/admin/segmentgen/designandgenerate/LowcodeSegmentGenGenericDesignAndGenerate.vue'
import {
  useModuleFormComps
} from "../../../../../components/admin/segmentgen/designandgenerate/lowcodeSegmentGenGenericDesignAndGenerate";


// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  lowcodeSegmentGenId: {
    type: String
  },
  // 引用生成id，用来加载引用的渲染数据
  refrenceSegmentGenId:{
    type: String
  },
  // 是否已生成，如果已生成使用自己之前生成的数据作为引用生成数据
  isGenerated:{
    type: Boolean,
    default: false
  }
})

// 表单项
const formComps = useModuleFormComps({props})



</script>
<template>
  <LowcodeSegmentGenGenericDesignAndGenerate
      :lowcodeSegmentGenId="lowcodeSegmentGenId"
      :formComps="formComps"
      :formLayout="[2,3,1,1,1,1,1]"
  ></LowcodeSegmentGenGenericDesignAndGenerate>

</template>


<style scoped>

</style>