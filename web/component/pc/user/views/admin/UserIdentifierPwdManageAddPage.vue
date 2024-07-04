<script setup name="UserIdentifierPwdManageAddPage" lang="ts">
/**
 * 用户登录标识密码管理添加页面
 */
import {reactive, ref} from 'vue'
import {create as userIdentifierPwdCreateApi} from "../../api/admin/userIdentifierPwdAdminApi"
import {
  remoteSelectUserIdentifierProps,
  remoteSelectUserProps
} from "../../components/userCompItem";
import {useAddPageFormItems} from "../../components/admin/userIdentifierPwdManage";
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  ...remoteSelectUserProps,
  ...remoteSelectUserIdentifierProps
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
    useAddPageFormItems({props})
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认添加',
  permission: 'admin:web:userIdentifierPwd:create',
})
// 提交按钮
const submitMethod = () => {
  return userIdentifierPwdCreateApi
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
          :layout="[[8,8]]"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>