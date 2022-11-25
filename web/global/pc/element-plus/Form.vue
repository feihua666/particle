<script setup name="Form">
/**
 * 自定义封装 Form 表单功能
 * 封装理由：1. 兼容添加和修改数据表单初始化功能
 *          2. 自动初始化表单修改数据
 *          3. 集成字段权限判断功能，方便实现权限
 *          4. 自动布局功能
 */
import {reactive ,inject,ref,computed,onMounted,watch} from 'vue'
import {isObject} from "../../common/tools/ObjectTools.js"

import {layoutProps,layoutIndex,layoutCompute} from './Layout.js'
// 主要用于修改场景，加载要修改的数据
import {dataMethodProps,reactiveDataMethodData,doDataMethod,emitDataMethodEvent} from './dataMethod.js'
import {dataMethodForFormProps} from './dataMethodForForm.js'
import PtFormItem from './FormItem.vue'

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 布局属性,
  ...layoutProps,
  // 数据加载属性,表单加载后数据不是数组，这里重置一下默认空数据为空对象
  ...Object.assign(dataMethodProps,dataMethodForFormProps),
  /**
   * {
   * field:{
   *     name: String, // 属性名称
   *     value: String|Number|Boolean, // 默认值
   * },
   * element:{
   *     comp: String, // 组件注册名如：el-input; txt=自定义文本显示
   *     formItemProps: { // form-item 属性
   *       required: true,//
   *     },
   *     compProps: { // 组件 属性
   *
   *     }
   *
   * }
   * }
   */
  comps: {
    type: Array,
    default: function () {
      return []
    }
  },
})
/*********** form 初始化属性 开始***************/
const form = {}
const formData = {}
// 初始化 comps
const initForm = (comps) =>{
  if(!comps) {
    return
  }
  comps.forEach(elementItem => {
    initFormItem(elementItem)
  })
}
const initFormItem = (elementItem) =>{
  if(isObject(elementItem)){
    let value = elementItem.field.value
    form[elementItem.field.name] = value
    formData[elementItem.field.name] = null
  }else{
    // 不是对象，就是数据，这里处理数组
    elementItem.forEach(elementItem=>{
      initFormItem(elementItem)
    })
  }
}
initForm(props.comps)
/*********** form 初始化属性 结束***************/
// 属性
const reactiveData = reactive({
  ...reactiveDataMethodData,
  // 表单初始数据，用来在重置时使用
  initForm: {},
  // 表单数据
  form: form,
  // 表单数据,区别于 form，该表单主要存储选中的数据对象
  formData: {}
})
// 计算属性
let layoutComputeFn = layoutCompute({props})
const layoutComputedLayout = computed(()=> {
  return layoutComputeFn(props.comps.length)
})

// 事件
const emit = defineEmits([
  emitDataMethodEvent.dataMethodResult,
  emitDataMethodEvent.dataMethodData,
])

// 挂载
onMounted(() => {
  // 加载初始数据
  doDataMethod({props,reactiveData,emit})
})
watch(
    () => reactiveData.form,
    (val) => {
      console.log('reactiveData.form',val)
    },
    { deep: true }
)
</script>
<template>
  <el-form v-bind="$attrs" :model="reactiveData.form"
           :validate="(prop,isValid,message) => {$emit('validate',prop,isValid,message)}" @submit.native.prevent>

    <template #default v-if="$slots.default">
      <slot name="default"></slot>
    </template>
    <template #default v-if="!$slots.default">
      <template v-for="(rowItem,rowIndex) in layoutComputedLayout" :key="rowIndex">
        <el-row type="flex" >
          <template  v-for="(colItem,colIndex) in rowItem"  :key="colIndex">
            <el-col :span="colItem">
              <template v-for="(elementItem,elementItemIndex) in [comps[layoutIndex(rowIndex,colIndex,layoutComputedLayout)]]" :key="elementItemIndex">
                <PtFormItem v-bind="elementItem.element.formItemProps" :compProps="elementItem.element.compProps" :form="reactiveData.form" :prop="elementItem.field.name" :comp="elementItem.element.comp">
                </PtFormItem>
              </template>
            </el-col>
          </template>
        </el-row>
      </template>
    </template>
  </el-form>
</template>

<style scoped>

</style>