import {list as dictListApi} from "../../dict/api/admin/dictAdminApi";

export const useCascaderDictCompItem = ({fieldName= 'parentId',required=false,label= '父级',tips = ''})=>{
    return         {
        field: {
            name: fieldName
        },
        element: {
            comp: 'PtCascader',
            formItemProps: {
                label: label,
                required: required,
                tips: tips || undefined
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
