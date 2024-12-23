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
          name: 'year',
        },
        element: {
          comp: 'PtDatePicker',
          formItemProps: {
            label: '年',
          },
          compProps: {
            type: 'year',
            valueFormat: 'YYYY'
          }
        }
      },
      {
        field: {
          name: 'month',
        },
        element: {
          comp: 'PtDatePicker',
          formItemProps: {
            label: '月',

          },
          compProps: {
            type: 'month',
            format: 'MM',
            valueFormat: 'MM'
          }
        }
      },
  useSelectCrmCustomerCompItem({fieldName: 'customerId',label: '客户'}),

]
export const monthStatisticPageFormItems = [

      {
        field: {
          name: 'isIncludeDaySummary',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否统计日汇总',
            tips: '如果不统计日汇总，请确保统计月份的日汇总已存在，否则统计不到数据'
          },
          compProps: {
            activeText: '统计日汇总',
            inactiveText: '不统计日汇总',
          }
        }
      },

]
