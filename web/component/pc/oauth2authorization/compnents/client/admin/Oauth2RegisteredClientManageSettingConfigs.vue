<script setup name="Oauth2RegisteredClientManageSettingConfigs" lang="ts">
/**
 * 注册客户端相关设置入口
 */

import {ref,reactive,nextTick} from "vue"
import ClientSettings from './ClientSettings.vue'
import TokenSettings from './TokenSettings.vue'


const clientSettingRef = ref(null)
const tokenSettingRef = ref(null)

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
  clientSettingConfigJson:{
    dialogVisible: false,
  },
  tokenSettingConfigJson:{
    dialogVisible: false,
  },
})
const toJsonStr = (json)=>{
  return JSON.stringify(json)
}
// 客户端设置确认提交
const clientSettingSubmit = ()=>{
  props.form.clientSettings = toJsonStr(clientSettingRef.value.form);
  reactiveData.clientSettingConfigJson.dialogVisible=false;
}
// [Vue warn]: Failed to locate Teleport target with selector "#dataqueryDatasourceApiJdbcBasicConfigDialogFooter". Note the target element must exist before the component is mounted - i.e. the target cannot be rendered by the component itself, and ideally should be outside of the entire Vue component tree.
// 如上异常信息，如果不加控制表单中传送的按钮找不到目标位置，先让dialog渲染完成，再渲染内部表单即可
const clientSettingConfigRender = ref(false)
const clientSettingDialogOpen = ()=>{
  nextTick(()=>{
    clientSettingConfigRender.value = true
  })
}


// token设置确认提交
const tokenSettingSubmit = ()=>{
  props.form.tokenSettings = toJsonStr(tokenSettingRef.value.form);
  reactiveData.tokenSettingConfigJson.dialogVisible=false;
}
// [Vue warn]: Failed to locate Teleport target with selector "#dataqueryDatasourceApiJdbcBasicConfigDialogFooter". Note the target element must exist before the component is mounted - i.e. the target cannot be rendered by the component itself, and ideally should be outside of the entire Vue component tree.
// 如上异常信息，如果不加控制表单中传送的按钮找不到目标位置，先让dialog渲染完成，再渲染内部表单即可
const tokenSettingConfigRender = ref(false)
const tokenSettingDialogOpen = ()=>{
  nextTick(()=>{
    tokenSettingConfigRender.value = true
  })
}

// 暴露方法
defineExpose({
  reactiveData
})
</script>
<template>

  <el-dialog v-model="reactiveData.clientSettingConfigJson.dialogVisible" width="70%" title="客户端配置Json"  @open="clientSettingDialogOpen" @closed="clientSettingConfigRender=false" append-to-body destroy-on-close>
    <ClientSettings v-if="clientSettingConfigRender" ref="clientSettingRef" :initJsonStr="form.clientSettings"
                   :onSubmit="clientSettingSubmit"
                    :authorizationGrantTypes="form.authorizationGrantTypes"
                   :buttonsTeleportProps="{disabled: false,to: '#oauth2RegisteredClientClientSettingDialogFooter'}">
    </ClientSettings>
    <template #footer>
      <!--   将表单按钮传送到这里   -->
      <div id="oauth2RegisteredClientClientSettingDialogFooter"></div>
    </template>
  </el-dialog>

  <el-dialog v-model="reactiveData.tokenSettingConfigJson.dialogVisible" width="70%" title="token配置Json" @open="tokenSettingDialogOpen" @closed="tokenSettingConfigRender=false" append-to-body destroy-on-close>
    <TokenSettings v-if="tokenSettingConfigRender" ref="tokenSettingRef" :initJsonStr="form.tokenSettings"
                  :onSubmit="tokenSettingSubmit"
                  :buttonsTeleportProps="{disabled: false,to: '#oauth2RegisteredClientTokenSettingDialogFooter'}">
    </TokenSettings>
    <template #footer>
      <!--   将表单按钮传送到这里   -->
      <div id="oauth2RegisteredClientTokenSettingDialogFooter"></div>
    </template>
  </el-dialog>

</template>

<style scoped>

</style>