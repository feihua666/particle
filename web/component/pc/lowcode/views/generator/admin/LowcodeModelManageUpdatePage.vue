<script setup name="AreaManageUpdatePage" lang="ts">
/**
 * 低代码模型管理更新页面
 */
import {reactive, ref} from 'vue'
import {
  update as lowcodeModelUpdateApi,
  detailForUpdate as detailForUpdateApi,
  list as lowcodeModelListApi
} from "../../../api/generator/admin/lowcodeModelAdminApi"
import {radioGroupData} from "../../../compnents/lowcodeModelComps";



// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  lowcodeModelId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    id: props.lowcodeModelId,
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
            label: '名称',
            required: true,
            tips: '将作为模型的原始名称变量输出'
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'nameEn'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '英文名称',
            required: true,
            tips: '将作为模型的原始英文名称变量输出'
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'tableName'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '表名称',
            tips: '可以根据表名加载模型项，比手动录入更方便'
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'tableTypeDictId'
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '模型表类型',
            required: true
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'lowcode_model_table_type'}
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
  permission: 'admin:web:lowcodeModel:update',
})
// 提交按钮
const submitMethod = () => {
  return lowcodeModelUpdateApi
}
// 初始化加载更新的数据
const dataMethod = () => {
  return detailForUpdateApi({id: props.lowcodeModelId})
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
          :layout="2"
          :comps="formComps">
  </PtForm>

</template>


<style scoped>

</style>