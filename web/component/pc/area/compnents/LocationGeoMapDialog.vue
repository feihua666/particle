<script setup name="LocationGeoMapDialog" lang="ts">
/**
 * 根据地理位置选择地图上的点
 */
import {ref} from 'vue'

const locationGeoMapRef = ref(null)
const dialogVisible = ref(false)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 确定时提交回调
  submit: Function,
  // 需要解析的字符串
  str: String,
  // 需要逆向解析的经纬度，索引0=经度，1=纬度
  point: Array
})

// 方法
// 显示
const open = () => {
  dialogVisible.value = true
}
// 关闭
const close = () => {
  dialogVisible.value = false
}
// 提交
const submit = () => {
  if(props.submit){
    let {formString,formLocation} = locationGeoMapRef.value
    let r = props.submit({str: formString.str,longitude: formLocation.longitude,latitude: formLocation.latitude})
    if(r !== false){
      close()
    }
  }else {
    close()
  }

}
// 暴露方法
defineExpose({
  open,
  close,
})
</script>

<template>
  <el-dialog v-model="dialogVisible" title="在地图上选择经纬度" draggable append-to-body destroy-on-close>
    <PtLocationGeoMap ref="locationGeoMapRef" :str="str" :point="point"></PtLocationGeoMap>
    <template #footer>
      <span>
        <PtButton type="primary" @click="submit" >确认</PtButton>
      </span>
    </template>
  </el-dialog>
</template>

<style scoped>

</style>