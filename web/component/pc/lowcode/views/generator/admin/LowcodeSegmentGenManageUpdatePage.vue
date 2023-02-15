<script setup name="AreaManageUpdatePage" lang="ts">
/**
 * 低代码片段生成管理更新页面
 */
import {reactive, ref} from 'vue'
import {
  update as lowcodeSegmentGenUpdateApi,
  detailForUpdate as detailForUpdateApi,
  list as lowcodeSegmentGenListApi
} from "../../../api/generator/admin/lowcodeSegmentGenAdminApi"
import {radioGroupData} from "../../../compnents/lowcodeSementTemplateComps";
import {list as lowcodeSegmentTemplateListApi} from "../../../api/generator/admin/lowcodeSegmentTemplateAdminApi";
import {list as lowcodeModelListApi} from "../../../api/generator/admin/lowcodeModelAdminApi";



// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  lowcodeSegmentGenId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    id: props.lowcodeSegmentGenId,
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
          name: 'lowcodeModelId'
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
  buttonText: '确认修改',
  permission: 'admin:web:lowcodeSegmentGen:update',
})
// 提交按钮
const submitMethod = () => {
  return lowcodeSegmentGenUpdateApi
}
// 初始化加载更新的数据
const dataMethod = () => {
  return detailForUpdateApi({id: props.lowcodeSegmentGenId})
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
          :comps="formComps">
  </PtForm>

</template>


<style scoped>

</style>