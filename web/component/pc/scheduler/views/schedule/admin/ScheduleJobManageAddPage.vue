<script setup name="ScheduleJobManageAddPage" lang="ts">
/**
 * 任务计划任务管理添加页面
 */
import {reactive ,ref} from 'vue'
import {addPageFormItems} from "../../../components/schedule/admin/scheduleJobManage";
import {addJob} from "../../../api/admin/scheduleJobAdminApi";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  schedulerName: {
    type: String
  },
  schedulerInstanceId: {
    type: String
  },
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    schedulerName: props.schedulerName,
    schedulerInstanceId: props.schedulerInstanceId
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
  permission: 'schedule:job:add',
})
// 提交按钮
const submitMethod = (form) => {
  let tempForm = {}
  for (let formKey in form) {
    tempForm[formKey] = form[formKey]
  }
  let a = ['httpHeaders', 'httpParams', 'dataMap','beanMethodParams']
  for (let i = 0; i < a.length; i++) {
    let key = a[i]
    if (tempForm[key]) {
      tempForm[key] = JSON.parse(tempForm[key])
    }else {
      tempForm[key] = null
    }
  }
  return addJob(tempForm)
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
          :method="submitMethod"
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