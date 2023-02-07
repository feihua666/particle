<script>
import { ref, h ,resolveComponent} from 'vue'
import UserManagePage from '../../component/pc/user/views/admin/UserManagePage.vue'
import {isString} from "./tools/StringTools";
import {extend, isObject} from "./tools/ObjectTools";

/**
 *
 * 自定义组件适配器，旨在将组件声明式化
 * 功能同 vue 内置元素 component,但component不支持插槽配置化，所以自己封装一个
 * 总结：
 * 1. 在vue中如果想监听事件官方通过加类似 @click就可以，但还有一种方式就是把@换成on，如：onClick,这样的一个好处是可以将事件转为属性传递
 *
 */
export default {
  name: "CompAdapter",
  props: {
    // 和vue内置元素 component is属性使用一致
    is: null,
    // 插槽,参见 https://cn.vuejs.org/api/render-function.html#h
    /*
    * 例：
    * {
    *   default: () => 'default slot',
    *   foo: () => h('div', 'foo'),
    *   bar: () => [h('span', 'one'), h('span', 'two')]
    * }
    * 或者
    * {
    * default: {
    *   is:xxx,
    *   attrs: {},
    * }
    * }
    * */
    slots:{
      type: Object,
      default: ()=>({})
    }
  },
  setup(props, {attrs,emit,expose,slots}) {
    // 返回渲染函数
    return () =>{
      let comp = isString(props.is) ? resolveComponent(props.is) : props.is
      // 发现不需要手动指定vmodel处理，会自动添加
      let attrModelValue = {
        //   modelValue: attrs.modelValue,
        //   'onUpdate:modelValue': (value) => emit('update:modelValue', value)
      }
      let attrsTemp = extend(attrs.modelValue === undefined ? {} : attrModelValue,attrs)
      let slotsTemp = {}
      for (const slotsKey in props.slots) {
        let val = props.slots[slotsKey]
        if (isObject(val)) {
          slotsTemp[slotsKey] = h(val.is,val.attrs)
        }else {
          slotsTemp[slotsKey] = val
        }
      }
      slotsTemp = extend({},slotsTemp,slots)


      return h(comp,attrsTemp, slotsTemp)
    }
  }
}

</script>

<template>

</template>



<style scoped>

</style>