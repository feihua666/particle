import {useRemoteSelectUserCompItem} from "../../../user/compnents/userCompItem";
import {treeQueryComps} from "../../../treeQueryComps";
import {useCascaderDeptCompItem} from "../deptCompItem";

export const pageFormItems = [
      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '部门编码',
            
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
            label: '部门名称',
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
            label: '类型',
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'dept_type'}
          }
        }
      },
  useCascaderDeptCompItem({}),
  ...treeQueryComps
]
export const useAddPageFormItems = ({props})=>{
  return [

    {
      field: {
        name: 'code',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '部门编码',
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
          label: '部门名称',
          required: true,
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
          label: '类型',
          required: true
        },
        compProps: {
          // 字典查询
          dictParam: {groupCode: 'dept_type'}
        }
      }
    },

    useRemoteSelectUserCompItem({
      props,
      required: false,
      fieldName: 'masterUserId',
      propUserIdFieldName: 'masterUserId',
      propUserNicknameFieldName: 'masterUserName',
      disabled: false,
      label: '负责人'
    }),

    {
      field: {
        name: 'isVirtual',
        value: false
      },
      element: {
        comp: 'el-switch',
        formItemProps: {
          label: '是否虚拟部门',
          required: true,
        },
        compProps: {
        }
      }
    },


    {
      field: {
        name: 'isComp',
        value: false
      },
      element: {
        comp: 'el-switch',
        formItemProps: {
          label: '是否为公司',
          required: true,
        },
        compProps: {
        }
      }
    },
    useCascaderDeptCompItem({}),
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

