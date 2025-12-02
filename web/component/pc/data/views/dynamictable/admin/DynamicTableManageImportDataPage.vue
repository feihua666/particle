<script setup name="DynamicTableManageImportDataPage" lang="ts">
/**
 * 动态数据表格管理导入数据页面
 */
import {reactive, ref} from 'vue'
import {
  importData,
  update as dynamicTableUpdateApi,
} from "../../../api/dynamictable/admin/dynamicTableAdminApi"

import {importDataFormItems} from "../../../components/dynamictable/admin/dynamicTableManage";


// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  dynamicTableId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    dynamicTableId: props.dynamicTableId,
  },
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    importDataFormItems
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认导入',
  permission: 'admin:web:dynamicTable:uploadImportData',
})
// 提交按钮
const submitMethod = () => {
  return doImportData()
}
// 数据查询
const doImportData = () => {
  let dataForm = new FormData()
  for (let formKey in reactiveData.form) {
    // file 参数在下面添加
    if(formKey == 'file'){
      continue
    }
    dataForm.append(formKey, reactiveData.form[formKey])
  }

  dataForm.append('file', reactiveData.form.file[0].raw)

  return importData(dataForm)
}
// 成功提示语
const submitMethodSuccess = () => {
  return '导入成功'
}

</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="80"
          :method="submitMethod"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          inline
          :layout="[1]"
          :comps="formComps">
  </PtForm>

</template>


<style scoped>

</style>
