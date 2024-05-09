import {useCascaderCrmCompanyCompItem, useCascaderCrmDeptCompItem} from "../../crmCompItem";
import {useRemoteSelectUserCompItem} from "../../../../user/compnents/userCompItem";
import {useCascaderDeptCompItem} from "../../../../dept/compnents/deptCompItem";


export const pageFormItems = [
      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '客户编码',
            
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
            label: '客户名称',
            
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },
      {
        field: {
          name: 'appellation',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '客户称呼',
            
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },
      {
        field: {
          name: 'typeDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '客户类型',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'crm_customer_type'}
          }
        }
      },
      {
        field: {
          name: 'genderDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '客户性别',
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'gender'}
          }
        }
      },
      {
        field: {
          name: 'birthDay',
        },
        element: {
          comp: 'el-date-picker',
          formItemProps: {
            label: '客户生日',
            
          },
          compProps: {
          }
        }
      },
  useCascaderCrmCompanyCompItem({fieldName: 'crmCompanyId',label: '客户公司'}),
  useCascaderCrmDeptCompItem({fieldName: 'crmDeptId',label: '客户公司部门'}),

      {
        field: {
          name: 'categoryDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '客户分类',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'crm_customer_category'}
          }
        }
      },
  useRemoteSelectUserCompItem({
    props: {},
    fieldName: 'belongUserId',
    label: '归属用户',
    propUserIdFieldName: 'belongUserId',
    propUserNicknameFieldName: 'belongUserNickname'
  }),
  useCascaderDeptCompItem({fieldName: 'belongCompId',label: '归属公司',isComp: true}),
  useCascaderDeptCompItem({fieldName: 'belongDeptId',label: '归属部门',isComp: false}),


]
export const useAddPageFormItems = ({props}) =>{
  return [

    {
      field: {
        name: 'code',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '客户编码',

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
          label: '客户名称',
          required: true,
        },
        compProps: {
          clearable: true,
        }
      }
    },


    {
      field: {
        name: 'appellation',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '客户称呼',

        },
        compProps: {
          clearable: true,
        }
      }
    },


    {
      field: {
        name: 'typeDictId',
      },
      element: {
        comp: 'PtDictFrontSelect',
        formItemProps: {
          label: '客户类型',
          required: true,
        },
        compProps: {
          // 字典查询
          dictParam: {groupCode: 'crm_customer_type'}
        }
      }
    },


    {
      field: {
        name: 'genderDictId',
      },
      element: {
        comp: 'PtDictFrontSelect',
        formItemProps: {
          label: '客户性别',
          tips: '仅限类型为个人时填写'
        },
        compProps: {
          // 字典查询
          dictParam: {groupCode: 'gender'}
        }
      }
    },


    {
      field: {
        name: 'age',
      },
      element: {
        comp: 'el-input-number',
        formItemProps: {
          label: '客户年龄',
        },
        compProps: {
        }
      }
    },


    {
      field: {
        name: 'birthDay',
      },
      element: {
        comp: 'el-date-picker',
        formItemProps: {
          label: '客户生日',

        },
        compProps: {
        }
      }
    },


    useCascaderCrmCompanyCompItem({fieldName: 'crmCompanyId',label: '客户公司'}),

    useCascaderCrmDeptCompItem({fieldName: 'crmDeptId',label: '客户公司部门'}),

    {
      field: {
        name: 'isBlack',
      },
      element: {
        comp: 'el-switch',
        formItemProps: {
          label: '是否为黑名单',
          required: true,
        },
        compProps: {
          activeText: '是',
          inactiveText: '否',
        }
      }
    },


    {
      field: {
        name: 'blackReason',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '黑名单原因',
          required: ({form}) => form.isBlack == true
        },
        compProps: {
          clearable: true,
        }
      }
    },

    {
      field: {
        name: 'categoryDictId',
      },
      element: {
        comp: 'PtDictFrontSelect',
        formItemProps: {
          label: '客户分类',

        },
        compProps: {
          // 字典查询
          dictParam: {groupCode: 'crm_customer_category'}
        }
      }
    },

    useRemoteSelectUserCompItem({
      props,
      fieldName: 'belongUserId',
      label: '归属用户',
      propUserIdFieldName: 'belongUserId',
      propUserNicknameFieldName: 'belongUserNickname',
      disabled: false
    }),
    useCascaderDeptCompItem({fieldName: 'belongCompId',label: '归属公司',isComp: true}),
    useCascaderDeptCompItem({fieldName: 'belongDeptId',label: '归属部门',isComp: false}),

    {
      field: {
        name: 'remark',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '备注',

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

