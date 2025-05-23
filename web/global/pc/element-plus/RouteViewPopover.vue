<script setup name="RouteViewPopover" lang="ts">
/**
 * 自定义封装 路由视图，可将路由显示到 drawer 或 dialog 中
 * 封装理由：1. 一致的方式使用 路由视图，并获取一致的表现
 *          2. 支持动态 keepAlive,路由 meta 元信息
 *          3. 其它 PtRouteView 支持的特性
 */
import {computed, onMounted, ref, watch} from 'vue'
import {useRoute, useRouter} from 'vue-router'
import PtRouteView from '../common/RouteView.vue'

const router = useRouter()
const route = useRoute()

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // // 路由层级 在多级路由下时可能中间级不会缓存问题 从1开始
  level: {
    type: Number
  },
  //
  beforeClose: {
    type: Function
  },
  // 是否启动抽屉 需要在路由meta中定义 showInDrawer 属性,配合使用
  enableDrawer: {
    type: Boolean,
    default: true
  },
  // 是否启动弹窗 需要在路由meta中定义 showInDialog 属性,配合使用
  enableDialog: {
    type: Boolean,
    default: true
  },
  // drawer 的属性
  drawerProps: {
    type: Object,
    default: () => ({})
  },
  // dialog 的属性
  dialogProps: {
    type: Object,
    default: () => ({})
  }
})
// 监听
watch(() => route.meta,(val) => {
  drawer.value = showInDrawer.value
  dialog.value = showInDialog.value
})
onMounted(()=> {
  drawer.value = showInDrawer.value
  dialog.value = showInDialog.value
})
const drawer = ref(false)
const dialog = ref(false)
// 计算属性
const showInDrawer = computed(() => {
  return props.enableDrawer && route.meta.showInDrawer === true
})
const showInDialog = computed(() => {
  return props.enableDialog && route.meta.showInDialog === true
})
// drawer 原生属性
const drawerProps = computed(() => {
  let defaultDrawerProps = {
    appendToBody: true,
    destroyOnClose: true,
    size: '65%'
  }
  let routeMetaDrawerProps = Object.assign(defaultDrawerProps, props.drawerProps, route.meta.drawerProps || {})
  return routeMetaDrawerProps
})
// dialog 原生属性
const dialogProps = computed(() => {
  let defaultDialogProps = {
    appendToBody: true,
    destroyOnClose: true
  }
  let routeMetaDialogProps = Object.assign(defaultDialogProps, props.dialogProps, route.meta.dialogProps || {})
  return routeMetaDialogProps
})
// drawer和dialog 关闭方法
const beforeClose = (done):void => {
  if (props.beforeClose) {
    props.beforeClose(done)
  }else {
    drawer.value = false
    dialog.value = false
    router.go(-1)
    done();
  }

}
const getRouteMetaName = () => {
  let r = ''
  if (route.meta) {
    r = route.meta.name
  }
  return r
}
</script>
<template>
  <el-drawer
      v-model="drawer"
      v-bind="drawerProps" :title="$attrs.title || getRouteMetaName()" :beforeClose="beforeClose">
    <PtRouteView key="pt-router-view" :level="level"></PtRouteView>
    <template #footer v-if="!$slots.footer || drawerProps.footer === false">
      <!--   该 class 在 form组件中有使用，修改请注意   -->
      <div class="pt-route-view-popover-drawer-footer" :id="drawerProps.footerBoxId"></div>
    </template>
  </el-drawer>
  <el-dialog v-model="dialog"
             v-bind="dialogProps" :title="$attrs.title || getRouteMetaName()" :beforeClose="beforeClose">
    <PtRouteView key="pt-router-view" :level="level"></PtRouteView>
    <template #footer v-if="!$slots.footer || dialogProps.footer === false">
      <!--   该 class 在 form组件中有使用，修改请注意   -->
      <div class="pt-route-view-popover-dialog-footer" :id="dialogProps.footerBoxId"></div>
    </template>
  </el-dialog>
</template>
