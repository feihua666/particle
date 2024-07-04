import {list as appListApi} from "../api/app/admin/openplatformAppAdminApi.ts";
export const useSelectAppCompItem = ({fieldName= 'openplatformAppId',required=false,label= 'app'})=>{
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
                // 加载数据
                dataMethod: appListApi,
            }
        }
    }
}