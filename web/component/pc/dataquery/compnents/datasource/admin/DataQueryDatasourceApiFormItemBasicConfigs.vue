<script setup name="DataQueryDatasourceApiFormItemBasicConfigs" lang="ts">
import {ref,reactive,nextTick} from "vue"
import JdbcApiBasicConfig from './apiconfigs/jdbc/JdbcApiBasicConfig.vue'


const jdbcConfigRef = ref(null)

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
  jdbc:{
    dialogVisible: false,
  },
})
const toJsonStr = (json)=>{
  return JSON.stringify(json)
}
// 入参示例确认提交
const jdbcSubmit = ()=>{
  props.form.configJson = toJsonStr(jdbcConfigRef.value.form)
  reactiveData.jdbc.dialogVisible=false;
}

// [Vue warn]: Failed to locate Teleport target with selector "#dataqueryDatasourceApiJdbcBasicConfigDialogFooter". Note the target element must exist before the component is mounted - i.e. the target cannot be rendered by the component itself, and ideally should be outside of the entire Vue component tree.
// 如上异常信息，如果不加控制表单中传送的按钮找不到目标位置，先让dialog渲染完成，再渲染内部表单即可
const jdbcApiBasicConfigRender = ref(false)
const jdbcDialogOpen = ()=>{
  nextTick(()=>{
    jdbcApiBasicConfigRender.value = true
  })
}
// 暴露方法
defineExpose({
  reactiveData
})
</script>
<template>
  <el-dialog  v-model="reactiveData.jdbc.dialogVisible" width="70%" title="jdbc配置Json" @open="jdbcDialogOpen" @closed="jdbcApiBasicConfigRender=false" append-to-body destroy-on-close>
    <JdbcApiBasicConfig v-if="jdbcApiBasicConfigRender" ref="jdbcConfigRef" :initJsonStr="form.configJson"
                        :onSubmit="jdbcSubmit"
                        :buttonsTeleportProps="{disabled: false,to: '#dataqueryDatasourceApiJdbcBasicConfigDialogFooter'}">
    </JdbcApiBasicConfig>
    <template #footer>
      <!--   将表单按钮传送到这里   -->
      <div id="dataqueryDatasourceApiJdbcBasicConfigDialogFooter"></div>
    </template>
  </el-dialog>
</template>

<style scoped>

</style>