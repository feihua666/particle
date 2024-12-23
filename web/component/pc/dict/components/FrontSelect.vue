<script setup name="FrontSelect" lang="ts">
/**
 * 字典下拉选项
 */
import {reactive, watch} from 'vue'
import PtSelect from '../../../../global/pc/element-plus/Select.vue'
import PtCheckboxGroup from '../../../../global/pc/element-plus/CheckboxGroup.vue'
import {
  changeDataModelValueEventHandle,
  emitDataModelEvent,
  reactiveDataModelData,
  updateDataModelValueEventHandle
} from '../../../../global/pc/element-plus/dataModel'
import {getGroupItems, getGroups, getItems} from "../api/front/dictFrontApi";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 值绑定
  modelValue: [Number, String, Array],
  // 查询的参数 参见 ../api/front/dictFrontApi.ts DictItemsParam
  dictParam: Object,
  dictApi: {
    type: String,
    default: 'getItems' //合法值 getItems,getGroupItems,getGroups
  },
  // 展示方式，可选 select checkbox
  view: {
    type: String,
    default: 'select'
  },
  resultDataHandler: {
    type: Function,
    default: (res)=>{}
  }
})
// 属性
const reactiveData = reactive({
  ...reactiveDataModelData(props)
})

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
  emitDataModelEvent.updateModelData,
])

// 方法
// 值更新事件
const updateModelValueEvent = updateDataModelValueEventHandle({reactiveData, emit})
// 值改变事件
const changeModelValueEvent = changeDataModelValueEventHandle({reactiveData, emit})
const changeModelDataEvent = changeDataModelValueEventHandle({reactiveData, emit,eventName: emitDataModelEvent.updateModelData})
// 请求方法
const dataMethod = () => {
  let methods = {getItems,getGroupItems,getGroups}

  return methods[props.dictApi](props.dictParam).then(res => {
    props.resultDataHandler(res)
    return Promise.resolve(res)
  })
}
</script>
<template>
  <PtSelect v-if="view == 'select'"
      v-model="reactiveData.currentModelValue"
      v-bind="$attrs"
      :dataMethod="dataMethod"
      @update:modelValue="updateModelValueEvent"
      @update:modelData="changeModelDataEvent"
      @change="changeModelValueEvent"
  ></PtSelect>
  <PtCheckboxGroup  v-else-if="view == 'checkbox'"
      v-model="reactiveData.currentModelValue"
      v-bind="$attrs"
      :dataMethod="dataMethod"
      @update:modelValue="updateModelValueEvent"
      @update:modelData="changeModelDataEvent"
      @change="changeModelValueEvent"
  ></PtCheckboxGroup>

</template>



<style scoped>

</style>
