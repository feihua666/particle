<script setup name="Pagination">
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  currentPage: {
    type: Number,
    default: 1
  },
  pageSize: {
    type: Number,
    default: 10
  },
  total: {
    type: Number,
    default: 0
  }
})

// 事件
const emit = defineEmits(['sizeChange','currentChange'])


</script>
<template>
  <el-pagination
      background
      @size-change="(val)=>{$emit('sizeChange', val)}"
      @current-change="(val) => {$emit('currentChange', val)}"
      :current-page="currentPage"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      style="text-align: right;">
  </el-pagination>
</template>