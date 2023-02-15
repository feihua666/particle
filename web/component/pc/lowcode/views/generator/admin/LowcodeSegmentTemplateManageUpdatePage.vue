<script setup name="AreaManageUpdatePage" lang="ts">
/**
 * 低代码片段模板管理更新页面
 */
import {reactive, ref} from 'vue'
import {
  update as lowcodeSegmentTemplateUpdateApi,
  detailForUpdate as detailForUpdateApi,
  list as lowcodeSegmentTemplateListApi
} from "../../../api/generator/admin/lowcodeSegmentTemplateAdminApi"
import {radioGroupData} from "../../../compnents/lowcodeSementTemplateComps";



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
  // 表单
  form: {
    id: props.lowcodeSegmentTemplateId,
    version: 1
  },
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
            required: true,
            tips: '编码仅用来唯一标识一个模板，不可重复'
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
            required: true,
            tips: '代表一个可读性的文本'
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'outputTypeDictId'
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '输出类型',
            required: true
          },
          compProps: {
            clearable: true,
            // 字典查询
            dictParam: {groupCode: 'lowcode_segment_template_output_type'}
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
            dataMethod: lowcodeSegmentTemplateListApi,
            dataMethodResultHandleConvertToTree: true,
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
            tips: '仅支持 enjoy 语法。模板内数据引用支持global,sys,ext,parent前缀的数据',
            displayBlock: true
          },
          compProps: {
            type: 'textarea',
            clearable: true,
            rows: 5,
            placeholder: '根据输出类型不同会有不同的处理，输出类型为目录或文件，名称模板渲染结果将会作为目录或文件路径处理并生成对应的磁盘目录或文件'
          }
        }
      },
      {
        field: {
          name: 'nameOutputVariable'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '名称输出变量名',
            tips: '表示直接子级可以在模板中使用 parent.[变量名]'
          },
          compProps: {
            clearable: true,
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
            tips: '仅支持 enjoy 语法。模板内数据引用支持global,sys,ext,parent,child前缀的数据',
            displayBlock: true
          },
          compProps: {
            type: 'textarea',
            clearable: true,
            rows: 10,
            placeholder: '可以引用子一级内容输出变量，在这里聚合'
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
            label: '内容输出变量名',
            tips: '表示父级可以在模板中使用 child.[变量名]'

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
            tips: '仅子级可访问，并可设置值，类型为Set&lt;String&gt;可以通过share.[变量名].add("xxx")添加共享数据'
          },
          compProps: {
            clearable: true,
            placeholder: '多个以逗号分隔'
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
            tips: '引用模板是本模板的一个副本，本模板将会合并到引用模板（本模板有值字段会覆盖引用模板对应字段），形成一个全模板'
          },
          compProps: {
            dataMethod: lowcodeSegmentTemplateListApi,
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
            displayBlock: true,
          },
          compProps: {
            type: 'textarea',
            clearable: true,
            placeholder: '填写一些备注内容'
          }
        }
      }
    ]
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认修改',
  permission: 'admin:web:lowcodeSegmentTemplate:update',
})
// 提交按钮
const submitMethod = () => {
  return lowcodeSegmentTemplateUpdateApi
}
// 初始化加载更新的数据
const dataMethod = () => {
  return detailForUpdateApi({id: props.lowcodeSegmentTemplateId})
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
          labelWidth="100"
          :dataMethod="dataMethod"
          :method="submitMethod()"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          inline
          :layout="[2,2,1,1,1,1,1,1,1]"
          :comps="formComps">
  </PtForm>

</template>


<style scoped>

</style>