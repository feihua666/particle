<script setup name="LowcodeSegmentTemplateManageAddPage" lang="ts">
/**
 * 低代码片段模板管理添加页面
 */
import {reactive ,ref} from 'vue'
import {create as lowcodeSegmentTemplateCreateApi,list as lowcodeSegmentTemplateListApi} from "../../../api/generator/admin/lowcodeSegmentTemplateAdminApi"
import {addPageFormItems} from "../../../compnents/admin/lowcodeSegmentTemplateManage";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  parentLowcodeSegmentTemplateId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    parentId: props.parentLowcodeSegmentTemplateId
  },
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
  permission: 'admin:web:lowcodeSegmentTemplate:create',
})
// 提交按钮
const submitMethod = () => {
  return lowcodeSegmentTemplateCreateApi
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
          labelWidth="100"
          :method="submitMethod()"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          inline
          :layout="[2,2,1,1,1,1,1,1,1,1]"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>