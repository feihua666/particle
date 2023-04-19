import {list as tenantListApi} from "../api/admin/tenantAdminApi";
export const selectTenantProps = {
    // 加载数据初始化参数,路由传参
    tenantId: {
        type: String
    },
}
export const useSelectTenantCompItem = ({props = {},fieldName = 'tenantId',required = false, label = '租户'})=>{
    return     {
        field: {
            name: fieldName,
            // selectTenantProps 中的tenantId保持一致
            value: props.tenantId
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: label,
                required: required
            },
            compProps: ()=>{
                let paramsExist = !!(props.tenantId)
                return {
                    disabled: paramsExist,
                    dataMethod: tenantListApi
                }
            }
        }
    }
}