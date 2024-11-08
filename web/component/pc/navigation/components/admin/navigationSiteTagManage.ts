export const pageFormItems = [
      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '标签编码',

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
            label: '标签名称',

          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },
      {
        field: {
          name: 'groupDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '分组',

          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'navigation_tag_group'}
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
            label: '标签编码',

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
            label: '标签名称',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


  {
    field: {
      name: 'groupDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '分组',
        required: true,
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'navigation_tag_group'}
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


      {
        field: {
          name: 'remark',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '备注',

          },
          compProps: {
            clearable: true,
          }
        }
      },

]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

