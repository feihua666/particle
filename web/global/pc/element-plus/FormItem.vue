<script setup name="FormItem" lang="ts">
/**
 * 自定义封装 FormItem 表单项功能
 * 封装理由：1. 集成多各表单输入于一体，省去烦琐的手动模板，只需要指定
 *          2. 一致的使用方式
 */
import {reactive ,inject,ref,computed} from 'vue'
import PtFormItemDetail from './FormItemDetail.vue'
import {getVal} from "../../common/tools/ObjectTools"
import {regs} from "../../common/tools/RegTools";


// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 组件名称，注册名称
  comp: {
    type: String
  },
  // 表单数据对象
  form: {
    type: Object,
    required: true
  },
  required: [Boolean,Function],
  // 表单额外数据对象
  formData: {
    type: Object,
    required: true
  },
  // 表单数据项的键值
  prop: {
    type: String,
    required: true
  },
  // 组件的属性
  compProps: {
    type: [Object,Function],
    default: () => ({})
  },
  // 验证如：mobile，email等
  validate: {
    type: Object
  },
  // element plus 原生属性
  rules: {
    type: Array
  },
  // 数据变化事件,如果在该方法中使用formData，可能会取不到新值的情况，可以结合nextTick使用
  valueChange: {
    type: Function,
    default: ({form,formData,prop,newValue,oldValue}) =>({})
  },
  // 表单提示文本
  tips: [String,Function],
  // 该提示将在label右边添加图标，鼠标移入提示
  labelTips:[String,Function],
})
const required = computed(() => {
  return getVal({required: props.required},'required',{form: props.form,formData: props.formData})
})
const compProps = computed(() => {
  return getVal({compProps: props.compProps},'compProps',{form: props.form,formData: props.formData})
})

const tips = computed(() => {
  return getVal({tips: props.tips},'tips',{form: props.form,formData: props.formData})
})
const labelTipsComputed = computed(() => {
  return getVal({labelTips: props.labelTips},'labelTips',{form: props.form,formData: props.formData})
})

export interface ValidateObj{
  // 是否必填
  required: boolean,
  // 详情详见 elementPlus 表单验证
  // 表单验证 同 validate
  rules: any[],
  // 表单验证 其中 rules 属性同上面rules
  validate: { mobile?: boolean,email?: boolean,rules?: any[] },
  // 表单的 label 名称
  label: string
}

const getFormItemRules = (validateObj:ValidateObj) => {
  let r = []
  if(validateObj.required){
    r.push({required: true, message: `${validateObj.label}不能为空`, trigger: 'blur'})
  }

  // 验证规则
  if(validateObj.rules){
    let rules = getVal(validateObj,'rules',{form: props.form,formData: props.formData})
    rules.forEach(item =>  {
      r.push(item)
    })
  }
  let v = validateObj.validate

  if(v){
    v = getVal(validateObj,'validate',{form: props.form,formData: props.formData})
    if (v.mobile === true) {
      r.push({ pattern:regs.mobilePattern, message: '请输入正确的手机号', trigger: ['blur'] })
    }
    if (v.email === true) {
      r.push({ type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur'] })
    }
    // 验证规则,兼容 rules,同 elementItem.element.formItemProps.rules ,这里也定义一个，目的是可以放在一个位置
    if(v.rules){
      v.rules.forEach(item =>  {
        r.push(item)
      })
    }
  }

  return r
}

</script>
<template>
<el-form-item v-bind="$attrs" :prop="prop" :required="required" :rules="getFormItemRules({required: required,rules: rules,validate: validate,label: $attrs.label})">
  <template v-if="$slots.default">
    <slot v-bind="{form}"></slot>
  </template>
  <template v-if="!$slots.default">
    <PtFormItemDetail v-bind="compProps" :form="form" :valueChange="valueChange" :formData="formData" :prop="prop" :comp="comp">
    </PtFormItemDetail>
  </template>
  <template #label="scope" v-if="$slots.label">
    <slot v-bind="scope"></slot>
  </template>
  <template #label="scope" v-if="!$slots.label && labelTips">
    <span>
      {{scope.label}}
        <el-tooltip :content="labelTipsComputed" raw-content placement="top" effect="light">
      <el-icon class="pt-form-item-labelTips"><InfoFilled /></el-icon>
        </el-tooltip>
    </span>
  </template>
  <template #error="scope" v-if="$slots.error">
    <slot v-bind="scope"></slot>
  </template>
  <span style="color: #acafb4;" v-if="tips" v-html="tips"></span>
</el-form-item>
</template>

<style scoped>
.el-form-item[displayBlock=true]{
  display: flex;
}
.pt-form-item-labelTips{
  width: 1.1em;
  height: 1.1em;
  margin-left: .35em;
  margin-right: .35em;
  vertical-align: -.15em;
  fill: currentColor;
  overflow: hidden;
}
</style>