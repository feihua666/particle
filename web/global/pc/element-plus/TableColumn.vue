<script setup name="TableColumn">
import {reactive ,computed,onMounted,inject,ref} from 'vue'
import PtTableColumn from './TableColumn.vue'
import PtImage from './Image.vue'
import SecretText from './SecretText.vue'
import PtCompAdapter from '../../common/CompAdapter.vue'
import {isObject} from "../../common/tools/ObjectTools";
import {isFunction} from "../../common/tools/FunctionTools";

/**
 * 自定义封装 Table column 表格
 * 封装理由：1. 可以配合 table 表格显示数据，更方便
 */
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 嵌套 数据，数组项 为 table-colmun 的全部属性,被嵌套的暂不支持 插槽
  nestColumns: {
    type: Array,
    default: () => ({})
  },
  // 列展示 表现 值可选 image
  // Object类型时为 PtCompAdapter属性
  // Function类型时为 返回值为 PtCompAdapter属性
  columnView: {
    type: [String,Object,Function],
  },
  // 属性配置
  props: {
    type: Object,
    default: () => ({})
  }
})

// 计算属性
// propsOptions
const propsOptions = computed(() => {
  let defaultProps = {
    // 嵌套 nestColumns 的子列数据
    children: 'children',
  }
  return Object.assign(defaultProps, props.props)
})
</script>
<template>
  <el-table-column v-bind="$attrs">
    <!-- scope 可以解构 { row, column, $index } -->
    <template  v-if="$slots.default" #default="scope">
      <slot v-bind="scope"></slot>
    </template>

    <template v-if="!$slots.default && (columnView || (nestColumns && nestColumns.length > 0))" #default="scope">
      <!--  图片  -->
      <template v-if="columnView == 'image'">
        <PtImage class="pt-table-column-image" :dialogProps="{appendToBody: true}"  previewView="default" style="height: 1.5rem;width: 1.5rem;" :src="scope.row[scope.column.property]" :preview-teleported="true">
          <template #error>
            <div class="image-slot">
              <el-icon><Picture /></el-icon>
            </div>
          </template>
        </PtImage>
      </template>
      <!--  图标  -->
      <template  v-else-if="columnView == 'elIcon'">
        <el-icon v-if="scope.row[scope.column.property]"><component :is="scope.row[scope.column.property]"></component></el-icon>
      </template>
      <!--  敏感文本信息  -->
      <template  v-else-if="columnView == 'PtSecretText'">
        <PtSecretText :modelValue="scope.row[scope.column.property]"></PtSecretText>
      </template>
      <template v-else-if="isObject(columnView)">
        <PtCompAdapter v-bind="columnView"></PtCompAdapter>
      </template>
      <template v-else-if="isFunction(columnView)">
        <PtCompAdapter v-bind="columnView(scope)"></PtCompAdapter>
      </template>

      <template v-if="nestColumns && nestColumns.length > 0">
        <template v-for="(item,index) in nestColumns" :key="index">
          <PtTableColumn v-bind="item"
                         :nestColumns="item[propsOptions.children] || []"
                         :props="propsOptions"></PtTableColumn>
        </template>
      </template>

    </template>
    <!-- scope 可以解构 { column, $index } -->
    <template #header="scope" v-if="$slots.header">
      <slot name="header" v-bind="scope"></slot>
    </template>
  </el-table-column>
</template>

<style scoped>
.pt-table-column-image .image-slot {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  background: var(--el-fill-color-light);
  color: var(--el-text-color-secondary);
  font-size: 1rem;
}
</style>