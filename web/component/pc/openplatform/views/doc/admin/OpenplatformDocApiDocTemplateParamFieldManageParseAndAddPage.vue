<script setup name="OpenplatformDocApiDocTemplateParamFieldManageAddPage" lang="ts">
/**
 * 开放接口文档模板参数字段管理添加页面
 */
import {reactive, ref} from 'vue'
import {parseAndCreate} from "../../../api/doc/admin/openplatformDocApiDocTemplateParamFieldAdminApi"
import {parseAndAddPageFormItems} from "../../../components/doc/admin/openplatformDocApiDocTemplateParamFieldManage";


// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    parseAndAddPageFormItems
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认解析并添加',
  permission: 'admin:web:openplatformDocApiDocTemplateParamField:create',
})
// 提交按钮
const submitMethod = () => {
  return parseAndCreate
}
// 成功提示语
const submitMethodSuccess = () => {
  return '添加成功，请刷新数据查看'
}

</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="80"
          :method="submitMethod()"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          inline
          :layout="[[8,8],1]"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>
