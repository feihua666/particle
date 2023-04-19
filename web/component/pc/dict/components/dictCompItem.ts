import {list as dictListApi} from "../../dict/api/admin/dictAdminApi";

export const useCascaderDictCompItem = ({fieldName= 'parentId',required=false,label= '父级'})=>{
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
                dataMethod: () => { return dictListApi({})},
                dataMethodResultHandleConvertToTree: true,
            }
        }
    }
}