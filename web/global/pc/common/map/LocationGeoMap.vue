<script setup name="LocationGeoMap" lang="ts">
/**
 * 根据地理位置选择地图上的点
 */
import {onMounted, reactive, ref} from 'vue'

import PtBaiduMap from './BaiduMap.vue'
import PtForm from '../../element-plus/Form.vue'
import {ElMessage} from 'element-plus'

let alert = (message)=>{
  ElMessage({
    showClose: true,
    message: message,
    type: 'error',
    showIcon: true,
    grouping: true
  })
}
const baiduMapRef = ref(null)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 需要解析的字符串
  str: String,
  // 需要逆向解析的经纬度，索引0=经度，1=纬度
  point: Array
})


// 属性
const reactiveData = reactive({
  // 字符串表单
  formString: {},
  // 经纬度表单
  formLocation: {}
})
// 字符串解析表单项
const formStringComps = ref(
    [
      {
        field: {
          name: 'str',
          value: props.str
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '位置',
            required: true
          },
          compProps: {
            clearable: true,
            placeholder: '输入一个位置，以显示在地图上，如：北京市',
          }
        }
      },])
// 经纬度解析表单项
const formLocationComps = ref(
    [
      {
        field: {
          name: 'longitude',
          value: props.point ? props.point[0] : null
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '经度',
            required: true
          },
          compProps: {
            clearable: true,
            placeholder: '如：116.404',
          }
        }
      },
      {
        field: {
          name: 'latitude',
          value: props.point ? props.point[1] : null
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '纬度',
            required: true
          },
          compProps: {
            clearable: true,
            placeholder: '如： 39.915',
          }
        }
      },
    ])


onMounted(()=> {

})

// 字符串解析提交按钮属性
const formStringSubmitAttrs = ref({
  buttonText: '解析到地图上'
})
// 经纬度解析提交按钮属性
const formLocationSubmitAttrs = ref({
  buttonText: '显示到地图上'
})
// 字符串解析提交按钮事件，主要是将输入的字符串解析到地图上并获取经纬度
const formStringSubmitMethod = () => {
  // 需要解析的字符串表单项
  let str = reactiveData.formString.str
  markStr(str)
}
const markStr = (str) => {
  if(!str){
    return
  }
  const {getPoint, addMarker,centerAndZoom,clearOverlays} = baiduMapRef.value
  clearOverlays()
  // 需要解析的字符串表单项
  getPoint(str,(point)=>{
    if(point){
      centerAndZoom(point)
      addMarker(point,{title: str})
      // 回显经纬度表单
      reactiveData.formLocation.longitude = point.lng
      reactiveData.formLocation.latitude = point.lat
    }else {
      alert('您选择的地址没有解析到结果')
    }
  })
}
// 经纬度解析提交按钮事件，主要是将输入的经纬度逆解析为字符串
const formLocationSubmitMethod = () => {

  const {newPoint} = baiduMapRef.value
  let point = newPoint(reactiveData.formLocation.longitude,reactiveData.formLocation.latitude)
  markPoint(point)
}
const markPoint = (point) => {
  const { addMarker,centerAndZoom,clearOverlays} = baiduMapRef.value
  clearOverlays()
  centerAndZoom(point)
  decodeByPoint(point,(str)=>{
    reactiveData.formString.str = str
    addMarker(point,{title: str})
  })
}
// 根据经纬度逆向解析，并在回调中处理得到的字符串
const decodeByPoint = (point,callback) => {
  const {newGeocoder} = baiduMapRef.value

  let geocoder =  newGeocoder()
  geocoder.getLocation(point, function(rs){
    let addComp = rs.addressComponents
    let r = []
    r.push(addComp.province)
    r.push(addComp.city)
    r.push(addComp.district)
    r.push(addComp.street)
    r.push(addComp.streetNumber)
    let str = r.join(' ')
    callback(str)
  })
}
// 地图点击回显
const mClick = (e)=>{
  let point = e.latlng
  const { addMarker,clearOverlays} = baiduMapRef.value
  clearOverlays()
  decodeByPoint(point,(str)=>{
    reactiveData.formString.str = str
    reactiveData.formLocation.longitude = point.lng
    reactiveData.formLocation.latitude = point.lat
    addMarker(point,{title: str})
  })
}
// 地图准备好后回显
const mapReady = () => {
  markStr(props.str)
  if(props.point && props.point[0] && props.point[1]){
    const {newPoint} = baiduMapRef.value
    let point = newPoint(props.point[0],props.point[1])
    markPoint(point)
  }

}
// 暴露方法
defineExpose({
  formString: reactiveData.formString,
  formLocation: reactiveData.formLocation,
})
</script>

<template>
  <PtForm :form="reactiveData.formString" :comps="formStringComps"
          :method="formStringSubmitMethod"
          :submitAttrs="formStringSubmitAttrs"
          inline
          defaultButtonsShow="submit,reset"></PtForm>
  <PtForm :form="reactiveData.formLocation" :comps="formLocationComps"
          :method="formLocationSubmitMethod"
          :submitAttrs="formLocationSubmitAttrs"
          inline
          defaultButtonsShow="submit,reset"></PtForm>
<PtBaiduMap ref="baiduMapRef" :mClick="mClick" @ready="mapReady" style="height: 500px;">

</PtBaiduMap>
</template>



<style scoped>

</style>
