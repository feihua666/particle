<script setup name="Menu">
/**
 * 自定义封装 菜单
 * 封装理由：1. 一致的方式使用 Menu，并获取一致的表现
 *          2. 使菜单自带 loading 自动处理效果，无需额外处理
 *          3. 后端使用时支持权限控制
 *          4. 自动加载数据，可结合后台接口更方便使用
 */
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
  // 数据
  options: {
    type: Array,
    default: () => ([])
  },
  // 数据加载相关
  ...dataMethodProps,
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
// 事件
const emit = defineEmits(['open','close','select',emitDataMethodEvent.dataMethodData,emitDataMethodEvent.dataMethodDataLoading,emitDataMethodEvent.dataMethodResult])

// 挂载
onMounted(() => {
  doDataMethod({props,reactiveData,emit})

})

// 方法

</script>
<template>
  <el-menu
      v-loading="loading"
      v-bind="$attrs"
      @open="(index,indexPath) => {$emit('open',index,indexPath)}"
      @close="(index,indexPath) => {$emit('close',index,indexPath)}"
      @select="(index,indexPath) => {$emit('select',index,indexPath)}"
  >

    <slot>
      <template v-for="(menuItem,index) in options" :key="index">
        <template v-if="isMenu(menuItem)">
          <PtSubMenu :index="menuItem[propsOptions.index] || menuItem[propsOptions.backIndex]" :titleText="menuItem[propsOptions.name]" :icon="menuItem[propsOptions.icon]" :options="menuItem[propsOptions.children]" :props="propsOptions"></PtSubMenu>
        </template>
        <template v-else-if="isPage(menuItem)">
          <PtMenuItem  :index="menuItem[propsOptions.index] || menuItem[propsOptions.backIndex]"  :titleText="menuItem[propsOptions.name]" :icon="menuItem[propsOptions.icon]" ></PtMenuItem>
        </template>
        <template v-else-if="isGroup(menuItem)">
          <PtMenuItemGroup :index="menuItem[propsOptions.index] || menuItem[propsOptions.backIndex]" :titleText="menuItem[propsOptions.name]" :icon="menuItem[propsOptions.icon]" :options="menuItem[propsOptions.children]" :props="propsOptions"></PtMenuItemGroup>
        </template>

      </template>
      <el-empty v-if="options.length == 0" :image-size="50" class="pt-height-100-pc"></el-empty>
    </slot>
  </el-menu>
</template>
<style scoped>

</style>