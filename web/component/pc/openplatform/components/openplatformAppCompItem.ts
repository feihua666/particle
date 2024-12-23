import {list as appListApi, listCurrentUser} from "../api/app/admin/openplatformAppAdminApi.ts";

export const useSelectAppCompItem = ({fieldName= 'openplatformAppId',required=false,label= '应用',disabled = false})=>{
    return         {
        field: {
            name: fieldName
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: label,
                required: required
            },
            compProps: {
                clearable: true,
                disabled: disabled,
                // 加载数据
                dataMethod: appListApi,
            }
        }
    }
}
export const useSelectAppForCurrentUserCompItem = ({fieldName= 'openplatformAppId',
                                                       required=false,label= 'app',valueChange = () => {},tips = ''})=>{
    return         {
        field: {
            name: fieldName,
            valueChange
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: label,
                required: required,
                tips: tips
            },
            compProps: {
                clearable: true,
                // 加载数据
                dataMethod: listCurrentUser,
            }
        }
    }
}
