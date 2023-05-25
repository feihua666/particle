export const pageFormItems = [
      {
        field: {
          name: 'messageId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '消息表主键',
            
          },
          compProps: {
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
          name: 'isRead',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否已读',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'readAt',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '读取时间',
            
          },
          compProps: {
          }
        }
      },
]
export const addPageFormItems = [




      {
        field: {
          name: 'messageId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '消息表主键',
            required: true,
          },
          compProps: {
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
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'isRead',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否已读',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'readAt',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '读取时间',
            
          },
          compProps: {
          }
        }
      },












]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

