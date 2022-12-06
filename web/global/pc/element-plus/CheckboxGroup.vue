<script setup name="CheckboxGroup">
/**
 * 自定义封装 checkboxGroup 级联选择
 * 封装理由：1. 可以自助获取数据，更方便
 *          2. 后端使用时支持权限控制
 *          3. 自带加载数据 dataLoading 功能效果
 */
import {reactive ,getCurrentInstance,computed,onMounted,inject,watch} from 'vue'
import {permissionProps,hasPermissionConfig} from './permission'
import {disabledProps,disabledConfig} from './disabled'
import {dataMethodProps,reactiveDataMethodData,doDataMethod,emitDataMethodEvent} from './dataMethod'
import {reactiveDataModelData,emitDataModelEvent,updateDataModelValueEventHandle,changeDataModelValueEventHandle} from './dataModel'
const { proxy } = getCurrentInstance()
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 值绑定 v-model 绑定 Array 类型的变量即可。 只有一个选项时的默认值类型为 Boolean
  modelValue: Array,
  // 最多选几个
  max: {
    type: Number
  },
  // 合并选中选项是否显示
  checkAllView: {
    type: Boolean,
    default: true
  },
  // 是否以按钮形式显示
  buttonView: {
    type: Boolean,
    default: false
  },
  // checkbox的样式
  checkboxProps: {
    type: Object,
    default: () => ({})
  },
  // 数据，数据中添加一个属性，可以单独设置checkbox原生属性 checkboxProps
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
  ...reactiveDataModelData(props),
  checkAll: false,
  isIndeterminate: false,
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
// 这里和 props.dataLoading 重名了，但在模板是使用 dataLoading 变量是这个值，也就是说这里会覆盖在模板中的值
const dataLoading = computed(() => {
  return props.dataLoading || reactiveData.dataMethodLocalLoading
})
const injectPermissions = inject('permissions', [])
// 是否有权限
const hasPermission = hasPermissionConfig({
  props,
  injectPermissions,
  noPermissionSimpleText: `「此」多项选择`
})
// 是否禁用
const hasDisabled = disabledConfig({props,dataLoading,hasPermission})
// 侦听

watch(
    () => options.value,
    (opt,oldOpt) => {
      checkAllCheck(reactiveData.currentModelValue,{value: opt})

    }
)
// 侦听
watch(
    () => props.modelValue,
    (val) => {
      reactiveData.oldModelValue = val
      reactiveData.currentModelValue = val
    }
)
// 事件
const emit = defineEmits([
  // 用来更新 modelValue
  emitDataModelEvent.updateModelValue,
  emitDataModelEvent.change,
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

const handleCheckAllChange = (val) => {

  let all = val ? options.value.map(item => item[propsOptions.value.value]) : []
  if (updateModelValueEvent(all)) {
    checkAllCheck(reactiveData.currentModelValue,options)
    return
  }else {
    if(val && props.max && props.max < options.value.length){
      proxy.$message({
        showClose: true,
        message: `最多选择 ${props.max} 项,不能使用全选功能`,
        type: 'warning',
        showIcon: true,
        grouping: true
      })
      checkAllCheck(reactiveData.currentModelValue,options)
      return
    }
  }
  reactiveData.currentModelValue = all
  reactiveData.isIndeterminate = false
}

const updateModelValueEventH = (value) => {
  // 附加权限判断
  if (updateModelValueEvent(value)) {
    return
  }
  checkAllCheck(value,options)
}

const checkAllCheck = (value,options) =>{
  const checkedCount = value.length
  reactiveData.checkAll = checkedCount === options.value.length
  reactiveData.isIndeterminate = checkedCount > 0 && checkedCount < options.value.length
}

const getCheckboxProps = (optionsCheckboxProps={})=> {
  return Object.assign(props.checkboxProps,optionsCheckboxProps)
}

</script>
<template>
    <el-checkbox
        v-if="checkAllView"
        v-model="reactiveData.checkAll"
        :indeterminate="reactiveData.isIndeterminate"
        :disabled="hasDisabled.disabled"
        @change="handleCheckAllChange">全选</el-checkbox>

    <el-checkbox-group v-if="hasPermission.render" class="pt-checkbox-group"  v-loading="dataLoading" element-dataLoading-background="rgba(122, 122, 122, 0)"
                       :title="hasDisabled.disabledReason || title"
        v-bind="$attrs"
        :max="max"
        v-model="reactiveData.currentModelValue"
        :disabled="hasDisabled.disabled"
        @update:modelValue="updateModelValueEventH"
        @change="changeModelValueEvent">
      <template v-if="buttonView">
        <el-checkbox-button v-for="(itemData,index) in options" :key="index" v-bind="getCheckboxProps(itemData.checkboxProps)" :label="itemData[propsOptions.value]">{{itemData[propsOptions.label] }}</el-checkbox-button>
      </template>
      <template v-else>
        <el-checkbox v-for="(itemData,index) in options" :key="index" v-bind="getCheckboxProps(itemData.checkboxProps)" :label="itemData[propsOptions.value]">{{itemData[propsOptions.label] }}</el-checkbox>
      </template>
    </el-checkbox-group>
</template>
<style >
.pt-checkbox-group .el-loading-spinner {
  --el-loading-spinner-size: 20px !important;
}
</style>