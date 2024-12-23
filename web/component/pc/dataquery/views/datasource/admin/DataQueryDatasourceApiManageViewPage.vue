<script setup name="AreaManageViewPage" lang="ts">
/**
 * 数据查询数据源接口管理更新页面
 */
import {reactive, ref} from 'vue'
import {detail as detailApi,} from "../../../api/datasource/admin/dataQueryDatasourceApiAdminApi"

import {useUpdatePageFormItems} from "../../../components/datasource/admin/dataQueryDatasourceApiManage";
import DataQueryDatasourceApiFormItemConfigs
  from '../../../components/datasource/admin/DataQueryDatasourceApiFormItemConfigs.vue'
import DataQueryDatasourceApiFormItemBasicConfigs
  from '../../../components/datasource/admin/DataQueryDatasourceApiFormItemBasicConfigs.vue'

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
    version: 1,
    isMaster: true
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
          dataQueryDatasourceApiFormItemBasicConfigsRef,
          addPublished: true
        })
)

// 初始化加载更新的数据
const dataMethod = () => {
  return detailApi({id: props.dataQueryDatasourceApiId})
}


</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="120"
          :dataMethod="dataMethod"
          defaultButtonsShow=""
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
