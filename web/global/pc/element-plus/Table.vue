<script setup name="Table" lang="ts">
/**
 * 自定义封装 Table 表格
 * 封装理由：1. 可以自助获取数据，更方便
 *          2. 后端使用时支持权限控制
 *          3. 自带加载数据 dataLoading 功能效果
 */
import {reactive ,computed,onMounted,watchEffect} from 'vue'
import {dataMethodProps,reactiveDataMethodData,doDataMethod,emitDataMethodEvent} from './dataMethod'
import PtTableColumn from './TableColumn.vue'
import PtPagination from './Pagination.vue'
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 重写 属性可以创建带斑马纹的表格
  stripe: {
    type: Boolean,
    default: true
  },
  // 重写 rowKey
  rowKey: {
    type: String,
    default: 'id'
  },
  // 默认情况下，Table 组件是不具有竖直方向的边框的， 如果需要，可以使用 border 属性，把该属性设置为 true 即可启用。
  border: {
    type: Boolean,
    default: true
  },

  // 列配置，可用来代替 table-column 插槽，数组项 参见 自定义 table-column 中的 nestColumn 配置项，内容一致
  columns: {
    type: Array,
    default: () => []
  },
  // 数据 显示的数据,与 data 相同
  options: {
    type: Array,
    default: () => ([])
  },
  // 数据 显示的数据
  data: {
    type: Array,
    default: () => ([])
  },
  // 使用表头分组的样式，主要是该样式默认有一个背景色，这里自定义
  userGroupHeaderView: {
    type: Boolean,
    default: true
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
  ...reactiveDataMethodData
})
// 计算属性
// 这里和 props.options 重名了，但在模板是使用 options 变量是这个值，也就是说这里会覆盖在模板中的值
const options = computed(() => {
  return props.options.length > 0 ? props.options : props.data.length > 0 ? props.data : reactiveData.dataMethodData
})

// 这里和 props.dataLoading 重名了，但在模板是使用 dataLoading 变量是这个值，也就是说这里会覆盖在模板中的值
const dataLoading = computed(() => {
  return props.dataLoading || reactiveData.dataMethodLocalLoading
})

//watchEffect(() => doDataMethod({props,reactiveData,emit}))
// 事件
const emit = defineEmits([
  'select',
  'select-all',
  'selection-change',
  'cell-mouse-enter',
  'cell-mouse-leave',
  'cell-click',
  'cell-dblclick',
  'cell-contextmenu',
  'row-click',
  'row-contextmenu',
  'row-dblclick',
  'header-click',
  'header-contextmenu',
  'sort-change',
  'filter-change',
  'current-change',
  'header-dragend',
  // 当用户对某一行展开或者关闭的时候会触发该事件（展开行时，回调的第二个参数为 expandedRows；树形表格时第二参数为 expanded）
  'expand-change',
  emitDataMethodEvent.dataMethodResult,
  emitDataMethodEvent.dataMethodData,
  emitDataMethodEvent.dataMethodDataLoading,
  emitDataMethodEvent.dataMethodDataLoading,
  'sizeChange',
  'currentChange'
])
// 挂载
onMounted(() => {
  doDataMethod({props,reactiveData,emit})
})
// 方法

