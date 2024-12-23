<script setup name="FormDesignMain" lang="ts">
/**
 * 表单设计主要工作区
 */
import draggable from 'vuedraggable'
import PtFormDesignItem from './FormDesignItem.vue'
import {inject, reactive} from "vue";

const formDesignData = inject('formDesignData')
const formDesignDataControl = inject('formDesignDataControl')

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
  },
  // 是否裹在表单中
  useForm: {
    type: Boolean,
    default: true
  },
  /**
   * 如果useForm=true，则form属性绑定
   */
  formProps: {
    type: Object
  }
})
// 属性
const reactiveData = reactive({
  options: props.options
})

// 方法
const dragStartEvent = (e) => {

}
const dragEndEvent = (e) => {

}
const dragAddEvent = (e) => {
  let newIndex = e.newIndex

  formDesignDataControl.formDesignItemUnSelectAll(newIndex)
  formDesignDataControl.formDesignItemSelectNew(newIndex)

}
const dragUpdateEvent = (e) => {

}
const dragChooseEvent = (e) => {
}
const dragMove = (e) => {

}

</script>
<template>
  <div class="form-design-main pt-height-100-pc">
    <el-form v-if="useForm" class="form-design-main-form pt-height-100-pc" :model="formDesignData.formDesignForm" v-bind="formProps">
      <draggable v-model="formDesignData.formDesignItems"
                 class="form-design-main-drag-box pt-height-100-pc pt-width-100-pc"
                 :item-key="itemKey"
                 :group="groupName"
                 ghostClass="form-design-main-draggable-ghost"
                 :animation="300"
                 handle=".form-design-main-draggable-handler"
                 @end="dragEndEvent"
                 @add="dragAddEvent"
                 @update="dragUpdateEvent"
                 @choose="dragChooseEvent"
                 :move="dragMove">
        <template #item="{ element,index }">
          <PtFormDesignItem dragHander="form-design-main-draggable-handler" :formDesignItemData="element" :currentIndex="index" :key="index"></PtFormDesignItem>
        </template>
      </draggable>
    </el-form>
    <draggable v-else v-model="formDesignData.formDesignItems"
               class="form-design-main-drag-box pt-height-100-pc pt-width-100-pc"
               :item-key="itemKey"
               :group="groupName"
               ghostClass="form-design-main-draggable-ghost"
               :animation="300"
               handle=".form-design-main-draggable-handler"
               @start="dragStartEvent"
               @end="dragEndEvent"
               @add="dragAddEvent"
               @update="dragUpdateEvent"
               :move="dragMove">
      <template #item="{ element,index }">
        <PtFormDesignItem dragHander="form-design-main-draggable-handler" :formDesignItemData="element" :currentIndex="index" :key="index"></PtFormDesignItem>
      </template>
    </draggable>
  </div>

</template>


<style>
.form-design-main{
  background-color: #ffffff;
}
.form-design-main-draggable-ghost {
  content: "";
  font-size: 0;
  height: 0px;
  box-sizing: border-box;
  display: block;
  background: #409EFF;
  border: 2px solid #409EFF;
  outline-width: 0 !important;
  padding: 0;
  overflow: hidden;
  width: 100% !important;
  border-radius: 2px !important;
}
.form-design-main-drag-box{
  border: 1px dashed #dbd3d3 !important;
  outline: 1px dashed #878282 !important;
  background: #fff;
}
</style>
