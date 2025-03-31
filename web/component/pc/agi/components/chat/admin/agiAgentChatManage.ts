export const pageHistoryFormItems = [
  {
    field: {
      name: 'title',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '对话标题',

      },
      compProps: {
        clearable: true,
        placeholder: '左前缀匹配',
      }
    }
  },
]

export const pageFormItems = [
      {
        field: {
          name: 'agiAgentId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '智能体id',

          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'chatId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '对话id',

          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'userId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '用户id',

          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'title',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '对话标题',

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
          name: 'title',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '对话标题',
            tips: '简明扼要标题，用来标识对话的主要内容',
            required: true,
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
            label: '描述',
            tips: '可以填写一些备注信息'
          },
          compProps: {
            clearable: true,
          }
        }
      },


]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

