import {list as reportSegmentTemplateListApi} from "../api/template/admin/reportSegmentTemplateAdminApi.ts";
export const useCascaderReportSegmentTemplateCompItem = ({
                                               fieldName= 'parentId',
                                               required=false,label= '父级',
                                               tips = '',
                                               valueChange = () => {}
})=>{
    return         {
        field: {
            name: fieldName,
            valueChange
        },
        element: {
            comp: 'PtCascader',
            formItemProps: {
                label: label,
                required: required,
                tips: tips
            },
            compProps: {
                clearable: true,
                // 加载数据
                dataMethod: reportSegmentTemplateListApi,
                dataMethodResultHandleConvertToTree: true,
            }
        }
    }
}