import {nextTick} from 'vue'
import {useRemoteSelectUserCompItem} from "../../../user/components/userCompItem";
import {useSelectTenantCompItem} from "../tenantCompItem";

export const usePageFormItems = ({props}) => {
  return [
    useRemoteSelectUserCompItem({props,required: false}),
    {
      field: {
        name: 'name',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '用户姓名',

        },
        compProps: {
          clearable: true,
          placeholder: '左前缀匹配',
        }
      }
    },
    useSelectTenantCompItem({}),
  ]
}
export const useAddPageFormItems = ({props,isForAdd = false})=>{
  let r =  [
    useRemoteSelectUserCompItem(
        {props,
          required: isForAdd ? false : true,
          valueChange: ({form, formData})=> {
            nextTick(()=>{
              if (formData.userId) {
                form.name = formData.userId.name || formData.userId.nickname
              }else {
                form.name = ''
              }
            })
          },
        tips: '填写后手动输入的账号、邮箱、手机号不再生效，不再匹配用户'
        }),


    {
      field: {
        name: 'name',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '用户姓名',
        },
        compProps: {
          clearable: true,
        }
      }
    },
    useSelectTenantCompItem({required: true}),

    {
      field: {
        name: 'isExpired',
        value: false,
      },
      element: {
        comp: 'el-switch',
        formItemProps: {
          label: '是否过期',
          required: true,
        },
        compProps: {
        }
      }
    },


    {
      field: {
        name: 'expiredReason',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '过期原因',
          required: ({form}) => form.isExpired == true
        },
        compProps: {
          clearable: true,
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
          label: '到期时间'
        },
        compProps:  {
          clearable: true,
          type: "datetime",
          placeholder: '不填写永不过期'
        }
      }
    },

    {
      field: {
        name: 'isLeave',
        value: false
      },
      element: {
        comp: 'el-switch',
        formItemProps: {
          label: '是否离职/退出',
          required: true,
        },
        compProps: {
        }
      }
    },


    {
      field: {
        name: 'leaveReason',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '离职/退出原因',
          required: ({form}) => form.isLeave == true
        },
        compProps: {
          clearable: true,
        }
      }
    },
    {
      field: {
        name: 'leaveAt',
      },
      element: {
        comp: 'PtDatePicker',
        formItemProps: {
          label: '离职/退出时间'
        },
        compProps:  {
          clearable: true,
          type: "datetime",
          placeholder: '不填写默认当前时间'
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
        name: 'remark'
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '备注'
        },
        compProps: {
          clearable: true,
        }
      }
    },
  ]

  let addOnly = [
    {
      field: {
        name: 'userAccount',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '账号',
          tips: '如果用户不存在将作为用户登录账号匹配用户',
        },
        compProps: {
          placeholder: '账号',
          clearable: true,
        }
      }
    },
    {
      field: {
        name: 'userEmail',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '邮箱',
          tips: '如果用户不存在将作为用户登录账号匹配用户',
          validate: {
            email: true
          }
        },
        compProps: {
          placeholder: '邮箱',
          clearable: true,
        }
      }
    },

    {
      field: {
        name: 'userMobile',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '手机号',
          tips: '如果用户不存在将作为用户登录账号匹配用户',
          validate: {
            mobile: true
          }
        },
        compProps: {
          placeholder: '手机号',
          clearable: true,
        }
      }
    },
    {
      field: {
        name: 'password',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '密码',
          tips: '如果用户不存在将作为初始密码，不填写将自动生成',
        },
        compProps: {
          placeholder: '密码',
          clearable: true,
        }
      }
    },
    {
      field: {
        name: 'isSendEmailNotice',
        value: true
      },
      element: {
        comp: 'el-switch',
        formItemProps: {
          label: '发送邮件通知',
          tips: '仅邮箱存在时生效',
        },
        compProps: {
          activeText: "发送",
          inactiveText: '不发送'
        }
      }
    },
  ]
  if(isForAdd){
    r = r.concat(addOnly)
  }
  return r
}

// 更新和添加一致
export const useUpdatePageFormItems = useAddPageFormItems