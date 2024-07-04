<script setup name="Tree" lang="ts">
/**
 * 自定义封装 Tree 树形选择
 * 封装理由：1. 可以自助获取数据，更方便
 *          2. 后端使用时支持权限控制
 *          3. 自带加载数据 dataLoading 功能效果
 *          4. 支持 v-model 指令
 *
 * 原生的属性 defaultCheckedKeys 是响应式的，只能通过重新赋值改变，但改变只能追加，以前已选中的值并不会取消选中
 *
 * 注意：modelValue 不能传同一个引用值，否则会出现不可预知问题（可能死循环）
 */
import {computed, inject, onMounted, reactive, watch, ref,nextTick,watchEffect} from 'vue'
import {hasPermissionConfig, permissionProps} from './permission'
import {disabledConfig, disabledProps} from './disabled'
import {dataMethodProps, doDataMethod, emitDataMethodEvent, reactiveDataMethodData} from './dataMethod'
import {dataMethodInitProps, doDataMethodInit, reactiveDataMethodInitData} from './dataMethodInit'
import {
  changeDataModelValueEventHandle,
  emitDataModelEvent,
  pushCurrentModelData,
  reactiveDataModelData,
  updateDataModelValueEventHandle
} from './dataModel'
import {getTreeLeafItem, isEqual, removeDuplicate, removeItems} from "../../common/tools/ArrayTools";
const treeRef = ref(null)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 值绑定
  modelValue: [Array],
  // v-model 绑定时是否包括半选中状态的数据
  modelValueIncludeHalfCheckedKeys: {
    type: Boolean,
    default: true
  },
  // v-model 是否只返回叶子节点，需要结合 modelValueIncludeHalfCheckedKeys属性使用，以最终获取选中的值为叶子节点
  modelValueIncludeLeafOnly: Boolean,
  // 默认勾选的节点的 key 的数组
  defaultCheckedKeys: {
    type: Array,
    default: ()=>[]
  },
  // 原属性覆盖 在显示复选框的情况下，是否严格的遵循父子不互相关联的做法，默认为 false
  // 注意：在 checkStrictly = false 时，如果 defaultCheckedKeys 或 modelValue 默认传值是非叶子节点，则不会被选中，但数据还是保留的
  checkStrictly: {
    type: Boolean,
    default: false
  },
  // 是否支持清空选项
  clearable: {
    type: Boolean,
    default: true
  },
  // 原生属性
  data: {
    type: Array,
    default: () => ([])
  },
  // 数据
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
  // 也可以在 props.props.value定义
  nodeKey: String,
  // 禁用相关属性
  ...disabledProps,
  // 权限相关
  ...permissionProps,
  // 数据初始化时，加载初始数据 loading 效果
  dataLoading: {
    type: Boolean,
    default: false
  },
  // 鼠标 hover 提示语
  title: {
    type: String
  },
  ...dataMethodProps,
  ...dataMethodInitProps,
  // 过滤，默认根据 propsOptions.label 过滤
  filterNodeMethod: Function,
  // 自定义属性，启动筛选框
  enableFilter: {
    type: Boolean,
    default: false
  },
  // 筛选框属性
  filterInputProps: {
    type: Object,
    default: ()=>({})
  }
})
// 属性
const reactiveData = reactive({
  ...reactiveDataMethodData(),
  ...reactiveDataMethodInitData(),
  ...reactiveDataModelData(props)
})
// 计算属性
// 这里和 props.options 重名了，但在模板是使用 options 变量是这个值，也就是说这里会覆盖在模板中的值
const options = computed(() => {
  return props.options.length > 0 ? props.options : (props.data.length > 0 ? props.data : reactiveData.dataMethodData)
})
// propsOptions
const propsOptions = computed(() => {
  let defaultProps = {
    // 指定选项的值为选项对象的某个属性值
    value: 'id',
    // 指定选项标签为选项对象的某个属性值
    label: 'name',
    // 指定选项的子选项为选项对象的某个属性值
    children: 'children',
    // 指定选项的禁用为选项对象的某个属性值
    disabled: 'isDisabled',
    // 指定节点是否为叶子节点，仅在指定了 lazy 属性的情况下生效
    isLeaf: 'isLeaf',
    class: '',
  }
  let r = Object.assign(defaultProps, props.props)
  if (props.nodeKey) {
    r.value = props.nodeKey
  }
  // 全局禁用时，直接禁用所有数据
  if(hasDisabled.value.disabled){
    // el-tree 支持函数形式
    r.disabled = function(data, node){return true}
  }
  return r
})
// 这里和 props.dataLoading 重名了，但在模板是使用 dataLoading 变量是这个值，也就是说这里会覆盖在模板中的值
const dataLoading = computed(() => {
  return props.dataLoading || reactiveData.dataMethodLocalLoading || reactiveData.dataMethodInitLocalLoading
})
const getDefaultCheckedKeysOnceControl = {
  defaultCheckedKeys: false,
  dataMethodInitData: false,
  hasMounted: false
}
// 获取默认的选中项
const getDefaultCheckedKeys = () => {
  let r = []

  if(reactiveData.currentModelValue && reactiveData.currentModelValue.length > 0){
    r = r.concat(reactiveData.currentModelValue)
  }
  if (!getDefaultCheckedKeysOnceControl.defaultCheckedKeys && props.defaultCheckedKeys && props.defaultCheckedKeys.length > 0) {
    r = r.concat(props.defaultCheckedKeys)
    getDefaultCheckedKeysOnceControl.defaultCheckedKeys = true
  }
  // 去重处理
  r = removeDuplicate(r)
  if(r.length !== 0){
    // 在显示复选框的情况下，是否严格的遵循父子不互相关联的做法，默认为 false
    if(!props.checkStrictly){
      // 只选中叶子节点
      if(options.value && options.value.length > 0){
        let leafItem = getTreeLeafItem(options.value)
        r = r.filter(value => leafItem.some(item => value == item[propsOptions.value.value]))
      }
    }
  }


  return r
}
// 这里不能使用计算属性，否则会动态响应，就改变了初始值的初衷
const defaultCheckedKeys = computed( getDefaultCheckedKeys)

