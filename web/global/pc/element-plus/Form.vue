<script setup name="Form" lang="ts">
/**
 * 自定义封装 Form 表单功能
 * 封装理由：1. 兼容添加和修改数据表单初始化功能
 *          2. 自动初始化表单修改数据
 *          3. 集成字段权限判断功能，方便实现权限
 *          4. 自动布局功能
 */
import {computed, onMounted, reactive, ref, watch} from 'vue'
import {clone, isObject} from "../../common/tools/ObjectTools"
import {doMethod, emitMethodEvent, methodProps, reactiveMethodData} from './method'
import {layoutCompute, layoutProps} from './Layout'
// 主要用于修改场景，加载要修改的数据
import {dataMethodProps, doDataMethod, emitDataMethodEvent, reactiveDataMethodData} from './dataMethod'
import {dataMethodForFormProps} from './dataMethodForForm'
// form 引用
const formRef = ref(null)

// form 表单默认提交按钮，共有三个
const defaultButtonsShow = {
  submit: true,// 提交按钮
  reset: true, // 重置按钮
  back: true, // 返回按钮
}
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
   *     value: String|Number|Boolean|Array, // 默认值
   * },
   * element:{
   *     comp: String, // 组件注册名如：el-input; txt=自定义文本显示
   *     formItemProps: { // form-item 属性
   *       required: boolean|({form,formData})=>boolean, 是否必填，会在label左侧添加小红点
   *       rules: any[] |({form,formData})=>any[], 表单项验证规划
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
  // 默认按钮是否显示
  // String时按index计算
  defaultButtonsShow: {
    type: [Object,String],
    default: () => {
      return {
        submit: true,
        reset: true,
        back: true,
      }
    }
  },
  // 提交按钮的绑定属性
  submitAttrs: {
    type: Object,
    default: () => ({})
  },
  // 可以传一个空的 form，其属性可以会通过 comps[i].field.name 生成，方便父组件拿到引用
  form: {
    type: Object,
    default: () => ({})
  },
  // formData和form有关联关系，原则上的form属性key一致，但值为绑定的数据对象而不是简单数据类型的值，这可以方便根据表单填写的（如选择了一个字典）值找对对应的数据对象
  formData: {
    type: Object,
    default: () => ({})
  },
  // 事件相关
  ...methodProps,
})
/*********** form 初始化属性 开始***************/
const form = props.form
const formData = props.formData
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
  // 数据与加载
  ...reactiveMethodData(),
  ...reactiveDataMethodData(),
  // 表单初始数据，用来在重置时使用
  initForm: clone(form),
  // 表单数据
  form: form,
  // 表单数据,区别于 form，该表单主要存储选中的数据对象
  formData: formData
})
// 计算属性
const layoutComputeFn = layoutCompute({props})
const layoutComputedLayout = computed(()=> {
  return layoutComputeFn(props.comps.length)
})
// 默认按钮显示计算结果
const defaultButtonsShowComputed = computed(()=> {
  if (isObject(props.defaultButtonsShow)) {
    return props.defaultButtonsShow
  }
  // 字符串
  let r  = {}
  for (let defaultButtonsShowKey in defaultButtonsShow) {
    r[defaultButtonsShowKey] = props.defaultButtonsShow.indexOf(defaultButtonsShowKey) >= 0
  }
  return r
})
watch(()=> reactiveData.dataMethodData,(val) => {
  for (let formKey in props.form) {
    props.form[formKey] = val[formKey]
  }
})
// 事件
const emit = defineEmits([
  emitDataMethodEvent.dataMethodResult,
  emitDataMethodEvent.dataMethodData,
  emitDataMethodEvent.dataMethodDataLoading,
  emitMethodEvent.methodResult,
])

// 挂载
onMounted(() => {
  // 加载初始数据
  doDataMethod({props,reactiveData,emit})
})

// 表单提交
const submitForm = ()=> {
  formRef.value.validate((valid, fields) => {
    if (valid) {
      doSubmitForm()
    }
  })
}
const tempDoSubmit = doMethod({props,reactiveData,emit})

const doSubmitForm = () => {
  tempDoSubmit(reactiveData.form)
}
// 重置表单
const resetForm = () => {
  formRef.value.resetFields()
}

</script>
<template>
  <el-form ref="formRef" v-bind="$attrs" :model="reactiveData.form"
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
                <PtFormItem v-bind="elementItem.element.formItemProps" :compProps="elementItem.element.compProps" :form="reactiveData.form" :formData="reactiveData.formData" :prop="elementItem.field.name" :comp="elementItem.element.comp">
                </PtFormItem>
              </template>
            </el-col>
          </template>
        </el-row>
      </template>
      <el-form-item v-if="comps && comps.length > 0" class="pt-button-form-item">
        <PtButton v-if="defaultButtonsShowComputed.submit" :loading="submitAttrs.loading || reactiveData.methodLocalLoading" type="primary" v-bind="submitAttrs" native-type="submit" @click="submitForm"></PtButton>
        <PtButton v-if="defaultButtonsShowComputed.reset" @click="resetForm">重置</PtButton>
        <PtButton v-if="defaultButtonsShowComputed.back" :route="(router) => { router.back() }">返回</PtButton>

        <!--  自定义插槽 默认添加提交按钮 -->
        <slot name="buttons"  v-bind:form="reactiveData.form">
        </slot>
      </el-form-item>
    </template>


    <!--  自定义插槽 可以用来添加按钮 -->
    <slot name="append"  v-bind:form="reactiveData.form"></slot>
  </el-form>
</template>

<style scoped>

</style>
<style>
/* element plus 本身就是 flex 布局 */
.pt-button-form-item .el-form-item__content{
  justify-content: center;

}
.el-form--inline .pt-button-form-item{
  display: flex;
}
</style>