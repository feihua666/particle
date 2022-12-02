<script setup name="MenuItem">
/**
 * 支持权限
 */
import {useSlots,reactive,inject} from 'vue'

import {permissionProps,hasPermissionConfig} from './permission'
import {disabledProps,disabledConfig} from './disabled'
const slots = useSlots()
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 图标名字
  icon: {
    type: String,
  },
  // 禁用相关属性
  ...disabledProps,
  // 权限相关
  ...permissionProps,
  // 鼠标 hover 提示语
  title: {
    type: String
  },
  // 标题文本
  titleText: {
    type: String
  },
})
// 属性
const reactiveData = reactive({
  // 根据默认 title 插槽文件计算按钮样式
  titleSlotText: slots.title ? slots.title()[0].children : '',
  // 全屏加载状态实例对象保持变量
  loadingService: null
})
const injectPermissions = inject('permissions', [])

// 是否有权限
const hasPermission = hasPermissionConfig({
  props,
  injectPermissions,
  noPermissionSimpleText:  `「${reactiveData.titleSlotText || '此'}」菜单项`
})
// 是否禁用
const hasDisabled = disabledConfig({props,hasPermission})

</script>

<template>
  <el-menu-item v-if="hasPermission.render"
                :title="hasDisabled.disabledReason || title"
                :disabled="hasDisabled.disabled"
                v-bind="$attrs">
    <el-icon v-if="icon || $slots.icon">
      <component :is="icon" v-if="icon" />
      <slot v-else name="icon" />
    </el-icon>

    <template #title v-if="$slots.title">
      <slot name="title" :hasPermission="hasPermission" />
    </template>
    <template #title v-if="!$slots.title">
      {{titleText}}
    </template>
  </el-menu-item>
</template>

<style scoped>

</style>