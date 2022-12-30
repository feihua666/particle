<script setup name="RoleUserRelManageDeleteByUserIdPage" lang="ts">
/**
 * 清空用户角色
 */
import {reactive ,ref} from 'vue'
import {deleteByUserId} from "../../api/admin/roleUserRelAdminApi"
import {remoteSelectUserCompItem, remoteSelectUserProps} from "../../../user/compnents/userCompItem";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  ...remoteSelectUserProps,
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
      remoteSelectUserCompItem({props,required: true}),
    ]
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认',
  permission: 'admin:web:roleUserRel:deleteByUserId',
})
// 提交按钮
const submitMethod = (form) => {
  return deleteByUserId({id: form.userId})
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
          inline
          :layout="1"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>