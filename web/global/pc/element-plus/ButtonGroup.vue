<script setup name="ButtonGroup" lang="ts">
/**
 * 自定义封装按钮组
 * 封装理由：1. 通过数据配置的方式生成按钮更方便
 */
import {reactive ,computed} from 'vue'
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  /**
   *  按钮，数据项是一个对象，兼容自定义 pt-button 的所有属性
   *  数组项如：{
   *    position: 'more' // 可选值 default 或 more
   *    txt: '删除' // 按钮的文本
   *    ... // 其它属性同自定义 pt-button
   *  }
   */
  options: {
    type: Array,
    default: () => []
  },
  // dropdown 属性,默认值在计算属性中
  dropdownOptions: {
    type: Object,
    default: () => {
      return {}
    }
  },
  // dropdown trigger button 属性，默认值在计算属性中
  dropdownTriggerButtonOptions: {
    type: Object,
    default: () => {
      return {}
    }
  }
})
// 属性
const reactiveData = reactive({
  dropdownVisible: false
})
// 计算属性

// 这里和 props.options 重名了，但在模板是使用 options 变量是这个计算值，也就是说这里会覆盖在模板中的值
const options = computed(() => {
  return props.options.filter(item => item.position == undefined || item.position == 'default')
})
// 更多按钮
const moreButtons = computed(() => {
  return props.options.filter(item => item.position == 'more')
})
// 下拉组件原始选项
const dropdownOptions = computed(() => {
  let dft = {
    trigger: 'click',
    hideOnClick: false
  }
  return Object.assign(dft,props.dropdownOptions)
})

// 下拉组件原始选项
const dropdownTriggerButtonOptions = computed(() => {
  let dft = {
    type: 'primary'
  }
  return Object.assign(dft,props.dropdownTriggerButtonOptions)
})
// 方法
// dropdown 显示隐藏事件处理
const dropdownVisible = (visible) => {
  reactiveData.dropdownVisible = visible
}
</script>
<template>
  <el-button-group v-bind="$attrs">
    <template v-for="(button,index) in options" :key="index">
      <PtButton v-bind="button">
        <template #default v-if="button.txt">
          {{button.txt}}
        </template>
      </PtButton>
    </template>

    <el-dropdown v-if="moreButtons.length > 0" ref="dropdown" @visible-change="dropdownVisible" v-bind="dropdownOptions">
      <PtButton v-bind="dropdownTriggerButtonOptions" :icon="reactiveData.dropdownVisible ? 'ArrowUpBold' : 'ArrowDownBold'"></PtButton>
      <template #dropdown>
        <el-dropdown-menu>
          <template v-for="(button,index) in moreButtons" :key="index">
            <el-dropdown-item>
              <PtButton v-bind="button">
                <template #default v-if="button.txt">
                  {{button.txt}}
                </template>
              </PtButton>
            </el-dropdown-item>
          </template>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </el-button-group>

</template>

<style scoped>

</style>