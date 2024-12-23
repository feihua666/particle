<script setup name="OpenplatformAppOpenapiManageAddPage" lang="ts">
/**
 * 开放平台应用与开放接口配置管理添加页面
 */
import {reactive, ref} from 'vue'
import {create as openplatformAppOpenapiCreateApi} from "../../../api/app/admin/openplatformAppOpenapiAdminApi"
import {useAddPageFormItems} from "../../../components/app/admin/openplatformAppOpenapiManage";


// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    useAddPageFormItems({})
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认添加',
  permission: 'admin:web:openplatformAppOpenapi:create',
  // 由于开放接口缓存，提示一下，参见后端 com.particle.global.openapi.api.AbstractGlobalOpenapi.getOpenapiClient
  methodConfirmText: `添加后大概5分钟后生效，确认添加吗？`,
})
// 提交按钮
const submitMethod = () => {
  return openplatformAppOpenapiCreateApi
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
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>
