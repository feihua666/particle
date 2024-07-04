import {list as dataQueryProviderListApi} from "../api/provider/admin/dataQueryProviderAdminApi";
export const useSelectDataqueryProviderCompItem = (
    {
        fieldName= 'dataQueryProviderId',
        required=false,
        label= '数据查询供应商',
        tips = ''
    })=>{
    return         {
        field: {
            name: fieldName
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
                dataMethod: dataQueryProviderListApi,
            }
        }
    }
}