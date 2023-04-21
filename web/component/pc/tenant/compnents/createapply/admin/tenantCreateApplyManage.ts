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
export const useAddPageFormItems = ({props})=>{
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
      props: props,required: true,fieldName: 'applyUserId',propUserIdFieldName: 'applyUserId',propUserNicknameFieldName: 'applyUserNickname',label: '申请用户'}),


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
        dictParam: {groupCode: 'tenant_create_apply_audit_status'},
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

