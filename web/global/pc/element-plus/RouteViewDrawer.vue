<script setup name="RouteViewDrawer">
/**
 * 自定义封装 路由视图，可将路由显示到 drawer中
 * 封装理由：1. 一致的方式使用 路由视图，并获取一致的表现
 *          2. 支持动态 keepAlive,路由 meta 元信息
 *          3. 其它 PtRouteView 支持的特性
 */
import {computed} from 'vue'
import { useRouter, useRoute } from 'vue-router'
import PtRouteView from './RouteView.vue'

const router = useRouter()
const route = useRoute()

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // // 路由层级 在多级路由下时可能中间级不会缓存问题 从1开始
  level: {
    type: Number
  },
  beforeClose: {
    type: Function
  },
  // 是否启动抽屉 需要在路由meta中定义showInDrawer属性,配合使用
  enableDrawer: {
    type: Boolean,
    default: true
  },
  // drawer 的属性
  drawerProps: {
    type: Object,
    default: () => ({})
  }
})

// 计算属性
const showInDrawer = computed(() => {
  return props.enableDrawer && route.meta.showInDrawer === true
})
const  drawerProps = computed(() => {
  let routeMetaDrawerProps = route.meta.drawerProps || {}
  return Object.assign(props.drawerProps,routeMetaDrawerProps)
})
// 方法
const handleClose = (done) => {
  if (props.beforeClose) {
    props.beforeClose(done)
  }else {
    router.go(-1)
    done();
  }

}
</script>
<template>
  <el-drawer
      v-bind="drawerProps"
      v-if="showInDrawer" :before-close="handleClose">
    <PtRouteView key="pt-router-view" :level="level"></PtRouteView>
  </el-drawer>
  <PtRouteView v-else key="pt-router-view" :level="level"></PtRouteView>
</template>