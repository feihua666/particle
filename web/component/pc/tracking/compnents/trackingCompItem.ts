import {list as trackingPageListApi} from "../api/admin/trackingPageAdminApi";


export const useCascaderTrackingPageCompItem = ({fieldName= 'parentId',required=false,label= '父级'})=>{
    return         {
        field: {
            name: fieldName
        },
        element: {
            comp: 'PtCascader',
            formItemProps: {
                label: label,
                required: required
            },
            compProps: {
                clearable: true,
                // 加载数据
                dataMethod: () => { return trackingPageListApi({})},
                dataMethodResultHandleConvertToTree: true,
            }
        }
    }
}