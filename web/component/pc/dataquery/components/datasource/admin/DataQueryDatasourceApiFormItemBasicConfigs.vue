<script setup name="DataQueryDatasourceApiFormItemBasicConfigs" lang="ts">
import {nextTick, reactive, ref} from "vue"
import JdbcApiBasicConfig from './apiconfigs/jdbc/JdbcApiBasicConfig.vue'
import HttpApiBasicConfig from './apiconfigs/http/HttpApiBasicConfig.vue'
import Neo4jApiBasicConfig from './apiconfigs/neo4j/Neo4jApiBasicConfig.vue'
import EsApiBasicConfig from './apiconfigs/es/EsApiBasicConfig.vue'


const jdbcConfigRef = ref(null)
const httpConfigRef = ref(null)
const neo4jConfigRef = ref(null)
const esConfigRef = ref(null)

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
  http:{
    dialogVisible: false,
  },
  neo4j:{
    dialogVisible: false,
  },
  es:{
    dialogVisible: false,
  },
})
const toJsonStr = (json)=>{
  return JSON.stringify(json)
}
// jdbc 确认提交
const jdbcSubmit = ()=>{
  props.form.configJson = toJsonStr(jdbcConfigRef.value.form)
  reactiveData.jdbc.dialogVisible=false;
}
// http 确认提交
const httpSubmit = ()=>{
  props.form.configJson = toJsonStr(httpConfigRef.value.form)
  reactiveData.http.dialogVisible=false;
}
// neo4j 确认提交
const neo4jSubmit = ()=>{
  props.form.configJson = toJsonStr(neo4jConfigRef.value.form)
  reactiveData.neo4j.dialogVisible=false;
}
// es 确认提交
const esSubmit = ()=>{
  props.form.configJson = toJsonStr(esConfigRef.value.form)
  reactiveData.es.dialogVisible=false;
}
// [Vue warn]: Failed to locate Teleport target with selector "#dataqueryDatasourceApiJdbcBasicConfigDialogFooter". Note the target element must exist before the component is mounted - i.e. the target cannot be rendered by the component itself, and ideally should be outside of the entire Vue component tree.
// 如上异常信息，如果不加控制表单中传送的按钮找不到目标位置，先让dialog渲染完成，再渲染内部表单即可
const jdbcApiBasicConfigRender = ref(false)
const jdbcDialogOpen = ()=>{
  nextTick(()=>{
    jdbcApiBasicConfigRender.value = true
  })
}

const httpApiBasicConfigRender = ref(false)
const httpDialogOpen = ()=>{
  nextTick(()=>{
    httpApiBasicConfigRender.value = true
  })
}

const neo4jApiBasicConfigRender = ref(false)
const neo4jDialogOpen = ()=>{
  nextTick(()=>{
    neo4jApiBasicConfigRender.value = true
  })
}
const esApiBasicConfigRender = ref(false)
const esDialogOpen = ()=>{
  nextTick(()=>{
    esApiBasicConfigRender.value = true
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

  <el-dialog  v-model="reactiveData.http.dialogVisible" width="70%" title="http配置Json" @open="httpDialogOpen" @closed="httpApiBasicConfigRender=false" append-to-body destroy-on-close>
    <HttpApiBasicConfig v-if="httpApiBasicConfigRender" ref="httpConfigRef" :initJsonStr="form.configJson"
                        :onSubmit="httpSubmit"
                        :buttonsTeleportProps="{disabled: false,to: '#dataqueryDatasourceApiHttpBasicConfigDialogFooter'}">
    </HttpApiBasicConfig>
    <template #footer>
      <!--   将表单按钮传送到这里   -->
      <div id="dataqueryDatasourceApiHttpBasicConfigDialogFooter"></div>
    </template>
  </el-dialog>

  <el-dialog  v-model="reactiveData.neo4j.dialogVisible" width="70%" title="neo4j配置Json" @open="neo4jDialogOpen" @closed="neo4jApiBasicConfigRender=false" append-to-body destroy-on-close>
    <Neo4jApiBasicConfig v-if="neo4jApiBasicConfigRender" ref="neo4jConfigRef" :initJsonStr="form.configJson"
                         :onSubmit="neo4jSubmit"
                         :buttonsTeleportProps="{disabled: false,to: '#dataqueryDatasourceApiNeo4jBasicConfigDialogFooter'}">
    </Neo4jApiBasicConfig>
    <template #footer>
      <!--   将表单按钮传送到这里   -->
      <div id="dataqueryDatasourceApiNeo4jBasicConfigDialogFooter"></div>
    </template>
  </el-dialog>

  <el-dialog  v-model="reactiveData.es.dialogVisible" width="70%" title="es配置Json" @open="esDialogOpen" @closed="esApiBasicConfigRender=false" append-to-body destroy-on-close>
    <EsApiBasicConfig v-if="esApiBasicConfigRender" ref="esConfigRef" :initJsonStr="form.configJson"
                      :onSubmit="esSubmit"
                      :buttonsTeleportProps="{disabled: false,to: '#dataqueryDatasourceApiEsBasicConfigDialogFooter'}">
    </EsApiBasicConfig>
    <template #footer>
      <!--   将表单按钮传送到这里   -->
      <div id="dataqueryDatasourceApiEsBasicConfigDialogFooter"></div>
    </template>
  </el-dialog>
</template>

<style scoped>

</style>
