<script setup name="FuncManageAddPage" lang="ts">
/**
 * 功能菜单管理添加页面
 */
import {reactive, ref} from 'vue'
import {create as funcCreateApi, list as funcListApi} from "../../api/admin/funcAdminApi"
import {list as funcGroupListApi} from "../../api/admin/funcGroupAdminApi"
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

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
            clearable: true,
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
            clearable: true,
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
  buttonText: '确认添加'
})
// 提交按钮
const submitMethod = () => {
  return funcCreateApi
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
          inline
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>