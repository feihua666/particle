import {list as adminComponentListApi} from "../api/admin/adminComponentAdminApi";

export const useSelectAdminComponentCompItem = ({fieldName= 'id',required=false,label= '组件'})=>{
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
                dataMethod: () => { return adminComponentListApi({})},
            }
        }
    }
}
