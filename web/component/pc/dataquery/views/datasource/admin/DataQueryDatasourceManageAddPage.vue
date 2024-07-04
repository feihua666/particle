<script setup name="DataQueryDatasourceManageAddPage" lang="ts">
/**
 * 数据查询数据源管理添加页面
 */
import {computed, reactive, ref} from 'vue'
import {create as DataQueryDatasourceCreateApi,list as DataQueryDatasourceListApi} from "../../../api/datasource/admin/dataQueryDatasourceAdminApi"
import { useAddPageFormItems} from "../../../components/datasource/admin/dataQueryDatasourceManage";
import DataQueryDatasourceManageConfigJsonPage from './DataQueryDatasourceManageConfigJsonPage.vue'




// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
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
    useAddPageFormItems({reactiveData,configJsonDialogVisible})
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认添加',
  permission: 'admin:web:dataQueryDatasource:create',
})
// 提交按钮
const submitMethod = () => {
  return DataQueryDatasourceCreateApi
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