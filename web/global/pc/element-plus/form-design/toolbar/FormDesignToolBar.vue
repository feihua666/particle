<script setup name="FormDesignToolBar" lang="ts">
/**
 * 工具
 */
import {inject, ref} from "vue";
import PtButton from '../../Button.vue'
import PtButtonGroup from '../../ButtonGroup.vue'
import PtAceEditor from '../../../common/aceEditor/AceEditor.vue'
import {clone} from "../../../../common/tools/ObjectTools";
import {generatePtForm} from "./generate/generatePtForm";
const formDesignData = inject('formDesignData')
const formDesignHistoryData = inject('formDesignHistoryData')
const formDesignDataControl = inject('formDesignDataControl')

// 清空
const clear = () => {
  formDesignData.value.formDesignItems.splice(0,formDesignData.value.formDesignItems.length)
}

const exportButtons = ref([
  {
    txt: '导出json',
    icon: 'Share',
    text: true,
    type: 'primary',
    method: ()=>{exportJson()}
  },
  {
    txt: '导出PtForm代码',
    icon: 'Share',
    text: true,
    type: 'primary',
    position: 'more',
    method: ()=>{exportPtForm()}
  }
])
const previewButtons = ref([
  {
    txt: '预览PtForm',
    icon: 'View',
    text: true,
    type: 'primary',
    method: ()=>{previewPtForm()}
  },
])
/******************导出 json 开始******************************/
const exportJsonDialogVisible = ref(false)
const formDesignItemsStr = ref('')
// 导出JSON
const exportJson = () => {
  exportJsonDialogVisible.value = true
  formDesignItemsStr.value = JSON.stringify(formDesignData.value,null, '  ')
}
/******************导出 json 结束*******************************************************/


/******************导出 PtForm 开始******************************/
const exportPtFormDialogVisible = ref(false)
const ptFormStr = ref('')
// 导出JSON
const exportPtForm = () => {
  exportPtFormDialogVisible.value = true
  initPreviewForm()
  ptFormStr.value = generatePtForm(previewForm.value.comps,previewForm.value.props)
}
/******************导出 PtForm 结束*******************************************************/

const initPreviewForm = ()=>{
  previewForm.value.form = clone(formDesignData.value.formDesignForm)
  previewForm.value.props = (formDesignData.value.formDesignFormSettingFormProps)
  let comps = []
  formDesignData.value.formDesignItems.forEach((item)=>{
    comps.push({
      field: item.comps.field,
      element:{
        comp: item.comps.comp,
        compProps: item.comps.compProps,
        formItemProps: item.comps.formItemProps,
      }
    })
  })
  previewForm.value.comps = comps
}

/******************预览 开始******************************/
const previewDialogVisible = ref(false)
const previewForm = ref({
  form: {},
  rules: [],
  comps: [],
  props: {},
  key: ''
})
const previewPtForm = ()=>{
  previewDialogVisible.value = true
  initPreviewForm()
  previewForm.value.key = new Date().getTime()

}

const previewFormDataDialogVisible = ref(false)
const previewFormDataStr = ref('')
const previewPtFormData = () => {
  previewFormDataDialogVisible.value = true
  previewFormDataStr.value = JSON.stringify(previewForm.value.form,null, '  ')
}
/******************预览 结束*******************************************************/

/******************查看操作历史数据 开始******************************/
const historyDataJsonDialogVisible = ref(false)
const historyDataJsonStr = ref('')
const viewHistoryDataJsonData = () => {
  historyDataJsonDialogVisible.value = true
  historyDataJsonStr.value = JSON.stringify(formDesignHistoryData.value,null, '  ')
}
/******************查看操作历史数据 结束*******************************************************/
</script>
<template>
  <div class="pt-form-design-toolbar pt-height-100-pc pt-flex-align-between pt-flex-align-cross-center" v-bind="$attrs">
    <div class="pt-form-design-toolbar-left">
      <el-button :disabled="!(formDesignHistoryData.index >=1)" icon="ArrowLeft" title="撤销"  @click="formDesignDataControl.undoHistoryStep"></el-button>
      <el-button :disabled="!(formDesignHistoryData.steps.length - formDesignHistoryData.index > 1)" icon="ArrowRight" title="重做"  @click="formDesignDataControl.redoHistoryStep"></el-button>
      <el-button icon="View" title="查看操作历史数据" text @click="viewHistoryDataJsonData">查看操作历史数据</el-button>
    </div>
    <div class="pt-form-design-toolbar-right">
      <PtButton type="danger" icon="Delete" title="清空" methodConfirmText="确认要清空吗？" text @click="clear">清空</PtButton>
      <PtButtonGroup :options="previewButtons" :dropdownTriggerButtonOptions="{text: true}"></PtButtonGroup>
      <PtButtonGroup :options="exportButtons" :dropdownTriggerButtonOptions="{text: true}"></PtButtonGroup>
    </div>
  </div>

<!-- 查看操作历史数据 -->
  <el-dialog v-model="historyDataJsonDialogVisible" title="查看操作历史数据" append-to-body destroy-on-close>
    <PtAceEditor  :readonly="true" v-model="historyDataJsonStr" mode="ace/mode/json"></PtAceEditor>
  </el-dialog>

  <!-- 预览相关 -->
  <el-dialog v-model="previewDialogVisible" title="预览PtForm,布局可通过表单设置修改" append-to-body destroy-on-close width="70%">
    <PtForm :key="previewForm.key"
            :form="previewForm.form"
            :rules="previewForm.rules"
            :comps="previewForm.comps"
            v-bind="previewForm.props"
            defaultButtonsShow="">
    </PtForm>
    <template #footer>
      <el-button icon="View" text title="PtForm表单JSON数据"  @click="previewPtFormData">PtForm表单JSON数据</el-button>
    </template>
  </el-dialog>
  <el-dialog v-model="previewFormDataDialogVisible" title="PtForm表单JSON数据" append-to-body destroy-on-close>
    <PtAceEditor  :readonly="true" v-model="previewFormDataStr" mode="ace/mode/json"></PtAceEditor>
  </el-dialog>


  <!-- 导出相关 -->
  <el-dialog v-model="exportJsonDialogVisible" title="导出JSON，该数据为表单设置器全局数据" append-to-body destroy-on-close>
    <PtAceEditor  :readonly="true" v-model="formDesignItemsStr" mode="ace/mode/json"></PtAceEditor>
  </el-dialog>
  <el-dialog v-model="exportPtFormDialogVisible" title="导出PtForm代码" append-to-body destroy-on-close>
    <PtAceEditor  :readonly="true" v-model="ptFormStr" mode="ace/mode/html"></PtAceEditor>
  </el-dialog>

</template>


<style scoped>

</style>