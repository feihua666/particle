<script setup name="LowCodeModelItemLoadByModelAndDataSourcePage" lang="ts">
/**
 * 根据模型（其实是模型中的表名）和数据源加载模型项页面
 */
import {reactive ,ref} from 'vue'
import {
  list as lowCodeModelListApi,
  loadByModelAndDatasource
} from "../../../api/generator/admin/lowCodeModelAdminApi"
import {list as lowCodeDataSourceListApi} from "../../../api/generator/admin/lowCodeDataSourceAdminApi";
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  lowCodeModelId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    [

      {
        field: {
          name: 'lowcodeModelId',
          value: props.lowCodeModelId
        },
        element: {
          comp: 'PtSelect',
          formItemProps: {
            label: '模型',
            required: true
          },
          compProps: {
            clearable: true,
            dataMethod: lowCodeModelListApi
          }
        }
      },
      {
        field: {
          name: 'lowcodeDatasourceId'
        },
        element: {
          comp: 'PtSelect',
          formItemProps: {
            label: '数据源',
            required: true
          },
          compProps: {
            clearable: true,
            dataMethod: lowCodeDataSourceListApi
          }
        }
      },
    ]
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认装载',
  permission: 'admin:web:lowcodeModel:loadByModelAndDatasource',
})
// 提交按钮
const submitMethod = () => {
  return loadByModelAndDatasource
}
// 成功提示语
const submitMethodSuccess = () => {
  return '装载成功'
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
          :layout="2"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>