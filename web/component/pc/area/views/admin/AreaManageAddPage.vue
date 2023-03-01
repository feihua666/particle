<script setup name="AreaManageAddPage" lang="ts">
/**
 * 区域管理添加页面
 */
import {reactive ,ref} from 'vue'
import {create as areaCreateApi,list as areaListApi} from "../../api/admin/areaAdminApi"
import LocationGeoMapDialog from '../../compnents/LocationGeoMapDialog.vue'
import {userAddPageFormItems} from "../../compnents/admin/areaManage";

const locationGeoMapDialogRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    userAddPageFormItems({locationGeoMapDialogRef})
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认添加',
  permission: 'admin:web:area:create',
})
// 提交按钮
const submitMethod = () => {
  return areaCreateApi
}
// 成功提示语
const submitMethodSuccess = () => {
  return '添加成功，请刷新数据查看'
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