<script setup name="Carousel">
/**
 * 自定义封装 Carousel 走马灯
 * 封装理由：1. 可以自助获取数据，更方便
 *          2. 自带加载数据 dataLoading 功能效果
 *          3. 增加名称为 item 的插槽，方便直接写内容
 */
import {reactive ,computed,onMounted,inject,ref} from 'vue'
import {dataMethodProps,reactiveDataMethodData,doDataMethod,emitDataMethodEvent} from './dataMethod'
const carouselRef = ref(null)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({

  // 数据项渲染表现，目前只支持 image
  itemView: {
    type: String,
    default: 'image'
  },
  // itemView 为 image 时，图片 fit属性，'fill' | 'contain' | 'cover' | 'none' | 'scale-down'
  // https://developer.mozilla.org/en-US/docs/Web/CSS/object-fit
  itemViewFit: {
    type: String,
    default: 'cover'
  },
  // 数据，数据项
  /**
   * {
   *   name: String, // 幻灯片的名字，可用作 setActiveItem 的参数
   *   label: String,// 该幻灯片所对应指示器的文本
   *   value: any,// itemView 属性对应的值，类型不同，可能值类型不同
   * }
   */
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
  // 数据初始化时，加载初始数据 loading 效果
  dataLoading: {
    type: Boolean,
    default: false
  },

  ...dataMethodProps
})
// 属性
const reactiveData = reactive({
  ...reactiveDataMethodData,
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
    value: 'value',
  }
  return Object.assign(defaultProps, props.props)
})
// 这里和 props.dataLoading 重名了，但在模板是使用 dataLoading 变量是这个值，也就是说这里会覆盖在模板中的值
const dataLoading = computed(() => {
  return props.dataLoading || reactiveData.dataMethodLocalLoading
})
const injectPermissions = inject('permissions', [])


// 事件
const emit = defineEmits([
  'change',
  emitDataMethodEvent.dataMethodResult,
  emitDataMethodEvent.dataMethodData,
  emitDataMethodEvent.dataMethodDataLoading,
])
// 挂载
onMounted(() => {
  doDataMethod({props,reactiveData,emit})
})

// 方法
// 获取引用的实例
const carouselRefInstance = () => {
  return carouselRef
}
</script>

<template>
  <el-carousel v-bind="$attrs" ref="carouselRef">
    <template v-if="$slots.default">
      <slot :options="options"></slot>
    </template>
    <template v-if="!$slots.default">
      <el-carousel-item v-for="(item,index) in options" :name="item.name" :label="item.label" :key="index">
        <slot name="item" :item="item" v-if="$slots.item"></slot>
        <template v-else>
          <el-image v-if="itemView == 'image'" :src="item[propsOptions.value]" :fit="itemViewFit" class="pt-width-100-pc pt-height-100-pc"></el-image>
        </template>
      </el-carousel-item>
    </template>

  </el-carousel>
</template>
<style scoped>

</style>