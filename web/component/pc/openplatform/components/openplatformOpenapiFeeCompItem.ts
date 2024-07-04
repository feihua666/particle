import {list as openapiFeeListApi} from "../api/openapi/admin/openplatformOpenapiFeeAdminApi";

/**
 * 开放接口费用表单配置项
 * @param props
 */
export const useSelectOpenapiFeeCompItem = ({props = {},
                                                  required = false,
                                                  fieldName='openplatformOpenapiFeeId',
                                                  label='计费规则',
                                                  valueChange = ()=>{},tips = ''})=>{

    return   {
        field: {
            name: fieldName,
            valueChange: valueChange
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: label,
                required: required,
                tips: tips
            },
            compProps:{
                dataMethod: openapiFeeListApi,
            }
        }
    }
}