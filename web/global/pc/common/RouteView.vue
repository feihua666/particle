<script setup name="RouteView">
/**
 * 自定义封装 路由视图
 * 封装理由：1. 一致的方式使用 路由视图，并获取一致的表现
 *          2. 支持动态 keepAlive,路由 meta 元信息
 *          3. 后端使用时支持权限控制
 */
import {computed,inject} from 'vue'
import { useRouter, useRoute } from 'vue-router'
const router = useRouter()
const route = useRoute()

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  //路由层级 在多级路由下时可能中间级不会缓存问题 从1开始
  level: {
    type: Number
  }
})

// 计算属性
const isKeepAlive = computed(() => {
  let r = false
  if(props.level != null && props.level != undefined){
    if(route.matched.length > props.level - 1){
      let matched = route.matched[props.level - 1]
      r = matched.meta.keepAlive === true
    }
  }else {
    r = route.meta.keepAlive === true
  }
  return r
})
</script>
<template>
  <router-view v-slot="{ Component }">
    <keep-alive  v-if="isKeepAlive">
      <component :is="Component" />
    </keep-alive>
    <component v-else :is="Component" />
  </router-view>
</template>