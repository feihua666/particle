<script setup name="Button">
/**
 * 自定义封装按钮
 * 封装理由：1. 一致的方式使用 button，并获取一致的表现
 *          2. 使按钮自带 loading 自动处理效果，无需额外处理
 *          3. 后端使用时支持权限控制
 */
import { useSlots,getCurrentInstance,reactive ,computed,watch,onMounted,inject} from 'vue'
import { ElLoading } from 'element-plus'
import {aiButtonStyle} from './ElStyleTools'
import {permissionProps,hasPermissionConfig} from './permission'
import {disabledProps,disabledConfig} from './disabled'
import {methodProps,reactiveMethodData,emitMethodEvent,method,doMethod} from './method'
import {isFunction} from '../../common/tools/FunctionTools'

const { proxy,appContext } = getCurrentInstance()
// 路由
const router = appContext.config.globalProperties.$router
const slots = useSlots()
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 重写loading
  loading: {
    type: Boolean,
    default: false
  },
  // 重写 icon
  icon: {
    type: String
  },
  // 重写 type
  type: {
    type: String
  },

  // 禁用相关属性
  ...disabledProps,
  // 鼠标 hover 提示语
  title: {
    type: String
  },

  // 权限相关
  ...permissionProps,
  // 表现，link 或 button
  view: {
    type: String,
    default: 'button'
  },
  // 事件相关
  ...methodProps,
  // 启动全屏 loading 效果
  enableLoadingFullScreen: {
    type: Boolean,
    default: false
  },
  // 路由，按钮点击时优先路由，会调用push方法，如果是一个函数 则 router为回调参数
  route: {
    default: null
  },
  // 按钮文本,如果没有默认slot时，生效
  buttonText: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 数据与加载
  ...reactiveMethodData,
  // 根据默认插槽文件计算按钮样式
  buttonStyle: slots.default ? aiButtonStyle(slots.default()[0].children) : {},
  buttonText: slots.default ? slots.default()[0].children : props.buttonText || '',
  // 全屏加载状态实例对象保持变量
  loadingService: null
})
// 计算属性

// 这里和 props.loading 重名了，但在模板是使用 loading 变量是这个值，也就是说这里会覆盖在模板中的值
const loading = computed(() => {
  return props.loading || reactiveData.methodLocalLoading
})
// 这里和 props.type 重名了，但在模板是使用 type 变量是这个值，也就是说这里会覆盖在模板中的值
const type = computed(() => {
  return props.type || reactiveData.buttonStyle.type
})
// 这里和 props.icon 重名了，但在模板是使用 icon 变量是这个值，也就是说这里会覆盖在模板中的值
const icon = computed(() => {
  return props.icon || reactiveData.buttonStyle.icon
})
const injectPermissions = inject('permissions', [])

// 是否有权限
const hasPermission = hasPermissionConfig({props,injectPermissions,noPermissionSimpleText: `「${reactiveData.buttonText ? reactiveData.buttonText : '此'}」按钮`})

const hasDisabled = disabledConfig({props,loading,hasPermission})
// 侦听
watch(
    () => loading.value,
    (loading,loadingOld) => {
      fullScreenLoading(loading)
    }
)
// 事件
const emit = defineEmits(['click',emitMethodEvent.methodResult])

// 挂载
onMounted(() => {
  fullScreenLoading(loading.value)
})

// 方法
const fullScreenLoading = (loading) =>{
  if (props.enableLoadingFullScreen) {
    // 全屏loading
    if (loading === true) {
      if (reactiveData.loadingService == null) {
        reactiveData.loadingService = ElLoading.service({
          lock: true,
          text: '加载中...',
          background: 'rgba(0, 0, 0, 0.7)',
        })
      }
    }else{
      // 关闭全屏loading
      if (reactiveData.loadingService != null) {
        reactiveData.loadingService.close()
        reactiveData.loadingService = null
      }
    }
  }
}
const tempDoSubmit = doMethod({props,reactiveData,emit})
const doSubmit = ($event) => {
  emit('click',$event)
  if(router && props.route){
    if (isFunction(props.route)) {
      props.route(router)
    }else {
      router.push(props.route)
    }
    return
  }
  tempDoSubmit($event)
}
// click 按钮事件
const submit = method({props,reactiveData,emit,hasPermission,doMethod: doSubmit})

</script>
<template>
    <el-link v-if="view == 'link' && hasPermission.render"
             v-bind="$attrs"
             :type="type"
             :disabled="hasDisabled.disabled"
             :icon="loading ? '' : icon"
             class="is-loading"
             :title="hasDisabled.disabledReason || title"
             @click="submit" >
      <el-icon v-if="loading" class="is-loading"><Loading /></el-icon>
      <slot>{{reactiveData.buttonText}}</slot>
    </el-link>
    <el-button v-else-if="view == 'button' && hasPermission.render"
               v-bind="$attrs"
               :type="type"
               :icon="icon"
               :loading="loading"
               :disabled="hasDisabled.disabled"
               :title="hasDisabled.disabledReason || title"
               @click="submit">
      <slot>{{reactiveData.buttonText}}</slot>
    </el-button>
</template>
<style scoped>
/**
在 el-button是loading状态时，取消了指针事件，这导致 在loading状态title不生效，这里设置为自动
 */
.el-button.is-loading {
  pointer-events: auto;
}
</style>
