import {
  useCascaderCmsChannelCompItem,
  useCascaderCmsContentCategoryCompItem,
  useSelectCmsSiteCompItem
} from "../cmsSiteCompItem";

export const pageFormItems = [
  useSelectCmsSiteCompItem({}),
  useCascaderCmsChannelCompItem({fieldName: 'cmsChannelId', label: '栏目'}),
      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '分类名称',

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
  useCascaderCmsChannelCompItem({fieldName: 'cmsChannelId', label: '栏目'}),


      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '分类名称',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'imageUrl',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '图片地址',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'imageDescription',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '图片描述',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'imageUrl1',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '图片地址1',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'imageDescription1',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '图片描述1',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'imageUrl2',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '图片地址2',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'imageDescription2',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '图片描述2',

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

  useCascaderCmsContentCategoryCompItem({})

]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

