<script setup name="TimePicker">
/**
 * 自定义封装输入
 * 封装理由：1. 后端使用时支持权限控制
 * 注意：由于elementplus picker使用 tooltip包裹，未传递 title，所以要禁用时不能提示禁用原因
 */
import {inject, reactive, watch} from 'vue'

import {hasPermissionConfig, permissionProps} from './permission'
import {disabledConfig, disabledProps} from './disabled'
import {
  changeDataModelValueEventHandle,
  emitDataModelEvent,
  reactiveDataModelData,
  updateDataModelValueEventHandle
} from './dataModel'


// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 值绑定 绑定值，如果它是数组，长度应该是 2（用来支持范围选择）
  modelValue: [Date,Number,String,Array],
  // 禁用相关属性
  ...disabledProps,
  // 权限相关
  ...permissionProps,
  // 鼠标 hover 提示语
  title: {
    type: String
  },
  // 是否支持清空选项
  clearable: {
    type: Boolean,
    default: true
  },
})

// 属性
const reactiveData = reactive({
  ...reactiveDataModelData(props)
})

const injectPermissions = inject('permissions', [])
// 是否有权限
const hasPermission = hasPermissionConfig({
  props,
  injectPermissions,
  noPermissionSimpleText: `「此」时间选择器`
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
  'focus',
  'blur',
  'calendar-change',
  'panel-change',
  'visible-change',
])

// 方法
// 值更新事件
const updateModelValueEvent = updateDataModelValueEventHandle({reactiveData,hasPermission,emit})
// 值改变事件
const changeModelValueEvent = changeDataModelValueEventHandle({reactiveData,hasPermission,emit})

</script>
<template>
  <el-time-picker v-if="hasPermission.render"
            v-model="reactiveData.currentModelValue"
            :title="hasDisabled.disabledReason || title"
            v-bind="$attrs"
            :disabled="hasDisabled.disabled"
            :clearable="clearable"
            @update:modelValue="updateModelValueEvent"
            @change="changeModelValueEvent"
            @focus="(e) => $emit('focus', e)"
            @blur="(e) => $emit('blur', e)"
            @visible-change="(visibility) => $emit('visible-change', visibility)"
  >
  </el-time-picker>
</template>

<style scoped>

</style>
