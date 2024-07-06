import {useSelectCrmCustomerCompItem} from "../../crmCompItem";


export const pageFormItems = [
  useSelectCrmCustomerCompItem({}),
      {
        field: {
          name: 'contactTypeDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '联系方式类型',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'contact_type'}
          }
        }
      },
      {
        field: {
          name: 'contact',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '联系方式',
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },

]
export const addPageFormItems = [


  useSelectCrmCustomerCompItem({required: true}),

  {
    field: {
      name: 'contactTypeDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '联系方式类型',
        required: true,
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'contact_type'}
      }
    }
  },


      {
        field: {
          name: 'contact',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '联系方式',
            required: true,
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

