import {useRemoteSelectUserCompItem} from "../../../user/compnents/userCompItem";

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
          name: 'userName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '姓名',
            
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },
      {
        field: {
          name: 'email',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '邮箱',
            
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },
      {
        field: {
          name: 'mobile',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '手机号',
            
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },
]
export const useAddPageFormItems = ({props})=>{
  return  [
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
        name: 'isFormal',
        value: false
      },
      element: {
        comp: 'el-switch',
        formItemProps: {
          label: '是否正式',
          required: true,
        },
        compProps: {
          activeText: '正式',
          inactiveText: '试用',
        }
      }
    },
    {
      field: {
        name: 'userLimitCount',
        value: 0
      },
      element: {
        comp: 'el-input-number',
        formItemProps: {
          label: '用户数限制',
          required: true,
          tips: '0 为不限制'
        },
        compProps: {

        }
      }
    },
    useRemoteSelectUserCompItem(
        {
          props,
          required: true,
          fieldName: 'masterUserId',
          propUserIdFieldName: 'masterUserId',
          propUserNicknameFieldName: 'masterUserNickname',
          label: '主用户'}),

    {
      field: {
        name: 'effectiveAt',
      },
      element: {
        comp: 'PtDatePicker',
        formItemProps: {
          label: '生效时间',
          tips: '不填写立即生效'
        },
        compProps:  {
          clearable: true,
          type: "datetime"
        }
      }
    },
    {
      field: {
        name: 'expireAt',
      },
      element: {
        comp: 'PtDatePicker',
        formItemProps: {
          label: '失效时间',
          tips: '不填写永不失效'
        },
        compProps:  {
          clearable: true,
          type: "datetime"
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
          activeText: '禁用',
          inactiveText: '启用',
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
        name: 'userName',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '姓名',
          tips: '仅作为联系人，展示使用'
        },
        compProps: {
          clearable: true,
        }
      }
    },


    {
      field: {
        name: 'email',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '邮箱',
          tips: '仅作为联系人邮箱，展示使用'
        },
        compProps: {
          clearable: true,
        }
      }
    },


    {
      field: {
        name: 'mobile',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '手机号',
          tips: '仅作为联系人手机号，展示使用'
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
        name: 'tenantThemeJson',
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
        name: 'tenantDefaultRouteJson',
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
        name: 'tenantLogoJson',
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
}

// 更新和添加一致
export const useUpdatePageFormItems = useAddPageFormItems

