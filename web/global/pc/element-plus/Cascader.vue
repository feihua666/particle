<script setup name="Cascader">
/**
 * 自定义封装 cascader 级联选择
 * 封装理由：1. 可以自助获取数据，更方便
 *          2. 后端使用时支持权限控制
 *          3. 自带加载数据 dataLoading 功能效果
 */
import {reactive ,computed,onMounted,inject} from 'vue'
import {permissionProps,hasPermissionConfig} from './permission'
import {disabledProps,disabledConfig} from './disabled'
import {dataMethodProps,reactiveDataMethodData,doDataMethod,emitDataMethodEvent} from './dataMethod'
import {reactiveDataModelData,emitDataModelEvent,updateDataModelValueEventHandle,changeDataModelValueEventHandle} from './dataModel'

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 值绑定
  modelValue: [Number, String, Array],
  // 是否支持清空选项
  clearable: {
    type: Boolean,
    default: true
  },
  // 多选模式下是否折叠Tag
  collapseTags: {
    type: Boolean,
    default: true
  },
  // 当鼠标悬停于折叠标签的文本时，是否显示所有选中的标签。 要使用此属性，collapse-tags属性必须设定为 true
  collapseTagsTooltip: {
    type: Boolean,
    default: true
  },
  // 输入框中是否显示选中值的完整路径
  showAllLevels: {
    type: Boolean,
    default: false
  },
  // 该选项是否可以被搜索
  filterable: {
    type: Boolean,
    default: false
  },
  // 数据
  options: {
    type: Array,
    default: () => ([])
  },
  // 选项
  props: {
    type: Object,
    // 默认值在计算属性那里设置
    default: () => ({})
  },

  // 禁用相关属性
  ...disabledProps,
  // 权限相关
  ...permissionProps,
  // 数据初始化时，加载初始数据 loading 效果
  dataLoading: {
    type: Boolean,
    default: false
  },
  // 鼠标 hover 提示语
  title: {
    type: String
  },
  ...dataMethodProps
})
// 属性
const reactiveData = reactive({
  ...reactiveDataMethodData,
  ...reactiveDataModelData(props)
})
// 计算属性
// 这里和 props.options 重名了，但在模板是使用 options 变量是这个值，也就是说这里会覆盖在模板中的值
const options = computed(() => {
  return props.options.length > 0 ? props.options : reactiveData.dataMethodData
})
// propsOptions
const propsOptions = computed(() => {
  let defaultProps = {
    // 指定选项的值为选项对象的某个属性值
    value: 'id',
    // 指定选项标签为选项对象的某个属性值
    label: 'name',
    // 指定选项的叶子节点的标志位为选项对象的某个属性值
    leaf: 'isLeaf',
    // 在选中节点改变时，是否返回由该节点所在的各级菜单的值所组成的数组，若设置 false，则只返回该节点的值
    emitPath: false,
    // 选择任意一级选项
    checkStrictly: true
  }
  return Object.assign(defaultProps, props.props)
})
// 这里和 props.dataLoading 重名了，但在模板是使用 dataLoading 变量是这个值，也就是说这里会覆盖在模板中的值
const dataLoading = computed(() => {
  return props.dataLoading || reactiveData.dataMethodLocalLoading
})
const injectPermissions = inject('permissions', [])
// 是否有权限
const hasPermission = hasPermissionConfig({
  props,
  injectPermissions,
  noPermissionSimpleText: `「此」级联选择`
})
// 是否禁用
const hasDisabled = disabledConfig({props,dataLoading,hasPermission})

// 事件
const emit = defineEmits([
  // 用来更新 modelValue
  emitDataModelEvent.updateModelValue,
  emitDataModelEvent.change,
  'focus',
  'blur',
  'visible-change',
  'expand-change',
  'remove-tag',
  emitDataMethodEvent.dataMethodResult,
  emitDataMethodEvent.dataMethodData,
])
// 挂载
onMounted(() => {
  doDataMethod({props,reactiveData,emit})
})
// 方法
// 值更新事件
const updateModelValueEvent = updateDataModelValueEventHandle({reactiveData,hasPermission,emit})
// 值改变事件
const changeModelValueEvent = changeDataModelValueEventHandle({reactiveData,hasPermission,emit})
</script>
<template>
<!-- 添加 el-cascader 类，主要是使用其默认css设置 如：内联显示 -->
  <div v-if="hasPermission.render"  v-loading="dataLoading" class="el-cascader pt-cascader" element-dataLoading-background="rgba(122, 122, 122, 0)"
        :title="hasDisabled.disabledReason || title">
    <el-cascader
                 v-model="reactiveData.currentModelValue"
                 v-bind="$attrs"
                 :clearable="clearable"
                 :show-all-levels="showAllLevels"
                 :filterable="filterable"
                 :options="options"
                 :props="propsOptions"
                 :collapseTags="collapseTags"
                 :collapseTagsTooltip="collapseTagsTooltip"
                 :disabled="hasDisabled.disabled"
                 @update:modelValue="updateModelValueEvent"
                 @change="changeModelValueEvent"
                 @focus="(e) => $emit('focus', e)"
                 @blur="(e) => $emit('blur', e)"
                 @visible-change="(visible) => $emit('visible-change', visible)"
                 @expand-change="(value) => $emit('expand-change', value)"
                 @remove-tag="(valueByOption) => $emit('remove-tag', valueByOption)">
      <!-- 在 filterable 为true时， 无匹配选项时的内容  -->
      <template #empty>未匹配到数据</template>
    </el-cascader>
  </div>

</template>
<style >
.pt-cascader .el-loading-spinner {
  --el-loading-spinner-size: 20px !important;
}
</style>