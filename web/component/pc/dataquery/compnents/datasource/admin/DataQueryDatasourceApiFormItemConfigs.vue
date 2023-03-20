<script setup name="DataQueryDatasourceApiFormItemConfigs" lang="ts">
import {ref,reactive,nextTick} from "vue"
import InParamExampleConfig from './apiconfigs/InParamExampleConfig.vue'
import InParamTestCaseDataConfig from './apiconfigs/InParamTestCaseDataConfig.vue'
import InParamDocConfig from './apiconfigs/InParamDocConfig.vue'
import InParamValidateConfig from './apiconfigs/InParamValidateConfig.vue'
import OutParamExampleConfig from './apiconfigs/OutParamExampleConfig.vue'
import OutParamSuccessConfig from './apiconfigs/OutParamSuccessConfig.vue'
import OutParamDocConfig from './apiconfigs/OutParamDocConfig.vue'
import DictConfig from './apiconfigs/DictConfig.vue'
import PageableAdapterConfig from './apiconfigs/PageableAdapterConfig.vue'


const inParamExampleConfigRef = ref(null)
const inParamTestCaseDataConfigRef = ref(null)
const inParamDocConfigRef = ref(null)
const inParamValidateConfigRef = ref(null)
const outParamExampleConfigRef = ref(null)
const outParamSuccessConfigRef = ref(null)
const outParamDocConfigRef = ref(null)
const dictConfigRef = ref(null)
const pageableAdapterConfigConfigRef = ref(null)

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
  inParamExampleConfigJson:{
    dialogVisible: false,
  },
  inParamTestCaseDataConfigJson:{
    dialogVisible: false,
  },
  inParamDoc:{
    dialogVisible: false,
  },
  inParamValidate:{
    dialogVisible: false,
  },
  outParamExampleConfigJson:{
    dialogVisible: false,
  },
  outParamSuccess:{
    dialogVisible: false,
  },
  outParamDoc:{
    dialogVisible: false,
  },
  dictConfig:{
    dialogVisible: false,
  },
  pageableAdapterConfig:{
    dialogVisible: false,
  },
})
const toJsonStr = (json)=>{
  return JSON.stringify(json)
}
// 入参示例确认提交
const inParamExampleSubmit = ()=>{
  if (inParamExampleConfigRef.value.getInitJson().inParamExamples.length > 0) {
    props.form.inParamExampleConfigJson = toJsonStr(inParamExampleConfigRef.value.getInitJson());
  }
  reactiveData.inParamExampleConfigJson.dialogVisible=false;
}
// 入参测试用例确认提交
const inParamTestCaseDataSubmit = ()=>{
  if (inParamTestCaseDataConfigRef.value.getInitJson().inParamTestCases.length > 0) {
    props.form.inParamTestCaseDataConfigJson = toJsonStr(inParamTestCaseDataConfigRef.value.getInitJson())
  }

  reactiveData.inParamTestCaseDataConfigJson.dialogVisible=false;
}
// 入参文档确认提交
const inParamDocSubmit = ()=>{
  if (inParamDocConfigRef.value.getInitJson().inParamDocs.length > 0) {
    props.form.inParamDocConfigJson = toJsonStr(inParamDocConfigRef.value.getInitJson());
  }
  reactiveData.inParamDoc.dialogVisible=false;
}
// 入参校验确认提交
const inParamValidateSubmit = ()=>{
  if (inParamValidateConfigRef.value.getInitJson().inParamValidateItems.length > 0) {
    props.form.inParamValidateConfigJson = toJsonStr(inParamValidateConfigRef.value.getInitJson());
  }
  reactiveData.inParamValidate.dialogVisible=false;
}


// 出参示例确认提交
const outParamExampleSubmit = ()=>{
  if (outParamExampleConfigRef.value.getInitJson().outParamExamples.length > 0) {
    props.form.outParamExampleConfigJson = toJsonStr(outParamExampleConfigRef.value.getInitJson());
  }
  reactiveData.outParamExampleConfigJson.dialogVisible=false;
}
// 出参成功配置确认提交
const outParamSuccessSubmit = ()=>{
  if (outParamSuccessConfigRef.value.getInitJson().outParamSuccesses.length > 0) {
    props.form.outParamSuccessConfigJson = toJsonStr(outParamSuccessConfigRef.value.getInitJson());
  }
  reactiveData.outParamSuccess.dialogVisible=false;
}

// 出参文档确认提交
const outParamDocSubmit = ()=>{
  if (outParamDocConfigRef.value.getInitJson().outParamDocs.length > 0) {
    props.form.outParamDocConfigJson = toJsonStr(outParamDocConfigRef.value.getInitJson());
  }
  reactiveData.outParamDoc.dialogVisible=false;
}

// 字典配置确认提交
const dictConfigSubmit = ()=>{
  if (dictConfigRef.value.getInitJson().dictItems.length > 0) {
    props.form.dictConfigJson = toJsonStr(dictConfigRef.value.getInitJson());
  }
  reactiveData.dictConfig.dialogVisible=false;
}

// 分页信息解析配置确认提交

// 入参示例确认提交
const pageableAdapterConfigSubmit = ()=>{
  props.form.pageableAdapterConfigJson = toJsonStr(pageableAdapterConfigConfigRef.value.form)
  reactiveData.pageableAdapterConfig.dialogVisible=false;
}

