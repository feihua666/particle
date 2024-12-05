<script setup name="AreaManageUpdatePage" lang="ts">
/**
 * 消息模板管理更新页面
 */
import {reactive, ref} from 'vue'
import {
  update as messageTemplateUpdateApi,
  detailForUpdate as detailForUpdateApi,
  list as MessageTemplateListApi
} from "../../api/admin/messageTemplateAdminApi"

import {useUpdatePageFormItems} from "../../components/admin/messageTemplateManage";
import {isEmpty} from "../../../../../global/common/tools/ObjectTools";
import MessageTemplateContentDetailJson from '../../components/admin/messagetemplatecontentdetailconfig/MessageTemplateContentDetailJson.vue'
const contentDetailJsonDialogVisible = ref(false)
const contentDetailJsonRef = ref(null)


// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  messageTemplateId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    id: props.messageTemplateId,
    version: 1
  },
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    useUpdatePageFormItems({contentDetailJsonDialogVisible})
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认修改',
  permission: 'admin:web:messageTemplate:update',
})
// 提交按钮
const submitMethod = () => {
  return messageTemplateUpdateApi
}
// 初始化加载更新的数据
const dataMethod = () => {
  return detailForUpdateApi({id: props.messageTemplateId}).then(res=>{
    let contentDetailJson = res.data.data.contentDetailJson
    if(contentDetailJson){
      contentDetailJsonObjTemp.value = JSON.parse(contentDetailJson)
    }
    return Promise.resolve(res)
  })
}
// 成功提示语
const submitMethodSuccess = () => {
  return '修改成功，请刷新数据查看'
}

const contentDetailJsonObjTemp = ref({})
// 弹窗提交
const contentDetailJsonSubmit = ()=>{
  let contentDetailJson = contentDetailJsonRef.value.getContentDetailJsonData()
  if (isEmpty(contentDetailJson) || isEmpty(contentDetailJson.contentDetails)) {
    reactiveData.form.contentDetailJson = ""
    contentDetailJsonObjTemp.value = {}
    contentDetailJsonDialogVisible.value = false

    return
  }

  reactiveData.form.contentDetailJson = JSON.stringify(contentDetailJson)
  contentDetailJsonObjTemp.value = JSON.parse(JSON.stringify(contentDetailJson))
  contentDetailJsonDialogVisible.value = false
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
          :layout="[3,1,1,1,1,2,2,2,2,1]"
          :comps="formComps">
  </PtForm>
  <el-dialog v-model="contentDetailJsonDialogVisible" width="70%" title="个性化内容详情配置" append-to-body destroy-on-close>

    <MessageTemplateContentDetailJson ref="contentDetailJsonRef" :initContentDetailJsonData="contentDetailJsonObjTemp"></MessageTemplateContentDetailJson>
    <template #footer>
      <span>
        <PtButton type="primary" @click="contentDetailJsonSubmit" >确认</PtButton>
      </span>
    </template>
  </el-dialog>
</template>


<style scoped>

</style>
