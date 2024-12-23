<script setup name="CrmCustomerContactManageAddPage" lang="ts">
/**
 * 客户联系方式管理添加页面
 */
import {reactive, ref} from 'vue'
import {create as crmCustomerContactCreateApi} from "../../../api/customer/admin/crmCustomerContactAdminApi"
import {addPageFormItems} from "../../../components/customer/admin/crmCustomerContactManage";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  crmCustomerId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    crmCustomerId: props.crmCustomerId
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
  permission: 'admin:web:crmCustomerContact:create',
})
// 提交按钮
const submitMethod = () => {
  return crmCustomerContactCreateApi
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
