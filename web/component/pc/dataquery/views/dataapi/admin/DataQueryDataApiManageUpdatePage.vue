<script setup name="DataQueryDataApiManageUpdatePage" lang="ts">
/**
 * 数据查询数据接口管理更新页面
 */
import {reactive, ref} from 'vue'
import {
  detailForUpdate as detailForUpdateApi,
  update as DataQueryDataApiUpdateApi
} from "../../../api/dataapi/admin/dataQueryDataApiAdminApi"

import {useUpdatePageFormItems} from "../../../components/dataapi/admin/dataQueryDataApiManage";
import DataQueryDatasourceApiFormItemConfigs
  from '../../../components/datasource/admin/DataQueryDatasourceApiFormItemConfigs.vue'
import DataQueryDatasApiFormItemConfigs from '../../../components/dataapi/admin/DataQueryDatasApiFormItemConfigs.vue'

const dataQueryDatasourceApiFormItemConfigsRef = ref(null)
const dataQueryDataApiFormItemConfigsRef = ref(null)

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  dataQueryDataApiId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    id: props.dataQueryDataApiId,
    version: 1,
    isMaster: true
  },
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    useUpdatePageFormItems(
        {form: reactiveData.form,
          formData: reactiveData.formData,
          dataQueryDatasourceApiFormItemConfigsRef,
          dataQueryDataApiFormItemConfigsRef,
          addPublished: true})
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认修改',
  permission: 'admin:web:dataQueryDataApi:update',
})
// 提交按钮
const submitMethod = () => {
  return DataQueryDataApiUpdateApi
}
// 初始化加载更新的数据
const dataMethod = () => {
  return detailForUpdateApi({id: props.dataQueryDataApiId})
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
          :method="submitMethod()"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          inline
          :comps="formComps">
  </PtForm>
  <DataQueryDatasourceApiFormItemConfigs :form="reactiveData.form" :formData="reactiveData.formData" ref="dataQueryDatasourceApiFormItemConfigsRef"></DataQueryDatasourceApiFormItemConfigs>
  <DataQueryDatasApiFormItemConfigs :form="reactiveData.form" :formData="reactiveData.formData" ref="dataQueryDataApiFormItemConfigsRef"></DataQueryDatasApiFormItemConfigs>

</template>


<style scoped>

</style>
