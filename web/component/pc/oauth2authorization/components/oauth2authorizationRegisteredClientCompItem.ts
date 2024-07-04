import {list as oauth2RegisteredClientListApi} from "../api/client/admin/oauth2RegisteredClientAdminApi";

/**
 * oauth2授权客户端表单配置项
 * @param props
 */
export const useOauth2SelectClientCompItem = ({props = {},
                                                required = false,
                                                fieldName='clientId',
                                                label='oauth2客户端',
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
                dataMethod: oauth2RegisteredClientListApi,
                props: {
                    value: 'clientId',
                    label: 'clientName'
                }
            }
        }
    }
}
