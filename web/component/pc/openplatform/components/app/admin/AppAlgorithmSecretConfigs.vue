<script setup name="AppAlgorithmSecretConfigs" lang="ts">
/**
 * 开放平台app算法密钥相关设置入口
 */

import {nextTick, reactive, ref} from "vue"
import AppRequestAlgorithmSecret from './AppRequestAlgorithmSecret.vue'
import AppResponseAlgorithmSecret from './AppResponseAlgorithmSecret.vue'


const openapiRequestAlgorithmSecretRef = ref(null)
const openapiResponseAlgorithmSecretRef = ref(null)

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 表单数据
  form: {
    type: Object,
    required: true
  },
  formData:{
    type: Object,
    required: true
  },
})
// 属性
const reactiveData = reactive({
  openapiRequestAlgorithmSecretConfigJson:{
    dialogVisible: false,
  },
  openapiResponseAlgorithmSecretConfigJson:{
    dialogVisible: false,
  },
})
const toJsonStr = (json)=>{
  return JSON.stringify(json)
}
// 请求设置确认提交
const openapiRequestAlgorithmSecretSubmit = ()=>{
  props.form.requestAlgorithmSecretJson = toJsonStr(openapiRequestAlgorithmSecretRef.value.form);
  reactiveData.openapiRequestAlgorithmSecretConfigJson.dialogVisible=false;
}
// [Vue warn]: Failed to locate Teleport target with selector "#dataqueryDatasourceApiJdbcBasicConfigDialogFooter". Note the target element must exist before the component is mounted - i.e. the target cannot be rendered by the component itself, and ideally should be outside of the entire Vue component tree.
// 如上异常信息，如果不加控制表单中传送的按钮找不到目标位置，先让dialog渲染完成，再渲染内部表单即可
const openapiRequestAlgorithmSecretConfigRender = ref(false)
const openapiRequestAlgorithmSecretDialogOpen = ()=>{
  nextTick(()=>{
    openapiRequestAlgorithmSecretConfigRender.value = true
  })
}


// 响应设置确认提交
const openapiResponseAlgorithmSecretSubmit = ()=>{
  props.form.responseAlgorithmSecretJson = toJsonStr(openapiResponseAlgorithmSecretRef.value.form);
  reactiveData.openapiResponseAlgorithmSecretConfigJson.dialogVisible=false;
}
// [Vue warn]: Failed to locate Teleport target with selector "#dataqueryDatasourceApiJdbcBasicConfigDialogFooter". Note the target element must exist before the component is mounted - i.e. the target cannot be rendered by the component itself, and ideally should be outside of the entire Vue component tree.
// 如上异常信息，如果不加控制表单中传送的按钮找不到目标位置，先让dialog渲染完成，再渲染内部表单即可
const openapiResponseAlgorithmSecretConfigRender = ref(false)
const openapiResponseAlgorithmSecretDialogOpen = ()=>{
  nextTick(()=>{
    openapiResponseAlgorithmSecretConfigRender.value = true
  })
}

// 暴露方法
defineExpose({
  reactiveData
})
</script>
<template>

  <el-dialog v-model="reactiveData.openapiRequestAlgorithmSecretConfigJson.dialogVisible" width="70%" title="请求配置Json"  @open="openapiRequestAlgorithmSecretDialogOpen" @closed="openapiRequestAlgorithmSecretConfigRender=false" append-to-body destroy-on-close>
    <AppRequestAlgorithmSecret v-if="openapiRequestAlgorithmSecretConfigRender" ref="openapiRequestAlgorithmSecretRef" :initJsonStr="form.requestAlgorithmSecretJson"
                   :onSubmit="openapiRequestAlgorithmSecretSubmit"
                   :buttonsTeleportProps="{disabled: false,to: '#openplatformAppRequestAlgorithmSecretConfigDialogFooter'}">
    </AppRequestAlgorithmSecret>
    <template #footer>
      <!--   将表单按钮传送到这里   -->
      <div id="openplatformAppRequestAlgorithmSecretConfigDialogFooter"></div>
    </template>
  </el-dialog>

  <el-dialog v-model="reactiveData.openapiResponseAlgorithmSecretConfigJson.dialogVisible" width="70%" title="响应配置Json" @open="openapiResponseAlgorithmSecretDialogOpen" @closed="openapiResponseAlgorithmSecretConfigRender=false" append-to-body destroy-on-close>
    <AppResponseAlgorithmSecret v-if="openapiResponseAlgorithmSecretConfigRender" ref="openapiResponseAlgorithmSecretRef" :initJsonStr="form.responseAlgorithmSecretJson"
                  :onSubmit="openapiResponseAlgorithmSecretSubmit"
                  :buttonsTeleportProps="{disabled: false,to: '#openplatformAppResponseAlgorithmSecretConfigDialogFooter'}">
    </AppResponseAlgorithmSecret>
    <template #footer>
      <!--   将表单按钮传送到这里   -->
      <div id="openplatformAppResponseAlgorithmSecretConfigDialogFooter"></div>
    </template>
  </el-dialog>

</template>

<style scoped>

</style>
