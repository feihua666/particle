<script setup name="CrawlerExecutionExecutePage" lang="ts">
/**
 * 爬虫执行页面
 */
import {computed, reactive, ref} from 'vue'
import {
  update as crawlerDefinitionHistoryUpdateApi,
  detailForUpdate as detailForUpdateApi, detail
} from "../../../api/definition/admin/crawlerDefinitionHistoryAdminApi"

import {updatePageFormItems} from "../../../components/definition/admin/crawlerDefinitionHistoryManage";
import {execute} from "../../../api/execution/admin/crawlerExecutionAdminApi.ts";
import {executePageFormItems} from "../../../components/execution/admin/crawlerExecutionManage.ts";


// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  crawlerDefinitionHistoryId: {
    type: String
  },
  crawlerDefinitionName: {
    type: String
  },
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    id: props.crawlerDefinitionHistoryId,
    // 这里仅声明，在加载数据后会初始化，在表单中不展示
    crawlerDefinitionId: '',
    // 在表单中仅展示传来的名称
    crawlerDefinitionName: props.crawlerDefinitionName,
    version: 1
  },
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    executePageFormItems
)

const isPublis = ref(false)
// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认执行',
  permission: 'admin:web:crawlerExecution:execute',
})
// 提交按钮
const submitMethod = (form) => {
  let data = {
    crawlerDefinitionId: form.crawlerDefinitionId,
    crawlerDefinitionHistoryId: form.id,
    // 这里写死，手动触发
    triggerTypeDictValue: 'MANUAL',
    // 爬虫运行时选项
    crawlRuntimeOptionsJson: form.crawlRuntimeOptionsJson,
    // 执行参数
    param: JSON.parse(form.param)
  }
  return execute(data)
}
// 初始化加载更新的数据
const dataMethod = () => {
  return detail({id: props.crawlerDefinitionHistoryId})
}
// 成功提示语
const submitMethodSuccess = () => {
  return '执行完成'
}

</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="80"
          :dataMethod="dataMethod"
          :method="submitMethod"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          inline
          :layout="[3,2,1,1]"
          :comps="formComps">
  </PtForm>

</template>


<style scoped>

</style>
