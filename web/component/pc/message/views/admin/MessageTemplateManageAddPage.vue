<script setup name="MessageTemplateManageAddPage" lang="ts">
/**
 * 消息模板管理添加页面
 */
import {reactive ,ref} from 'vue'
import {create as messageTemplateCreateApi,list as MessageTemplateListApi} from "../../api/admin/messageTemplateAdminApi"
import { useAddPageFormItems} from "../../compnents/admin/messageTemplateManage";
import {isEmpty} from "../../../../../global/common/tools/ObjectTools";
import MessageTemplateContentDetailJson from '../../compnents/admin/messagetemplatecontentdetailconfig/MessageTemplateContentDetailJson.vue'
const contentDetailJsonDialogVisible = ref(false)
const contentDetailJsonRef = ref(null)


// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    useAddPageFormItems({contentDetailJsonDialogVisible})
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认添加',
  permission: 'admin:web:messageTemplate:create',
})
// 提交按钮
const submitMethod = () => {
  return messageTemplateCreateApi
}
// 成功提示语
const submitMethodSuccess = () => {
  return '添加成功，请刷新数据查看'
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