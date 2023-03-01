<script setup name="AreaManageUpdatePage" lang="ts">
/**
 * 区域管理更新页面
 */
import {reactive, ref} from 'vue'
import {
  update as areaUpdateApi,
  detailForUpdate as detailForUpdateApi,
  list as areaListApi
} from "../../api/admin/areaAdminApi"

import LocationGeoMapDialog from '../../compnents/LocationGeoMapDialog.vue'
import {useUpdatePageFormItems} from "../../compnents/admin/areaManage";

const locationGeoMapDialogRef = ref(null)

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  areaId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    id: props.areaId,
    version: 1
  },
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    useUpdatePageFormItems({locationGeoMapDialogRef})
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认修改',
  permission: 'admin:web:area:update',
})
// 提交按钮
const submitMethod = () => {
  return areaUpdateApi
}
// 初始化加载更新的数据
const dataMethod = () => {
  return detailForUpdateApi({id: props.areaId})
}
// 成功提示语
const submitMethodSuccess = () => {
  return '修改成功，请刷新数据查看'
}

const dialogSubmit = ({str,longitude,latitude})=>{
  reactiveData.form.longitude = longitude + ''
  reactiveData.form.latitude = latitude + ''
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
  <LocationGeoMapDialog ref="locationGeoMapDialogRef" :submit="dialogSubmit" :point="[reactiveData.form.longitude,reactiveData.form.latitude]"></LocationGeoMapDialog>

</template>


<style scoped>

</style>