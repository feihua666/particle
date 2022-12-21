<script setup name="FrontSelect" lang="ts">
/**
 * 字典下拉选项
 */
import {reactive, watch} from 'vue'
import PtSelect from '../../../../global/pc/element-plus/Select.vue'
import {reactiveDataModelData,emitDataModelEvent,updateDataModelValueEventHandle,changeDataModelValueEventHandle} from '../../../../global/pc/element-plus/dataModel'
import {getItems,getGroupItems,getGroups} from "../api/front/dictFrontApi";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 值绑定
  modelValue: [Number, String, Array],
  // 查询的参数 参见 DictItemsParam
  dictParam: Object,
  dictApi: {
    type: String,
    default: 'getItems' //合法值 getItems,getGroupItems,getGroups
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
])

// 方法
// 值更新事件
const updateModelValueEvent = updateDataModelValueEventHandle({reactiveData, emit})
// 值改变事件
const changeModelValueEvent = changeDataModelValueEventHandle({reactiveData, emit})
// 请求方法
const dataMethod = () => {
  let methods = {getItems,getGroupItems,getGroups}

  return methods[props.dictApi](props.dictParam)
}
</script>
<template>
  <PtSelect
      v-model="reactiveData.currentModelValue"
      v-bind="$attrs"
      :dataMethod="dataMethod"
      @update:modelValue="updateModelValueEvent"
      @change="changeModelValueEvent"
  ></PtSelect>
</template>



<style scoped>

</style>