const paginationCurrentChange = (val) => {
  reactiveData.dataMethodPageQuery.pageNo = val
  doDataMethod({props,reactiveData,emit})
  emit('currentChange', val)
}
const paginationSizeChange = (val) => {
  reactiveData.dataMethodPageQuery.pageSize = val
  doDataMethod({props,reactiveData,emit})
  emit('sizeChange', val)
}
// 刷新数据
const refreshData = ():void => {
  if (reactiveData.dataMethodPage && reactiveData.dataMethodPage.isPage) {
    // 重新查询
    reactiveData.dataMethodPageQuery.pageNo = 1
  }else {
    // page
    reactiveData.dataMethodPageQuery.pageNo = null
  }

  doDataMethod({props,reactiveData,emit})
}
// 暴露刷新方法
defineExpose({
  refreshData,
  dataMethodLocalLoading: reactiveData.dataMethodLocalLoading
})
</script>
<template>
  <el-table  class="pt-table pt-width-100-pc"
             :userGroupHeaderView="userGroupHeaderView"
             v-bind="$attrs"
             :rowKey="rowKey"
             v-loading="dataLoading"
             :data="options"
             :stripe="stripe"
             :border="border"
             :select="(selection, row) => { $emit('select',selection, row) }"
             :select-all="(selection) => { $emit('select-all',selection) }"
             :selection-change="(selection) => { $emit('selection-change',selection) }"
             :cell-mouse-enter="(row, column, cell, event) => { $emit('cell-mouse-enter',row, column, cell, event) }"
             :cell-mouse-leave="(row, column, cell, event) => { $emit('cell-mouse-leave',row, column, cell, event) }"
             :cell-click="(row, column, cell, event) => { $emit('cell-click',row, column, cell, event) }"
             :cell-dblclick="(row, column, cell, event) => { $emit('cell-dblclick',row, column, cell, event) }"
             :cell-contextmenu="(row, column, cell, event) => { $emit('cell-contextmenu',row, column, cell, event) }"
             :row-click="(row, column, event) => { $emit('row-click',row, column, event) }"
             :row-contextmenu="(row, column, event) => { $emit('row-contextmenu',row, column, event) }"
             :row-dblclick="(row, column, event) => { $emit('row-dblclick',row, column, event) }"
             :header-click="(column, event) => { $emit('header-click',column, event) }"
             :header-contextmenu="(column, event) => { $emit('header-contextmenu',column, event) }"
             :sort-change="({ column, prop, order }) => { $emit('sort-change',{ column, prop, order }) }"
             :filter-change="(filters) => { $emit('filter-change',filters) }"
             :current-change="(currentRow, oldCurrentRow) => { $emit('current-change',currentRow, oldCurrentRow) }"
             :header-dragend="(newWidth, oldWidth, column, event) => { $emit('header-dragend',newWidth, oldWidth, column, event) }"
             :expand-change="(row, expanded) => { $emit('expand-change',row, expanded) }"
  >
    <!--  如果指定了默认插槽直接使用默认插槽，保留 el-table 的原始功能  -->
    <template v-if="$slots.default">
      <slot></slot>
    </template>
    <!--  如果没有指定默认插槽且指定了列配置，则默认使用列配置数据  -->
    <template v-if="!$slots.default && columns && columns.length > 0">
      <template  v-for="(item,index) in columns" :key="index">
        <PtTableColumn v-bind="item" :nestColumns="item.nestColumns || []"></PtTableColumn>
      </template>
      <!-- 可用来添加操作等按钮  -->
      <slot name="defaultAppend"></slot>
    </template>
    <!--  保留原始功能 append 插槽  -->
    <template #append v-if="$slots.append">
      <slot name="append"></slot>
    </template>
    <!--  保留原始功能 empty 插槽  -->
    <template #empty v-if="$slots.empty">
      <slot name="empty"></slot>
    </template>

  </el-table>
  <!-- 分页 ，分页可根据返回数据自动判断是否展示-->
  <PtPagination v-if="reactiveData.dataMethodPage && reactiveData.dataMethodPage.isPage"
                :currentPage="reactiveData.dataMethodPage.pageNo"
                :pageSize="reactiveData.dataMethodPage.pageSize"
                :total="reactiveData.dataMethodPage.totalCount"
                @size-change="paginationSizeChange"
                @current-change="paginationCurrentChange"
                class="pt-table-pagenation"
  >

  </PtPagination>
</template>
<style scoped>
.pt-table-pagenation{
  margin-top: 1rem;
}
</style>
<style>
/* 将表头背景设置一个颜色，和表头分组显示方式一致 */
div[usergroupheaderview="true"].pt-table thead th.el-table__cell {
  background-color: var(--el-fill-color-light) !important;
}
/* 表头文字在 element plus 中默认都是不可选的，这里改成可以选择 */
.el-table th.el-table__cell{
  user-select: initial !important;
}
</style>