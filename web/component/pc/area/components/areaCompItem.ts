import {list as areaListApi} from "../api/admin/areaAdminApi";

export const useCascaderAreaCompItem = ({fieldName= 'parentId',required=false,label= '父级'})=>{
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
                dataMethod: () => { return areaListApi({})},
                dataMethodResultHandleConvertToTree: true,
            }
        }
    }
}