<script setup name="NavigationSubmitManageUpdateContentPage" lang="ts">
/**
 * 导航提交管理更新内容页面
 * 本页面将siteDataJson单独提取出来，编辑
 */
import {reactive, ref} from 'vue'
import {
  update as navigationSubmitUpdateApi,
  detailForUpdate as detailForUpdateApi
} from "../../api/admin/navigationSubmitAdminApi"

import {updatePageFormItems as navigationSiteUpdatePageFormItems} from "../../components/admin/navigationSiteManage";


// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  navigationSubmitId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
  },
  // 表单数据对象
  formData: {},
  // 提交表单数据
  submitData: {}
})
// 表单项
const formComps = ref(
    navigationSiteUpdatePageFormItems
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认修改',
  permission: 'admin:web:navigationSubmit:update',
})
// 提交按钮
const submitMethod = () => {
  reactiveData.submitData.siteDataJson = JSON.stringify(reactiveData.form)
  return navigationSubmitUpdateApi(reactiveData.submitData)
}
// 初始化加载更新的数据
const dataMethod = () => {
  return detailForUpdateApi({id: props.navigationSubmitId}).then(res => {
    let data = res.data.data
    for (let dataKey in data) {
      reactiveData.submitData[dataKey] = data[dataKey]
    }
    let siteDataJson = data.siteDataJson
    if (siteDataJson) {
      return Promise.resolve(JSON.parse(siteDataJson));
    }else{
      return Promise.resolve({name:data.name, url: data.url,title: data.title});
    }
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
          :layout="[3,3,1,1,1,1]"
          :comps="formComps">
  </PtForm>

</template>


<style scoped>

</style>
