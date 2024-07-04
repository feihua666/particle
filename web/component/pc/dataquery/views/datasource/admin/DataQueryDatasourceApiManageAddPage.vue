<script setup name="DataQueryDatasourceApiManageAddPage" lang="ts">
/**
 * 数据查询数据源接口管理添加页面
 */
import {reactive ,ref} from 'vue'
import {create as DataQueryDatasourceApiCreateApi,list as DataQueryDatasourceApiListApi} from "../../../api/datasource/admin/dataQueryDatasourceApiAdminApi"
import {useAddPageFormItems} from "../../../components/datasource/admin/dataQueryDatasourceApiManage";
import DataQueryDatasourceApiFormItemConfigs from '../../../components/datasource/admin/DataQueryDatasourceApiFormItemConfigs.vue'
import DataQueryDatasourceApiFormItemBasicConfigs from '../../../components/datasource/admin/DataQueryDatasourceApiFormItemBasicConfigs.vue'

const dataQueryDatasourceApiFormItemConfigsRef = ref(null)
const dataQueryDatasourceApiFormItemBasicConfigsRef = ref(null)

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
        {
          form: reactiveData.form,
          formData: reactiveData.formData,
          dataQueryDatasourceApiFormItemConfigsRef,
          dataQueryDatasourceApiFormItemBasicConfigsRef,
          addSingleDirectElements: true
        })
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认添加',
  permission: 'admin:web:dataQueryDatasourceApi:create',
})
// 提交按钮
const submitMethod = () => {
  return DataQueryDatasourceApiCreateApi
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
          :layout="[3,3,1,3,3,3,3,3,3]"
          :comps="formComps">
  </PtForm>
<!-- 用于一些 点击配置按钮弹窗全部放里面统一处理-->
  <DataQueryDatasourceApiFormItemConfigs :form="reactiveData.form" :formData="reactiveData.formData" ref="dataQueryDatasourceApiFormItemConfigsRef"></DataQueryDatasourceApiFormItemConfigs>
<!-- 用于一些 点击配置按钮弹窗全部放里面统一处理 但基础配置，因为不同的数据源，基础配置不同，这里单独抽离出来  -->
  <DataQueryDatasourceApiFormItemBasicConfigs :form="reactiveData.form" :formData="reactiveData.formData" ref="dataQueryDatasourceApiFormItemBasicConfigsRef"></DataQueryDatasourceApiFormItemBasicConfigs>
</template>


<style scoped>

</style>