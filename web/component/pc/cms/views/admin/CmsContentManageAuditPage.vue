<script setup name="CmsContentManageUpdatePage" lang="ts">
/**
 * 内容管理更新页面
 */
import {reactive, ref} from 'vue'
import {
  audit as cmsContentAuditApi,
} from "../../api/admin/cmsContentAdminApi"

import {useAuditPageFormItems} from "../../components/admin/cmsContentManage";


// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  cmsContentId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    id: props.cmsContentId,
    version: 1
  },
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    useAuditPageFormItems()
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认修改',
  permission: 'admin:web:cmsContent:audit',
})
// 提交按钮
const submitMethod = () => {
  return cmsContentAuditApi
}
// 成功提示语
const submitMethodSuccess = () => {
  return '审核成功，请刷新数据查看'
}

</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="120"
          :method="submitMethod()"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          inline
          :layout="[1,1]"
          :comps="formComps">
  </PtForm>

</template>


<style scoped>

</style>
