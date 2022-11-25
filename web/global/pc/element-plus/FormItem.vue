<script setup name="FormItem">
/**
 * 自定义封装 FormItem 表单项功能
 * 封装理由：1. 集成多各表单输入于一体，省去烦琐的手动模板，只需要指定
 *          2. 一致的使用方式
 */
import {reactive ,inject,ref,computed} from 'vue'
import PtFormItemDetail from './FormItemDetail.vue'
import {getVal} from "../../common/tools/ObjectTools.js"


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
  // 表单数据项的键值
  prop: {
    type: String,
    required: true
  },
  // 组件的属性
  compProps: {
    type: Object,
    default: () => ({})
  },
  // 验证如：mobile，email等
  validate: {
    type: Object
  }
})



const getFormItemRules = (validateObj) => {
  let r = []
  if(getVal(validateObj,'required',props.form)){
    r.push({required: true, message: `${validateObj.label}不能为空`, trigger: 'blur'})
  }

  // 验证规则
  if(validateObj.rules){
    validateObj.rules.forEach(item =>  {
      r.push(item)
    })
  }
  let v = validateObj.validate

  if(v){
    if (v.mobile === true) {
      r.push({ pattern:/^[1][0-9]{10}$/, message: '请输入正确的手机号', trigger: ['blur'] })
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
<el-form-item v-bind="$attrs" :prop="prop" :rules="getFormItemRules({required: $attrs.required,rules: $attrs.rules,validate: $attrs.validate,label: $attrs.label})">
  <template v-if="$slots.default">
    <slot v-bind="{form}"></slot>
  </template>
  <template v-if="!$slots.default">
    <PtFormItemDetail v-bind="compProps" :form="form" :prop="prop" :comp="comp">
    </PtFormItemDetail>
  </template>
  <template #label="scope" v-if="$slots.label">
    <slot v-bind="scope"></slot>
  </template>
  <template #error="scope" v-if="$slots.error">
    <slot v-bind="scope"></slot>
  </template>
</el-form-item>
</template>

<style scoped>

</style>