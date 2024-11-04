<script setup name="NavigationStaticDeployManageDeployPage" lang="ts">
/**
 * 导航网站静态部署管理部署页面
 */
import {reactive ,ref} from 'vue'
import { deploy as navigationStaticDeployApi} from "../../api/admin/navigationStaticDeployAdminApi"
import {deployPageFormItems} from "../../components/admin/navigationStaticDeployManage";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  navigationStaticDeployId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    id: props.navigationStaticDeployId,
  },
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    deployPageFormItems
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认部署',
  permission: 'admin:web:navigationStaticDeploy:deploy',
})
// 提交按钮
const submitMethod = () => {
  return navigationStaticDeployApi
}
// 成功提示语
const submitMethodSuccess = () => {
  return '部署成功，请刷新数据查看'
}

</script>
<template>
  <!-- 部署表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="80"
          :method="submitMethod()"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          inline
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>