const injectPermissions = inject('permissions', [])
// 是否有权限
const hasPermission = hasPermissionConfig({
  props,
  injectPermissions,
  noPermissionSimpleText: `「此」级联选择`
})
// 是否禁用
const hasDisabled = disabledConfig({props,dataLoading,hasPermission})
// 侦听
watch(
    () => props.modelValue,
    (val) => {
      reactiveData.oldModelValue = val
      reactiveData.currentModelValue = val
    }
)

watch(
    () => defaultCheckedKeys.value,
    (val) => {

      // 清空一下，重新选中
      clearCheckedAll()
    }
)
// 数据加载完成，重新计算
watch(
    () => reactiveData.dataMethodInitData,
    (val) => {

      getDefaultCheckedKeysOnceControl.dataMethodInitData = true

      // 加载完初始化选中数据，emit 处理
      emitCheckedKeys(val||[],[])

    }
)
// 如果有变动，重新加载一遍
watch(
    () => props.defaultCheckedKeys,
    (val) => {
      getDefaultCheckedKeysOnceControl.defaultCheckedKeys = true
    }
)
watch(
    () => reactiveData.currentModelValue,
    (val) => {

      emitModelData({data: options.value,value: val})
    }
)
// 监听参数变化，重新初始化
watch(
    () => props.dataInitMethodParam,
    (newParam)=> {
      doDataMethodInit({props,reactiveData})
    }
)
watch(
    () => props.dataMethodParam,
    (val) => {
      doDataMethod({props,reactiveData,emit})
    }
)
// 事件
const emit = defineEmits([
  // 用来更新 modelValue
  emitDataModelEvent.updateModelValue,
  emitDataModelEvent.change,
  emitDataModelEvent.updateModelData,
  'node-click',
  'node-contextmenu',
  'check-change',
  'check',
  'current-change',
  'node-expand',
  'node-collapse',
  'node-drag-start',
  'node-drag-enter',
  'node-drag-leave',
  'node-drag-over',
  'node-drag-end',
  'node-drop',
  emitDataMethodEvent.dataMethodResult,
  emitDataMethodEvent.dataMethodData,
  emitDataMethodEvent.dataMethodDataLoading,
])
// 挂载
onMounted(() => {
  doDataMethod({props,reactiveData,emit})
  doDataMethodInit({props,reactiveData})
  getDefaultCheckedKeysOnceControl.hasMounted = true

})
// 方法
// 值更新事件
const updateModelValueEvent = updateDataModelValueEventHandle({reactiveData,hasPermission,emit,eventName: null})
// 值改变事件
const changeModelValueEvent = changeDataModelValueEventHandle({reactiveData,hasPermission,emit,eventName: null})

const emitCheckedKeys = (checkedKeysParam?:any[],halfCheckedKeysParam?:any[]) => {
  if (!getDefaultCheckedKeysOnceControl.hasMounted) {
    return
  }
  let r = []

  let checkedKeys = checkedKeysParam || getCheckedKeys(props.modelValueIncludeLeafOnly)
  if(checkedKeys){
    r = r.concat(checkedKeys)
  }

  if(props.modelValueIncludeHalfCheckedKeys){
    let halfCheckedKeys = halfCheckedKeysParam || getHalfCheckedKeys()
    if(halfCheckedKeys){
      r = r.concat(halfCheckedKeys)
    }
  }
  r = removeDuplicate(r)
  let equal = isEqual(r,reactiveData.currentModelValue)
  if (!equal) {
    updateModelValueEvent(r)
    changeModelValueEvent(r)
  }
}

