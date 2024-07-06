import {useSelectCrmCustomerCompItem, useSelectCrmCustomerRelationDefineCompItem} from "../../crmCompItem";


export const pageFormItems = [
  useSelectCrmCustomerCompItem({}),
  useSelectCrmCustomerCompItem({fieldName: 'anotherCrmCustomerId',label: '另一个客户'}),
  useSelectCrmCustomerRelationDefineCompItem({fieldName: 'crmCustomerRelationDefineId',label: '客户关系定义'}),

]
export const addPageFormItems = [

  useSelectCrmCustomerCompItem({required: true}),
  useSelectCrmCustomerCompItem({fieldName: 'anotherCrmCustomerId',label: '另一个客户',required: true}),
  useSelectCrmCustomerRelationDefineCompItem({fieldName: 'crmCustomerRelationDefineId',label: '客户关系定义',required: true}),

  {
        field: {
          name: 'relationDetail',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '关系详情描述',
          },
          compProps: {
            clearable: true,
          }
        }
      },












]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

