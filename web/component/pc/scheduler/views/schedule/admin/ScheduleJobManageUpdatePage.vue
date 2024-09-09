<script setup name="ScheduleJobManageUpdatePage" lang="ts">
/**
 * 任务计划任务管理更新页面
 */
import {reactive, ref} from 'vue'
import {updatePageFormItems} from "../../../components/schedule/admin/scheduleJobManage";
import {getJobDetailExt, updateJob} from "../../../api/admin/scheduleJobAdminApi";



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
  name: {
    type: String
  },
  group: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    oldName: props.name,
    oldGroup: props.group,
  },
  // 表单数据对象
  formData: {},
})

// 表单项
const formComps = ref(
    updatePageFormItems
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认修改',
  permission: 'schedule:job:update',
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
  return updateJob(tempForm)
}
// 初始化加载更新的数据
const dataMethod = () => {
  return getJobDetailExt({
    schedulerName: props.schedulerName,
    schedulerInstanceId: props.schedulerInstanceId,
    name: props.name,
    group: props.group,
  }).then(res => {
    let data = res.data.data
    let a = ['httpHeaders', 'httpParams', 'dataMap','beanMethodParams']
    for (let i = 0; i < a.length; i++) {
      let key = a[i]
      if (data[key]) {
        data[key] = JSON.stringify(data[key])
      }else {
        data[key] = null
      }
    }
    return Promise.resolve(res)
  })
}
// 成功提示语
const submitMethodSuccess = () => {
  return '修改成功，请刷新数据查看'
}

</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="80"
          :dataMethod="dataMethod"
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