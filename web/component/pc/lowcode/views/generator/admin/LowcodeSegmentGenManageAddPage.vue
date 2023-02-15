<script setup name="LowcodeSegmentGenManageAddPage" lang="ts">
/**
 * 低代码片段生成管理添加页面
 */
import {reactive ,ref} from 'vue'
import {create as lowcodeSegmentGenCreateApi,list as lowcodeSegmentGenListApi} from "../../../api/generator/admin/lowcodeSegmentGenAdminApi"
import {list as lowcodeSegmentTemplateListApi} from "../../../api/generator/admin/lowcodeSegmentTemplateAdminApi";
import {list as lowcodeModelListApi} from "../../../api/generator/admin/lowcodeModelAdminApi";


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
          name: 'name'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '生成名称',
            required: true
          },
          compProps: {
            clearable: true,
          }
        }
      },

      {
        field: {
          name: 'lowcodeSegmentTemplateId'
        },
        element: {
          comp: 'PtCascader',
          formItemProps: {
            label: '低代码片段模板',
            required: true
          },
          compProps: {
            dataMethod: lowcodeSegmentTemplateListApi,
            dataMethodResultHandleConvertToTree: true,
          }
        }
      },
      {
        field: {
          name: 'lowcodeModelId',
        },
        element: {
          comp: 'PtSelect',
          formItemProps: {
            label: '低代码模型',
          },
          compProps: {
            dataMethod: lowcodeModelListApi,
          }
        }
      },
      {
        field: {
          name: 'generateTypeDictId'
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '生成类型',
            required: true
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'lowcode_segment_gen_type'},
          }
        }
      },
      {
        field: {
          name: 'refrenceSegmentGenId'
        },
        element: {
          comp: 'PtSelect',
          formItemProps: {
            label: '引用生成',
          },
          compProps: {
            dataMethod: lowcodeSegmentGenListApi,
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
            label: '描述'
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
  buttonText: '确认添加',
  permission: 'admin:web:lowcodeSegmentGen:create',
})
// 提交按钮
const submitMethod = () => {
  return lowcodeSegmentGenCreateApi
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
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>