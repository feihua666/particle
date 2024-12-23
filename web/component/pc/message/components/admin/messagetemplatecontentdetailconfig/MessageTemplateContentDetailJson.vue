<script setup name="MessageTemplateContentDetailJson" lang="ts">
/**
 * 消息模板内容详情配置项
 */
import {ref} from 'vue'
import MessageTemplateContentDetailItem from './MessageTemplateContentDetailItem.vue'
import {getItems} from "../../../../dict/api/front/dictFrontApi";

// 和后端 com.particle.global.notification.notify.NotifyParam.Type 一致
// 对应的字典为 message_notify_type
const types = ref([])
const activeName = ref('')
const messageTemplateContentDetailItemRef = ref(null)

const props = defineProps({
  // 初始化的数据 和 后端 com.particle.message.domain.MessageTemplate.ContentDetailJson 一致
  initContentDetailJsonData: {
    type: Object,
    default: () => ({
      contentDetails: {}
    })
  }
})

const getItemInitForm = (type) => {
  if(props.initContentDetailJsonData.contentDetails){
    return props.initContentDetailJsonData.contentDetails[type]
  }
  return {}
}

getItems({groupCode: 'message_notify_type'}).then(res => {
  types.value = res.data.data
  activeName.value = res.data.data[0].value
})
const getContentDetailJsonData = ()=>{
  let r = {contentDetails: {}}
  for (let i = 0; i < messageTemplateContentDetailItemRef.value.length; i++) {
    // 只获取有填写数据的表单
    let formItem = messageTemplateContentDetailItemRef.value[i]
    if (isFormEmpty(formItem.form)) {
      continue
    }
    r.contentDetails[formItem.type] = formItem.form;
  }

  return r
}
const isFormEmpty = (form)=>{
  if(!form){
    return true
  }
  return (!form.thirdTemplateCode) && (!form.contentTpl)
}
defineExpose({
  getContentDetailJsonData
})
</script>
<template>
  <el-tabs v-model="activeName">
    <template v-for="item in types" :key="item.id">
      <el-tab-pane :label="item.name" :name="item.value">
        <template #label v-if="!isFormEmpty(getItemInitForm(item.value))">
          <el-badge type="success" is-dot class="pt-message-template-content-detail-json-badge">{{ item.name }}</el-badge>
        </template>
        <MessageTemplateContentDetailItem
            ref="messageTemplateContentDetailItemRef"
            :type="item.value"
            :initForm="getItemInitForm(item.value)"
        ></MessageTemplateContentDetailItem>
      </el-tab-pane>
    </template>

  </el-tabs>
</template>


<style scoped>

</style>
<style>
.pt-message-template-content-detail-json-badge .el-badge__content.is-fixed{
 top: .8rem;
}
</style>
