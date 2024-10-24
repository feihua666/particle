<script setup name="NavigationSiteCategoryRelManageDeleteByNavigationCategoryIdPage" lang="ts">
/**
 * 清空导航分类导航分类
 */
import {reactive ,ref} from 'vue'
import {deleteByNavigationCategoryId} from "../../api/admin/navigationSiteCategoryRelAdminApi"
// 以下两项为自动生成，不准确，请手动根据实际情况修改
import {
  useCascaderNavigationCategoryCompItem
} from "../../components/navigationCategoryCompItem";
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  navigationCategoryId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {navigationCategoryId: props.navigationCategoryId},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    [
      useCascaderNavigationCategoryCompItem({fieldName: 'navigationCategoryId',label: '导航分类',required: true}),
    ]
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认',
  permission: 'admin:web:navigationSiteCategoryRel:deleteByNavigationCategoryId',
})
// 提交按钮
const submitMethod = (form) => {
  return deleteByNavigationCategoryId({id: form.navigationCategoryId})
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
