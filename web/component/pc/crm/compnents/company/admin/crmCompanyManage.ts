import {treeQueryComps} from "../../../../treeQueryComps";
import {useCascaderCrmCompanyCompItem} from "../../crmCompItem";


export const pageFormItems = [
      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '公司编码',
            
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
            label: '公司名称',
            
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },
      {
        field: {
          name: 'nameSimple',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '公司简称',
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },
    useCascaderCrmCompanyCompItem({}),
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
            label: '公司编码',
          },
          compProps: {
            clearable: true,
            placeholder: '编码唯一',
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
            label: '公司名称',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'nameSimple',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '公司简称',
          },
          compProps: {
            clearable: true,
          }
        }
      },

  useCascaderCrmCompanyCompItem({}),

      {
        field: {
          name: 'seq',
          value: 10
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

