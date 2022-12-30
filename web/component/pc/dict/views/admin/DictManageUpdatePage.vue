<script setup name="DictManageUpdatePage" lang="ts">
/**
 * 字典管理更新页面
 */
import {reactive, ref} from 'vue'
import {
  update as dictUpdateApi,
  detailForUpdate as detailForUpdateApi,
  list as dictListApi
} from "../../api/admin/dictAdminApi"
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  dictId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    id: props.dictId,
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
          name: 'isGroup',
          value: false
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否字典组'
          },
          compProps: {
            activeText: '字典组',
            inactiveText: '字典项',
          }
        }
      },
      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '编码',
            required: ({form}) => form.isGroup == true,
            title: '编码全局唯一，用来唯一标识一个字典组'
          },
          compProps: ({form})=>{
            let disabled = form.isGroup == false
            if(disabled){
              form.code = ''
            }
            return {
              clearable: true,
              placeholder: '编码唯一如：user_code',
              disabled: disabled
            }
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
            label: '名称',
            required: true
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'value'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '字典值',
            required: ({form})=> form.isGroup == false
          },
          compProps:  ({form})=>{
            let disabled = form.isGroup == true
            if(disabled){
              form.value = ''
            }
            return {
              clearable: true,
              disabled: disabled
            }
          }
        }
      },
      {
        field: {
          name: 'valueUnit',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '字典值单位'
          },
          compProps:  {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'isSystem',
          value: false
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否系统'
          },
          compProps: {
            activeText: '系统字典',
            inactiveText: '自定义字典',
          }
        }
      },
      {
        field: {
          name: 'isPublic',
          value: false
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否公共'
          },
          compProps: {
            activeText: '公共字典',
            inactiveText: '私有字典',
          }
        }
      },

      {
        field: {
          name: 'isDisabled',
          value: false
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否禁用'
          },
          compProps: {
            activeText: '禁用',
            inactiveText: '启用',
          }
        }
      },
      {
        field: {
          name: 'disabledReason'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '禁用原因',
            required: ({form}) => form.isDisabled == true

          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'privateFlag'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '私有标识'
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'privateFlagMemo'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '私有标识说明',
            required: ({form}) => !!form.privateFlag
          },
          compProps: {
            clearable: true,
          }
        }
      },

      {
        field: {
          name: 'groupFlag'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '分组标识'
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'groupFlagMemo'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '分组标识说明',
            required: ({form}) => !!form.groupFlag
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'tags'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '标签',
            title: '标签用来给字典项分组，多个以逗号分隔'
          },
          compProps: {
            clearable: true,
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
            required: ({form}) => form.isGroup == false,
          },
          compProps: {
            clearable: true,
            // 加载数据
            dataMethod: () => { return dictListApi({})},
            dataMethodResultHandleConvertToTree: true,
          }
        }
      },
      {
        field: {
          name: 'seq',
          value: 10
        },
        element: {
          comp: 'el-input-number',
          formItemProps: {
            label: '排序'
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
  buttonText: '确认修改',
  permission: 'admin:web:dict:update',
})
// 提交按钮
const submitMethod = () => {
  return dictUpdateApi
}
// 初始化加载更新的数据
const dataMethod = () => {
  return detailForUpdateApi({id: props.dictId})
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