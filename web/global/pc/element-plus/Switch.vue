<script setup name="Swicth">
/**
 * 自定义封装输入
 * 封装理由：1. 后端使用时支持权限控制
 */
import { reactive ,computed,inject, watch, ref} from 'vue'
import { ElMessageBox } from 'element-plus'

import {permissionProps,hasPermissionConfig} from './permission'
import {disabledProps,disabledConfig} from './disabled'
import {reactiveDataModelData,emitDataModelEvent,updateDataModelValueEventHandle,changeDataModelValueEventHandle} from './dataModel'
import { emitMethodEvent, method, methodProps, reactiveMethodData} from './method'


// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 值绑定 绑定值，必须等于 active-value 或 inactive-value，默认为 Boolean 类型
  modelValue: [Boolean,Number,String],
  // 禁用相关属性
  ...disabledProps,
  // 权限相关
  ...permissionProps,
  // 鼠标 hover 提示语
  title: {
    type: String
  },
  // 重写loading
  loading: {
    type: Boolean,
    default: false
  },
  // switch 状态改变前的钩子， 返回 false 或者返回 Promise 且被 reject 则停止切换
  beforeChange: {
    type: Function,
    default: () => true
  },
  // 事件相关
  ...methodProps,
})

// 属性
const reactiveData = reactive({
  // 数据与加载
  ...reactiveMethodData(),
  ...reactiveDataModelData(props)
})
// 计算属性
// 这里和 props.loading 重名了，但在模板是使用 loading 变量是这个值，也就是说这里会覆盖在模板中的值
const loading = computed(() => {
  return props.loading || reactiveData.methodLocalLoading
})
const injectPermissions = inject('permissions', [])
// 是否有权限
const hasPermission = hasPermissionConfig({
  props,
  injectPermissions,
  noPermissionSimpleText: `「此」开关`
})

// 是否禁用
const hasDisabled = disabledConfig({props,hasPermission})
// 侦听
watch(
    () => props.modelValue,
    (val) => {
      reactiveData.oldModelValue = val
      reactiveData.currentModelValue = val
    }
)
// 事件
const emit = defineEmits([
  // 用来更新 modelValue
  emitDataModelEvent.updateModelValue,
  emitDataModelEvent.change,
  emitMethodEvent.methodResult,
])

// 方法
// 值更新事件
const updateModelValueEvent = updateDataModelValueEventHandle({reactiveData,hasPermission,emit})
// 值改变事件
const changeModelValueEvent = changeDataModelValueEventHandle({reactiveData,hasPermission,emit})

const beformChangeValue = ref()
const beforeChangeHandle = () => {
  beformChangeValue.value = reactiveData.currentModelValue
  return props.beforeChange()
}
const change = (value) => {
  if(changeModelValueEvent(value)){
    return
  }
  submit(value)
}
const confirmCancelFn = ()=>{
  reactiveData.oldModelValue = beformChangeValue.value
  reactiveData.currentModelValue = beformChangeValue.value
}
// click 按钮事件
const submit = method({props,reactiveData,emit,hasPermission,confirmCancelFn})

</script>
<template>
  <el-switch v-if="hasPermission.render"
             v-model="reactiveData.currentModelValue"
             :title="hasDisabled.disabledReason || title"
             v-bind="$attrs"
             :disabled="hasDisabled.disabled"
             :loading="loading"
             :before-change="beforeChangeHandle"
             @update:modelValue="updateModelValueEvent"
             @change="change"
  >
  </el-switch>
</template>

<style scoped>

</style>