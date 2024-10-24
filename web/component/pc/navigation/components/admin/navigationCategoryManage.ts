import {useCascaderNavigationCategoryCompItem} from "../navigationCategoryCompItem";

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
            placeholder: '左前缀匹配',
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
            placeholder: '左前缀匹配',
          }
        }
      },

]
export const addPageFormItems = [




      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '编码',
            tips: '如果程序中不使用不用填写'
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
            label: '名称',
            required: true,
            tips: '分类的名称'
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'icon',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '图标',
            tips: '支持http开头的地址、图片base64编码'
          },
          compProps: {
            clearable: true,
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
        label: '排序',
        required: true,
      },
      compProps: {
      }
    }
  },


  useCascaderNavigationCategoryCompItem({}),

  {
    field: {
      name: 'remark',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '描述',

      },
      compProps: {
        clearable: true,
      }
    }
  },
]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

