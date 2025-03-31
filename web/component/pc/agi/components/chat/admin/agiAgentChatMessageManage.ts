export const pageFormItems = [
      {
        field: {
          name: 'agiAgentChatId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '智能体对话id',
            
          },
          compProps: {
          }
        }
      },
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
          name: 'messageType',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '消息类型',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
]
export const addPageFormItems = [




      {
        field: {
          name: 'agiAgentChatId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '智能体对话id',
            required: true,
          },
          compProps: {
          }
        }
      },


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
            required: true,
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
          name: 'messageType',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '消息类型',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'content',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '消息内容',
            
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
            
          },
          compProps: {
            clearable: true,
          }
        }
      },












]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

