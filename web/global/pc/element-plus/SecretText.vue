<script setup name="SecretText" lang="ts">
/**
 * 敏感信息组件 Secret
 * 一般为展示用，比如：密码，将显示为 ****
 * 封装理由：1. 支持 v-model 传值
 *          2. 后端使用时支持权限控制
 */
import {computed, inject, reactive, watch} from 'vue'
import {hasPermissionConfig, permissionProps} from './permission'
import {disabledConfig, disabledProps} from './disabled'

import {isArray} from "../../common/tools/ArrayTools";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 值绑定
  modelValue: [Number, String, Array],
  // 鼠标 hover 提示语
  title: {
    type: String
  },
  /**
   * 显示的敏感信息隐藏文本
   */
  secretValue: {
    type: Function,
    default: (currentModelValue) => {
      // 字符参考 https://mp.weixin.qq.com/s?__biz=MjM5MTUwNjA2Mw==&mid=2650322799&idx=5&sn=f70090da6b1506f0d5b876e2f6bead11&chksm=beb89c8f89cf15997a6b54039b15fedfcb07ab2cd6ca617d455db3058f58048a742ec23786c3&scene=27
      return '☀☀☀☀'
    }
  },
  /**
   * 原文本
   */
  originValue: {
    type: Function,
    default: (currentModelValue) => {
      if (isArray(currentModelValue)) {
        return currentModelValue.join(' ')
      }
      return currentModelValue
    }
  },
  // 默认是否展示隐藏文本
  defaultShowSecretText: {
    type: Boolean,
    default: true
  },
  // 显示切换按钮
  showSwitchButton: {
    type: Boolean,
    default: true
  },
  // 禁用相关属性
  ...disabledProps,
  // 权限相关
  ...permissionProps,
})
// 属性
const reactiveData = reactive({
  currentModelValue: props.modelValue,
  showSecretText: props.defaultShowSecretText
})

const injectPermissions = inject('permissions', [])
// 是否有权限
const hasPermission = hasPermissionConfig({
  props,
  injectPermissions,
  noPermissionSimpleText: `「此」信息`
})
// 是否禁用
const hasDisabled = disabledConfig({props,hasPermission})

// 显示的最终的值
const computedValue = computed(()=> {
  if(reactiveData.showSecretText){
    return props.secretValue(reactiveData.currentModelValue)
  }
  return props.originValue(reactiveData.currentModelValue)
})
// 侦听
watch(
    () => props.modelValue,
    (val) => {
      reactiveData.currentModelValue = val
    }
)
watch(
    () => props.defaultShowSecretText,
    (val) => {
      reactiveData.showSecretText = val
    }
)
// 切换
const doSubmit = () => {
  // 从明文切换到隐藏不需要判断权限
  if(reactiveData.showSecretText){
    let doAlertOrCustomFnIfNeccessaryResult = hasPermission.value.doAlertOrCustomFnIfNeccessary()
    if (doAlertOrCustomFnIfNeccessaryResult) {
      return
    }
  }
  reactiveData.showSecretText = !reactiveData.showSecretText
}
</script>

<template>
<span
    v-if="hasPermission.render"  class="pt-openapiSecret-text"
    :title="hasDisabled.disabledReason || title"
    v-bind="$attrs"
>
  <el-icon v-if="showSwitchButton" class="pt-openapiSecret-text-bt" :class="{disabled: hasDisabled.disabled}"  @click="doSubmit">
    <Hide v-if="reactiveData.showSecretText" ></Hide>
    <View v-else ></View>
  </el-icon>
  <span class="pt-openapiSecret-text-value" >{{computedValue}}</span>

</span>
</template>


<style scoped>
.pt-openapiSecret-text-bt{
  margin-right: .5rem;
  cursor: pointer;
  vertical-align: middle;
}
.pt-openapiSecret-text-bt.disabled{
  cursor: not-allowed;
}

</style>
