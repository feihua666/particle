<script setup name="TableColumn">
import {reactive ,computed,onMounted,inject,ref} from 'vue'
import PtTableColumn from './TableColumn.vue'
import PtImage from './Image.vue'
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
  columnView: {
    type: String,
  },
  // 属性配置
  props: {
    type: Object,
    default: () => ({})
  }
})

// element plus 绑定的插槽属性为 $index，但报错，这时改成 index，暂先这样
const scope$index = ref('index')

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
      <template v-if="columnView == 'image'">
        <PtImage class="pt-table-column-image" :dialogProps="{appendToBody: true}"  previewView="default" style="height: 1.5rem;width: 1.5rem;" :src="scope.row[scope.column.property]" :preview-teleported="true">
          <template #error>
            <div class="image-slot">
              <el-icon><Picture /></el-icon>
            </div>

          </template>
        </PtImage>
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