<script setup name="DataQueryDataApiManageAddPage" lang="ts">
/**
 * 数据查询数据接口管理添加页面
 */
import {reactive ,ref} from 'vue'
import {create as DataQueryDataApiCreateApi} from "../../../api/dataapi/admin/DataQueryDataApiAdminApi"
import {useAddPageFormItems} from "../../../compnents/dataapi/admin/dataQueryDataApiManage";
import DataQueryDatasourceApiFormItemConfigs from '../../../compnents/datasource/admin/DataQueryDatasourceApiFormItemConfigs.vue'
import DataQueryDatasApiFormItemConfigs from '../../../compnents/dataapi/admin/DataQueryDatasApiFormItemConfigs.vue'
 const dataQueryDatasourceApiFormItemConfigsRef = ref(null)
 const dataQueryDataApiFormItemConfigsRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    useAddPageFormItems(
        {form: reactiveData.form,
          formData: reactiveData.formData,
          dataQueryDatasourceApiFormItemConfigsRef,
          dataQueryDataApiFormItemConfigsRef
        })
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认添加',
  permission: 'admin:web:DataQueryDataApi:create',
})
// 提交按钮
const submitMethod = () => {
  return DataQueryDataApiCreateApi
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
          labelWidth="120"
          :method="submitMethod()"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          inline
          :layout="[[8,8],3,[8],3,[8,8],3,[8]]"
          :comps="formComps">
  </PtForm>
  <DataQueryDatasourceApiFormItemConfigs :form="reactiveData.form" :formData="reactiveData.formData" ref="dataQueryDatasourceApiFormItemConfigsRef"></DataQueryDatasourceApiFormItemConfigs>
  <DataQueryDatasApiFormItemConfigs :form="reactiveData.form" :formData="reactiveData.formData" ref="dataQueryDataApiFormItemConfigsRef"></DataQueryDatasApiFormItemConfigs>

</template>


<style scoped>

</style>