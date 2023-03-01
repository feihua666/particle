<script setup name="FuncManageUpdatePage" lang="ts">
/**
 * 功能菜单管理更新页面
 */
import {reactive, ref} from 'vue'
import {
  detailForUpdate as detailForUpdateApi,
  list as funcListApi,
  update as funcUpdateApi
} from "../../api/admin/funcAdminApi"
import {list as funcGroupListApi} from "../../api/admin/funcGroupAdminApi"
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import {useUpdatePageFormItems} from "../../compnents/admin/funcManage";
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  funcId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    id: props.funcId,
    version: 1
  },
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    useUpdatePageFormItems({formData: reactiveData.formData})
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认修改',
  permission: 'admin:web:func:update'
})
// 提交按钮
const submitMethod = () => {
  return funcUpdateApi
}
// 初始化加载更新的数据
const dataMethod = () => {
  return detailForUpdateApi({id: props.funcId})
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
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          :submitAttrs="submitAttrs"
          inline
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>