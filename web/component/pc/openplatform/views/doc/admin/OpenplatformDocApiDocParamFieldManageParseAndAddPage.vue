<script setup name="OpenplatformDocApiDocParamFieldManageAddPage" lang="ts">
/**
 * 开放接口文档参数字段管理解析并添加页面
 */
import {reactive, ref} from 'vue'
import {parseAndCreate} from "../../../api/doc/admin/openplatformDocApiDocParamFieldAdminApi"
import {parseAndAddPageFormItems} from "../../../components/doc/admin/openplatformDocApiDocParamFieldManage";


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
  permission: 'admin:web:openplatformDocApiDocParamField:create',
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
