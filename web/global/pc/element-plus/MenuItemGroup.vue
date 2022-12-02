<script setup name="MenuItemGroup">
import {reactive ,computed,watch,onMounted} from 'vue'
import {dataMethodProps,reactiveDataMethodData,doDataMethod,emitDataMethodEvent} from './dataMethod'
import PtMenuItem from './MenuItem.vue'


// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 数据初始化时，加载初始数据 loading 效果
  dataLoading: {
    type: Boolean,
    default: false
  },
  // 数据
  options: {
    type: Array,
    default: () => ([])
  },
  // 数据加载相关
  ...dataMethodProps,
  // 标题文本
  titleText: {
    type: String
  },
})
// 属性
const reactiveData = reactive({
  // 数据与加载
  ...reactiveDataMethodData,
})
// 计算属性

// loading 计算
const loading = computed(() => {
  return props.dataLoading || reactiveData.dataMethodLocalLoading
})
// 这里和 props.options 重名了，但在模板是使用 options 变量是这个值，也就是说这里会覆盖在模板中的值
const options = computed(() => {
  return props.options.length > 0 ? props.options : reactiveData.dataMethodData
})

// 侦听
watch(
    () => loading.value,
    (loading,loadingOld) => {

    }
)
// 事件
const emit = defineEmits([emitDataMethodEvent.dataMethodData,emitDataMethodEvent.dataMethodDataLoading,emitDataMethodEvent.dataMethodResult])

// 挂载
onMounted(() => {
  doDataMethod({props,reactiveData,emit})
})

// 方法

</script>
<template>
  <el-menu-item-group
      v-loading="loading"
      v-bind="$attrs"
  >
    <template #title v-if="$slots.title">
      <slot name="title" />
    </template>
    <template #title  v-if="!$slots.title">
      {{titleText}}
    </template>

    <template #default v-if="$slots.default">
      <slot name="default" :options="options" />
    </template>
    <template #default  v-if="!$slots.default">
      <template v-for="(menuItem,index) in options" :key="index">
        <PtMenuItem :icon="menuItem.icon" :titleText="menuItem.name"></PtMenuItem>
      </template>
    </template>

  </el-menu-item-group>
</template>
<style scoped>

</style>