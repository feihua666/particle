<script setup name="TableFormButton" lang="ts">
/**
 * 自定义表格表单按钮
 * 封装理由：1. 一致的方式使用 单按钮，并获取一致的表现
 *          2. 使按钮点击自动弹窗表格表单，方便在一个表单项配置json数组字符串的场景
 */
import {onMounted, reactive, nextTick, watch,ref} from 'vue'
import {emitDataModelEvent, reactiveDataModelData, updateDataModelValueEventHandle,changeDataModelValueEventHandle} from "./dataModel";
import {clone} from "../../common/tools/ObjectTools";
import {backMove, frontMove} from "../../common/tools/ArrayTools";

const formRef = ref(null)
const tableRef = ref(null)

// 用来判断是否添加，true=添加，false=修改
const isAdd = ref(true)
// 修改时，记录修改的位置
const updateIndex = ref(null)

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 值绑定
  modelValue: String,
  buttonProps: {
    type: Object,
    default: () => ({})
  },
  /**
   * 注意存在自定义属性或方法
   * 自定义方法1：formSubmitDataHandler
   */
  formProps: {
    type: Object,
    default: () => ({})
  },
  /**
   * 注意存在自定义属性或方法
   * 自定义属性1：propForDeleteView
   * 自定义属性2：showMoveUpAndDownButton
   */
  tableProps: {
    type: Object,
    default: () => ({})
  },
  dialogProps: {
    type: Object,
    default: () => ({})
  },
})
// 属性
const reactiveData = reactive({
  tableData: [],
  form: {
  },
  formData:{},
  ...reactiveDataModelData(props),
  dialogVisible: false
})
// 侦听
watch(
    () => props.modelValue,
    (val) => {
      reactiveData.oldModelValue = val
      reactiveData.currentModelValue = val
      initData(val)
    }
)
// 侦听
watch(
    () => isAdd.value,
    (val,valOld) => {
      submitAttrs.value = ({
        buttonText: val ? '添加' : '修改',
      })
    }
)
// 事件
const emit = defineEmits([
  // 用来更新 modelValue
  emitDataModelEvent.updateModelValue,
  emitDataModelEvent.change
])

// form表单确认提交
const submitMethod = ():void => {
  let data = clone(reactiveData.form)

  if(props.formProps.formSubmitDataHandler){
    data = props.formProps.formSubmitDataHandler({isAdd,formRef,form: data,formData: reactiveData.formData})
  }
  if(isAdd.value){
    reactiveData.tableData.push(data)
  }else {
    reactiveData.tableData.splice(updateIndex.value,1,data)
  }
}

// 提交按钮属性
const submitAttrs = ref({
  buttonText: isAdd.value ? '添加' : '修改',
})
const formRender = ref(false)
const dialogOpen = ()=>{
  nextTick(()=>{
    formRender.value = true
  })
}
onMounted(()=>{
  // 挂载后初始化数据
  initData(props.modelValue)
})
const initData = (dataStr)=>{
  if(dataStr){
    let data = JSON.parse(dataStr)
    reactiveData.tableData = data
  }
}
const hasPermission = undefined
// 方法
// 值更新事件
const updateModelValueEvent = updateDataModelValueEventHandle({reactiveData,hasPermission,emit})
// 值改变事件
const changeModelValueEvent = changeDataModelValueEventHandle({reactiveData,hasPermission,emit})
const inputModelValueEvent = changeDataModelValueEventHandle({reactiveData,hasPermission,emit,eventName: 'input'})

const toJsonStr = (json)=>{
  return JSON.stringify(json)
}

// 表格操作按钮
const getTableRowButtons = ({row, column, $index}) => {
  if($index < 0){
    return []
  }
  let tableRowButtons = [
    {
      txt: '修改',
      text: true,
      // 删除操作
      method(){
        let item = row
        resetForm()

        updateIndex.value = $index
        nextTick(()=>{
          isAdd.value = false
          for (let formKey in item) {
            reactiveData.form[formKey] = item[formKey]
          }
        })
      }
    },
    {
      txt: '删除',
      text: true,
      methodConfirmText: `确定要删除 ${row[props.tableProps.propForDeleteView]} 吗？`,
      // 删除操作
      method(){
        reactiveData.tableData.splice($index,1)
      }
    }
  ]
  if (props.tableProps.showMoveUpAndDownButton) {
    tableRowButtons.push({
      txt: '上移',
      text: true,
      // 删除操作
      method(){
        frontMove(reactiveData.tableData,$index)
      }
    })
    tableRowButtons.push({
      txt: '下移',
      text: true,
      // 删除操作
      method(){
        backMove(reactiveData.tableData,$index)
      }
    })
  }
  return tableRowButtons;
}
const resetForm = ()=> {
  // ormRef.value?.resetForm()
  formRef.value?.formRef.value?.resetFields()
}
const onResetForm = () => {
  isAdd.value = true
}
// 最终提交
const submitFinal = ():void => {
  let formJsonStr = toJsonStr(reactiveData.tableData)
  if(reactiveData.tableData.length <= 0){
    formJsonStr = ''
  }
  updateModelValueEvent(formJsonStr)
  changeModelValueEvent(formJsonStr)
  inputModelValueEvent(formJsonStr)
  reactiveData.dialogVisible=false;
}
</script>
<template>
  <PtButton v-bind="$attrs" :text="true" :type="reactiveData.currentModelValue ? 'primary' : 'default'" @click="reactiveData.dialogVisible = !reactiveData.dialogVisible" >点击配置</PtButton>
  <el-dialog v-model="reactiveData.dialogVisible" :width="dialogProps.width || '70%'" :title="dialogProps.title || ''" @open="dialogOpen" @closed="formRender=false" append-to-body destroy-on-close>
    <PtForm v-if="formRender" ref="formRef" :form="reactiveData.form" :formData="reactiveData.formData" class="pt-wdith-100-pc"
            :method="submitMethod"
            defaultButtonsShow="submit,reset"
            :submitAttrs="submitAttrs"
            :layout="formProps.layout"
            :comps="formProps.comps"
            :labelWidth="formProps.labelWidth"
            :onResetForm="onResetForm"
    >
    </PtForm>
    <PtTable v-if="formRender" ref="tableRef"
             :options="reactiveData.tableData"
             :columns="tableProps.columns">

      <!--  操作按钮  -->
      <template #defaultAppend>
        <el-table-column label="操作" width="180">
          <template #default="{row, column, $index}">
            <PtButtonGroup :options="getTableRowButtons({row, column, $index})">
            </PtButtonGroup>
          </template>
        </el-table-column>
      </template>
    </PtTable>
    <template #footer>
      <span>
        <PtButton type="primary" @click="submitFinal" >确认</PtButton>
      </span>
    </template>
  </el-dialog>
</template>
<style scoped>
</style>
