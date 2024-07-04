import {list as messageTemplateListApi} from "../api/admin/messageTemplateAdminApi";
export const useCascaderMessageTemplateCompItem = ({fieldName= 'parentId',required=false,label= '父级',valueChange = ()=>{}})=>{
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
                dataMethod: () => { return messageTemplateListApi({})},
                dataMethodResultHandleConvertToTree: true,
            }
        }
    }
}