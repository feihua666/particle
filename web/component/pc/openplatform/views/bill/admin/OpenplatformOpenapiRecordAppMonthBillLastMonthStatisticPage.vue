<script setup name="OpenplatformOpenapiRecordAppMonthBillLastMonthStatisticPage" lang="ts">
/**
 * 统计上月开放平台应用月账单页面
 */
import {reactive, ref} from 'vue'
import {
  monthBillStatisticPageFormItems
} from "../../../components/bill/admin/openplatformOpenapiRecordCustomerMonthBillManage";
import {lastMonthAppBillStatistic} from "../../../api/bill/admin/openplatformOpenapiRecordAppMonthBillAdminApi";


// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    monthBillStatisticPageFormItems
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认统计',
  permission: 'admin:web:openplatformOpenapiRecordAppOpenapiMonthSummary:lastMonthStatistic',
})
// 提交按钮
const submitMethod = () => {
  return lastMonthAppBillStatistic
}
// 成功提示语
const submitMethodSuccess = () => {
  return '统计成功，请刷新数据查看'
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
