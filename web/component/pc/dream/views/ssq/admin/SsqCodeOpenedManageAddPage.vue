<script setup name="SsqCodeOpenedManageAddPage" lang="ts">
/**
 * 双色球开奖管理添加页面
 */
import {reactive ,ref} from 'vue'
import {create as ssqCodeOpenedCreateApi} from "../../../api/ssq/admin/ssqCodeOpenedAdminApi"
import {addPageFormItems} from "../../../compnents/ssq/admin/ssqCodeOpenedManage";


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
  permission: 'admin:web:ssqCodeOpened:create',
})
// 提交按钮
const submitMethod = () => {
  return ssqCodeOpenedCreateApi
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