<template>
  <div class="demo-range">
    <PtTimePicker
        v-model="dynamicModel"
        :isRange="isRange"
        :key="isRange"

        range-separator="To"
        start-placeholder="Start time"
        end-placeholder="End time"
    ></PtTimePicker>
    <el-time-picker
        v-model="value2"
        is-range
        arrow-control
        range-separator="To"
        start-placeholder="Start time"
        end-placeholder="End time"
    ></el-time-picker>
  </div>
  <el-button @click="isRange = !isRange">ssss</el-button>
</template>

<script lang="ts" setup>
import {ref, watch} from 'vue'

const dynamicModel = ref(null)
const value1 = ref<[Date, Date]>([
  new Date(2016, 9, 10, 8, 40),
  new Date(2016, 9, 10, 9, 40),
])
const value2 = ref<[Date, Date]>([
  new Date(2016, 9, 10, 8, 40),
  new Date(2016, 9, 10, 9, 40),
])
const isRange = ref(false  )
watch(()=>isRange.value,(value)=>{
  console.log('isRange.value',value)
  if (value === true) {
    dynamicModel.value = value1.value
  }else {
    dynamicModel.value = null

  }
})
</script>

<style>
.demo-range .el-date-editor {
  margin: 8px;
}

.demo-range .el-range-separator {
  box-sizing: content-box;
}
</style>
