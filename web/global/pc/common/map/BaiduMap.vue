<script setup name="BaiduMap" lang="ts">
/**
 * 自定义封装百度地图
 * 官方示例 http://lbsyun.baidu.com/jsdemo.htm#a1_2
 */
import {nextTick, onMounted, ref} from 'vue'

const baiduMapRef = ref(null)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  initZoom: {
    type: Number,
    default: 14
  },
  // 地图初始坐标
  // 类型为数组[116.404, 39.915]
  initPoint: {
    type: Array,
    default: function () {
      return [116.404, 39.915]
    }
  },
  // 地图点击事件
  mClick: {
    type: Function,
    default: function () {

    }
  },
  // 是否组件加载完毕自动初始化地图
  initAuto: {
    type: Boolean,
    default: true
  },
  // 百度申请的key
  ak: {
    type: String,
    default: '69a5a1e7260cd2cd298c33666e436530'
  }
})
// 属性
// 记一次失败的说明，地图实例不能使用 reactive 响应数据持有，这里单独使用一个对象持有，否则没有poi信息显示，这试了好久没发现问题
const mapInstanceHolder = ({
  // 地图实例
  map: null,
  global: window
})
// 事件
const emit = defineEmits(['ready','mClick'])

onMounted(()=>{
  nextTick(()=>{
    bMapScript(props.ak).then( BMapGL => {
      if (props.initAuto === true) {
        init(BMapGL)
      } else {
        emit('ready',mapInstanceHolder.map)
      }
    })
  })
})

// 方法
// 初始化
const init = (BMapGL) => {
  if (mapInstanceHolder.map) {
    return
  }
  mapInstanceHolder.map = new BMapGL.Map(baiduMapRef?.value)
  let point = newPoint(props.initPoint[0], props.initPoint[1])
  centerAndZoom(point, props.initZoom)
  let scaleControl = new BMapGL.ScaleControl({anchor: mapInstanceHolder.global.BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
  let zoomControl = new BMapGL.ZoomControl();  //左上角，添加默认缩放控件
  mapInstanceHolder.map.addControl(scaleControl);
  mapInstanceHolder.map.addControl(zoomControl);
  mapInstanceHolder.map.enableScrollWheelZoom(true);   //启用滚轮放大缩小，默认禁用
  mapInstanceHolder.map.enableContinuousZoom(true);    //启用地图惯性拖拽，默认禁用

  bindClick()
  emit('ready', mapInstanceHolder.map)
}
// 地图脚本初始化
const bMapScript = (ak) => {
  // 感谢vue-baidu-map项目
  if (!mapInstanceHolder.global.BMapGL) {
    mapInstanceHolder.global.BMapGL = {}
    mapInstanceHolder.global.BMapGL._preloader = new Promise((resolve, reject) => {

      mapInstanceHolder.global._initBaiduMap = function () {
        resolve(mapInstanceHolder.global.BMapGL)
        mapInstanceHolder.global.document.body.removeChild($script)
        mapInstanceHolder.global.BMapGL._preloader = null
        mapInstanceHolder.global._initBaiduMap = null
      }
      const $script = document.createElement('script')
      mapInstanceHolder.global.document.body.appendChild($script)
      $script.src = `https://api.map.baidu.com/api?v=1.0&type=webgl&ak=${ak}&callback=_initBaiduMap`
      $script.onerror = reject
    })
    return mapInstanceHolder.global.BMapGL._preloader
  } else if (!mapInstanceHolder.global.BMapGL._preloader) {
    return Promise.resolve(mapInstanceHolder.global.BMapGL)
  } else {
    return mapInstanceHolder.global.BMapGL._preloader
  }
}
const getMapIns = () => {
  return mapInstanceHolder.map
}
// 地图初始化完成后调用
const bindClick = () => {
  mapInstanceHolder.map.addEventListener('click', (e) => {
    props.mClick(e)
    emit('mClick', e)
  })
}
const centerAndZoom = (point, zoom)=> {
  mapInstanceHolder.map.centerAndZoom(point, zoom ? zoom : mapInstanceHolder.map.getZoom());
}
// 新建一个点
const newPoint = (longitude, latitude) => {
  let point = new mapInstanceHolder.global.BMapGL.Point(longitude, latitude)
  return point
}
// 新建一个geo定位器
const newGeocoder = () =>{
  let geo = new mapInstanceHolder.global.BMapGL.Geocoder()
  return geo
}
// 新建一个标注
const addMarker = (point,options) => {
  let marker = new mapInstanceHolder.global.BMapGL.Marker(point,options)
  mapInstanceHolder.map.addOverlay(marker);
}
const getPoint = (address, callback) => {
  // 创建地址解析器实例
  let myGeo = newGeocoder()
  // 将地址解析结果显示在地图上,并调整地图视野
  myGeo.getPoint(address, (point) => {
    callback(point)
  })
}
const getAddress = (point, callback) => {
  // 创建地址解析器实例
  let myGeo = newGeocoder()
  myGeo.getLocation(point, (rs) => {
    let addComp = rs.addressComponents
    let addr = []
    addr.push(addComp.province)
    addr.push(addComp.city)
    addr.push(addComp.street)
    addr.push(addComp.streetNumber)
    callback(addr, rs)
  })
}
// 地址解析并标注
const addressMarker = (address) => {
  getPoint(address, (point) => {
    if (point) {
      centerAndZoom(point,null)
      addMarker(point)
    }
  })
}
const clearOverlays = () => {
  mapInstanceHolder.map.clearOverlays()
}
// 暴露方法
defineExpose({
  map: mapInstanceHolder.map,
  getMapIns,
  clearOverlays,
  addressMarker,
  getAddress,
  getPoint,
  addMarker,
  newGeocoder,
  newPoint,
  centerAndZoom
})
</script>
<template>
  <div ref="baiduMapRef" class="pt-baidu-map">
  </div>
</template>


<style scoped>
.pt-baidu-map{
  height: 100%;
  width:100%;
}
</style>
