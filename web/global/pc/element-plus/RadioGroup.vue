<script setup name="RadioGroup">
/**
 * 自定义封装 radioGroup 级联选择
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
  modelValue: [String,Number,Boolean],
  // 是否以按钮形式显示
  buttonView: {
    type: Boolean,
    default: false
  },
  // radio的样式
  radioProps: {
    type: Object,
    default: () => ({})
  },
  // 数据，数据中添加一个属性，可以单独设置radio原生属性 radioProps
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
  noPermissionSimpleText: `「此」单项选择`
})
// 是否禁用
const hasDisabled = disabledConfig({props,dataLoading,hasPermission})
// 侦听

watch(
    () => options.value,
    (opt,oldOpt) => {
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
  }
  reactiveData.currentModelValue = all
  reactiveData.isIndeterminate = false
}

const updateModelValueEventH = (value) => {
  // 附加权限判断
  if (updateModelValueEvent(value)) {
    return
  }
}

const getRadioProps = (optionsRadioProps={})=> {
  return Object.assign(props.radioProps,optionsRadioProps)
}

</script>
<template>
    <el-radio-group  v-if="hasPermission.render" class="pt-radio-group"  v-loading="dataLoading" element-dataLoading-background="rgba(122, 122, 122, 0)"
                     :title="hasDisabled.disabledReason || title"
        v-bind="$attrs"
        v-model="reactiveData.currentModelValue"
        :disabled="hasDisabled.disabled"
        @update:modelValue="updateModelValueEventH"
        @change="changeModelValueEvent">
      <template v-if="buttonView">
        <el-radio-button v-for="(itemData,index) in options" :key="index" v-bind="getRadioProps(itemData.radioProps)" :label="itemData[propsOptions.value]">{{itemData[propsOptions.label] }}</el-radio-button>
      </template>
      <template v-else>
        <el-radio v-for="(itemData,index) in options" :key="index" v-bind="getRadioProps(itemData.radioProps)" :label="itemData[propsOptions.value]">{{itemData[propsOptions.label] }}</el-radio>
      </template>


    </el-radio-group>
</template>
<style >
.pt-radio-group .el-loading-spinner {
  --el-loading-spinner-size: 20px !important;
}
</style>