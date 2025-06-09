<script setup name="DataQueryDatasourceManageReloadPage" lang="ts">
/**
 * 数据查询数据源重新加载页面
 */
import {reactive, ref} from 'vue'
import {
  reload
} from "../../../api/datasource/admin/dataQueryDatasourceAdminApi"

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

// 表单项
const formComps = ref(
    [
      {
        field: {
          name: 'isRemoveOnly',
          value: true
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否仅移除',
            tips: '建议仅移除就可以，移除后在接口调用时会自动加载，如果选择移除后加载，可能会有问题（如果有接口调用导致重复加载数据源）'
          },
          compProps: {
            clearable: true,
            activeText: '仅移除',
            inactiveText: '移除后加载'
          }
        }
      },
    ]
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认',
  permission: 'admin:web:dataQueryDatasource:reload',
})
// 提交按钮
const submitMethod = () => {
  return reload
}

// 成功提示语
const submitMethodSuccess = () => {
  return '操作成功'
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
</template>


<style scoped>

</style>
