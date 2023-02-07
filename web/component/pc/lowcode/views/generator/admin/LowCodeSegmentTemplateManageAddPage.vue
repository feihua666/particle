<script setup name="LowCodeSegmentTemplateManageAddPage" lang="ts">
/**
 * 低代码片段模板管理添加页面
 */
import {reactive ,ref} from 'vue'
import {create as lowCodeSegmentTemplateCreateApi,list as lowCodeSegmentTemplateListApi} from "../../../api/generator/admin/lowCodeSegmentTemplateAdminApi"
import {radioGroupData} from "../../../compnents/lowCodeSementTemplateComps";


// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    [
      {
        field: {
          name: 'code'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '编码',
            required: true
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'name'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '模板名称',
            required: true
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'nameTemplate'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '名称模板',
            tips: '仅支持 enjoy 语法。模板变量仅支持全局和子级及子级引用的输出变量',
            displayBlock: true
          },
          compProps: {
            type: 'textarea',
            clearable: true,
            rows: 5
          }
        }
      },
      {
        field: {
          name: 'contentTemplate'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '内容模板',
            tips: '仅支持 enjoy 语法。模板变量仅支持全局和子级及子级引用的输出变量',
            displayBlock: true
          },
          compProps: {
            type: 'textarea',
            clearable: true,
            rows: 10
          }
        }
      },
      {
        field: {
          name: 'referenceSegmentTemplateId'
        },
        element: {
          comp: 'PtCascader',
          formItemProps: {
            label: '引用模板',
          },
          compProps: {
            dataMethod: lowCodeSegmentTemplateListApi,
            dataMethodResultHandleConvertToTree: true,
          }
        }
      },
      {
        field: {
          name: 'outputType'
        },
        element: {
          comp: 'PtSelect',
          formItemProps: {
            label: '输出类型',
            required: true
          },
          compProps: {
            options: radioGroupData
          }
        }
      },
      {
        field: {
          name: 'outputVariable'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '输出变量名',
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'shareVariables'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '共享变量名',
            tips: '仅子级可访问，并可设置值，类型为List&lt;String&gt;'
          },
          compProps: {
            clearable: true,
            placeholder: '多个以逗号分隔'
          }
        }
      },
      {
        field: {
          name: 'parentId'
        },
        element: {
          comp: 'PtCascader',
          formItemProps: {
            label: '父级',
          },
          compProps: {
            dataMethod: lowCodeSegmentTemplateListApi,
            dataMethodResultHandleConvertToTree: true,
          }
        }
      },
      {
        field: {
          name: 'remark'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '描述',
          },
          compProps: {
            clearable: true,
          }
        }
      }
    ]
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认添加',
  permission: 'admin:web:lowcodeSegmentTemplate:create',
})
// 提交按钮
const submitMethod = () => {
  return lowCodeSegmentTemplateCreateApi
}
// 成功提示语
const submitMethodSuccess = () => {
  return '添加成功，请刷新数据查看'
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
          :layout="[2,1,1]"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>