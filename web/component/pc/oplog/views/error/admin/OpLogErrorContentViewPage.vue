<script setup name="OpLogErrorContentViewPage" lang="ts">
/**
 * 操作异常日志内容查看页面
 */
import {reactive, ref} from 'vue'
import {
  detailByOpLogErrorId
} from "../../../api/error/admin/opLogErrorContentAdminApi"

import {viewPageFormItems} from "../../../components/error/admin/opLogErrorContentManage";


// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  opLogErrorContentId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    id: props.opLogErrorContentId,
    version: 1
  },
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    viewPageFormItems
)

// 初始化加载更新的数据
const dataMethod = () => {
  return detailByOpLogErrorId({id: props.opLogErrorContentId})
}


</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="80"
          :dataMethod="dataMethod"
          defaultButtonsShow=""
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          :layout="1"
          :comps="formComps">
  </PtForm>

</template>


<style scoped>

</style>