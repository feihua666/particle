<script setup name="LowcodeSegmentTemplateManageRenderTestPage" lang="ts">
/**
 * 低代码片段模板管理渲染页面
 */
import {reactive, ref} from 'vue'
import {renderTest} from "../../../api/generator/admin/lowcodeSegmentTemplateAdminApi"
import {cloneObj} from "../../../../../../global/common/tools/ObjectTools";

import {ElMessage} from 'element-plus'

const alert = (message,type='success')=>{
  ElMessage({
    showClose: true,
    message: message,
    type: type,
    showIcon: true,
    grouping: true
  })
}

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  lowcodeSegmentTemplateId: {
    type: String
  }
})

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    rootSegmentTemplateId: props.lowcodeSegmentTemplateId
  },
  renderResultForm: {
    templateContentResult: '',
    templateNameContentResult: '',
    templateNameContentResultFile: '',
  },
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    [
      {
        field: {
          name: 'global',
          value: JSON.stringify({test: 'testGlobalValue'})
        },
        element: {
          comp: 'PtAceEditor',
          formItemProps: {
            label: '全局渲染数据',
          },
          compProps: {
            clearable: true,
            mode: "ace/mode/json",
            class: 'pt-width-100-pc'
          }
        }
      },
      {
        field: {
          name: 'ext',
          value: JSON.stringify({test: 'testExtValue'})
        },
        element: {
          comp: 'PtAceEditor',
          formItemProps: {
            label: '扩展渲染数据',
          },
          compProps: {
            clearable: true,
            mode: "ace/mode/json",
            class: 'pt-width-100-pc'

          }
        }
      },
      {
        field: {
          name: 'outputFileParentAbsoluteDir',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '输出文件的父目录绝对路径',
            tips: '如：/user/yw/test 或 C:\\yw\\test'
          },
          compProps: {
            clearable: true,

          }
        }
      },
    ]
)
const renderResultFormComps = ref(
    [
      {
        field: {
          name: 'templateNameContentResult'
        },
        element: {
          comp: 'PtAceEditor',
          formItemProps: {
            label: '名称渲染结果文本',
          },
          compProps: {
            clearable: true,
            mode: "ace/mode/json",
            class: 'pt-width-100-pc'
          }
        }
      },
      {
        field: {
          name: 'templateContentResult'
        },
        element: {
          comp: 'PtAceEditor',
          formItemProps: {
            label: '渲染结果文本',
          },
          compProps: {
            clearable: true,
            mode: "ace/mode/json",
            class: 'pt-width-100-pc'

          }
        }
      },
      {
        field: {
          name: 'templateNameContentResultFile'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '名称渲染结果文件句柄',
          },
          compProps: {
            clearable: true,

          }
        }
      },
    ]
)
// 提交按钮属性
const submitAttrs = ref({
  buttonText: '开始渲染',
  permission: 'admin:web:lowcodeSegmentTemplate:renderTest',
})
// 提交按钮
const submitMethod = (form) => {
  let formTemp = cloneObj(form)
  if (formTemp.global) {
    formTemp.global = JSON.parse(formTemp.global)
  }
  if (formTemp.ext) {
    formTemp.ext = JSON.parse(formTemp.ext)
  }
  return renderTest(formTemp)
}
const renderTestResultDialogVisible = ref(false)

// 成功提示语
const submitMethodSuccess = (res) => {
  renderTestResultDialogVisible.value = true

  reactiveData.renderResultForm.templateContentResult = res.data.data.templateContentResult
  reactiveData.renderResultForm.templateNameContentResult = res.data.data.templateNameContentResult
  reactiveData.renderResultForm.templateNameContentResultFile = res.data.data.templateNameContentResultFile

  // 这里没有返回数据，直接实现并且延迟，确保在弹窗后再提示，否则可能提示框被dialog弹窗遮挡
  setTimeout(()=>{alert('渲染成功') },300)
}

</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="100"
          :method="submitMethod"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"

          label-position="top"
          :layout="1"
          :comps="formComps">
  </PtForm>

  <el-dialog v-model="renderTestResultDialogVisible" width="70%" title="查看渲染结果数据" append-to-body destroy-on-close>
    <PtForm :form="reactiveData.renderResultForm"
            labelWidth="100"
            defaultButtonsShow=""
            label-position="top"
            :layout="[2,1]"
            :comps="renderResultFormComps">
    </PtForm>
  </el-dialog>
</template>


<style scoped>

</style>
