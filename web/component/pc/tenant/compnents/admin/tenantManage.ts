export const pageFormItems = [
      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '租户编码',
            
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
            label: '租户名称',
            
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },
      {
        field: {
          name: 'contactUserName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '联系人姓名',
            
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },
      {
        field: {
          name: 'contactUserEmail',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '联系人邮箱',
            
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },
      {
        field: {
          name: 'contactUserPhone',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '联系人电话',
            
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
            label: '租户编码',
            required: true,
            tips: '编码唯一'
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
            label: '租户名称',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'isDisabled',
          value: false
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否禁用',
            required: true,
          },
          compProps: {
          }
        }
      },

      {
        field: {
          name: 'disabledReason',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '禁用原因',
            required: ({form})=>{
              return form.isDisabled
            }
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'contactUserName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '联系人姓名',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'contactUserEmail',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '联系人邮箱',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'contactUserPhone',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '联系人电话',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'tenantDomain',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '租户域名',
            
          },
          compProps: {
            clearable: true,
            placeholder: '如：a.example.com'
          }
        }
      },


      {
        field: {
          name: 'tenantTheme',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '租户主题',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'tenantDefaultRoute',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '默认路由',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'configJson',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '额外配置json',
            displayBlock: true
          },
          compProps: {
            type: 'textarea',
            rows: 15
          }
        }
      },
  {
    field: {
      name: 'tenantLogoUrl',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '租户logo',
      },
      compProps: {
        clearable: true,
        placeholder: '以 http 开关'
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

