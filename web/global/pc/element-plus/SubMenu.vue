<script setup name="SubMenu">
import {computed, onMounted, reactive, watch} from 'vue'
import {dataMethodProps, doDataMethod, emitDataMethodEvent, reactiveDataMethodData} from './dataMethod'
import {menuConfig, menuProps} from './menu'


// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 数据初始化时，加载初始数据 loading 效果
  dataLoading: {
    type: Boolean,
    default: false
  },
  // 图标名字
  icon: {
    type: String,
  },
  // 数据
  options: {
    type: Array,
    default: () => ([])
  },
  // 数据加载相关
  ...dataMethodProps,
  // 可选值 sub-menu,menu-item,menu-item-group
  menuView: {
    type: String,
    default: 'menu-item'
  },
  // 标题文本
  titleText: {
    type: String
  },
  ...menuProps
})
// 属性
const reactiveData = reactive({
  // 数据与加载
  ...reactiveDataMethodData(),
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
const {
  propsOptions,
  isMenu,
  isPage,
  isGroup,
} = menuConfig({props})
// 侦听
watch(
    () => loading.value,
    (loading,loadingOld) => {

    }
)
watch(
    () => props.dataMethodParam,
    (val) => {
      doDataMethod({props,reactiveData,emit})
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
  <el-sub-menu
      v-loading="loading"
      v-bind="$attrs"
  >
    <template #title v-if="$slots.title">
      <slot name="title" />
    </template>
    <template #title v-if="!$slots.title">
      <el-icon v-if="icon || $slots.icon">
        <component :is="icon" v-if="icon" />
        <slot v-else name="icon" />
      </el-icon>
      <span>{{titleText}}</span>
    </template>

    <template #default v-if="$slots.default">
      <slot name="default" :options="options" ></slot>
    </template>
    <template #default  v-if="!$slots.default">
      <template v-for="(menuItem,index) in options" :key="index">

        <template v-if="isMenu(menuItem)">
          <PtSubMenu :index="menuItem[propsOptions.index]|| menuItem[propsOptions.backIndex]" :titleText="menuItem[propsOptions.name]" :icon="menuItem[propsOptions.icon]" :options="menuItem[propsOptions.children]" :props="propsOptions"></PtSubMenu>
        </template>
        <template v-else-if="isPage(menuItem)">
          <PtMenuItem  :index="menuItem[propsOptions.index]|| menuItem[propsOptions.backIndex]"  :titleText="menuItem[propsOptions.name]" :icon="menuItem[propsOptions.icon]" ></PtMenuItem>
        </template>
        <template v-else-if="isGroup(menuItem)">
          <PtMenuItemGroup   :index="menuItem[propsOptions.index] || menuItem[propsOptions.backIndex]" :titleText="menuItem[propsOptions.name]" :icon="menuItem[propsOptions.icon]" :options="menuItem[propsOptions.children]" :props="propsOptions"></PtMenuItemGroup>
        </template>

      </template>
    </template>

  </el-sub-menu>
</template>
<style scoped>

</style>
