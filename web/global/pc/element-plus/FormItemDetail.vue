<script setup name="FormItemDetail">
/**
 * 自定义封装 FormItemDetail 表单项功能
 * 封装理由：1. 集成多各表单输入于一体，省去烦琐的手动模板，只需要指定使用的组件
 *          2. 一致的使用方式
 */
import {getVal} from "../../common/tools/ObjectTools"


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
  }
})

// 取文本的值
const propValue = () => {
  let r = getVal(props.form,props.prop,props.form)
  return r
}
const txtValue = () => {
  let r = propValue()
  if(r === 0){
    return r
  }
  if(typeof r == 'boolean'){
    return r ? '是' : '否'
  }
  return r
}
</script>
<template>
  <template v-if="comp">
    <template v-if="comp == 'txt'">
      <span>{{txtValue()}}</span>
    </template>
    <template v-else>
      <component :is="comp" v-model="form[prop]" v-bind="$attrs">
        <!--  插槽  -->
        <template #default v-if="$slots.default">
          <slot></slot>
        </template>
      </component>
    </template>
  </template>
</template>

<style scoped>

</style>