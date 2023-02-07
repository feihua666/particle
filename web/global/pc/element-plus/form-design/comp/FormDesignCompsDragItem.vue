<script setup name="FormDesignComponent" lang="ts">
/**
 * 可用组件
 */
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 显示的名称
  name: {
    type: String,
    default: '未指定名称'
  },
  // 显示的图标
  icon: {
    type: String,
    default: 'EditPen'
  },
  title: {
    type: String,
  }
})
</script>
<template>
  <el-check-tag effect="plain" class="pt-form-design-component" :title="title">
    <el-icon>
      <component :is="icon"></component>
    </el-icon>
    {{name}}
  </el-check-tag>
</template>


<style scoped>
.pt-form-design-component{
  cursor: move;
  display: inline-block;
  /* 去掉动画，否则在拖拽时 其 ghost 有过度效果 */
  transition: none !important;
}
</style>