import {nextTick} from 'vue'
import {useRemoteSelectUserCompItem} from "../../../user/compnents/userCompItem";
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
export const useAddPageFormItems = ({props})=>{
  return  [
    useRemoteSelectUserCompItem(
        {props,
      required: true,
          valueChange: ({form, formData})=> {
            nextTick(()=>{
              if (formData.userId) {
                form.name = formData.userId.name || formData.userId.nickname
              }else {
                form.name = ''
              }
            })
          }
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

  ]
}

// 更新和添加一致
export const useUpdatePageFormItems = useAddPageFormItems
