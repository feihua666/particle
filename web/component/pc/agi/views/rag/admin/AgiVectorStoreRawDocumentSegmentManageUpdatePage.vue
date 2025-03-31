<script setup name="AgiVectorStoreRawDocumentSegmentManageUpdatePage" lang="ts">
/**
 * 知识存储原始文档片段管理更新页面
 */
import {reactive, ref} from 'vue'
import {
  update as agiVectorStoreRawDocumentSegmentUpdateApi,
  detailForUpdate as detailForUpdateApi
} from "../../../api/rag/admin/agiVectorStoreRawDocumentSegmentAdminApi"

import {useUpdatePageFormItems} from "../../../components/rag/admin/agiVectorStoreRawDocumentSegmentManage";
import {remoteSelectAgiVectorStoreRawDocumentProps} from "../../../components/agiCompItem";


// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  agiVectorStoreRawDocumentSegmentId: {
    type: String
  },
  ...remoteSelectAgiVectorStoreRawDocumentProps
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    id: props.agiVectorStoreRawDocumentSegmentId,
    version: 1
  },
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    useUpdatePageFormItems(props)
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认修改',
  permission: 'admin:web:agiVectorStoreRawDocumentSegment:update',
  methodConfirmText: `确定要修改吗？修改后将删除已嵌入的向量数据，如果需要重新嵌入，请使用嵌入操作重新嵌入`,

})
// 提交按钮
const submitMethod = () => {
  return agiVectorStoreRawDocumentSegmentUpdateApi
}
// 初始化加载更新的数据
const dataMethod = () => {
  return detailForUpdateApi({id: props.agiVectorStoreRawDocumentSegmentId})
}
// 成功提示语
const submitMethodSuccess = () => {
  return '修改成功，请刷新数据查看'
}

</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="80"
          :dataMethod="dataMethod"
          :method="submitMethod()"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          inline
          :layout="1"
          :comps="formComps">
  </PtForm>

</template>


<style scoped>

</style>
