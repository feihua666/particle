import {useSelectAppCompItem} from "../../openplatformAppCompItem";
import {
    useOauth2SelectClientCompItem
} from "../../../../oauth2authorization/components/oauth2authorizationRegisteredClientCompItem";
import {useCascaderOpenapiCompItem} from "../../openplatformOpenapiCompItem";
import {useSelectCrmCustomerCompItem} from "../../../../crm/components/crmCompItem";

export const pageFormItems = [
  useSelectAppCompItem({}),
  useOauth2SelectClientCompItem({
    fieldName: 'appId',
    label: 'appId'
  }),
  useCascaderOpenapiCompItem({
    fieldName: 'openplatformOpenapiId',
    label: '开放接口',
    disableGroup: true
  }),
  {
    field: {
      name: 'dayAt',
    },
    element: {
      comp: 'PtDatePicker',
      formItemProps: {
        label: '日期'
      },
      compProps:  {
        clearable: true,
        type: "date",
      }
    }
  },
  useSelectCrmCustomerCompItem({fieldName: 'customerId',label: '客户'}),
]