// [Vue warn]: Failed to locate Teleport target with selector "#dataqueryDatasourceApiJdbcBasicConfigDialogFooter". Note the target element must exist before the component is mounted - i.e. the target cannot be rendered by the component itself, and ideally should be outside of the entire Vue component tree.
// 如上异常信息，如果不加控制表单中传送的按钮找不到目标位置，先让dialog渲染完成，再渲染内部表单即可
const pageableAdapterConfigRender = ref(false)
const pageableAdapterConfigDialogOpen = ()=>{
  nextTick(()=>{
    pageableAdapterConfigRender.value = true
  })
}
// 暴露方法
defineExpose({
  reactiveData
})
</script>
<template>
  <el-dialog v-model="reactiveData.inParamExampleConfigJson.dialogVisible" width="70%" title="入参示例配置Json" append-to-body destroy-on-close>
    <InParamExampleConfig ref="inParamExampleConfigRef" :initJsonStr="form.inParamExampleConfigJson"></InParamExampleConfig>
    <template #footer>
      <span>
        <PtButton type="primary" @click="inParamExampleSubmit" >确认</PtButton>
      </span>
    </template>
  </el-dialog>

  <el-dialog v-model="reactiveData.inParamTestCaseDataConfigJson.dialogVisible" width="70%" title="测试用例配置Json" append-to-body destroy-on-close>
    <InParamTestCaseDataConfig ref="inParamTestCaseDataConfigRef" :initJsonStr="form.inParamTestCaseDataConfigJson"></InParamTestCaseDataConfig>
    <template #footer>
      <span>
        <PtButton type="primary" @click="inParamTestCaseDataSubmit" >确认</PtButton>
      </span>
    </template>
  </el-dialog>

  <el-dialog v-model="reactiveData.inParamDoc.dialogVisible" width="70%" title="入参文档配置Json" append-to-body destroy-on-close>
    <InParamDocConfig ref="inParamDocConfigRef" :rootInParamType="formData.inParamTypeDictId?.value" :initJsonStr="form.inParamDocConfigJson"></InParamDocConfig>
    <template #footer>
      <span>
        <PtButton type="primary" @click="inParamDocSubmit" >确认</PtButton>
      </span>
    </template>
  </el-dialog>

  <el-dialog v-model="reactiveData.inParamValidate.dialogVisible" width="70%" title="入参校验配置Json" append-to-body destroy-on-close>
    <InParamValidateConfig ref="inParamValidateConfigRef" :initJsonStr="form.inParamValidateConfigJson"></InParamValidateConfig>
    <template #footer>
      <span>
        <PtButton type="primary" @click="inParamValidateSubmit" >确认</PtButton>
      </span>
    </template>
  </el-dialog>

  <el-dialog v-model="reactiveData.outParamExampleConfigJson.dialogVisible" width="70%" title="出参示例配置Json" append-to-body destroy-on-close>
    <OutParamExampleConfig ref="outParamExampleConfigRef" :initJsonStr="form.outParamExampleConfigJson"></OutParamExampleConfig>
    <template #footer>
      <span>
        <PtButton type="primary" @click="outParamExampleSubmit" >确认</PtButton>
      </span>
    </template>
  </el-dialog>

  <el-dialog v-model="reactiveData.outParamSuccess.dialogVisible" width="70%" title="出参成功判断配置Json" append-to-body destroy-on-close>
    <OutParamSuccessConfig ref="outParamSuccessConfigRef" :initJsonStr="form.outParamSuccessConfigJson"></OutParamSuccessConfig>
    <template #footer>
      <span>
        <PtButton type="primary" @click="outParamSuccessSubmit" >确认</PtButton>
      </span>
    </template>
  </el-dialog>


  <el-dialog v-model="reactiveData.outParamDoc.dialogVisible" width="70%" title="出参文档配置Json" append-to-body destroy-on-close>
    <OutParamDocConfig ref="outParamDocConfigRef" :rootInParamType="formData.outParamTypeDictId?.value" :initJsonStr="form.outParamDocConfigJson"></OutParamDocConfig>
    <template #footer>
      <span>
        <PtButton type="primary" @click="outParamDocSubmit" >确认</PtButton>
      </span>
    </template>
  </el-dialog>


  <el-dialog v-model="reactiveData.dictConfig.dialogVisible" width="70%" title="字典配置Json" append-to-body destroy-on-close>
    <DictConfig ref="dictConfigRef" :initJsonStr="form.dictConfigJson"></DictConfig>
    <template #footer>
      <span>
        <PtButton type="primary" @click="dictConfigSubmit" >确认</PtButton>
      </span>
    </template>
  </el-dialog>

  <el-dialog  v-model="reactiveData.pageableAdapterConfig.dialogVisible" width="70%" title="分页信息解析配置Json" @open="pageableAdapterConfigDialogOpen" @closed="pageableAdapterConfigRender=false" append-to-body destroy-on-close>
    <PageableAdapterConfig v-if="pageableAdapterConfigRender" ref="pageableAdapterConfigConfigRef" :initJsonStr="form.pageableAdapterConfigJson"
                        :onSubmit="pageableAdapterConfigSubmit"
                        :buttonsTeleportProps="{disabled: false,to: '#dataqueryDatasourceApipageableAdapterConfigDialogFooter'}">
    </PageableAdapterConfig>
    <template #footer>
      <!--   将表单按钮传送到这里   -->
      <div id="dataqueryDatasourceApipageableAdapterConfigDialogFooter"></div>
    </template>
  </el-dialog>
</template>

<style scoped>

</style>