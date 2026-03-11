<script setup name="Pagination">
import {inject, computed} from 'vue'
import {hasPermissionConfig, permissionProps} from './permission'
import {disabledConfig, disabledProps} from './disabled'

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  currentPage: {
    type: [Number,String],
    default: 1,
  },
  pageSize: {
    type: [Number,String],
    default: 10,
  },
  total: {
    type: [Number,String],
    default: 0,
  },
  // 分页大小 'large' | 'default' | 'small'
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

// 类型转换计算属性
const convertedCurrentPage = computed(() => Number(props.currentPage))
const convertedPageSize = computed(() => Number(props.pageSize))
const convertedTotal = computed(() => Number(props.total))

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
      :current-page="convertedCurrentPage"
      :page-sizes="[10, 20, 50, 100, 200, 500]"
      :page-size="convertedPageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="convertedTotal"
      :disabled="hasDisabled.disabled"
      :title="hasDisabled.disabledReason"
      style="text-align: right;">
  </el-pagination>
</template>
