<script setup name="AgiVectorStoreRawDocumentManageAddPage" lang="ts">
/**
 * 知识存储原始文档管理添加页面
 */
import {reactive ,ref} from 'vue'
import {create as agiVectorStoreRawDocumentCreateApi} from "../../../api/rag/admin/agiVectorStoreRawDocumentAdminApi"
import {addPageFormItems} from "../../../components/rag/admin/agiVectorStoreRawDocumentManage";


// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    addPageFormItems
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认添加',
  permission: 'admin:web:agiVectorStoreRawDocument:create',
})
// 提交按钮
const submitMethod = () => {
  return agiVectorStoreRawDocumentCreateApi
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
          :layout="[1]"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>
