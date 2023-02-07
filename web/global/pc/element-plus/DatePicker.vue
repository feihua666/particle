<script setup name="DatePicker">
/**
 * 自定义封装输入
 * 封装理由：1. 后端使用时支持权限控制
 */
import { reactive ,inject, watch, computed} from 'vue'

import {permissionProps,hasPermissionConfig} from './permission'
import {disabledProps,disabledConfig} from './disabled'
import {reactiveDataModelData,emitDataModelEvent,updateDataModelValueEventHandle,changeDataModelValueEventHandle} from './dataModel'


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
  // 值格式
  valueFormat: String,
  // 自定义 valueFormat，true=不计算默认情况
  customValueFormat: {
    type: Boolean,
    default: false
  },
  // 类型 el-date-picker 原生属性如：date
  type: String
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
  noPermissionSimpleText: `「此」日期选择器`
})
// 是否禁用
const hasDisabled = disabledConfig({props,hasPermission})
const valueFormat = computed(()=>{
  if(props.type && !props.valueFormat && !props.customValueFormat){
    if('date' == props.type){
      return 'YYYY-MM-DD'
    }else if('datetime' == props.type){
      return 'YYYY-MM-DD HH:mm:ss'
    }
  }
  return props.valueFormat

})
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
  <el-date-picker v-if="hasPermission.render"
            v-model="reactiveData.currentModelValue"
            :title="hasDisabled.disabledReason || title"
            v-bind="$attrs"
                  :valueFormat="valueFormat"
                  :type="type"
            :disabled="hasDisabled.disabled"
            :clearable="clearable"
            @update:modelValue="updateModelValueEvent"
            @change="changeModelValueEvent"
            @focus="(e) => $emit('focus', e)"
            @blur="(e) => $emit('blur', e)"
            @calendar-change="(val) => $emit('calendar-change', val)"
            @panel-change="(date, mode, view) => $emit('panel-change', date, mode, view)"
            @visible-change="(visibility) => $emit('visible-change', visibility)"
  >
    <template #default v-if="$slots.default">
      <slot name="default" :hasPermission="hasPermission"></slot>
    </template>
    <template #range-separator v-if="$slots['range-separator']">
      <slot name="range-separator" :hasPermission="hasPermission"></slot>
    </template>
  </el-date-picker>
</template>

<style scoped>

</style>