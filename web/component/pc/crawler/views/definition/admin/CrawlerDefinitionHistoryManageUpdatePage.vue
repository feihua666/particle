<script setup name="CrawlerDefinitionHistoryManageUpdatePage" lang="ts">
/**
 * 爬虫定义历史管理更新页面
 */
import {computed, reactive, ref} from 'vue'
import {
  update as crawlerDefinitionHistoryUpdateApi,
  detailForUpdate as detailForUpdateApi
} from "../../../api/definition/admin/crawlerDefinitionHistoryAdminApi"

import {updatePageFormItems} from "../../../components/definition/admin/crawlerDefinitionHistoryManage";


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
    updatePageFormItems
)

const isPublis = ref(false)
// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认修改',
  permission: 'admin:web:crawlerDefinitionHistory:update',
  disabled: computed(() => isPublis.value),
  disabledReason: computed(() => isPublis.value ? '该版本已发布，请勿修改' : undefined)
})
// 提交按钮
const submitMethod = () => {
  return crawlerDefinitionHistoryUpdateApi
}
// 初始化加载更新的数据
const dataMethod = () => {
  return detailForUpdateApi({id: props.crawlerDefinitionHistoryId})
      .then(res => {
        if (res.data.data.isPublish === true) {
          isPublis.value = true
        }
        return res
      })
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
          :submitAttrs="submitAttrs"
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          inline
          :layout="[3,1,1]"
          :comps="formComps">
  </PtForm>

</template>


<style scoped>

</style>
