<script setup name="DataQueryDatasApiFormItemConfigs" lang="ts">
import {ref,reactive,nextTick} from "vue"
import AdaptMultipleAggregationConfig from './apiconfigs/AdaptMultipleAggregationConfig.vue'
import AdaptCustomScriptConfig from './apiconfigs/AdaptCustomScriptConfig.vue'


const adaptMultipleAggregationConfigRef = ref(null)
const adaptCustomScriptConfigConfigRef = ref(null)

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
  adaptMultipleAggregationConfigJson:{
    dialogVisible: false,
  },

  adaptCustomScriptConfig:{
    dialogVisible: false,
  },
})
const toJsonStr = (json)=>{
  return JSON.stringify(json)
}


// 接口聚合确认提交
const adaptMultipleAggregationConfigSubmit = ()=>{
  if (adaptMultipleAggregationConfigRef.value.getInitJson().aggregationItems.length > 0) {
    props.form.adaptConfigJson = toJsonStr(adaptMultipleAggregationConfigRef.value.getInitJson());
  }else {
    props.form.adaptConfigJson = ''
  }
  reactiveData.adaptMultipleAggregationConfigJson.dialogVisible=false;
}

// 分页信息解析配置确认提交
const adaptCustomScriptConfigSubmit = ()=>{
  props.form.adaptConfigJson = toJsonStr(adaptCustomScriptConfigConfigRef.value.form)
  reactiveData.adaptCustomScriptConfig.dialogVisible=false;
}

// [Vue warn]: Failed to locate Teleport target with selector "#dataqueryDatasourceApiAdaptCustomScriptConfigDialogFooter". Note the target element must exist before the component is mounted - i.e. the target cannot be rendered by the component itself, and ideally should be outside of the entire Vue component tree.
// 如上异常信息，如果不加控制表单中传送的按钮找不到目标位置，先让dialog渲染完成，再渲染内部表单即可
const adaptCustomScriptConfigRender = ref(false)
const adaptCustomScriptConfigDialogOpen = ()=>{
  nextTick(()=>{
    adaptCustomScriptConfigRender.value = true
  })
}
// 暴露方法
defineExpose({
  reactiveData
})
</script>
<template>
  

  <el-dialog v-model="reactiveData.adaptMultipleAggregationConfigJson.dialogVisible" width="70%" title="多接口聚合配置Json" append-to-body destroy-on-close>
    <AdaptMultipleAggregationConfig ref="adaptMultipleAggregationConfigRef" :initJsonStr="form.adaptConfigJson"></AdaptMultipleAggregationConfig>
    <template #footer>
      <span>
        <PtButton type="primary" @click="adaptMultipleAggregationConfigSubmit" >确认</PtButton>
      </span>
    </template>
  </el-dialog>

  

  <el-dialog  v-model="reactiveData.adaptCustomScriptConfig.dialogVisible" width="70%" title="自定义脚本配置Json" @open="adaptCustomScriptConfigDialogOpen" @closed="adaptCustomScriptConfigRender=false" append-to-body destroy-on-close>
    <AdaptCustomScriptConfig v-if="adaptCustomScriptConfigRender" ref="adaptCustomScriptConfigConfigRef" :initJsonStr="form.adaptConfigJson"
                        :onSubmit="adaptCustomScriptConfigSubmit"
                        :buttonsTeleportProps="{disabled: false,to: '#dataqueryDatasourceApiAdaptCustomScriptConfigDialogFooter'}">
    </AdaptCustomScriptConfig>
    <template #footer>
      <!--   将表单按钮传送到这里   -->
      <div id="dataqueryDatasourceApiAdaptCustomScriptConfigDialogFooter"></div>
    </template>
  </el-dialog>
</template>

<style scoped>

</style>