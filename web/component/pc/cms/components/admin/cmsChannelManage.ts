import {useCascaderCmsChannelCompItem, useSelectCmsSiteCompItem} from "../cmsSiteCompItem";

export const pageFormItems = [
    useSelectCmsSiteCompItem({}),
      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '栏目编码',

          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配'
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
            label: '栏目名称',

          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配'
          }
        }
      },
]
export const addPageFormItems = [

  useSelectCmsSiteCompItem({required: true}),

      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '栏目编码',
            tips: '编码唯一，用来唯一标识站点，注意需要保持url命令规则'

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
            label: '栏目名称',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'templatePath',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '栏目模板路径',
            required: true,
            tips: '支持绝对路径和相对路径，相对路径是相对于系统的 classpath:/template-cms'
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'templateIndex',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '栏目模板',
            required: true,
            tips: '相对于栏目模板路径，如：index.html'
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'staticPath',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '栏目静态页存放路径',
            tips: '仅支持绝对路径,在页面静态化时静态页面存放路径'
          },
          compProps: {
            clearable: true,
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
        label: '排序'
      },
      compProps: {
        clearable: true,
      }
    }
  },
  useCascaderCmsChannelCompItem({}),


]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

