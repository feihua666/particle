<script setup name="LowcodeModelManageAddPage" lang="ts">
/**
 * 低代码模型管理添加页面
 */
import {reactive ,ref} from 'vue'
import {create as lowcodeModelCreateApi,list as lowcodeModelListApi} from "../../../api/generator/admin/lowcodeModelAdminApi"
import {radioGroupData} from "../../../compnents/lowcodeModelComps";
import {lowerFirst, upperFirst} from "../../../../../../global/common/tools/StringTools";


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
          name: 'nameEn',
          valueChange:({form,formData,newValue})=>{
            if(newValue){
              let newValueTemp = newValue.toLowerCase().replace('-','').replace('_','')
              form.nameEnEntity = upperFirst(newValueTemp)
              form.requestPath = newValue.toLowerCase()
            }else {
              form.nameEnEntity = ''
              form.requestPath = ''
            }
          }
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
          name: 'nameEnEntity',
          valueChange:({form,formData,newValue})=>{
            if(newValue){
              let newValueTemp = newValue.toLowerCase().replace('-','').replace('_','')
              form.nameEnEntityVar = lowerFirst(newValueTemp)
            }else {
              form.nameEnEntityVar = ''
            }
          }
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '英文实体名称',
            required: true,
            tips: '将作为模型的实体名称变量输出，首字母必须大写'
          },
          compProps: {
            placeholder: '首字母必须大写',
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'nameEnEntityVar'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '英文实体变量名称',
            required: true,
            tips: '将作为模型的实体变量名称变量输出,首字母一般小写'
          },
          compProps: {
            placeholder: '首字母一般小写',
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
          name: 'requestPath'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '请求路径',
            required: true
          },
          compProps: {
            clearable: true,
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
  permission: 'admin:web:lowcodeModel:create',
})
// 提交按钮
const submitMethod = () => {
  return lowcodeModelCreateApi
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
          :layout="2"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>