<script setup name="FormItemDetail">
/**
 * 自定义封装 FormItemDetail 表单项功能
 * 封装理由：1. 集成多各表单输入于一体，省去烦琐的手动模板，只需要指定使用的组件
 *          2. 一致的使用方式
 */
import {computed, inject, nextTick, reactive, ref, watch} from 'vue'
import {getVal} from "../../common/tools/ObjectTools"

import {hasPermissionConfig, permissionProps} from './permission'
import {disabledConfig, disabledProps} from './disabled'
import {
  changeDataModelValueEventHandle,
  emitDataModelEvent,
  reactiveDataModelData,
  updateDataModelValueEventHandle
} from './dataModel'
import PtCompAdapter from '../../common/CompAdapter.vue'

const adapterRef = ref(null)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 组件名称，注册名称
  comp: {
    type: String
  },
  // 组件是否支持权限，如果支持权限，不再封装，将使用组件的权限逻辑
  permissionSupport: {
    type: Boolean,
    default: undefined
  },
  // 表单数据对象
  form: {
    type: Object,
    required: true
  },
  // 表单额外数据对象
  formData: {
    type: Object,
    required: true
  },
  // 表单数据项的键值
  prop: {
    type: String,
    required: true
  },
  // 禁用相关属性
  ...disabledProps,
  // 鼠标 hover 提示语
  title: {
    type: String
  },
  // 数据变化事件,一般只要有数据变化就会触发，如果在该方法中使用formData，可能会取不到新值的情况，可以结合nextTick使用
  valueChange: {
    type: Function,
    default: ({form,formData,prop,newValue,oldValue}) =>({})
  },
  // 数据变化事件,一般ui组件值手动更新会触发（如ui手动输入或手动ui选择下拉框），如果在该方法中使用formData，可能会取不到新值的情况，可以结合nextTick使用
  updateModelValueChange: {
    type: Function,
    // 暂不支持oldValue
    default: ({form,formData,prop,newValue,oldValue}) =>({})
  },
  // 数据变化事件,一般ui组件值手动更新会触发（如手动ui选择下拉框），如果在该方法中使用formData，可能会取不到新值的情况，可以结合nextTick使用
  changeModelValueChange: {
    type: Function,
    // 暂不支持oldValue
    default: ({form,formData,prop,newValue,oldValue}) =>({})
  },
  // 权限相关
  ...permissionProps,
})
// 属性
const reactiveData = reactive({
  ...reactiveDataModelData(props)
})
// 计算属性
const permissionSupport = computed(() => {
  if(props.permissionSupport === true || props.permissionSupport === false){
    return props.permissionSupport
  }
  if(props.comp){

    // 如果是原生 elementPlus 组件，不支持权限，如果指定了权限码，使用内置权限封装
    if (props.comp.indexOf('el-') == 0) {
      return false
    }
    // 自动计算，如果是自己封装的，基本都支持权限
    if (props.comp.indexOf('Pt') == 0) {
      return true
    }
  }
})
// 初始值
reactiveData.currentModelValue = props.form[props.prop]
reactiveData.oldModelValue = props.form[props.prop]
props.formData[props.prop + 'Ref'] = adapterRef

const injectPermissions = inject('permissions', [])

// 是否有权限
const hasPermission = hasPermissionConfig({props,injectPermissions,noPermissionSimpleText: `「此」`})

const hasDisabled = disabledConfig({props,hasPermission})
// 事件
const emit = defineEmits([
  // 用来更新 modelValue
  emitDataModelEvent.updateModelValue,
  emitDataModelEvent.change,
])

watch(() => props.form[props.prop],(val,oldVal)=> {
  if (hasPermission.value.enable && !hasPermission.value.hasPm) {
    // 没有权限时，在下一次更新将原来值重置回来，不能编辑
    nextTick(() => {
      props.form[props.prop] = reactiveData.oldModelValue
    })
  }else {
    reactiveData.currentModelValue = val
    reactiveData.oldModelValue = val
    props.valueChange({form: props.form,formData: props.formData,prop:  props.prop,newValue: val,oldValue: oldVal})
  }

})
// 方法
// 值更新事件
const updateModelValueEvent = updateDataModelValueEventHandle({reactiveData,hasPermission,emit,valueEventCallback:(value)=>{
    props.updateModelValueChange({form: props.form,formData: props.formData,prop:  props.prop,newValue: value,oldValue: null})
  }})
const updateModelValueEmitOnlyEvent = updateDataModelValueEventHandle({reactiveData,hasPermission,emit,emitOnly:true,valueEventCallback:(value)=>{
    props.updateModelValueChange({form: props.form,formData: props.formData,prop:  props.prop,newValue: value,oldValue: null})
  }})

// 值改变事件
const changeModelValueEvent = changeDataModelValueEventHandle({reactiveData,hasPermission,emit,valueEventCallback:(value)=>{
    props.changeModelValueChange({form: props.form,formData: props.formData,prop:  props.prop,newValue: value,oldValue: null})
  }})
// 取文本的值
const propValue = () => {
  let r = getVal(props.form,props.prop,props.form)
  return r
}
const txtValue = () => {
  let r = propValue()
  if(r === 0){
    return r
  }
  if(typeof r == 'boolean'){
    return r ? '是' : '否'
  }
  return r
}
</script>
<template>
  <template v-if="comp && hasPermission.render">
    <template v-if="comp == 'txt'">
      <span>{{txtValue()}}</span>
    </template>
    <template v-else>
      <PtCompAdapter ref="adapterRef" v-if="permissionSupport" :is="comp" v-model="props.form[props.prop]"
                 v-bind="$attrs"
                 :permission="permission"
                 :permissions="permissions"
                 :noPermissionView="noPermissionView"
                 :noPermissionFn="noPermissionFn"
                 :noPermissionSimpleText="noPermissionSimpleText"
                 :noPermissionText="noPermissionText"
                 :disabled="hasDisabled.disabled"
                 :disabledReason="disabledReason"
                 @update:modelValue="updateModelValueEmitOnlyEvent"
                 @update:modelData="(data) => { props.formData[props.prop] = data }"
      >
        <!--  插槽  -->
        <template #default v-if="$slots.default">
          <slot></slot>
        </template>
      </PtCompAdapter>
      <PtCompAdapter  ref="adapterRef" v-else :is="comp" v-model="props.form[props.prop]" v-bind="$attrs"
                 :disabled="hasDisabled.disabled"
                 @update:modelValue="updateModelValueEvent"
                 @change="changeModelValueEvent"
                 @update:modelData="(data) => { props.formData[props.prop] = data }"
                 :title="hasDisabled.disabledReason || title">
        <!--  插槽  -->
        <template #default v-if="$slots.default">
          <slot></slot>
        </template>
      </PtCompAdapter>
    </template>
  </template>
</template>

<style scoped>

</style>
