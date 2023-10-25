import {useCascaderUsageCountDefineCompItem, useSelectUsageCountConfigCompItem} from "../usageCountCompItem";
import {useRemoteSelectUserCompItem} from "../../../user/compnents/userCompItem";
import {useSelectTenantCompItem} from "../../../tenant/compnents/tenantCompItem";

export const pageFormItems = [
      {
        field: {
          name: 'usageCountRecordId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '使用次数记录主键',
            
          },
          compProps: {
          }
        }
      },
  useCascaderUsageCountDefineCompItem({
    fieldName: 'usageCountDefineId',
    label: '使用次数定义',
    disableGroup: true
  }),
  useSelectUsageCountConfigCompItem({}),

  useRemoteSelectUserCompItem({
    props: {},
    fieldName: 'usageUserId',
    label: '使用用户'
  }),

  useSelectTenantCompItem({
    props: {},
    fieldName: 'usageTenantId',
    label: '使用租户',
  }),
]
