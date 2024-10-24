import {list as navigationCategoryListApi} from "../api/admin/navigationCategoryAdminApi";

export const useCascaderNavigationCategoryCompItem = ({fieldName= 'parentId',
                                                          required=false,label= '父级',
                                                          valueChange = ()=>{}})=>{
    return  {
        field: {
            name: fieldName,
            valueChange: valueChange
        },
        element: {
            comp: 'PtCascader',
            formItemProps: {
                label: label,
                required: required
            },
            compProps: {
                // 加载数据
                dataMethod: () => { return navigationCategoryListApi({})},
                dataMethodResultHandleConvertToTree: true,
            }
        }
    }
}
