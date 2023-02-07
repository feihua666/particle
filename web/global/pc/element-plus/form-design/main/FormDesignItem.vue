<script setup name="FormDesignItem" lang="ts">
import {getValue} from "../../../../common/tools/ObjectTools";
import {computed, inject,nextTick} from "vue";
import {frontMove} from "../../../../common/tools/ArrayTools";
const formDesignDataControl = inject('formDesignDataControl')
const formDesignData = inject('formDesignData')

/**
 * 设计区的项
 */
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  /**
   * 组件配置
   * 类型见 formDesignItemType.ts
   */
  formDesignItemData: {
    type: Object
  },
  // 索引
  currentIndex: {
    type: Number
  },
  // 是否使用表单项
  useFormItem: {
    type: Boolean,
    default: true
  },
  // 是否选中，如果被选中将会高亮
  isSelected: {
    type: Boolean,
    default: false
  },
  // 拖拽
  dragHander: {
    type: String,
    default: 'form-design-item-drag-handler'
  }
})

const isSelected = computed(()=>{
  return props.isSelected || getValue(props.formDesignItemData,'designControl.formDesignItem.isSelected')
})

const selectedClick = ()=>{
  nextTick(()=>{
    formDesignDataControl.formDesignItemUnSelectAll(props.currentIndex)
    formDesignDataControl.formDesignItemSelectState(props.currentIndex,true)
  })}

const topClick = ()=>{
  formDesignDataControl.formDesignItemUpMove(props.currentIndex)
}
const bottomClick = ()=>{
  formDesignDataControl.formDesignItemDownMove(props.currentIndex)

}
const deleteClick = ()=>{
  formDesignDataControl.formDesignItemDelete(props.currentIndex)

}
</script>
<template>
<div class="form-design-item" :isSelected="isSelected" @click="selectedClick">
  <el-form-item v-if="useFormItem" v-bind="formDesignItemData.comps.formItemProps">
    <component v-model="formDesignItemData.attrs.compForm['defaultValue']" :is="formDesignItemData.comps.comp" v-bind="formDesignItemData.comps.compProps"></component>
  </el-form-item>
  <component v-else :is="formDesignItemData.comps.comp" v-bind="formDesignItemData.comps.compProps"></component>

  <div class=" form-design-item-drag pt-flex-center-all">
    <!-- 拖拽手柄区域 -->
    <div v-if="isSelected" class="form-design-item-drag-handler pt-flex-center-all" :class="dragHander">
      <el-icon><Aim /></el-icon>
      <span>{{formDesignItemData.view.name}}</span>
    </div>
    <div  v-if="isSelected" class="form-design-item-toolbar pt-flex-center-all">
      <el-button link title="上移组件" @click.stop="topClick"><el-icon color="#fff"><Top /></el-icon></el-button>
      <el-button link title="下移组件" @click.stop="bottomClick"><el-icon color="#fff"><Bottom /></el-icon></el-button>
      <el-button link title="删除组件" @click.stop="deleteClick"><el-icon color="#fff"><Delete /></el-icon></el-button>

    </div>
  </div>

</div>
</template>


<style scoped>
.form-design-item{
  position: relative;
}
/* 选中后高亮 */
.form-design-item[isSelected=true]{
  outline: 2px solid #409EFF;
  opacity: .7;
}

/* 辅助 */
.form-design-item-drag{
  position: absolute;
  top: -20px;
  left: -2px;
  height: 20px;
  line-height: 20px;
  z-index: 9;
}
.form-design-item-drag-handler{
  height: 20px;
  line-height: 20px;
  background: #409EFF;
  color: #ffffff;
  font-size: 12px;
  cursor: move;
}
.form-design-item-drag-handler .el-icon{
  margin-left:.2rem;
  margin-right:.2rem;
}
.form-design-item-drag-handler span{
  margin-right:.2rem;
}
.form-design-item-toolbar{
  height: 20px;
  line-height: 20px;
  font-size: 12px;
  background: #409EFF;
  color: #ffffff;
}
</style>
