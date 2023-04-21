<script setup name="AreaManageUpdatePage" lang="ts">
/**
 * 数据查询数据源管理更新页面
 */
import {computed, reactive, ref} from 'vue'
import {
  update as DataQueryDatasourceUpdateApi,
  detailForUpdate as detailForUpdateApi,
  list as DataQueryDatasourceListApi
} from "../../../api/datasource/admin/DataQueryDatasourceAdminApi"

import {
  useUpdatePageFormItems
} from "../../../compnents/datasource/admin/dataQueryDatasourceManage";
import DataQueryDatasourceManageConfigJsonPage from './DataQueryDatasourceManageConfigJsonPage.vue'


// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  dataQueryDatasourceId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    id: props.dataQueryDatasourceId,
    version: 1
  },
  // 表单数据对象
  formData: {},
})

// 弹窗相关
const configJsonDialogVisible = ref(false)
const typeDictValue = computed(()=>{
  return reactiveData.formData.typeDictId?.value
})
// 表单项
const formComps = ref(
    useUpdatePageFormItems({reactiveData,configJsonDialogVisible})
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认修改',
  permission: 'admin:web:dataQueryDatasource:update',
})
// 提交按钮
const submitMethod = () => {
  return DataQueryDatasourceUpdateApi
}
// 初始化加载更新的数据
const dataMethod = () => {
  return detailForUpdateApi({id: props.dataQueryDatasourceId})
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

  <el-dialog v-model="configJsonDialogVisible" width="70%" title="配置Json" append-to-body destroy-on-close>
    <DataQueryDatasourceManageConfigJsonPage :initData="reactiveData.form.configJson" :success="(result) => {reactiveData.form.configJson = result;configJsonDialogVisible = false;}" :typeDictValue="typeDictValue"></DataQueryDatasourceManageConfigJsonPage>
  </el-dialog>
</template>


<style scoped>

</style>