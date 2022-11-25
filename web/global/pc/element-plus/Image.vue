<script setup name="Image">
/**
 * 自定义封装 Image 图片功能
 * 封装理由：1. 添加额外预览功能，除了默认的全屏预览，添加了弹窗跑马灯预览方式
 * 注意：previewSrcList 里如果指定了值，并不会包含 src 的值，请手动指定，如果 previewSrcList 没有指定，预览时会添加 src 值
 */
import {reactive ,inject,ref,computed} from 'vue'
import PtCarousel from './Carousel.vue'
import {emitDataMethodEvent} from "./dataMethod";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 重写 src
  src: {
    type: String
  },
  // 重写 图片 fit属性，'fill' | 'contain' | 'cover' | 'none' | 'scale-down'
  // https://developer.mozilla.org/en-US/docs/Web/CSS/object-fit
  fit: {
    type: String,
    default: 'cover'
  },
  // 预览时，默认的 previewSrcList 索引，默认为 0
  initialIndex: {
    type: Number
  },
  hideOnClickModal: {
    type: Boolean,
    default: true
  },
  // 预览的方式，none=不预览，default=全屏默认预览，dialog=弹窗预览
  previewView: {
    type: String,
    default: 'none'
  },
  // 图片预览 url 列表
  previewSrcList: {
    type: Array,
    default: () => []
  },
  // 预览弹窗属性
  dialogProps: {
    type: Object,
    default: () => ({})
  },
  // 预览弹窗里跑马灯属性
  carouselProps: {
    type: Object,
    default: () => ({})
  }

})
const dialogProps = computed(()=>{
  let defaultDialogProps = {
    draggable: true,
  }
  return Object.assign(defaultDialogProps,props.dialogProps)
})
const carouselProps = computed(()=>{
  let defaultCarouselProps = {
    height: '60vh',
  }
  return Object.assign(defaultCarouselProps,props.carouselProps)
})
// 属性计算
const previewOptions = computed(() => {
  let index = 0
  let dialogOptions = []
  let previewSrcList = []
  props.previewSrcList.forEach((item,ind) => {
    if(props.src == item){
      index = ind
    }
    dialogOptions.push({
      value: item
    })
    previewSrcList.push(item)
  })
  if (dialogOptions.length == 0 && props.src) {
    dialogOptions.push({
      value: props.src
    })
    previewSrcList.push(props.src)
  }

  return {index,dialogOptions,previewSrcList}
})

const initialIndex = computed(()=>{
  if(props.initialIndex !== undefined){
    return props.initialIndex
  }
  return previewOptions.value.index

})
// 事件
const emit = defineEmits([
  'click',
])

const dialogVisible = ref(false)

// 方法
const preview = () => {
  emit('click')
  if (props.previewView == 'dialog') {
    dialogVisible.value = true
  }
}
</script>
<template>
<!-- :previewView="previewView" 属性仅用来css定位 -->
  <el-image
      v-bind="$attrs"
      class="pt-image"
      :preview-src-list="previewView == 'default' ? previewOptions.previewSrcList : undefined"
      :src="src"
      :fit="fit"
      :initial-index="initialIndex"
      :previewView="previewView"
      :hideOnClickModal="hideOnClickModal"
      @click="preview"
  >
    <template #placeholder v-if="$slots.placeholder">
      <slot name="placeholder"></slot>
    </template>
    <template #error v-if="$slots.error">
      <slot name="error"></slot>
    </template>
    <template #viewer v-if="$slots.viewer">
      <slot name="viewer"></slot>
    </template>
  </el-image>

  <el-dialog v-model="dialogVisible" v-bind="dialogProps">
    <PtCarousel v-bind="carouselProps" :options="previewOptions.dialogOptions" :autoplay="false" :initial-index="initialIndex"></PtCarousel>
  </el-dialog>
</template>


<style>
.pt-image img[previewview="dialog"].el-image__inner {
  cursor: pointer;
}
</style>