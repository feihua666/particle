<script setup name="FormButton" lang="ts">
/**
 * 自定义表单按钮
 * 封装理由：1. 一致的方式使用 单按钮，并获取一致的表现
 *          2. 使按钮点击自动弹窗表单，方便在一个表单项配置json字符串的场景
 */
import {onMounted, reactive, nextTick, watch,ref} from 'vue'
import {emitDataModelEvent, reactiveDataModelData, updateDataModelValueEventHandle,changeDataModelValueEventHandle} from "./dataModel";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 值绑定
  modelValue: String,
  buttonProps: {
    type: Object,
    default: () => ({})
  },
  formProps: {
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
  form: {
  },
  ...reactiveDataModelData(props),
  dialogVisible: false
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
  emitDataModelEvent.change
])

// 添加入参文档按钮
const submitMethod = ():void => {
  let formJsonStr = toJsonStr(reactiveData.form)
  updateModelValueEvent(formJsonStr)
  changeModelValueEvent(formJsonStr)
  inputModelValueEvent(formJsonStr)
  reactiveData.dialogVisible=false;
}

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认',
})
const formRender = ref(false)
const dialogOpen = ()=>{
  nextTick(()=>{
    formRender.value = true
  })
}
onMounted(()=>{
  // 挂载后初始化数据
  if(props.modelValue){
    let form = JSON.parse(props.modelValue)
    for (let formKey in form) {
      reactiveData.form[formKey] = form[formKey]
    }
  }
})
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
</script>
<template>
  <PtButton v-bind="$attrs" :text="true" :type="reactiveData.currentModelValue ? 'primary' : 'default'" @click="reactiveData.dialogVisible = !reactiveData.dialogVisible" >点击配置</PtButton>
  <el-dialog v-model="reactiveData.dialogVisible" :width="dialogProps.width || '70%'" :title="dialogProps.title || ''" @open="dialogOpen" @closed="formRender=false" append-to-body destroy-on-close>
    <PtForm v-if="formRender" ref="formRef" :form="reactiveData.form" class="pt-wdith-100-pc"
            :method="submitMethod"
            defaultButtonsShow="submit,reset"
            :submitAttrs="submitAttrs"
            :layout="formProps.layout"
            :comps="formProps.comps"
            :buttonsTeleportProps="{disabled: false,to: '#formButtonDialogFooter'}"
    >
    </PtForm>
    <template #footer>
      <!--   将表单按钮传送到这里   -->
      <div id="formButtonDialogFooter"></div>
    </template>
  </el-dialog>
</template>
<style scoped>
</style>
