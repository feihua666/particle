<script setup name="Input">
/**
 * 自定义封装输入
 * 封装理由：1. 后端使用时支持权限控制
 */
import { reactive ,inject, watch} from 'vue'

import {permissionProps,hasPermissionConfig} from './permission'
import {disabledProps,disabledConfig} from './disabled'
import {reactiveDataModelData,emitDataModelEvent,updateDataModelValueEventHandle,changeDataModelValueEventHandle} from './dataModel'


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
  // 默认的 append slot组件
  appendComp: Object
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
  noPermissionSimpleText: `「此」输入`
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
  'input',
  'clear',
])

// 方法
// 值更新事件
const updateModelValueEvent = updateDataModelValueEventHandle({reactiveData,hasPermission,emit})
// 值改变事件
const changeModelValueEvent = changeDataModelValueEventHandle({reactiveData,hasPermission,emit})
const inputModelValueEvent = changeDataModelValueEventHandle({reactiveData,hasPermission,emit,eventName: 'input'})

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
  >
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
<!--  通过属性配置 append  -->
    <template #append v-if="!$slots.append && appendComp">
      <component :is="appendComp.comp" v-bind="appendComp.compProps"></component>
    </template>
  </el-input>
</template>

<style scoped>

</style>