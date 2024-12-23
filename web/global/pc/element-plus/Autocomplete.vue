<script setup name="Autocomplete">
/**
 * 自定义封装输入
 * 封装理由：1. 后端使用时支持权限控制
 */
import {inject, reactive} from 'vue'

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
  // 值绑定
  modelValue: String,
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
  noPermissionSimpleText: `「此」自动补全输入`
})
// 是否禁用
const hasDisabled = disabledConfig({props,hasPermission})

// 事件
const emit = defineEmits([
  // 用来更新 modelValue
  emitDataModelEvent.updateModelValue,
  emitDataModelEvent.change,
  // element plus 官方文档关于事件没有列全，实际这些事件都可以触发，和 input类似
  'focus',
  'blur',
  'input',
  'clear',
  'select',
])

// 方法
// 值更新事件
const updateModelValueEvent = updateDataModelValueEventHandle({reactiveData,hasPermission,emit})
// 值改变事件
const changeModelValueEvent = changeDataModelValueEventHandle({reactiveData,hasPermission,emit})
const inputModelValueEvent = changeDataModelValueEventHandle({reactiveData,hasPermission,emit,eventName: 'input'})

const selectModelValueEvent = changeDataModelValueEventHandle({reactiveData,hasPermission,emit,eventName: 'select'})

</script>
<template>
  <el-input v-if="hasPermission.render"
            v-model="reactiveData.currentModelValue"
            :title="hasDisabled.disabledReason || title"
            v-bind="$attrs"
            :disabled="hasDisabled.disabled"
            :clearable="clearable"
            @update:modelValue="updateModelValueEvent"
            @change="changeModelValueEvent"
            @focus="(e) => $emit('focus', e)"
            @blur="(e) => $emit('blur', e)"
            @clear="(e) => $emit('clear', e)"
            @input="inputModelValueEvent"
            @select="selectModelValueEvent"
  >
    <template #default v-if="$slots.default">
      <slot name="default" :hasPermission="hasPermission"></slot>
    </template>
    <template #prefix v-if="$slots.prefix">
      <slot name="prefix" :hasPermission="hasPermission"></slot>
    </template>
    <template #suffix v-if="$slots.suffix">
      <slot name="suffix" :hasPermission="hasPermission"></slot>
    </template>
    <template #prepend v-if="$slots.prepend">
      <slot name="prepend" :hasPermission="hasPermission"></slot>
    </template>
    <template #append v-if="$slots.append">
      <slot name="append" :hasPermission="hasPermission"></slot>
    </template>
  </el-input>
</template>

<style scoped>

</style>
