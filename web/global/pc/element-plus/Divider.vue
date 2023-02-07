<script name="divider" setup lang="ts">
/**
 * 分隔线，主要是将默认插槽转为属性
 */
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 分隔线文案，可以通过默认插槽覆盖
  content: {
    type: String
  }
})
</script>

<template>
<el-divider v-bind="$attrs">
  <slot>{{content}}</slot>
</el-divider>
</template>

<style scoped>

</style>