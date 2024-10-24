<script setup name="NavigationSiteCategoryRelManageDeleteByNavigationSiteIdPage" lang="ts">
/**
 * 清空导航网站导航网站
 */
import {reactive ,ref} from 'vue'
import {deleteByNavigationSiteId} from "../../api/admin/navigationSiteCategoryRelAdminApi"
// 以下两项为自动生成，不准确，请手动根据实际情况修改
import {useRemoteSelectNavigationSiteCompItem, remoteSelectNavigationSiteProps} from "../../components/navigationSiteCompItem";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  ...remoteSelectNavigationSiteProps,
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    [
      useRemoteSelectNavigationSiteCompItem({props,required: true}),
    ]
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认',
  permission: 'admin:web:navigationSiteCategoryRel:deleteByNavigationSiteId',
})
// 提交按钮
const submitMethod = (form) => {
  return deleteByNavigationSiteId({id: form.navigationSiteId})
}
// 成功提示语
const submitMethodSuccess = () => {
  return '删除成功，请刷新数据查看'
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
          :layout="1"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>
