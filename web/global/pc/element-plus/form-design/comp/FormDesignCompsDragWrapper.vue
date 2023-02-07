<script setup name="FormDesignCompsItem" lang="ts">
import draggable from 'vuedraggable'
import PtFormDesignCompsDragItem from './FormDesignCompsDragItem.vue'
import {computed} from "vue";
import { v4 as uuidv4 } from 'uuid';
/**
 * 可用组件项
 */
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 数据
  options: {
    type: Array,
    default: ()=>[]
  },
  // 拖拽分组名称
  groupName:{
    type: String,
    default: 'ptFormDesignDraggableGroup'
  },
  /**
   * 唯一key
   * 默认和 formDesignItemType.ts uniqueId 一致
   */
  itemKey: {
    type: String,
    default: 'uniqueId'
  }
})

const options = computed(()=>{
  props.options.forEach((item ,index)=> {
    item[props.itemKey] = uuidv4()
  })
  return props.options
})

// 方法
const dragStartEvent = (e) => {

}
const dragEndEvent = (e) => {

}
const dragAddEvent = (e) => {

}
const dragUpdateEvent = (e) => {

}
const dragChooseEvent = (e) => {
}
const dragMove = (e) => {

}
</script>
<template>
  <draggable
      class="form-design-comps-item form-design-comps-item-drag-box pt-flex"
      :list="options"
      :item-key="itemKey"
      :group="{name: groupName, pull: 'clone', put: false}"
      @start="dragStartEvent"
      @end="dragEndEvent"
      @add="dragAddEvent"
      @update="dragUpdateEvent"
      @choose="dragChooseEvent"
      :move="dragMove">
    <template #item="{element}">
      <PtFormDesignCompsDragItem class="form-design-comps-item-component" :name="element.view.name" :title="element.view.title"></PtFormDesignCompsDragItem>
    </template>
  </draggable>
</template>
<style scoped>
.form-design-comps-item{
  justify-content: space-between;
  flex-wrap: wrap;
}
.form-design-comps-item-component{
  margin-top: .5rem;
  width: 5rem;
}
</style>