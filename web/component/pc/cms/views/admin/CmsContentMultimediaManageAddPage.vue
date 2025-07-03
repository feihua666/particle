<script setup name="CmsContentMultimediaManageAddPage" lang="ts">
/**
 * 内容多媒体管理添加页面
 */
import {reactive ,ref} from 'vue'
import {create as cmsContentMultimediaCreateApi} from "../../api/admin/cmsContentMultimediaAdminApi"
import {addPageFormItems, useAddPageFormItems} from "../../components/admin/cmsContentMultimediaManage";


// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    useAddPageFormItems({props: {}})
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认添加',
  permission: 'admin:web:cmsContentMultimedia:create',
})
// 提交按钮
const submitMethod = () => {
  return cmsContentMultimediaCreateApi
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
          :layout="[2,1,2,2,2]"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>
