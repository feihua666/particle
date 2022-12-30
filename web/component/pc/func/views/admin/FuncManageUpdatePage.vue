<script setup name="FuncManageUpdatePage" lang="ts">
/**
 * 功能菜单管理更新页面
 */
import {reactive, ref} from 'vue'
import {
  detailForUpdate as detailForUpdateApi,
  list as funcListApi,
  update as funcUpdateApi
} from "../../api/admin/funcAdminApi"
import {list as funcGroupListApi} from "../../api/admin/funcGroupAdminApi"
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  funcId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    id: props.funcId,
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
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '编码',
            required: true,
            title: '编码全局唯一，用来唯一标识一个功能项'
          },
          compProps: {
            clearable: true,
            disabled: true,
            disabledReason: '编码不能修改'
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
          name: 'funcGroupId',
          value: ''
        },
        element: {
          comp: 'PtSelect',
          formItemProps: {
            label: '功能分组',
            required: true
          },
          compProps: {
            clearable: true,
            dataMethod: funcGroupListApi,
          }
        }
      },
      {
        field: {
          name: 'icon',
          // 默认一个链接图标
          value: 'Link'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '图标'
          },
          compProps: ({form}) => {
            let prefixIcon = 'Link'
            if(ElementPlusIconsVue[form.icon]){
              prefixIcon = form.icon
            }

            return {
              clearable: true,
              placeholder: '仅支持内置图标名称',
              prefixIcon: prefixIcon
            }
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
          name: 'isShow',
          value: true
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否展示'
          },
          compProps: {
            activeText: '展示',
            inactiveText: '隐藏',
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
            clearable: true,
            // 加载数据
            dataMethod: () => { return funcListApi({})},
            dataMethodResultHandleConvertToTree: true,
          }
        }
      },
      {
        field: {
          name: 'typeDictId'
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '类型',
            required: true
          },
          compProps: {
            clearable: true,
            // 字典查询
            dictParam: {groupCode: 'func_type'}
          }
        }
      },
      {
        field: {
          name: 'url'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '路由',
            rules: [
              { validator: (rule: any, value: any, callback: any) => {
                  if (!value && reactiveData.formData.typeDictId?.value == 'page') {
                    callback(new Error('路由不能为空'))
                  } else {
                    callback()
                  }
                }, trigger: 'blur' }
            ],
            required: ({formData}) => formData.typeDictId?.value == 'page'
          },
          compProps: {
            clearable: true,
            placeholder: '如：/admin/user/add'
          }
        }
      },
      {
        field: {
          name: 'permissions'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '权限码'
          },
          compProps: {
            clearable: true,
            placeholder: '如：admin:user:add'
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
  permission: 'admin:web:func:update'
})
// 提交按钮
const submitMethod = () => {
  return funcUpdateApi
}
// 初始化加载更新的数据
const dataMethod = () => {
  return detailForUpdateApi({id: props.funcId})
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
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          :submitAttrs="submitAttrs"
          inline
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>