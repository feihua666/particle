<script setup name="AreaManageUpdatePage" lang="ts">
/**
 * 数据查询数据源接口管理更新页面
 */
import {reactive, ref} from 'vue'
import {
  update as DataQueryDatasourceApiUpdateApi,
  detailForUpdate as detailForUpdateApi,
} from "../../../api/datasource/admin/DataQueryDatasourceApiAdminApi"

import {useUpdatePageFormItems} from "../../../compnents/datasource/admin/dataQueryDatasourceApiManage";
import DataQueryDatasourceApiFormItemConfigs from '../../../compnents/datasource/admin/DataQueryDatasourceApiFormItemConfigs.vue'
import DataQueryDatasourceApiFormItemBasicConfigs from '../../../compnents/datasource/admin/DataQueryDatasourceApiFormItemBasicConfigs.vue'

const dataQueryDatasourceApiFormItemConfigsRef = ref(null)
const dataQueryDatasourceApiFormItemBasicConfigsRef = ref(null)

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  dataQueryDatasourceApiId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    id: props.dataQueryDatasourceApiId,
    version: 1
  },
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    useUpdatePageFormItems(
        {
          form: reactiveData.form,
          formData: reactiveData.formData,
          dataQueryDatasourceApiFormItemConfigsRef,
          dataQueryDatasourceApiFormItemBasicConfigsRef
        })
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认修改',
  permission: 'admin:web:dataQueryDatasourceApi:update',
})
// 提交按钮
const submitMethod = () => {
  return DataQueryDatasourceApiUpdateApi
}
// 初始化加载更新的数据
const dataMethod = () => {
  return detailForUpdateApi({id: props.dataQueryDatasourceApiId})
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
          labelWidth="120"
          :dataMethod="dataMethod"
          :method="submitMethod()"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          inline
          :layout="[3,3,1,3,3,3,3,3]"
          :comps="formComps">
  </PtForm>
  <DataQueryDatasourceApiFormItemConfigs :form="reactiveData.form" :formData="reactiveData.formData" ref="dataQueryDatasourceApiFormItemConfigsRef"></DataQueryDatasourceApiFormItemConfigs>
  <!--基础配置，因为不同的数据源，基础配置不同，这里单独抽离出来  -->
  <DataQueryDatasourceApiFormItemBasicConfigs :form="reactiveData.form" :formData="reactiveData.formData" ref="dataQueryDatasourceApiFormItemBasicConfigsRef"></DataQueryDatasourceApiFormItemBasicConfigs>
</template>


<style scoped>

</style>