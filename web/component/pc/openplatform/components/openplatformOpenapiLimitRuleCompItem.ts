import {list as openapiLimitRuleListApi} from "../api/openapi/admin/openplatformOpenapiLimitRuleAdminApi";

/**
 * 开放接口限制规则表单配置项
 * @param props
 */
export const useSelectOpenapiLimitRuleCompItem = ({props = {},
                                                  required = false,
                                                  fieldName='openplatformOpenapiLimitRuleId',
                                                  label='限制规则',
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
                dataMethod: openapiLimitRuleListApi,
            }
        }
    }
}