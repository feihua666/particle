<script setup name="FuncManageCrudAddPage" lang="ts">
/**
 * 功能菜单crud 批量添加页面
 */
import {onMounted, reactive, ref} from 'vue'
import {
  create as funcCreateApi,
  detailForUpdate as detailForUpdateApi,
  list as funcListApi
} from "../../api/admin/funcAdminApi"
import {list as funcGroupListApi} from "../../api/admin/funcGroupAdminApi"
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import {clone} from "../../../../../global/common/tools/ObjectTools"
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  // 父级，主要用来自动填充表单
  parentId: {
    type: String
  },
  // 编码前缀
  codePrefix: {
    type: String
  },
  // 名称前缀
  namePrefix: {
    type: String
  },
  // 权限码前缀
  permissionPrefix: {
    type: String
  },
  // 功能分组
  funcGroupId: {
    type: String
  },
  // 类型字典id，如：页面、按钮
  typeDictId: {
    type: String
  },
  // 归属组件
  componentOf: {
    type: String
  },
  // 下面为crud额外的权限
  createExtPermission: {
    type: String
  },
  deleteExtPermission: {
    type: String
  },
  updateExtPermission: {
    type: String
  },
  queryPageExtPermission: {
    type: String
  },
  // 是否为树，如果为树表则会在，添加查询和修改时，添加queryList权限
  isTree: {
    type: Boolean,
    default: false
  }
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    codePrefix: props.codePrefix,
    namePrefix: props.namePrefix,
    permissionPrefix: props.permissionPrefix,
    funcGroupId: props.funcGroupId,
    typeDictId: props.typeDictId,
    componentOf: props.componentOf,
  },
  // 表单数据对象
  formData: {},
})



const data = [
  {
    codeSuffix: 'query',
    nameSuffix: '查询',
    permissionSuffix: 'pageQuery',
    treePermissionSuffix: 'queryList',
    extPermission: props.queryPageExtPermission || null
  },
  {
    codeSuffix: 'create',
    nameSuffix: '添加',
    permissionSuffix: 'create',
    treePermissionSuffix: 'queryList',
    extPermission: props.createExtPermission || null
  },
  {
    codeSuffix: 'delete',
    nameSuffix: '删除',
    permissionSuffix: 'delete',
    extPermission: props.deleteExtPermission || null
  },
  {
    codeSuffix: 'update',
    nameSuffix: '修改',
    permissionSuffix: 'update',
    treePermissionSuffix: 'queryList',
    extPermission: props.updateExtPermission || null
  }
]
// 表单项
const formComps = ref(
    [
      {
        field: {
          name: 'items',
          // 默认全选
          value: data.map(i =>i.nameSuffix)
        },
        element: {
          comp: 'PtCheckboxGroup',
          formItemProps: {
            label: '项目',
            required: true,
          },
          compProps: {
            dataMethod: ()=> {
              return data
            },
            props: {
              value: 'nameSuffix',
              label: 'nameSuffix'
            },
            dataMethodResultHandle: directDataMethodResultHandle
          }
        }
      },
      {
        field: {
          name: 'codePrefix',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '编码前缀',
            required: true
          },
          compProps: {
            clearable: true,
            placeholder: '如：user_manage_'
          }
        }
      },
      {
        field: {
          name: 'namePrefix'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '名称前缀',
            required: true
          },
          compProps: {
            clearable: true,
            placeholder: '如：用户'
          }
        }
      },
      {
        field: {
          name: 'permissionPrefix'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '权限码前缀',
            required: true,
            tips: '注意一般结尾带冒号'
          },
          compProps: {
            clearable: true,
            placeholder: '如：admin:web:user:'
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
            required: true,
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
            dictParam: {groupCode: 'func_type'},
            defaultValueItem: (item) => item.value == 'button'
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
          name: 'componentOf'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '归属组件',
            required: true,
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
  permission: 'admin:web:func:create'
})
const appendCommaIfNotEmpty = (str: string) => {
  if (!str) {
    return ''
  }
  return str + ','
}
// 提交按钮
const submitMethod = async (form) => {
  let items = form.items
  for (let i = 0; i < items.length; i++) {
    let newForm = clone(form)
    try {
      delete newForm.items
      delete newForm.codePrefix
      delete newForm.namePrefix
      delete newForm.permissionPrefix
      Reflect.deleteProperty(newForm, 'items')
      Reflect.deleteProperty(newForm, 'codePrefix')
      Reflect.deleteProperty(newForm, 'namePrefix')
      Reflect.deleteProperty(newForm, 'permissionPrefix')
    } catch (e) {
    }
    let item = data.find(it => it.nameSuffix == items[i])
    newForm.code = form.codePrefix + item.codeSuffix
    newForm.name = form.namePrefix + item.nameSuffix

    // 针对树表添加额外处理
    let permissionForTree = ''
    if (props.isTree && item.treePermissionSuffix) {
      permissionForTree = (form.permissionPrefix + item.treePermissionSuffix)
    }

    newForm.permissions = appendCommaIfNotEmpty(item.extPermission)+ appendCommaIfNotEmpty(permissionForTree) + (form.permissionPrefix + item.permissionSuffix)

    await funcCreateApi(newForm);
  }

}
// 初始化加载更新的数据
const parentInit = () => {
  if(!props.parentId){
    return
  }

  detailForUpdateApi({id: props.parentId}).then(res => {
    let data = res.data.data
    reactiveData.form.funcGroupId = data.funcGroupId
    reactiveData.form.parentId = data.id
    reactiveData.form.codePrefix = data.code + '_'
    reactiveData.form.namePrefix = data.name
    reactiveData.form.componentOf = data.componentOf
  })
}

// 成功提示语
const submitMethodSuccess = () => {
  return '添加成功，请刷新数据查看'
}

function directDataMethodResultHandle({success}){
  return success
}

onMounted(()=>{
  parentInit()
})
</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="80"
          :method="submitMethod"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          inline
          :layout="[1]"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>