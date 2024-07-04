export const pageFormItems = [

      {
        field: {
          name: 'userNickname',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '用户昵称',
            
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },

      {
        field: {
          name: 'sessionMd5',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '会话标识md5',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },

      {
        field: {
          name: 'trackingPageCode',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '页面编码',
            
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },
      {
        field: {
          name: 'actionType',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '行为类型',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'actionAtStart',
        },
        element: {
          comp: 'PtDatePicker',
          formItemProps: {
            label: '行为开始时间',
          },
          compProps: {
            type: 'datetime'
          }
        }
      },
  {
    field: {
      name: 'actionAtEnd',
    },
    element: {
      comp: 'PtDatePicker',
      formItemProps: {
        label: '行为结束时间',
      },
      compProps: {
        type: 'datetime'
      }
    }
  },

      {
        field: {
          name: 'traceId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '追踪id',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },

]

