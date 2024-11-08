import {treeQueryComps} from "../../../../treeQueryComps";
import {useCascaderCrmCompanyCompItem, useCascaderCrmDeptCompItem} from "../../crmCompItem";


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
  useCascaderCrmCompanyCompItem({fieldName: 'crmCompanyId',label: '客户公司'}),

    useCascaderCrmDeptCompItem({}),
    ...treeQueryComps,
]
export const addPageFormItems = [




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


  useCascaderCrmCompanyCompItem({fieldName: 'crmCompanyId',label: '客户公司',required: true}),




  useCascaderCrmDeptCompItem({}),




      {
        field: {
          name: 'seq',
          value: 1000
        },
        element: {
          comp: 'el-input-number',
          formItemProps: {
            label: '排序',
            required: true,
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
        label: '备注',

      },
      compProps: {
        clearable: true,
      }
    }
  },
]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

