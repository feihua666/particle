<script setup name="FuncApplicationFuncRelManageDeleteByFuncIdPage" lang="ts">
/**
 * 清空功能下对应的所以应用
 */
import {reactive, ref} from 'vue'
import {remoteSelectFuncProps, useRemoteSelectFuncCompItem} from "../../components/funcCompItem";
import {deleteByFuncId} from "../../api/admin/funcApplicationFuncRelAdminApi";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  ...remoteSelectFuncProps,
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
      useRemoteSelectFuncCompItem({props,required: true}),
    ]
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认',
  permission: 'admin:web:funcApplicationFuncRel:deleteByFuncId',
})
// 提交按钮
const submitMethod = (form) => {
  return deleteByFuncId({id: form.funcId})
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
