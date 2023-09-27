<script setup name="ReportSegmentTemplateManageCopyPage" lang="ts">
/**
 * 报告片段模板管理复制页面
 */
import {reactive ,ref} from 'vue'
import {
  copy as reportSegmentTemplateCopyApi,
  list as reportSegmentTemplateListApi
} from "../../../api/template/admin/reportSegmentTemplateAdminApi.ts"

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  reportSegmentTemplateId: {
    type: String
  },
  parentReportSegmentTemplateId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {id: props.reportSegmentTemplateId},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    [
      {
        field: {
          name: 'parentId',
          value: props.parentReportSegmentTemplateId
        },
        element: {
          comp: 'PtCascader',
          formItemProps: {
            label: '父级',
            tips: '复制的节点将放到该节点下'
          },
          compProps: {
            dataMethod: reportSegmentTemplateListApi,
            dataMethodResultHandleConvertToTree: true,
          }
        }
      },
      {
        field: {
          name: 'isIncludeAllChildren',
          value: true
        },
        element: {
          comp: 'el-checkbox',
          formItemProps: {
            label: '包括孙节点',
            labelTips: '仅选择父级有效'
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'keyWordReplace'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '替换文本',
            tips: '如：text=newText,text1=newText1。等号右边替换左边',
            displayBlock: true
          },
          compProps: {
            clearable: true,
            placeholder: '如：text=newText,text1=newText1'
          }
        }
      },
      

    ]
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认复制',
  permission: 'admin:web:reportSegmentTemplate:copy',
})
// 提交按钮
const submitMethod = () => {
  return reportSegmentTemplateCopyApi
}
// 成功提示语
const submitMethodSuccess = () => {
  return '复制成功，请刷新数据查看'
}

</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="100"
          :method="submitMethod()"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          inline
          :layout="1"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>