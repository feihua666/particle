import {useSelectCrmCustomerRelationDefineCompItem} from "../../crmCompItem";


export const pageFormItems = [
      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '关系定义编码',
            
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
            label: '关系定义名称',
            
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },
  useSelectCrmCustomerRelationDefineCompItem({
    fieldName: 'bidirectionalId',
    isBidirectional: true,
    label: '对应双向关系',
  }),
]
export const addPageFormItems = [

      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '关系定义编码',
            
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
            label: '关系定义名称',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },
  {
    field: {
      name: 'isBidirectional',
      value: true
    },
    element: {
      comp: 'el-switch',
      formItemProps: {
        label: '关系类型',
      },
      compProps: {
        activeText: '双向',
        inactiveText: '单向',
      }
    }
  },
  useSelectCrmCustomerRelationDefineCompItem({
    fieldName: 'bidirectionalId',
    isBidirectional: true,
    label: '双向关系定义',
    tips: '双向关系定义,仅限关系类型为单向关系时填写',
    required: ({form}) => (!form.isBidirectional)
  }),
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

