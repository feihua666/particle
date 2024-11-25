import {useSelectOpenapiFeeCompItem} from "../../openplatformOpenapiFeeCompItem";
import {useCascaderOpenapiCompItem} from "../../openplatformOpenapiCompItem";
import {treeQueryComps} from "../../../../treeQueryComps";
import {useSelectProviderCompItem} from "../../openplatformProviderCompItem";

export const pageFormItems = [
      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '编码',

          },
          compProps: {
            clearable: true,
            placeholder: '模糊匹配',
          }
        }
      },
      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '名称',

          },
          compProps: {
            clearable: true,
            placeholder: '模糊匹配',
          }
        }
      },

      {
        field: {
          name: 'url',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '接口地址',

          },
          compProps: {
            clearable: true,
            placeholder: '模糊匹配',
          }
        }
      },
  useSelectOpenapiFeeCompItem({
    fieldName: 'defaultOpenplatformOpenapiFeeId',
    label: '默认计费规则',
  }),
  useCascaderOpenapiCompItem({}),
  ...treeQueryComps
]
export const addPageFormItems = [


  {
    field: {
      name: 'isGroup',
      value: false
    },
    element: {
      comp: 'el-switch',
      formItemProps: {
        label: '是否为组',
        required: true,
        tips: '分组只填写名称即可'
      },
      compProps: {
        activeText: '分组',
        inactiveText: '接口',
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
            tips: '符合变量命名规范，尽量使用小写字母用下划线分隔',
            labelTips: '一般用于程序判断，如果没有硬编码，可不填写,provider实现方式必填需要与实现支持接口保持一致'
          },
          compProps: {
            clearable: true,
            placeholder: '编码唯一'
          }
        }
      },


      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '名称',
            required: true,
            tips: '用于展示'
          },
          compProps: {
            clearable: true,
          }
        }
      },



      {
        field: {
          name: 'permissions',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '接口权限码',
            tips: '用来定义该接口需要的权限,仅支持一个定义不支持多个',
            displayBlock: true,
            required: ({form}) => !form.isGroup,
          },
          compProps: {
            clearable: true,
            placeholder: '如：openapi:query'
          }
        }
      },


      {
        field: {
          name: 'url',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '接口地址',
            required: ({form}) => !form.isGroup,
            tips: '请以/开头，对应编码接口映射',
            labelTips: '用于接口请求匹配，以获取对应的接口<br/>1. provider方式自定义即可<br/>2. 数据查询方式需与数据查询api接口地址保持一致,需以 /openapi/dq 开头<br/>3. 自定义接口需要与接口映射保持一致'
          },
          compProps: {
            clearable: true,
            placeholder: '地址唯一，如：/openapi/query'
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
            label: '是否禁用',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'disabledReason',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '禁用原因',
            required: ({form})=>{
              return form.isDisabled
            }
          },
          compProps: {
            clearable: true,
          }
        }
      },
  useSelectOpenapiFeeCompItem({
    fieldName: 'defaultOpenplatformOpenapiFeeId',
    label: '默认计费规则',
    tips: '默认计费规则，可以分配接口时覆盖该规则'
  }),


  useCascaderOpenapiCompItem({}),
  useSelectProviderCompItem({
    fieldName: 'openplatformProviderIds',
    labelTips: '如果接口是由供应商提供，需要选择正确的供应商，一般由开发人员来指定，和编码相关',
    tips: '定义该接口由哪个供应商提供，如果有多个可以多选',
    multiple: true
  }),
  {
    field: {
      name: 'remark',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '描述',
        tips: '随便写点什么'

      },
      compProps: {
        clearable: true,
      }
    }
  },

]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

