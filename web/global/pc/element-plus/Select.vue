<script setup name="Select">
/**
 * 自定义封装 select 选择
 * 封装理由：1. 可以自助获取数据，更方便
 *          2. 后端使用时支持权限控制
 *          3. 自带加载数据 dataLoading 功能效果
 */
import {reactive ,computed,onMounted,inject} from 'vue'
import {permissionProps,hasPermissionConfig} from './permission'
import {disabledProps,disabledConfig} from './disabled'
import {dataMethodProps,reactiveDataMethodData,doDataMethod,emitDataMethodEvent} from './dataMethod'
import {reactiveDataModelData,emitDataModelEvent,updateDataModelValueEventHandle,changeDataModelValueEventHandle} from './dataModel'
import {isPromise} from "../../common/tools/PromiseTools";
import {isArray} from "../../common/tools/ArrayTools";

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
  // 该选项是否可以被搜索
  filterable: {
    type: Boolean,
    default: true
  },
  // 需要 filterable 为 true 方可开启远程搜索
  remote: {
    type: Boolean,
    default: false
  },
  // 重写占位提示
  placeholder: {
    type: String
  },
  // 远程搜索函数，扩展 el-select,可以返回 promise ,配合 remoteMethodResult 方法属性，提取数据
  remoteMethod: {
    type: Function
  },
  // 处理远程搜索加载后的数据，仅限 remoteMethod 返回 promise 时有效
  // 主要是给 remoteMethod 获取的数据一个处理数据的机会
  remoteMethodResultHandle: {
    type: Function,
    default: ({success,error}) => {
      // success 为 res
      if (success) {
        return success.data
      }
      if (error) {
        return []
      }
    }
  },
  // 是否分组展示，会智能判断是否需要分组展示
  groupView: {
    type: Boolean,
    default: false
  },
  // option 的样式
  optionProps: {
    type: Object,
    default: () => ({})
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
  // 分组选项
  groupProps: {
    type: Object,
    // 默认值在计算属性那里设置
    default: () => ({})
  },
  // 禁用相关属性
  ...disabledProps,
  // 权限相关
  ...permissionProps,
  // 数据初始化时，加载初始数据 loading 效果，element plus 还有一个 loading 属性用来远程搜索时显示加载效果（是在远程搜索弹出层展示的）
  dataLoading: {
    type: Boolean,
    default: false
  },
  // loading 属性用来远程搜索时显示加载效果（是在远程搜索弹出层展示的）
  loading: {
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
  ...reactiveDataModelData(props),
  remoteLoading: props.loading
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
  }
  return Object.assign(defaultProps, props.props)
})
// 分组时取分组的数据
const groupProps = computed(() => {
  let defaultProps = {
    // 指定选项标签为选项对象的某个属性值
    label: 'name',
    // 数据键名
    options: 'options'
  }
  return Object.assign(defaultProps, props.groupProps)
})
// 这里和 props.dataLoading 重名了，但在模板是使用 dataLoading 变量是这个值，也就是说这里会覆盖在模板中的值
const groupView = computed(() => {
  return props.groupView || options.value?.some(item => item[groupProps.value.label] !== undefined && item[groupProps.value.options] !== undefined)
})

// 这里和 props.dataLoading 重名了，但在模板是使用 dataLoading 变量是这个值，也就是说这里会覆盖在模板中的值
const dataLoading = computed(() => {
  return props.dataLoading || reactiveData.dataMethodLocalLoading
})
// 这里和 props.loading 重名了，但在模板是使用 loading 变量是这个值，也就是说这里会覆盖在模板中的值
const loading = computed(() => {
  return props.loading || reactiveData.remoteLoading
})
// 这里和 props.placeholder 重名了，但在模板是使用 placeholder 变量是这个值，也就是说这里会覆盖在模板中的值
const placeholder = computed(() => {
  return props.placeholder || props.filterable && props.remote ? '输入关键字远程搜索': null
})
const injectPermissions = inject('permissions', [])
// 是否有权限
const hasPermission = hasPermissionConfig({
  props,
  injectPermissions,
  noPermissionSimpleText: `「此」下拉选择`
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
  emitDataMethodEvent.dataMethodDataLoading,
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

const getOptionProps = (optionsOptionProps={})=> {
  return Object.assign(props.optionProps,optionsOptionProps)
}

// 加载数据
const doRemoteMethod = (query) =>{
  reactiveData.remoteLoading = true
  if (props.remoteMethod) {
    const result =  props.remoteMethod(query)
    if (isPromise(result)) {
      const promiseResult = result.then(res =>{
        reactiveData.dataMethodData = props.remoteMethodResultHandle({success: res})
      }).catch(error => {
        reactiveData.dataMethodData = props.remoteMethodResultHandle({error: error})||[]
      }).finally(()=>{
        reactiveData.remoteLoading = false
      })
    }else if(isArray(result)) {
      reactiveData.dataMethodData = result
      reactiveData.remoteLoading = false
    }else {
      reactiveData.remoteLoading = false
    }
  }else {
    reactiveData.remoteLoading = false
  }
}
</script>
<template>

    <el-select  v-if="hasPermission.render"  class="pt-select" element-dataLoading-background="rgba(122, 122, 122, 0)"
                :title="hasDisabled.disabledReason || title"
        v-model="reactiveData.currentModelValue"
        v-bind="$attrs"
        :clearable="clearable"
        :filterable="filterable"
        :remote="remote"
        :placeholder="placeholder"
        :collapseTags="collapseTags"
        :collapseTagsTooltip="collapseTagsTooltip"
        :disabled="hasDisabled.disabled"
        :loading="loading"
        v-loading="dataLoading"
        :remoteMethod="doRemoteMethod"
        @update:modelValue="updateModelValueEvent"
        @change="changeModelValueEvent"
        @focus="(e) => $emit('focus', e)"
        @blur="(e) => $emit('blur', e)"
        @visible-change="(visible) => $emit('visible-change', visible)"
        @expand-change="(value) => $emit('expand-change', value)"
        @remove-tag="(valueByOption) => $emit('remove-tag', valueByOption)">
      <template v-if="groupView">
        <el-option-group
            v-for="(groupData,groupIndex) in options"
            :key="groupIndex"
            :label="groupData[groupProps.label]"
        >
          <el-option
              v-for="(itemData,index) in groupData[groupProps.options]"
              :key="index"
              v-bind="getOptionProps(itemData.optionProps)" :label="itemData[propsOptions.label]" :value="itemData[propsOptions.value]">
          </el-option>
        </el-option-group>
      </template>

      <template v-else>
        <el-option
            v-for="(itemData,index) in options"
            :key="index"
            v-bind="getOptionProps(itemData.optionProps)" :label="itemData[propsOptions.label]" :value="itemData[propsOptions.value]">
        </el-option>
      </template>

    </el-select>

</template>
<style >
.pt-select .el-loading-spinner {
  --el-loading-spinner-size: 20px !important;
}
</style>