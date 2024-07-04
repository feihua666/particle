import {useCascaderUsageCountDefineCompItem, useSelectUsageCountConfigCompItem} from "../usageCountCompItem";
import {useRemoteSelectUserCompItem} from "../../../user/components/userCompItem";
import {useSelectTenantCompItem} from "../../../tenant/components/tenantCompItem";

export const pageFormItems = [
    {
        field: {
            name: 'usageCountKey',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '使用次数key',

            },
            compProps: {
                clearable: true,
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