const events = {
  'node-click': function (){emit('node-click',...arguments)},
  'node-contextmenu': function (){emit('node-contextmenu',...arguments)},
  'check-change': function (){
    if(hasPermission.value.enable && hasPermission.value.hasPm){
      emit('check-change',...arguments)
    }
  },
  'check': function (dataItem,{checkedNodes, checkedKeys, halfCheckedNodes,halfCheckedKeys }){
    if (props.modelValueIncludeLeafOnly) {
      emitCheckedKeys(getCheckedKeys(props.modelValueIncludeLeafOnly), halfCheckedKeys);
    } else {
      // checkedKeys 中包含全部选中的
      emitCheckedKeys(checkedKeys,halfCheckedKeys);
    }
    if(hasPermission.value.enable && hasPermission.value.hasPm){
      emit('check',...arguments)
    }
  },
  'current-change': function (){emit('current-change',...arguments)},
  'node-expand': function (){emit('node-expand',...arguments)},
  'node-collapse': function (){emit('node-collapse',...arguments)},
  'node-drag-start': function (){emit('node-drag-start',...arguments)},
  'node-drag-enter': function (){emit('node-drag-enter',...arguments)},
  'node-drag-leave': function (){emit('node-drag-leave',...arguments)},
  'node-drag-over': function (){emit('node-drag-over',...arguments)},
  'node-drag-end': function (){emit('node-drag-end',...arguments)},
  'node-drop': function (){emit('node-drop',...arguments)},
}

/**
 * 设置选中的key
 * @param keys
 */
const setCheckedKeys = (keys,leafOnly)=>{
  treeRef?.value?.setCheckedKeys(keys,leafOnly)
}
/**
 * 清空所有选中项
 */
const clearCheckedAll = () => {
  treeRef.value?.setCheckedKeys([])
}
// (leafOnly) 接收一个布尔类型参数，默认为 false. 如果参数是 true, 它只返回当前选择的子节点数组。
const getCheckedKeys = (leafOnly) => {
  return treeRef.value?.getCheckedKeys(leafOnly)
}
// 若节点可被选中(show-checkbox 为 true)，则返回目前半选中的节点的 key 所组成的数组
const getHalfCheckedKeys = () => {
  return treeRef.value?.getHalfCheckedKeys()
}

// filter 相关
const filterText = ref('')
watch(filterText, (val) => {
  treeRef.value?.filter(val)
})
interface Tree {
  [propsOptions.value.value]: number | string
  [propsOptions.value.label]: string
  [propsOptions.value.children]?: Tree[]
}
// 过滤
const filterNode = (value: string, data: Tree) => {
  if (!value) return true
  return data[propsOptions.value.label].includes(value)
}


// 触发 updateModelData
const emitModelData = ({data,value})=>{

  // 如果没有数据
  let r = []
  let param = {
    data,
    value,
    result: r,
    childrenKey: propsOptions.value.children,
    valueKey: propsOptions.value.value}
  pushCurrentModelData(param)
  // 事件派发
  emit(emitDataModelEvent.updateModelData,r)
}

</script>
<template>
  <el-input v-if="hasPermission.render && enableFilter" v-model="filterText" :placeholder="filterInputProps.placeholder || '输入名称过滤关键字'" v-bind="filterInputProps" />
  <el-tree ref="treeRef" v-if="hasPermission.render"  v-loading="dataLoading" class="pt-tree pt-width-100-pc"
           :title="hasDisabled.disabledReason || title"
               v-model="reactiveData.currentModelValue"
               v-bind="$attrs"
               :clearable="clearable"
               :default-checked-keys="defaultCheckedKeys"
               :data="options"
               :props="propsOptions"
               :filter-node-method="filterNodeMethod || filterNode"
               :checkStrictly="checkStrictly"
               :disabled="hasDisabled.disabled"
               :node-key="propsOptions.value"
               @node-click="events['node-click']"
               @node-contextmenu="events['node-contextmenu']"
               @check-change="events['check-change']"
               @check="events['check']"
               @current-change="events['current-change']"
               @node-expand="events['node-expand']"
               @node-collapse="events['node-collapse']"
               @node-drag-start="events['node-drag-start']"
               @node-drag-enter="events['node-drag-enter']"
               @node-drag-leave="events['node-drag-leave']"
               @node-drag-over="events['node-drag-over']"
               @node-drag-end="events['node-drag-end']"
               @node-drop="events['node-drop']">

    <template #default=" { node, data }" v-if="$slots.default">
      <slot v-bind="{ node, data }"></slot>
    </template>
  </el-tree>

</template>
<style scoped>
.pt-tree{
  min-width: 10rem;
}
</style>