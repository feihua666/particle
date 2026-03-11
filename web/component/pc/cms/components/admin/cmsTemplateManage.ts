import {useCascaderCmsTemplateCompItem} from "../cmsCompItem";
import {treeQueryComps} from "../../../treeQueryComps";

export const pageFormItems = [
      {
        field: {
          name: 'templateKey',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '唯一键',

          },
          compProps: {
            clearable: true,
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
            label: '文件名',

          },
          compProps: {
            clearable: true,
          }
        }
      },
  {
    field: {
      name: 'isDirectory',
    },
    element: {
      comp: 'PtSelect',
      formItemProps: {
        label: '是否目录',
      },
      compProps: {
        dataMethod: () => {
          return {
            data: [
              {id: 'true',name: '目录'},
              {id: 'false',name: '文件'},
            ]
          }
        }
      }
    }
  },
  useCascaderCmsTemplateCompItem({}),

  ...treeQueryComps
]
export const useAddPageFormItems = ({isForAdd= true})=>{
  return [
    {
      field: {
        name: 'name',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '文件名',
          required: true,
        },
        compProps: {
          clearable: true,
        }
      }
    },


    {
      field: {
        name: 'isDirectory',
      },
      element: {
        comp: 'el-switch',
        formItemProps: {
          label: '是否目录',
          required: true,
        },
        compProps: {
          activeText: '目录',
          inactiveText: '文件',
          disabled: !isForAdd,
        }
      }
    },


    {
      field: {
        name: 'seq',
        value: 1000
      },
      element: {
        comp: 'el-input-number',
        formItemProps: {
          label: '排序',
          required: true,
        },
        compProps: {
        }
      }
    },

    useCascaderCmsTemplateCompItem({tips: '仅支持目录作为父级'}),

    {
      field: {
        name: 'content',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '模板内容',
          required: ({form}) => (!form.isDirectory),
          tips: '目录请留空，模板内容，请使用 freemarker 模板语法',
          displayBlock: true,
        },
        compProps: {
          clearable: true,
          type: 'textarea',
          rows: 15
        }
      }
    },
  ]
}

// 更新和添加一致
export const useUpdatePageFormItems = useAddPageFormItems

