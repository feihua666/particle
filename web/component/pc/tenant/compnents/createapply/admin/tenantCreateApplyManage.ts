import {useRemoteSelectUserCompItem} from "../../../../user/compnents/userCompItem";

export const pageFormItems = [
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
          name: 'tenantTypeDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '租户类型',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'tenant_type'}
          }
        }
      },

  useRemoteSelectUserCompItem({
    props: {},fieldName: 'applyUserId',label: '申请用户'}),

  {
        field: {
          name: 'auditStatusDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '审核状态',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'tenant_create_apply_audit_status'}
          }
        }
      },

  useRemoteSelectUserCompItem({
    props: {},fieldName: 'auditUserId',label: '审核用户'}),

]
export const useAddPageFormItems = ({props,funcApplicationDialogVisible})=>{
  return [

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
        name: 'tenantTypeDictId',
      },
      element: {
        comp: 'PtDictFrontSelect',
        formItemProps: {
          label: '租户类型',
          required: true
        },
        compProps: {
          // 字典查询
          dictParam: {groupCode: 'tenant_type'}
        }
      }
    },
    useRemoteSelectUserCompItem({
      props: props,
      required: false,
      fieldName: 'applyUserId',
      propUserIdFieldName: 'applyUserId',
      propUserNicknameFieldName: 'applyUserNickname',
      label: '申请用户'}),

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
    {
      field: {
        name: 'effectiveDays',
        value: 0
      },
      element: {
        comp: 'el-input-number',
        formItemProps: {
          label: '申请天数',
          required: true,
          tips: '0 为不限制，将根据截止日期自动计算'
        },
        compProps: {

        }
      }
    },

    {
      field: {
        name: 'effectiveAt',
      },
      element: {
        comp: 'PtDatePicker',
        formItemProps: {
          label: '生效时间',
          tips: '不填写立即生效,将使用当前时间'
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
          tips: '不填写永不失效，将根据申请天数自动计算'
        },
        compProps:  {
          clearable: true,
          type: "datetime"
        }
      }
    },
    {
      field: {
        name: 'extJsonObj',
      },
      element: {
        comp: 'PtButton',
        formItemProps: {
          label: '申请应用',
          tips: '选择申请的应用及功能',
          required: true
        },
        compProps: ({form,formData})=>{
          return {
            text: true,
            type: form.extJsonObj ? 'primary' : 'default',
            buttonText: '点击应用设置',
            method: ()=>{
              if(funcApplicationDialogVisible){
                funcApplicationDialogVisible.value = true
              }
            }
          }
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
          tips: '如果用户不存在将作为用户的昵称,创建用户'
        },
        compProps: {
          placeholder: '姓名',
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
        name: 'contactUserPhone',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '联系人手机',
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

export const useAuditPageFormItems = ({props})=>{
return [

  {
    field: {
      name: 'auditStatusDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '审核状态',
        required: true
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'tenant_create_apply_audit_status',tags: 'audit'},
      }
    }
  },
  {
    field: {
      name: 'auditStatusComment',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '审核意见',
        required: true,
        displayBlock: true
      },
      compProps: {
        type: 'textarea',
        clearable: true,
        rows: 10,
        placeholder: '填写您的审核意见'
      }
    }
  },

]
}

/**
 * 从 useAddPageFormItems 复制页面，基本类似，因为后端使用的是一个表单对象
 * @param props
 * @param funcApplicationDialogVisible
 */
export const useOneClickAddPageFormItems = useAddPageFormItems
