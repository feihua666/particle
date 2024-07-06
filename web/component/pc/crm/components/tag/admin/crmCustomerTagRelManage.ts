import {useSelectCrmCustomerCompItem, useSelectCrmCustomerTagCompItem} from "../../crmCompItem";


export const pageFormItems = [
  useSelectCrmCustomerCompItem({}),
  useSelectCrmCustomerTagCompItem({}),
]
export const addPageFormItems = [

  useSelectCrmCustomerCompItem({required: true}),
  useSelectCrmCustomerTagCompItem({required: true}),

]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

