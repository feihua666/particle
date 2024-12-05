<script setup name="Pagination">
import {inject} from 'vue'
import {hasPermissionConfig, permissionProps} from './permission'
import {disabledConfig, disabledProps} from './disabled'

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  currentPage: {
    type: Number,
    default: 1
  },
  pageSize: {
    type: Number,
    default: 10
  },
  total: {
    type: Number,
    default: 0
  },
  size: {
    type: String,
  },
  background: {
    type: Boolean,
    default: true
  },
  // 禁用相关属性
  ...disabledProps,
  // 权限相关
  ...permissionProps,
})
const injectPermissions = inject('permissions', [])
// 是否有权限
const hasPermission = hasPermissionConfig({props,injectPermissions,noPermissionSimpleText: `「此」分页操作`})

const hasDisabled = disabledConfig({props,hasPermission})
// 事件
const emit = defineEmits(['sizeChange','currentChange'])

const sizeChange = (val)=>{
  let doAlertOrCustomFnIfNeccessaryResult = hasPermission.value.doAlertOrCustomFnIfNeccessary()
  if (doAlertOrCustomFnIfNeccessaryResult) {
    return
  }
  emit('sizeChange', val)
}
const currentChange = (val)=>{
  let doAlertOrCustomFnIfNeccessaryResult = hasPermission.value.doAlertOrCustomFnIfNeccessary()
  if (doAlertOrCustomFnIfNeccessaryResult) {
    return
  }
  emit('currentChange', val)
}
</script>
<template>
  <el-pagination v-if="hasPermission.render"
      :background="background"
      :size="size"
      @size-change="sizeChange"
      @current-change="currentChange"
      :current-page="currentPage"
      :page-sizes="[10, 20, 50, 100, 200, 500]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      :disabled="hasDisabled.disabled"
      :title="hasDisabled.disabledReason"
      style="text-align: right;">
  </el-pagination>
</template>
