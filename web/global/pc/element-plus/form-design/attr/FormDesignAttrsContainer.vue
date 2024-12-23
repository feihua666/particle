<script setup name="FormDesignAttrs" lang="ts">
/**
 * 属性设置
 */
import {inject, onMounted, onUnmounted, reactive, ref, watch} from 'vue'
import type {TabsPaneContext} from 'element-plus'
import PtForm from '../../Form.vue'
import {
  formDesignAttrsFormItemCompsAttrs,
  formDesignAttrsFormItemFormChange,
  formDesignAttrsFormItemInitForm
} from "./formDesignAttrsFormItemCompsAttrs";
import {formDesignAttrsCompFormChange, formDesignAttrsCompInitForm} from "../comp/formDesignCompsAttrs.ts";
import {formDesignFormSettingAttrs} from "./formDesignFormSettingAttrs";
import {formChange, initForm} from "./FormDesignAttrs";

// 当前选中的
const formDesignData = inject('formDesignData')
const currentFormDesignItemData = inject('currentFormDesignItemData')
const formDesignDataControl = inject('formDesignDataControl')

const tabsActiveName = ref('compsAttrs')
const attrsActiveName = ref(['compAttr','formItemAttr'])
const formSettingAttrsActiveName = ref(['formSettingBasicAttr'])

onMounted(()=>{
  formDesignDataControl.bindListener('onAddNew',formDesignAttrsFormItemInitForm)
  formDesignDataControl.bindListener('onAddNew',formDesignAttrsCompInitForm)
  formDesignDataControl.bindListener('onAddNew',formDesignDataControl.formDesignItemInitFormKey)
})
onUnmounted(()=>{
  formDesignDataControl.removeListener('onAddNew',formDesignAttrsFormItemInitForm)
  formDesignDataControl.removeListener('onAddNew',formDesignAttrsCompInitForm)
  formDesignDataControl.removeListener('onAddNew',formDesignDataControl.formDesignItemInitFormKey)
})

const handleClick = (tab: TabsPaneContext, event: Event) => {
}
// 在组件属性表单变化时，联动组件内容
watch(()=> currentFormDesignItemData.value?.attrs.compForm,(compForm,oldCompForm)=>{
  if (compForm) {
    // 属性改变，联动组件实际属性
    formDesignAttrsCompFormChange(currentFormDesignItemData.value,formDesignData.value.formDesignForm)
    formDesignDataControl.addNewHistoryStep()
  }
},{
  deep: true
})
// 在表单项表单变化时，联动组件内容
watch(()=> currentFormDesignItemData.value?.attrs.formItemForm,(formItemForm)=>{
  if (formItemForm) {
    // 属性改变，联动表单项组件实际属性
    formDesignAttrsFormItemFormChange(currentFormDesignItemData.value)
    formDesignDataControl.addNewHistoryStep()
  }
},{
  deep: true
})
// 表单属性

// 属性
const reactiveData = reactive({
  formDesignMainFormProps: formDesignData.value.formDesignFormSettingFormProps
})
initForm(formDesignData.value.formDesignFormSettingForm,reactiveData.formDesignMainFormProps,formDesignData.value.formDesignFormSettingAttrs,'formSettingPropsHandler',{})
// 表单变化联动属性变化
watch(()=> formDesignData.value.formDesignFormSettingForm,(newForm)=>{
  formChange(newForm,reactiveData.formDesignMainFormProps,formDesignData.value.formDesignFormSettingAttrs,'formSettingPropsHandler',{})
  formDesignDataControl.addNewHistoryStep()
},{
  deep: true
})
</script>
<template>
  <el-tabs v-model="tabsActiveName" class="pt-form-design-attrs-tabs" @tab-click="handleClick">
    <el-tab-pane name="compsAttrs">
      <template #label>
        <span class="pt-form-design-attrs-tabs-label">
          <el-icon><Operation /></el-icon>
          <span>组件属性</span>
        </span>
      </template>
      <el-collapse v-model="attrsActiveName">
        <el-collapse-item title="组件属性" name="compAttr">
          <!--   组件属性设置表单   -->
          <PtForm v-if="currentFormDesignItemData"
                  defaultButtonsShow=""
                  :key="currentFormDesignItemData.uniqueId"
                  :layout="1"
                  labelWidth="100"
                  label-position="left"
                  :form="currentFormDesignItemData.attrs.compForm"
                  :comps="currentFormDesignItemData.attrs.compAttrs">

          </PtForm>
        </el-collapse-item>
        <el-collapse-item title="表单项属性" name="formItemAttr">
          <!--   表单项属性设置表单   -->
          <PtForm v-if="currentFormDesignItemData"
                  defaultButtonsShow=""
                  :key="currentFormDesignItemData.uniqueId + 'formItem'"
                  :layout="1"
                  labelWidth="100"
                  label-position="left"

            :form="currentFormDesignItemData.attrs.formItemForm"
            :comps="currentFormDesignItemData.attrs.formItemAttrs || formDesignAttrsFormItemCompsAttrs">

          </PtForm>
        </el-collapse-item>
      </el-collapse>

    </el-tab-pane>
    <el-tab-pane name="formAttrs">
      <template #label>
        <span class="pt-form-design-attrs-tabs-label">
          <el-icon><Operation /></el-icon>
          <span>表单属性</span>
        </span>
      </template>
      <el-collapse v-model="formSettingAttrsActiveName">
        <el-collapse-item title="表单基本属性" name="formSettingBasicAttr">
          <!--   表单属性设置表单   -->
          <PtForm
              defaultButtonsShow=""
              :layout="1"
              labelWidth="100"
              label-position="left"
              :form="formDesignData.formDesignFormSettingForm"
              :comps="formDesignData.formDesignFormSettingAttrs || formDesignFormSettingAttrs">
          </PtForm>
        </el-collapse-item>
      </el-collapse>

    </el-tab-pane>
  </el-tabs>
</template>

<style scoped>
.pt-form-design-attrs-tabs-label .el-icon {
  vertical-align: middle;
}
.pt-form-design-attrs-tabs-label span {
  vertical-align: middle;
  margin-left: 4px;
}

</style>
<style>
.pt-form-design-attrs-tabs .el-tabs__header {
  margin: 0;
}
</style>
