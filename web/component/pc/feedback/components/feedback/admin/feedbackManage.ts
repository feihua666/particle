export const pageFormItems = [

  {
    field: {
      name: 'contactUsername',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '联系姓名',

      },
      compProps: {
        clearable: true,
        placeholder: '左前缀匹配',
      }
    }
  },
  {
    field: {
      name: 'contactTelephone',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '联系电话',

      },
      compProps: {
        clearable: true,
        placeholder: '左前缀匹配',
      }
    }
  },
  {
    field: {
      name: 'contactEmail',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '联系邮箱',

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
          name: 'feedbackContent',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '问题建议内容',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'feedbackAt',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '问题建议时间',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'feedbackUserId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '问题建议用户id',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'contactUsername',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '联系姓名',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'contactTelephone',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '联系电话',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'contactEmail',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '联系邮箱',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'isContactTelephoneMobile',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否联系电话为手机号',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'isHandle',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否已处理',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'handleResult',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '处理结果',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'firstFeedbackId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '第一条意见反馈id',
            
          },
          compProps: {
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
export const manualHandlePageFormItems = [
  {
    field: {
      name: 'handleResult',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '处理结果',
        required: true,
        displayBlock: true,
      },
      compProps: {
        type: 'textarea',
        rows: 14,
        clearable: true,
      }
    }
  },
]

