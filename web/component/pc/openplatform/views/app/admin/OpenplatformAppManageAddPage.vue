<script setup name="OpenplatformAppManageAddPage" lang="ts">
/**
 * 开放平台应用管理添加页面
 */
import {reactive, ref} from 'vue'
import {create as openplatformAppCreateApi} from "../../../api/app/admin/openplatformAppAdminApi"
import {useAddPageFormItems} from "../../../components/app/admin/openplatformAppManage";
import AppAlgorithmSecretConfigs from '../../../components/app/admin/AppAlgorithmSecretConfigs.vue'

const appAlgorithmSecretConfigsRef = ref(null)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
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
    useAddPageFormItems({props,appAlgorithmSecretConfigsRef})
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认添加',
  permission: 'admin:web:openplatformApp:create',
})
// 提交按钮
const submitMethod = () => {
  return openplatformAppCreateApi
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
          labelWidth="120"
          :method="submitMethod()"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          inline
          :comps="formComps">
  </PtForm>

  <AppAlgorithmSecretConfigs :form="reactiveData.form" :formData="reactiveData.formData" ref="appAlgorithmSecretConfigsRef"></AppAlgorithmSecretConfigs>
</template>


<style scoped>

</style>
